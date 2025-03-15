/**
 * ele.me Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.hdlitest.servicetest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author kupao
 * @version : AgentRefType.java, v 0.1 2024年08月26日 15:26 cyril Exp $
 */
@AllArgsConstructor
@Getter
public enum AgentRefTypeEnum {
    USER_QUERY("userQuery"),
    KEYWORD_INFO("keywordInfo"),
    LLM("llm"),
    CHAT_CLASSIFY("chatClassify"),
    KNOWLEDGE_SEARCH("knowledgeSearch"),
    SYSTEM_VARIABLE("systemVariable"),
    TOOL("tool"),
    AGENT_MODE("agent_model"),
    TOOL_EXECUTE_RESULT("execute_result"),
    CHAT_CARD_SUBMIT("chat_card_submit"),
    ;
    private final String type;

    public static AgentRefTypeEnum getByType(String type) {
        for (AgentRefTypeEnum value : AgentRefTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }

    public boolean is(String type) {
        if (this.type.equals(type)) {
            return true;
        }
        return false;
    }
}