package com.zhanghf.dto;

import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;

import java.math.BigInteger;

public class CommonDTO {



    public static final String CHARSET_NAME = "UTF-8";
    public static final String HEADER_NAME = "Content-Type";
    public static final String HEADER_VALUE = "application/json;charset=utf-8";

    public static final String SHA_256_TYPE = "SHA-256";
    public static final String SHA_512_TYPE = "SHA-512";
    public static final String MD5_TYPE = "MD5";

    /**
     * 医保机构私钥key
     */
    public static final String PRIVATE_KEY = "MHwwKAYKKoZIhvcNAQwBBTAaBBRpHhmtmc4bmVTRFsumXHEIYuU+9gICCAAEUFX6KGT6tcatlxM27Ho37RCQdbjaQ7AXswUMp5q4zx9Rry16YrTbQDxSg3XpN4i/n651b8BvxiOoKdYqQE/arJF5Zg1XuJzHkz8sqlYV/q+e";

    /**
     * 医保机构私钥密码
     */
    public static final String PRIVATE_PASS = "f0cc90028cb0446fb2005315b9c0b99f";

    /**
     * 签名工具方
     */
    public static final String SIGNATURE_PROVIDER = "BC";

    /**
     * ECC k1 pab
     */
    private static final X9ECParameters CURVE_PARAMS = CustomNamedCurves.getByName("secp256k1");

    /**
     * ECC k1 pab
     */
    public static final ECDomainParameters CURVE = new ECDomainParameters(
            CURVE_PARAMS.getCurve(), CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());

    /**
     * ECC k1 n
     */
    public static final BigInteger HALF_CURVE_ORDER = CURVE_PARAMS.getN().shiftRight(1);
}
