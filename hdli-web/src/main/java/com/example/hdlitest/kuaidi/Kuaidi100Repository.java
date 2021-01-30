package com.example.hdlitest.kuaidi;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: luyi
 * @date: 2020/7/2 10:54 上午
 */
public class Kuaidi100Repository {

    private static String url = "https://poll.kuaidi100.com/poll";

    public static void main(String[] args) {
        dingyue();

//        query();
    }

    public static void query(){
        QueryRequestDetail requestDetail = new QueryRequestDetail();
        requestDetail.setCom("jd");
        requestDetail.setNum("JD0015288802440");
        requestDetail.setResultv2(1);
        String param = JSONObject.toJSONString(requestDetail);
        String url = "https://poll.kuaidi100.com/poll/query.do";
        String customer ="851FF37E58CE03326DC9FE4FA93B03F1";
        String key = "AoPSLNUP3137";
        String sign = MD5Utils.encode(param+key+customer);
        HashMap params = new HashMap();
        params.put("param",param);
        params.put("sign",sign);
        params.put("customer",customer);

        String jsonObject = HttpClientUtil.post(url,params);
        System.out.println(jsonObject);

    }

    public static void dingyue(){
        String url = "https://poll.kuaidi100.com/poll";
        TaskRequest req = new TaskRequest();
        req.setKey("AoPSLNUP3137");
        req.setCompany("jd");
//        req.setFrom("上海浦东新区");
//        req.setTo("广东深圳南山区");
        req.setNumber("JD0015288802440");
        Parameters parameters = new Parameters();
        parameters.setCallbackurl("http://www.yourdmain.com/kuaidi");
        req.setParameters(parameters);

        Map<String, String> p = new HashMap<String, String>();
        p.put("schema", "json");
        p.put("param", JSONObject.toJSONString(req));
        System.out.println(JSON.parseObject(JSONObject.toJSONString(p)));


        String jsonObject = HttpClientUtil.post(url,p);
        System.out.println(jsonObject);
    }



    /**
     * 订阅请求参数
     */
    @Data
    static class TaskRequest{
        /**
         * 必填
         * 订阅的快递公司的编码，一律用小写字母
         * ems
         */
        private String company;
        /**
         * 必填
         * 订阅的快递单号，单号的最大长度是 32 个字符
         * em263999513jp
         */
        private String number;

        private String from;

        private String to;

        /**
         * 必填
         * 公司编号
         */
        private String key;

        /**
         * 附加参数信息
         *  callbackurl 回调接口的地址
         *  autoCom 添加此字段且将此值设为 1，则表示开始智能判断单号所属公司的功能，开启后，company 字段可为空,即只传运单号（number 字段）
         */
        private Parameters parameters;

    }

    @Data
    static class Parameters{
        /**
         * 必填
         * 回调接口的地址
         */
        private String callbackurl;

        private String salt;

        private String resultv2;
        private String autoCom;
        private String interCom;
        private String departureCountry;
        private String departureCom;
        private String destinationCountry;
        private String destinationCom;
        private String phone;

    }

    static class TaskResponse{

        private boolean result;

        private String returnCode;

        private String status;

    }
    @Data
    static class PullInfo{
        /**
         * 监控状态:polling:监控中，shutdown:结束，abort:中止，
         * updateall：重新推送。其中当快递单为已签收时 status=shutdown，
         * 当 message 为“3 天查询无记录”或“60 天无变化时”status= abort ，
         * 对于 stuatus=abort 的状度，需要增加额外的处理逻辑
         */
        private String status;
        /**
         * 已弃用
         */
        private String billstatus;

        private String message;


        private String autoCheck;

        private String comOld;
        private String comNew;
        /**
         * 最新查询结果，若在订阅报文中通过 interCom 字段开通了国际版，则此 lastResult 表示出发国的查询结果，全量，倒序（即时间最新的在最前）
         */
        private LogisticsContent lastResult;
        /**
         * 表示最新的目的国家的查询结果，只有在订阅报文中通过 interCom=1 字段开通了国际版才会显示此数据元，全量，倒序（即时间最新的在最前）
         */
        private LogisticsContent destResult;

    }

    @Data
    static class LogisticsContent{
        private String message;
        /**
         * 快递单当前状态，包括 0 在途，1 揽收，2 疑难，3 签收，4 退签，5 派件，6 退回，7 转单，10 待清关，11 清关中，12 已清关，13 清关异常，14 收件人拒签等 13 个状态
         */
        private String state;
        /**
         * 通讯状态，请忽略
         */
        private String status;
        /**
         * 快递单明细状态标记，暂未实现，请忽略
         */
        private String condition;

        /**
         * 是否签收标记
         */
        private String ischeck;
        /**
         * 快递公司编码,一律用小写字母
         */
        private String com;
        /**
         * 单号
         */
        private String nu;

        private List<LogisticsDetail> data;


    }


    /**
     * [{
     *                 "context":"上海分拨中心/装件入车扫描 ",
     *                 "time":"2012-08-28 16:33:19",
     *                 "ftime":"2012-08-28 16:33:19",
     *                 "status":"在途",
     *                 "areaCode":"310000000000",
     *                 "areaName":"上海市"
     *             }
     */
    @Data
    static class LogisticsDetail{
        private String context;
        private String time;
        private String ftime;
        private String status;
        private String areaCode;
        private String areaName;
    }

    /**
     * 查询接口的请求参数
     */
    @Data
    static class QueryRequest{

        private String customer;
        private String sign;
        private QueryRequestDetail param;

    }
    @Data
    static class QueryRequestDetail{
        /**
         * 必填
         * 查询的快递公司的编码， 一律用小写字母
         */
        private String com;
        /**
         * 必填
         * 查询的快递单号， 单号的最大长度是 32 个字符
         */
        private String num;
        /**
         * 收、寄件人的电话号码（手机和固定电话均可，只能填写一个，顺丰单号必填，其他快递公司选填。如座机号码有分机号，分机号无需上传。）
         */
        private String phone;

        private String from;
        private String to;
        private int resultv2;
    }

}
