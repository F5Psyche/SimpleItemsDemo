package com.zhanghf.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhanghf
 * @version 1.0
 * @date 14:55 2020/3/2
 */
public class TreeFormUtils {

    public static JSONArray listToTreeUtils(JSONArray arr, String id, String pid, String child) {
        JSONArray array = new JSONArray();
        JSONObject hash = new JSONObject();
        for (int i = 0; i < arr.size(); i++) {
            JSONObject json = arr.getJSONObject(i);
            hash.put(json.getString(id), json);
        }
        for (int i = 0; i < arr.size(); i++) {
            JSONObject aVal = arr.getJSONObject(i);
            if (aVal.get(pid) != null) {
                JSONObject hashVP = hash.getJSONObject(aVal.get(pid).toString());
                if (hashVP != null) {
                    //检查是否有child属性
                    if (hashVP.get(child) != null) {
                        JSONArray ch = hashVP.getJSONArray(child);
                        ch.add(aVal);
                        hashVP.put(child, ch);
                    } else {
                        JSONArray ch = new JSONArray();
                        ch.add(aVal);
                        hashVP.put(child, ch);
                    }
                } else {
                    array.add(aVal);
                }
            } else {
                array.add(aVal);
            }
        }
        return array;
    }
}
