package com.zhanghf.util;

import com.zhanghf.dto.CommonDTO;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class CommonUtils {

    /**
     * 将异常信息转换为字符串
     *
     * @param e 异常信息
     * @return 字符串
     */
    public static String exceptionToString(Exception e) {
        PrintWriter pw = null;
        try {
            StringWriter sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception ex) {
            return "ExceptionToString is error";
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * @param uuid        唯一识别码
     * @param inputStream InputStream
     * @return ResultVo
     */
    public static ResultVo<String> inputStreamToString(String uuid, InputStream inputStream) {
        ResultVo<String> resultVo = new ResultVo<>();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            inputStreamReader = new InputStreamReader(inputStream, CommonDTO.CHARSET_NAME);
            bufferedReader = new BufferedReader(inputStreamReader);
            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                buffer.append(lines);
            }
            resultVo.setResult(buffer.toString());
            resultVo.setSuccess(true);
        } catch (Exception e) {
            resultVo.setResult("");
            resultVo.setCode("8099");
            resultVo.setResultDes(e.toString());
            log.error("<inputStreamToString.Exception>uuid={}, errMsg={}", uuid, exceptionToString(e));
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                resultVo.setResult("");
                resultVo.setCode("8099");
                resultVo.setResultDes(e.toString());
                log.error("<inputStreamToString.IOException>uuid={}, errMsg={}", uuid, exceptionToString(e));
            }
        }
        return resultVo;
    }
}
