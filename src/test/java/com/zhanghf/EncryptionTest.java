package com.zhanghf;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.zhanghf.util.EncryptionUtils;
import com.zhanghf.util.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCSException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.zhanghf.constant.CommonDMO.*;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:17 2020/3/12
 */
@Slf4j
public class EncryptionTest {

    @Test
    public void sha256() {
        String uuid = UUID.randomUUID().toString();
        String encryptionText = "hello word";
        String text = EncryptionUtils.encryption(uuid, encryptionText, SHA_256_TYPE);
        log.info("uuid={}, encryptionText={}, text={}", uuid, encryptionText, text);
    }

    @Test
    public void sha512() {
        String uuid = UUID.randomUUID().toString();
        String encryptionText = "hello word";
        String text = EncryptionUtils.encryption(uuid, encryptionText, SHA_512_TYPE);
        log.info("uuid={}, encryptionText={}, text={}", uuid, encryptionText, text);

    }

    @Test
    public void MD5() {
        String uuid = UUID.randomUUID().toString();
        String encryptionText = "hello word";
        String text = EncryptionUtils.encryption(uuid, encryptionText, MD5_TYPE);
        log.info("uuid={}, encryptionText={}, text={}", uuid, encryptionText, text);
    }

    @Test
    public void sign() {
        String uuid = UUID.randomUUID().toString();
        JSONObject payload = new JSONObject();
        List<String> eInvoiceIds = new ArrayList<>();
        eInvoiceIds.add("3502847785-68269698");
        eInvoiceIds.add("3179716449-68771413");
        eInvoiceIds.add("4046371349-60181335");
        eInvoiceIds.add("1597122670-31107634");
        eInvoiceIds.add("8379739945-24895652");
        List list = Lists.newArrayList("3502847785-68269698", "3179716449-68771413", "4046371349-60181335", "1597122670-31107634", "8379739945-24895652");
        log.info("uuid={}, eInvoiceIds={}, list={}", uuid, eInvoiceIds, list);
        payload.put("eInvoiceIds", eInvoiceIds); // eInvoiceIds为实际需要查询的eInvoiceId列表
        payload.put("payerId", "725705683346688243"); // payerId为实际的payerId
        payload.put("corpId", "11330000MB18470516"); // corpId为实际发起的保险机构代码
        long sequenceId = RandomUtils.nextLong();
        payload.put("sequenceId", sequenceId); // sequenceId为实际的保险机构发起的业务流水号
        long timestamp = System.currentTimeMillis() / 1000;
        String nonce = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        log.info("uuid={}, sequenceId={}, timestamp={}, nonce={}", uuid, sequenceId, timestamp, nonce);
        try {
            JSONObject condition = SignUtils.getSignRequest(uuid, "11330000MB18470516", payload, timestamp, nonce);
            System.out.println(condition);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PKCSException e) {
            e.printStackTrace();
        } catch (OperatorCreationException e) {
            e.printStackTrace();
        }
    }
}
