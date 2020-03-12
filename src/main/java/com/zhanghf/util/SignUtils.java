package com.zhanghf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.util.Base64;

import static com.zhanghf.dto.CommonDTO.*;

/**
 * 签名工具类
 *
 * @author zhanghf
 * @version 1.0
 * @date 9:08 2020/3/12
 */
@Slf4j
public class SignUtils {


    /**
     * 构造REST加签请求
     *
     * @param instCode
     * @param payload   数据请求载体
     * @param timestamp
     * @param nonce
     * @return REST加签请求
     * @throws IOException
     * @throws PKCSException
     * @throws OperatorCreationException
     */
    public static JSONObject getSignRequest(String uuid, String instCode, JSONObject payload, long timestamp,
                                            String nonce) throws IOException, PKCSException,
            OperatorCreationException {
        // 签名
        String payloadStr = Base64.getEncoder().encodeToString(JSON.toJSONBytes(payload));
        byte[] signPayloadBody = (instCode + payloadStr + timestamp + nonce).getBytes();
        byte[] sig = sign(uuid, signPayloadBody);
        String signature = ByteUtils.toHexString(sig);

        // 签名请求
        JSONObject signRequest = new JSONObject();
        signRequest.put("instCode", instCode);
        signRequest.put("payload", payloadStr);
        signRequest.put("timestamp", timestamp);
        signRequest.put("nonce", nonce);
        signRequest.put("signature", signature);

        return signRequest;
    }

    /**
     * 用bouncyCastle进行数据签名
     *
     * @param dataContent 待签名数据载体(instCode+payload+timestamp+nonce).bytes
     * @return 签名结果
     * @throws IOException
     * @throws PKCSException
     * @throws OperatorCreationException
     */
    public static byte[] sign(String uuid, byte[] dataContent) throws IOException, PKCSException,
            OperatorCreationException {

        // 签名工具init
        Security.addProvider(new BouncyCastleProvider());
        PKCS8EncryptedPrivateKeyInfo pair = new PKCS8EncryptedPrivateKeyInfo(
                EncryptedPrivateKeyInfo.getInstance(Base64.getDecoder().decode(PRIVATE_KEY)));

        JceOpenSSLPKCS8DecryptorProviderBuilder jce = new JceOpenSSLPKCS8DecryptorProviderBuilder();
        jce.setProvider(SIGNATURE_PROVIDER);
        InputDecryptorProvider decProv = jce.build(PRIVATE_PASS.toCharArray());
        PrivateKeyInfo info = pair.decryptPrivateKeyInfo(decProv);
        JcaPEMKeyConverter pemKeyConverter = new JcaPEMKeyConverter();
        PrivateKey privateKey = pemKeyConverter.getPrivateKey(info);
        BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) privateKey;
        BigInteger d = bcecPrivateKey.getD();
        byte[] privateKeyId = getPaddedByte(d, 32);
        SHA256Digest sha256Digest = new SHA256Digest();
        HMacDSAKCalculator hMacDSAKCalculator = new HMacDSAKCalculator(sha256Digest);
        ECDSASigner signer = new ECDSASigner(hMacDSAKCalculator);
        ECPrivateKeyParameters privKey = new ECPrivateKeyParameters(new BigInteger(1, privateKeyId),
                CURVE);
        signer.init(true, privKey);

        // 获取摘要
        byte[] digest = getDigestBytes(dataContent);
        BigInteger[] components = signer.generateSignature(digest);
        components[1] = toCanonicalised(components[1]);

        int recId = -1;

        // 1 header + 32 bytes for R + 32 bytes for S
        byte v = (byte) recId;
        byte[] r = getPaddedByte(components[0], 32);
        byte[] s = getPaddedByte(components[1], 32);

        byte[] sig = new byte[65];
        System.arraycopy(r, 0, sig, 0, 32);
        System.arraycopy(s, 0, sig, 32, 32);
        sig[64] = v;
        return sig;
    }

    /**
     * 获取SHA-256摘要
     *
     * @param dataContent
     * @return SHA-256摘要
     */
    public static byte[] getDigestBytes(byte[] dataContent) {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(dataContent);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }

    /**
     * byte数组扩充至指定长度
     *
     * @param value
     * @param length
     * @return 扩充后的byte数组
     */
    private static byte[] getPaddedByte(BigInteger value, int length) {

        byte[] result = new byte[length];
        byte[] bytes = value.toByteArray();
        int bytesLength;
        int srcOffset;
        if (bytes[0] == 0) {
            bytesLength = bytes.length - 1;
            srcOffset = 1;
        } else {
            bytesLength = bytes.length;
            srcOffset = 0;
        }

        if (bytesLength > length) {
            throw new RuntimeException("Input is too large to put in byte array of size " + length);
        }

        int destOffset = length - bytesLength;
        System.arraycopy(bytes, srcOffset, result, destOffset, bytesLength);
        return result;
    }

    /**
     * 标准化
     *
     * @param s
     * @return 标准化的数值
     */
    private static BigInteger toCanonicalised(BigInteger s) {

        if (!isCanonical(s)) {
            s = CURVE.getN().subtract(s);
        }

        return s;
    }

    /**
     * 是否标准化数值
     *
     * @param s
     * @return 检查结果:true|false
     */
    private static boolean isCanonical(BigInteger s) {
        return s.compareTo(HALF_CURVE_ORDER) <= 0;
    }
}
