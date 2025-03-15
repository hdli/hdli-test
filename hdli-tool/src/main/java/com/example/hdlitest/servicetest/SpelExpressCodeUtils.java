package com.example.hdlitest.servicetest;

/**
 * <p>
 *
 * @author <a href="mailto:penghaozi.phz@alibaba-inc.com">penghaozi</a>
 * @version 1.0.0
 * @since 2022-12-14
 */
public class SpelExpressCodeUtils {
    private SpelExpressCodeUtils() {}

    /**
     * 开放服务的code处理
     *
     * @param code 数据服务Code
     * @return String
     */
    public static String process(String code) {
        if (code == null) {
            return null;
        }
        return code.replaceAll("[\\^.$&*@`\\-'%()#]", "");
    }
}
