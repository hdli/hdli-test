/**
 * ele.me Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.hdlitest.tool;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author kupao
 * @version : HtmlUtils.java, v 0.1 2024年03月15日 16:14 cyril Exp $
 */
@Slf4j
public class HtmlUtils {

    public static final String JSON_ARRAY_ATTRIBUTE_DATA_TYPE = "jsonArray";
    public static final String JSON_OBJECT_ATTRIBUTE_DATA_TYPE = "jsonObject";
    public static final String SINGLE_ATTRIBUTE_DATA_TYPE = "single";

    /**
     * @param html          <html><body><input type='text' name='username'><input type='password' name='password'></body></html>
     * @param tag           input 表情名
     * @param attributeName 标签里的属性
     * @return
     */

    public static List<String> extractFromHtml(String html, String tag, String attributeName) {
        try {
            List<String> resultList = new ArrayList<>();
            // 解析HTML字符串
            Document doc = Jsoup.parse(html);

            // 选择所有tag对应的表情
            Elements inputs = doc.select(tag);

            // 遍历input标签
            for (Element input : inputs) {
                // 获取name属性的值
                String nameValue = input.attr(attributeName);
                if (StringUtils.isEmpty(nameValue)) {
                    continue;
                }

                log.info("HtmlUtils extractFromHtml html:{}, tag:{}, attributeName:{}, result:{}", html, tag, attributeName, nameValue);
                resultList.add(nameValue);
            }
            return resultList;
        } catch (Exception e) {
            log.error("HtmlUtils extractFromHtml exception", e);
            return Collections.emptyList();
        }
    }

    /**
     * @param html <html><body><input type='text' name='username'><input type='password' name='password'></body></html>
     * @param tag  input 表情名
     * @return
     */
    public static boolean includesTagAttribute(String html, String tag, List<String> attributeNames) {
        try {
            // 解析HTML字符串
            Document doc = Jsoup.parse(html);
            // 选择所有tag对应的表情
            Elements inputs = doc.select(tag);
            // 遍历input标签
            for (Element input : inputs) {
                // 获取name属性的值
                for (String attributeName : attributeNames) {
                    String nameValue = input.attr(attributeName);
                    if (StringUtils.isNotBlank(nameValue)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            log.error("HtmlUtils includesTagAttribute exception", e);
            return false;
        }

    }

    public static String text(String html) {
        try {
            return Jsoup.parse(html).text();
        } catch (Exception e) {
            log.error("HtmlUtils includesTagAttribute exception", e);
            return html;
        }
    }

    public static String replaceAttribute(String html, String tag, String attributeName, String dataType, String replaceKey, Object originValue,
        Object targetValue) {
        try {
            HtmlAttributeReplace replace = new HtmlAttributeReplace();
            replace.setAttributeName(attributeName);
            replace.setDataType(dataType);
            replace.setReplaceKey(replaceKey);
            replace.setReplaceValueList(Collections.singletonList(Pair.of(originValue, targetValue)));
            return batchReplaceAttribute(html, tag, Collections.singletonList(replace));
        } catch (Throwable e) {
            log.error("HtmlUtils replaceAttribute exception", e);
            return html;
        }
    }

    public static String batchReplaceAttribute(String html, String tag, List<HtmlAttributeReplace> replaceList) {
        try {
            // 解析HTML字符串
            Document doc = Jsoup.parse(html, "", Parser.xmlParser());
            // 选择所有tag对应的表情
            Elements elements = doc.select(tag);
            if (CollectionUtils.isEmpty(elements)) {
                return html;
            }
            for (HtmlAttributeReplace htmlAttributeReplace : replaceList) {
                for (Element element : elements) {
                    if (htmlAttributeReplace.getPredictElement() != null && !htmlAttributeReplace.getPredictElement().test(element)) {
                        continue;
                    }
                    String attrValue = element.attr(htmlAttributeReplace.getAttributeName());
                    if (StringUtils.isBlank(attrValue)) {
                        continue;
                    }
                    if (JSON_ARRAY_ATTRIBUTE_DATA_TYPE.equalsIgnoreCase(htmlAttributeReplace.getDataType()) && JSONUtil.isTypeJSONArray(attrValue)) {
                        JSONArray array = JSON.parseArray(attrValue);
                        for (int i = 0; i < array.size(); i++) {
                            JSONObject item = array.getJSONObject(i);
                            replaceItem(htmlAttributeReplace, item);
                        }
                        attrValue = array.toString();
                    }
                    if (JSON_OBJECT_ATTRIBUTE_DATA_TYPE.equalsIgnoreCase(htmlAttributeReplace.getDataType()) && JSONUtil.isTypeJSONObject(attrValue)) {
                        JSONObject item = JSON.parseObject(attrValue);
                        replaceItem(htmlAttributeReplace, item);
                        attrValue = item.toString();
                    }
                    if (SINGLE_ATTRIBUTE_DATA_TYPE.equalsIgnoreCase(htmlAttributeReplace.getDataType())) {
                        attrValue = replaceSingle(htmlAttributeReplace, attrValue);
                    }
                    element.attr(htmlAttributeReplace.getAttributeName(), attrValue);
                }
            }
            return doc.toString();
        } catch (Throwable e) {
            log.error("HtmlUtils batchReplaceAttribute exception", e);
            return html;
        }
    }

    private static String replaceSingle(HtmlAttributeReplace replace, String attrValue) {
        if (StringUtils.isBlank(attrValue)) {
            return attrValue;
        }
        if (CollectionUtils.isEmpty(replace.getReplaceValueList())) {
            return attrValue;
        }
        for (Pair<Object, Object> pair : replace.getReplaceValueList()) {
            String originStringValue = String.valueOf(pair.getLeft());
            String targetStringValue = String.valueOf(pair.getRight());
            if (Objects.equals(attrValue, originStringValue)) {
                return targetStringValue;
            }
        }
        return attrValue;
    }

    private static void replaceItem(HtmlAttributeReplace replace, JSONObject item) {
        if (MapUtils.isEmpty(item)) {
            return;
        }
        if (replace.getPredict() != null && !replace.getPredict().test(item)) {
            return;
        }
        Object itemValue = item.get(replace.getReplaceKey());
        if (Objects.isNull(itemValue)) {
            return;
        }
        if (CollectionUtils.isEmpty(replace.getReplaceValueList())) {
            return;
        }
        for (Pair<Object, Object> pair : replace.getReplaceValueList()) {

            if (Objects.equals(itemValue, pair.getLeft())) {
                item.put(replace.getReplaceKey(), pair.getRight());
            } else if (itemValue instanceof String) {
                String originStringValue = String.valueOf(pair.getLeft());
                String targetStringValue = String.valueOf(pair.getRight());
                if (Objects.equals(itemValue, originStringValue)) {
                    item.put(replace.getReplaceKey(), targetStringValue);
                }
            }else if (itemValue instanceof Integer){
                Integer left = Integer.valueOf(String.valueOf(pair.getLeft()));
                Integer right = Integer.valueOf(String.valueOf(pair.getRight()));
                if (Objects.equals(itemValue, left)){
                    item.put(replace.getReplaceKey(), right);
                }
            }
        }

    }
}