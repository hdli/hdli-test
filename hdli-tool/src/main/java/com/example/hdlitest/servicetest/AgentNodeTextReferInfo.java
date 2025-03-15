/**
 * ele.me Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.hdlitest.servicetest;


import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author kupao
 * @version : AgentNodeTextReferInfo.java, v 0.1 2024年08月26日 11:01 cyril Exp $
 */
@Data
public class AgentNodeTextReferInfo {

    /**
     * html
     */
    private String textHtml;

    private String simpleHtml;

    /**
     * 文本
     */
    private String text;

    /**
     * 引用信息
     */
    private List<ReferInfo> refInfoList;

    private String answerHtml;

    @Data
    public static class ReferSegmentInfo {
        /**
         * direct
         * future
         */
        private String type;
        private String nodeId;
        private String content;

        private ReferInfo referInfo;

    }

    // 我是 #query# 你是 #llm#
    public List<ReferSegmentInfo> buildReferResultInfoList() {
        String textTemp = text;

        List<ReferSegmentInfo> referSegmentInfos = new ArrayList<>();
        if (CollectionUtils.isEmpty(getRefInfoList())) {
            ReferSegmentInfo referResultInfo = new ReferSegmentInfo();
            referResultInfo.setType(AgentNodeReferResultTypeEnum.CUSTOM.getType());
            referResultInfo.setContent(textTemp);
            referSegmentInfos.add(referResultInfo);

            return referSegmentInfos;
        }

        String finalReplaceKey = "";
        for (ReferInfo referInfo : getRefInfoList()) {
            String replaceKey = referInfo.buildReplaceKey();
            String splitRegex = AgentProcessConstants.REFER_SPLIT_REGEX.replace("param", replaceKey);

            String[] split = textTemp.split(splitRegex);
            int length = split.length;

            if (length == 1) {
                ReferSegmentInfo referResultInfo = new ReferSegmentInfo();
                referResultInfo.setType(referInfo.buildReferResultInfoType());
                referResultInfo.setNodeId(referInfo.getNodeId());
                referResultInfo.setReferInfo(referInfo);

                referSegmentInfos.add(referResultInfo);
            }
            if (length == 2) {
                if (split[0].equals(replaceKey)) {
                    ReferSegmentInfo referResultInfo = new ReferSegmentInfo();
                    referResultInfo.setType(referInfo.buildReferResultInfoType());
                    referResultInfo.setNodeId(referInfo.getNodeId());
                    referResultInfo.setReferInfo(referInfo);

                    referSegmentInfos.add(referResultInfo);

                    textTemp =  split[1];
                } else {
                    ReferSegmentInfo referResultInfo = new ReferSegmentInfo();
                    referResultInfo.setType(AgentNodeReferResultTypeEnum.CUSTOM.getType());
                    referResultInfo.setContent(split[0]);
                    referSegmentInfos.add(referResultInfo);

                    ReferSegmentInfo referResultInfoFuture = new ReferSegmentInfo();
                    referResultInfoFuture.setType(referInfo.buildReferResultInfoType());
                    referResultInfoFuture.setNodeId(referInfo.getNodeId());
                    referResultInfoFuture.setReferInfo(referInfo);

                    referSegmentInfos.add(referResultInfoFuture);
                }

            }
            if (length == 3) {
                ReferSegmentInfo referResultInfo = new ReferSegmentInfo();
                referResultInfo.setType(AgentNodeReferResultTypeEnum.CUSTOM.getType());
                referResultInfo.setContent(split[0]);

                referSegmentInfos.add(referResultInfo);

                ReferSegmentInfo referResultInfoFuture = new ReferSegmentInfo();
                referResultInfoFuture.setType(referInfo.buildReferResultInfoType());
                referResultInfoFuture.setNodeId(referInfo.getNodeId());
                referResultInfoFuture.setReferInfo(referInfo);
                referSegmentInfos.add(referResultInfoFuture);

                textTemp =  split[2];
            }
            finalReplaceKey = replaceKey;
        }

        if (!textTemp.contains(finalReplaceKey)) {
            ReferSegmentInfo referResultInfo = new ReferSegmentInfo();
            referResultInfo.setType(AgentNodeReferResultTypeEnum.CUSTOM.getType());
            referResultInfo.setContent(textTemp);

            referSegmentInfos.add(referResultInfo);
        }

        return referSegmentInfos;
    }

    @Data
    public static class ReferInfo {
        /**
         * 获取future
         */
        private String bizId;

        /**
         * 名称
         */
        private String refName;

        /**
         * 引用id
         */
        private String refId;


        private String refType;

        /**
         * 引用数据类型
         */
        private String refDataType;

        /**
         * 节点
         */
        private String nodeId;

        /**
         * 节点名
         */
        private String nodeName;

        /**
         * json路径
         */
        private String jsonPath;


        private String refDataSourceType;

        private String value;

        public String buildReferResultInfoType() {
            AgentRefTypeEnum byType = AgentRefTypeEnum.getByType(refType);
            switch (byType) {
                case SYSTEM_VARIABLE:
                case USER_QUERY:
                case KEYWORD_INFO:
                case CHAT_CLASSIFY:
                case TOOL:
                    return AgentNodeReferResultTypeEnum.DIRECT.getType();
                default:
                    return AgentNodeReferResultTypeEnum.FUTURE.getType();
            }
        }


        public String buildReplaceKey() {
            AgentRefTypeEnum byType = AgentRefTypeEnum.getByType(refType);
            switch (byType) {
                case SYSTEM_VARIABLE:
                case USER_QUERY:
                    return AgentExpressConstants.STR_POUND + refId + AgentExpressConstants.STR_POUND;
                default:
                    return AgentExpressConstants.STR_POUND + nodeId + "_" + refId + AgentExpressConstants.STR_POUND;
            }
        }

        /**
         * 条件判断节点表达式变量key
         * @return
         */

        public String buildVariableKey() {
            String key = null;
            if (StringUtils.isEmpty(refId)) {
                key = refType + "_" + nodeId;
            } else {
                if (AgentLlmOutputTypeEnum.JSON.getType().equals(refDataSourceType)) {
                    // 将字符串转为字节数组并编码
                    Base64.Encoder encoder = Base64.getEncoder();
                    String encodedString = encoder.encodeToString(refId.getBytes());
                    key = refType + "_" + nodeId + "_" + encodedString;
                } else {
                    key = refType + "_" + nodeId + "_" + refId;
                }
            }

            return AgentExpressConstants.STR_POUND + SpelExpressCodeUtils.process(key);
        }

        /**
         * 条件判断节点表 参数值对应code
         * @return
         */
        public String buildVariableValueKey() {
            String key = null;
            if (StringUtils.isEmpty(refId)) {
                key =  refType + "_" + nodeId;
            } else {
                if (AgentLlmOutputTypeEnum.JSON.getType().equals(refDataSourceType)) {
                    // 将字符串转为字节数组并编码
                    Base64.Encoder encoder = Base64.getEncoder();
                    String encodedString = encoder.encodeToString(refId.getBytes());
                    key = refType + "_" + nodeId + "_" + encodedString;
                } else {
                    key = refType + "_" + nodeId + "_" + refId;
                }
            }
            return SpelExpressCodeUtils.process(key);
        }
    }
}