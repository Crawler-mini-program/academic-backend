package com.crawler.backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Api(tags = "项目初始化测试")
public class HelloController {
    @GetMapping("/index")
    @ApiOperation("返回hello world")
    @ApiResponses(value =
        @ApiResponse(code = 200,message = "访问成功")
    )
    public JSONObject index() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://47.92.240.36/academic/api/v1/fields/super-course?pageSize=10&pageNo=0&content=计算机");
        CloseableHttpResponse response = httpclient.execute(httpget);

        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
        return jsonObject;
    }
}
