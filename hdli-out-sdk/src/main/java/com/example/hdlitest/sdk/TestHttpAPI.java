package com.example.hdlitest.sdk;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author luyi
 * @date 2023/2/24 8:29 下午
 */
@RetrofitClient(
        baseUrl = "${cr.opengateway.domain}"
)
public interface TestHttpAPI {

    @POST("/luyi/guavalimitdemo/test")
    String createEmployee();

}
