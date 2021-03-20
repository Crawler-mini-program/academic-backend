package com.crawler.backend.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.crawler.backend.utils.token.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private JWTUtil jwtUtil ;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 地址过滤
        String url = request.getRequestURL().toString() ;
        log.info(url);
        if (url.contains("/getSessionKeyOropenid")||url.contains("/check-token")){
            return true ;
        }
        // Token 验证
        String token = request.getHeader(jwtUtil.getHeader());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(jwtUtil.getHeader());
        }
        if(StringUtils.isEmpty(token)){
            JSONObject res = new JSONObject();
            res.put("success", false);
            res.put("message", "用户未登录！");
            out = response.getWriter();
            out.append(res.toString());
            throw new Exception(jwtUtil.getHeader()+ "不能为空");
        }
        Claims claims = jwtUtil.getTokenClaim(token);
        if(claims == null || jwtUtil.isTokenExpired(claims.getExpiration())){
            JSONObject res = new JSONObject();
            res.put("success", false);
            res.put("message", "用户登录登录已失效！");
            out = response.getWriter();
            out.append(res.toString());
            throw new Exception(jwtUtil.getHeader() + "失效，请重新登录");
        }
        //设置 identityId 用户身份ID
        System.out.println(claims.getSubject());
        request.setAttribute("openId", claims.getSubject());
        return true;
    }
}