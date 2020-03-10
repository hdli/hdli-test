package com.example.hdlitest.designMode.decorator.passport.upgrade;


import com.example.hdlitest.designMode.decorator.passport.old.ISigninService;
import com.example.hdlitest.designMode.decorator.passport.old.ResultMsg;

/**
 * Created by Tom on 2019/3/17.
 */
public class SiginForThirdService implements ISiginForThirdService {

    //需要装饰的类
    private ISigninService signinService;

    public SiginForThirdService(ISigninService signinService) {
        this.signinService = signinService;
    }

    @Override
    public ResultMsg regist(String username, String password) {
        return signinService.regist(username,password);
    }

    @Override
    public ResultMsg login(String username, String password) {
        return signinService.login(username,password);
    }

    @Override
    public ResultMsg loginForQQ(String id) {
        return null;
    }

    @Override
    public ResultMsg loginForWechat(String id) {
        return null;
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return null;
    }

    @Override
    public ResultMsg loginForTelphone(String telphone, String code) {
        return null;
    }

    @Override
    public ResultMsg loginForRegist(String username, String passport) {
        return null;
    }
}
