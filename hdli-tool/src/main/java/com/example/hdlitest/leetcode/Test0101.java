package com.example.hdlitest.leetcode;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author luyi
 * @date 2025/1/2 17:08
 */
public class Test0101 {


    public static void main(String[] args) {

        InformationCollectionConfigInfo a = new InformationCollectionConfigInfo();
        a.setId(1L);
        a.setMemoryIds(Arrays.asList(1L,2L));
        List<InformationCollectionConfigInfo> list = new ArrayList<>();
        list.add(a);
        String jsonString = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);



    }



    public static String getConfig(String req){
        JSONObject base64JsonObject = JSONObject.parseObject(req);
        JSONArray configArray = base64JsonObject.getJSONArray("cells");
        JSONArray config = new JSONArray();
        for (int i = 0; i < configArray.size(); i++) {
            String part = configArray.getString(i);
            String unzip = unzip(part);
            config.addAll(JSONArray.parseArray(unzip));
        }
        base64JsonObject.put("cells", config);
        return base64JsonObject.toJSONString();
    }

    public static String getStr(String cellString){
        List<String> base64Cells = new ArrayList<>();
        String gzip = gzip(cellString);

        base64Cells.add(gzip);

        JSONObject runConfigInfoJson = new JSONObject();
        runConfigInfoJson.put("processId", "200000286");
        runConfigInfoJson.put("errorList", Lists.newArrayList());
        runConfigInfoJson.put("cells", base64Cells);

        return JSON.toJSONString(runConfigInfoJson);

    }

    public static String unzip(String req){
        byte[] decode = Base64.decode(req);
        byte[] bytes = ZipUtil.unGzip(decode);
        String partStrConfig = new String(bytes, StandardCharsets.UTF_8);
        return partStrConfig;
    }


    public static String gzip(String cellString){
        byte[] gzipBytes = ZipUtil.gzip(cellString, StandardCharsets.UTF_8.name());
        String partStrConfig = new String(gzipBytes, StandardCharsets.UTF_8);
        return Base64.encode(partStrConfig);
    }



    @Data
    public static class InformationCollectionConfigInfo{
        private long id;
        private List<Long> memoryIds;
    }


    @Data
    public static class TenantAgentUserMemory{
        private Long id;
    }
}
