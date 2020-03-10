package com.example.hdlitest.designMode.decorator.passport.upgrade;


import com.example.hdlitest.designMode.decorator.passport.old.ISigninService;
import com.example.hdlitest.designMode.decorator.passport.old.ResultMsg;

/**
 * 原功能的拓展
 * Created by Tom on 2019/3/17.
 */
public interface ISiginForThirdService extends ISigninService {

    /**
     * QQ登录
     * @param id
     * @return
     */
    ResultMsg loginForQQ(String id);

    /**
     * 微信登录
     * @param id
     * @return
     */
    ResultMsg loginForWechat(String id);

    /**
     * 记住登录状态后自动登录
     * @param token
     * @return
     */
    ResultMsg loginForToken(String token);

    /**
     * 手机号登录
     * @param telphone
     * @param code
     * @return
     */
    ResultMsg loginForTelphone(String telphone, String code);

    /**
     * 注册后自动登录
     * @param username
     * @param passport
     * @return
     */
    ResultMsg loginForRegist(String username, String passport);
}
