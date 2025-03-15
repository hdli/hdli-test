package com.example.hdlitest.servicetest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: zhangaiyu
 * @date: 2024/11/7 15:42
 * @description:
 */
@AllArgsConstructor
@Getter
public enum AgentLlmOutputTypeEnum {

    /**
     * 文本格式
     */

    TEXT("text"),

    /**
     * json
     */
    JSON("json"),

    ;

    private final String type;

    public static String getType(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        for (AgentLlmOutputTypeEnum value : AgentLlmOutputTypeEnum.values()) {
            if (value.getType().equalsIgnoreCase(type)) {
                return value.getType();
            }
        }

        return null;
    }
}
