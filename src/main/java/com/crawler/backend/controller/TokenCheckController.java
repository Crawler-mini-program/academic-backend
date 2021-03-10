package com.crawler.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.crawler.backend.utils.token.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@AllArgsConstructor
public class TokenCheckController {
    @Resource
    private final JWTUtil jwtUtil ;
    @RequestMapping(value = "/check-token")
    public JSONObject checkToken(String token){
        JSONObject res = new JSONObject();
        if(token==null){
            res.put("success", false);
            res.put("message", "用户未登录！");
        }else {
            Claims claims = jwtUtil.getTokenClaim(token);
            if (claims == null || jwtUtil.isTokenExpired(claims.getExpiration())) {
                res.put("success", false);
                res.put("message", "用户登录登录已失效！");
            }else{
                res.put("success", true);
                res.put("message", "登陆成功");
            }
        }
        return res;
    }
}
