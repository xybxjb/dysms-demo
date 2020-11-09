package cn.slipbend.interceptor;

import cn.slipbend.util.JWTUtil;
import cn.slipbend.util.ServerResponse;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("请求路径：  " + request.getRequestURL());

        // 获取请求头中的令牌
        String token = request.getHeader("token");
        System.out.println("token:" + token);
        ServerResponse serverResponse = ServerResponse.getError("");

        try{
            // 验证令牌
            JWTUtil.verify(token);
//            System.out.println(JWTUtil.verify(token).getClaim("userId").asInt());
            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            serverResponse = serverResponse.getError("无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            serverResponse = serverResponse.getError("token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            serverResponse = serverResponse.getError("token算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            serverResponse = serverResponse.getError("token无效");
        }
        String feedback = JSON.toJSONString(serverResponse);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(feedback);
        return false;
    }


}
