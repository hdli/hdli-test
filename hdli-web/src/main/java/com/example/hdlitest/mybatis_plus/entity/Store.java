package com.example.hdlitest.mybatis_plus.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商铺
 * </p>
 *
 * @author lihuidong
 * @since 2021-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Store对象", description="商铺")
public class Store extends Model<Store> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商铺ID")
    @TableId(value = "store_id", type = IdType.AUTO)
    private Long storeId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime gmtApprove;

    @ApiModelProperty(value = "创建者ID")
    private String creatorId;

    @ApiModelProperty(value = "修改者ID")
    private String modifierId;

    @ApiModelProperty(value = "审核员ID")
    private String approverId;

    @ApiModelProperty(value = "店铺编码")
    private String storeCode;

    @ApiModelProperty(value = "商铺名称")
    private String storeName;

    @ApiModelProperty(value = "商铺类型，1-线上店铺，2-线下门店，3-导购员店铺")
    private Integer storeType;

    @ApiModelProperty(value = "经营类型")
    private Integer businessType;

    @ApiModelProperty(value = "商铺状态:1-正常，2-暂停营业，3-已歇业(删除)")
    private Integer storeStatus;

    @ApiModelProperty(value = "经营状态")
    private Integer businessStatus;

    @ApiModelProperty(value = "渠道列表")
    private String channelCodeList;

    @ApiModelProperty(value = "省")
    private String prov;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "街道")
    private String town;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "关联主店ID列表")
    private String masterStoreIdList;

    @ApiModelProperty(value = "商家ID")
    private String merchantId;

    @ApiModelProperty(value = "经营类目列表")
    private String businessCategoryList;

    @ApiModelProperty(value = "导购员编码")
    private String guideCode;

    @ApiModelProperty(value = "负责人姓名")
    private String userName;

    @ApiModelProperty(value = "负责人电话")
    private String userPhone;

    @ApiModelProperty(value = "负责人邮箱")
    private String userEmail;

    @ApiModelProperty(value = "负责人证件号码")
    private String userIdcardNumber;

    @ApiModelProperty(value = "用户证件类型")
    private Integer userIdcardType;

    @ApiModelProperty(value = "负责人证件照片列表")
    private String userIdcardPhotoList;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "商铺图片url")
    private String storePicList;

    @ApiModelProperty(value = "店铺logo")
    private String storeLogo;

    @ApiModelProperty(value = "授权状态：1-通过，2-未通过")
    private Integer authStatus;

    @ApiModelProperty(value = "对应外部商家门店ID")
    private String outerId;

    @ApiModelProperty(value = "所属品牌ID")
    private String brandId;

    @ApiModelProperty(value = "商铺所有人ID")
    private String userId;

    @ApiModelProperty(value = "商铺评级")
    private Integer grade;

    @ApiModelProperty(value = "商铺认证")
    private String authentication;

    @ApiModelProperty(value = "开店时间")
    private String openTime;

    @ApiModelProperty(value = "关店时间")
    private String closeTime;

    @ApiModelProperty(value = "防并发修改标志")
    private Long version;

    @ApiModelProperty(value = "内饰使用扩展属性")
    private String features;

    @ApiModelProperty(value = "外部扩展字段")
    private String extractFeatures;

    private String market;

    @ApiModelProperty(value = "商铺图片url")
    private String picUrl;

    @ApiModelProperty(value = "地图位置选点")
    private String location;

    @ApiModelProperty(value = "是否支持自提1：是0：否")
    private Integer selfTake;

    @ApiModelProperty(value = "是否启用店仓1：是0：否")
    private Integer storage;

    @ApiModelProperty(value = "店铺描述，营业时间说明")
    private String description;

    @ApiModelProperty(value = "授权商家")
    private String authMerchant;

    @ApiModelProperty(value = "店铺图标")
    private String picLogo;

    @ApiModelProperty(value = "店铺外部编码")
    private String outStoreId;

    @ApiModelProperty(value = "平台")
    private String platform;


    @Override
    protected Serializable pkVal() {
        return this.storeId;
    }

}
