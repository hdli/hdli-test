package com.example.hdlitest.kuaidi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/7/5 23:10
 */
@RestController
public class KuaidiController {

    @PostMapping("/kuaidi")
    public void test(HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
        String param = request.getParameter("param");

        System.out.println("接受结果："+param);

        String out = "{\"result\":\"true\",\"returnCode\":\"200\",\"message\":\"成功\"}";
        response.getWriter().print(out);
    }
}
