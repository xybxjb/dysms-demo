package cn.slipbend.util;

import cn.slipbend.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

public class JWTUtil {
    private static final String SIGN = "qwopnx.kl98ahbzzz";

    /**
     * 生成 token 【header.payload.signature】
     * @param user payload中要放置的用户信息
     * @return token
     */
    public static String getToken(User user){
        // 设置过期时间
        Calendar instance = Calendar.getInstance();
        // 默认7天过期
        instance.add(Calendar.DATE,7);

        String token = JWT.create()
                            // payload
                            .withClaim("userId", user.getId())
                            // 过期时间
                            .withExpiresAt(instance.getTime())
                            // 签名
                            .sign(Algorithm.HMAC256(SIGN));

        return token;
    }

    /**
     * 验证 token 合法性,验证通过后返回解码 token
     * @param token
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

}
