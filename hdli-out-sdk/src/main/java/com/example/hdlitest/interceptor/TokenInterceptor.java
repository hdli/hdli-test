package com.example.hdlitest.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BaseGlobalInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author luyi
 * @date 2023/2/24 8:27 下午
 */
@Component
@Slf4j
public class TokenInterceptor extends BaseGlobalInterceptor {

    @Value("${cr.opengateway.appId:}")
    private String appId;
    @Value("${cr.opengateway.appSecret:}")
    private String appSecret;

    @Override
    protected Response doIntercept(Chain chain) throws IOException {
        log.info("TokenInterceptor");
        Request request = chain.request();
        Request newReq = null;
        byte[] toEncodeContent = (this.appId + ":" + this.appSecret).getBytes(StandardCharsets.UTF_8);
        String value = Base64.getEncoder().encodeToString(toEncodeContent);
        newReq = request.newBuilder().header("Authorization", "Basic " + value).build();
        return chain.proceed(newReq);
    }
}
