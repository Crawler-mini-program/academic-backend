package com.crawler.backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crawler.backend.model.UserInfo;
import com.crawler.backend.service.UserService;
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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class SearchScholarByFieldController {
    private UserService userService;
    @GetMapping("/search-scholar-by-field")
    @ApiOperation("根据领域得到教师列表")
    @ApiResponses(value =
        @ApiResponse(code = 200,message = "访问成功")
    )
    public JSONObject SearchScholarByField(String page_size, String page_no, String fieldId) throws IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://47.92.240.36/academic/api/v2/scholars/suggest-scholar?num=" + page_size + "&page=" + page_no + "&fieldId=" + fieldId);
        CloseableHttpResponse response = httpclient.execute(httpget);

        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
        return jsonObject;
    }

    @GetMapping("/user-search-scholar-by-field")
    @ApiOperation("根据用户所选领域得到教师列表")
    @ApiResponses(value =
        @ApiResponse(code = 200,message = "访问成功")
    )
    public JSONObject UserSearchScholarByField(HttpServletRequest request, String page_size, String page_no) throws IOException{
        String uid = request.getAttribute("openId").toString();
        UserInfo user= userService.getUserById(uid);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        long fieldid=user.getFieldid()==0?41517072L:user.getFieldid();
        HttpGet httpget = new HttpGet("http://47.92.240.36/academic/api/v2/scholars/suggest-scholar?num=" + page_size + "&page=" + page_no + "&fieldId=" + fieldid);
        CloseableHttpResponse response = httpclient.execute(httpget);

        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
        return jsonObject;
    }

    @GetMapping("/search-full-scholar")
    @ApiOperation("获取老师完整信息（包括项目和论文数）")
    @ApiResponses(value =
    @ApiResponse(code = 200,message = "访问成功")
    )
    public JSONObject SearchFullScholar(String orgId , String scholarId) throws IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget1 = new HttpGet("http://47.92.240.36/academic/api/v2/scholars/suggest-scholar?orgId="+orgId);
        HttpGet httpget2 = new HttpGet("http://47.92.240.36/academic/api/v1/scholars/"+scholarId);
        CloseableHttpResponse response = httpclient.execute(httpget1);
        HttpEntity entity1 = response.getEntity();
        response = httpclient.execute(httpget2);
        HttpEntity entity2 = response.getEntity();
        JSONObject jsonObject1 = JSON.parseObject(EntityUtils.toString(entity1));
        JSONObject jsonObject2 = JSON.parseObject(EntityUtils.toString(entity2));
        JSONArray arrays = (JSONArray)jsonObject1.get("data");
        JSONObject data=null;
        for(int i=0;i<arrays.size();i++){
            if(((JSONObject)arrays.get(i)).get("scholarId").toString().contentEquals(scholarId)){
                JSONObject tmp = (JSONObject)arrays.get(i);
                data = (JSONObject)jsonObject2.get("data");
                data.put("paperCount",tmp.get("paperCount"));
                data.put("projectCount",tmp.get("projectCount"));
                data.put("patentCount",tmp.get("patentCount"));
                data.put("paperTitle",tmp.get("paperTitle"));
                break;
            }
        }
        jsonObject2.put("data",data);
        return jsonObject2;
    }
}
