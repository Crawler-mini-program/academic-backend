package com.crawler.backend.Interceptor;

import com.crawler.backend.utils.token.JWTUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component

public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private JWTUtil jwtUtil ;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 地址过滤
        String uri = request.getRequestURI() ;
        if (uri.contains("/getSessionKeyOropenid")){
            return true ;
        }
        // Token 验证
        String token = request.getHeader(jwtUtil.getHeader());
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(jwtUtil.getHeader());
        }
        if(StringUtils.isEmpty(token)){
            throw new Exception(jwtUtil.getHeader()+ "不能为空");
        }
        Claims claims = jwtUtil.getTokenClaim(token);
        if(claims == null || jwtUtil.isTokenExpired(claims.getExpiration())){
            throw new Exception(jwtUtil.getHeader() + "失效，请重新登录");
        }
        //设置 identityId 用户身份ID
        System.out.println(claims.getSubject());
        request.setAttribute("openId", claims.getSubject());
        return true;
    }
}