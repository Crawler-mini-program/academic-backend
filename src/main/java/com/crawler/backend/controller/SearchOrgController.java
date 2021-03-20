package com.crawler.backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

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
public class SearchOrgController {
    @GetMapping("/search-org")
    @ApiOperation("关键词模糊搜索机构")
    @ApiResponses(value =
        @ApiResponse(code = 200,message = "访问成功")
    )
    public JSONObject SearchOrg(String page_size, String page_no, String content) throws IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://47.92.240.36/academic/api/v1/s?num=" + page_size + "&page=" + page_no + "&content=" + content + "&type=4");
        CloseableHttpResponse response = httpclient.execute(httpget);

        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
        JSONArray arrays = (JSONArray)jsonObject.get("data");
        JSONArray res = new JSONArray();
        for(int i=0;i<arrays.size();i++){
            if(((JSONObject)arrays.get(i)).get("name").toString().contains("大学")||
                    ((JSONObject)arrays.get(i)).get("name").toString().contains("学院")){
                res.add(arrays.get(i));
            }
        }
        jsonObject.put("data",res);
        return jsonObject;
    }
}
