package com.crawler.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(tags = "项目初始化测试")
public class HelloController {
    @GetMapping("/index")
    @ApiOperation("返回hello world")
    @ApiResponses(value =
        @ApiResponse(code = 200,message = "访问成功")
    )
    public String index(){
        return "Hello World!";
    }
}
