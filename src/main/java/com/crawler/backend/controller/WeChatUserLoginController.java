package com.crawler.backend.controller;

import com.crawler.backend.service.AuthService;
import com.crawler.backend.service.UserService;
import com.crawler.backend.service.dto.AuthUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * api登录授权
 *
 * @author zhuhuix
 * @date 2020-03-30
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = "系统授权接口")
public class WeChatUserLoginController {

    private final AuthService authService;

    public WeChatUserLoginController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("登录授权")
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthUserDto authUserDto, HttpServletRequest request) {
        return ResponseEntity.ok(authService.login(authUserDto, request));
    }

}
