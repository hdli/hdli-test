package com.example.hdlitest.io.excel;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 行政区划模型
 * @Author chenqi
 * @Date 2020/7/20
 **/
@Data
public class RegionSDO implements Serializable {
    /**
     * 行政区划编码
     */
    public String code;
    /**
     * 外部编码
     */
    public String outCode;
    /**
     * 行政区划名称
     */
    public String name;
    /**
     * 层级
     * 1 省 2 市 3 区，县 4 街道，镇，乡 5
     */
    public Integer divLevel;
    /**
     * 父级区划编码
     */
    public String parentCode;
    /**
     * 电话区号
     */
    public String telCode;
    /**
     * 邮政编码
     */
    public String postCode;

    /**
     * 1	地级市城区
     * 2	县级市
     * 3	县城城区
     * 4	乡镇
     * 5	省会城区
     * 6	中心城市
     */
    public Integer style;

    /**
     * 状态 1: 正常  2：停用
     */
    public Integer state;
    /**
     * 新code
     */
    public String newCode;

    /**
     * 1:常规  2：虚拟
     */
    public Integer isFullName;

    /**
     *
     *   启用时间
     */
    private Date enableDate;

    /**
     *   停用时间
     *
     */
    private Date disableDate;

    /**
     *   描述
     *
     */
    private String description;
}
