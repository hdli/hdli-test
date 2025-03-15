/**
 * ele.me Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.hdlitest.servicetest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author kupao
 * @version 节点组件code: NodeComponentCodeEnum.java, v 0.1 2024年07月24日 16:53 cyril Exp $
 */
@AllArgsConstructor
@Getter
public enum NodeComponentCodeEnum {

    AGENT_KEYWORD_COLLECT("agent-info-gather", "customize-agent","agent-关键信息收集"),
    AGENT_LLM_EXECUTOR("agent-llm-executor", "customize-agent","agent-llm执行器"),
    AGENT_KNOWLEDGE_SEARCH("agent-knowledge-search", "customize-agent","agent-知识检索"),
    AGENT_CONDITION("agent-condition", "customize-agent","agent-条件判断"),
    AGENT_CHAT_CLASSIFY("agent-chat-classify", "customize-agent","agent-对话分类"),
    AGENT_CHAT_ANSWER("agent-chat-answer", "customize-agent","agent-对话回复"),
    AGENT_TOOL("agent-tool", "customize-agent","agent-工具"),

    AGENT_KEYWORD_CONFIRM("agent-info-confirm", "customize-agent","agent-关键信息确认"),
    ;
    private final String code;
    private final String scene;
    private final String desc;

    public static String componentKey(String componentCode, String scene) {
        return scene + "#" + componentCode;
    }

    public static String componentKey(NodeComponentCodeEnum nodeComponentCodeEnum) {
        return nodeComponentCodeEnum.getScene() + "#" + nodeComponentCodeEnum.getCode();
    }

    public static NodeComponentCodeEnum getByCode(String code) {
        for (NodeComponentCodeEnum value : NodeComponentCodeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }

        return null;
    }

}