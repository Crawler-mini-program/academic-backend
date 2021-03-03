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
public class SearchScholarByOrgController {
    @GetMapping("/search-scholar-by-org")
    @ApiOperation("根据机构得到教师列表")
    @ApiResponses(value =
    @ApiResponse(code = 200,message = "访问成功")
    )
    public JSONObject SearchScholarByField(String page_size, String page_no, String orgId) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://47.92.240.36/academic/api/v2/scholars/suggest-scholar?num=" + page_size + "&page=" + page_no + "&orgId=" + orgId);
        CloseableHttpResponse response = httpclient.execute(httpget);

        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
        return jsonObject;
    }
}
