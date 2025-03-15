/**
 * ele.me Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.example.hdlitest.servicetest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author kupao
 * @version : AgentNodeReferResultTypeEnum.java, v 0.1 2024年09月18日 17:24 cyril Exp $
 */
@AllArgsConstructor
@Getter
public enum AgentNodeReferResultTypeEnum {
    /**
     * 答案直接获取
     */
    DIRECT("direct"),

    /**
     * 答案通过future获取或者轮训获取
     */
    FUTURE("future"),

    /**
     * 答案是用户自定义的文案
     */
    CUSTOM("custom"),
    ;
    private final String type;
}