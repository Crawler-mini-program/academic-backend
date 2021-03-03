package com.crawler.backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
public class GetSuggestUniController {
    @GetMapping("/get-suggest-uni")
    @ApiOperation("选中子领域后得到推荐的大学")
    @ApiResponses(value =
    @ApiResponse(code = 200,message = "访问成功")
    )
    public JSONObject GetSuggestUni(String page_size, String page_no, String fieldId) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://47.92.240.36/academic/api/v1/rank/top-organizations-by-orgInnovation?num=" + page_size + "&page=" + page_no + "&field=" + fieldId + "&orgType=university&sortType=innovationIndex");
        CloseableHttpResponse response = httpclient.execute(httpget);

        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
        return jsonObject;
    }
}
