package com.crawler.backend.controller;

import cn.hutool.extra.emoji.EmojiUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crawler.backend.model.WeChatUserInfo;
import com.crawler.backend.service.UserService;
import com.crawler.backend.utils.token.JWTUtil;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import com.crawler.backend.utils.AES;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@AllArgsConstructor
public class LoginController {
    private UserService userService;
    /**
     * 获取微信小程序 用户信息或用户手机号码
     * @param info 调用微信登陆返回的Code
     * @return
     */
    @Resource
    private JWTUtil jwtUtil ;
    @RequestMapping("/getSessionKeyOropenid")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("微信登录")

    public synchronized JSONObject getSessionKeyOropenid(@RequestBody WeChatUserInfo info) throws Exception {
        String code=info.getCode();
        String iv=info.getIv();
        String encryptedData=info.getEncrypteData();
        System.out.println("code:" + code);
        System.out.println("iv:" + iv);
        System.out.println("encryptedData:" + encryptedData);

        CloseableHttpClient httpclient = HttpClients.createDefault();
        //发送get请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        HttpGet httpget = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid=" + "wxc9321df3ad37a166" + "&secret=" + "a3742096914abd0cd85ce76146329c69" + "&js_code=" + code + "&grant_type=authorization_code");
        CloseableHttpResponse response = httpclient.execute(httpget);

        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
        log.info(jsonObject.toJSONString());

//----------------------------------解密用户信息-----------------------------------------
        String userInfo = null;
        JSONObject userInfoJSON = null;
        Thread.sleep(500);
        try {
            byte[] resultByte = AES.decrypt(Base64.decodeBase64(encryptedData),
                    Base64.decodeBase64(jsonObject.getString("session_key")),
                    Base64.decodeBase64(iv));

            userInfo = new String(resultByte, "UTF-8");
//            System.out.println("userInfo:" + userInfo);
            userInfoJSON = JSON.parseObject(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("userInfo:" + userInfoJSON);
//----------------------------------解密用户信息-----------------------------------------

        //具体可以获取什么用户信息可以到微信小程序文档查看
        //注意：获取用户信息和获取用户手机号 这两个是单独获取的，不能同时获取
//        System.out.println("openId:" + jsonObject.getString("openid"));
        String openId = jsonObject.getString("openid");
        String token = jwtUtil.getToken(openId);
        if(userService.getUserById(openId)==null){
            if(userService.saveOrUpdateUser(userInfoJSON)){
                log.info("saveUser ok!");
                return userService.getUserByIdToJson(openId,token);
            }
        }


        userService.saveOrUpdateUser(userInfoJSON);
        return userService.getUserByIdToJson(openId,token);

//        System.out.println(EmojiUtil.toHtmlHex(userInfoJSON.getString("nickName")));
    }


//    /**
//     * 获取微信公众号用户openid
//     * @param code 调用微信登陆回调返回的Code，state
//     * @return
//     */
//    @RequestMapping("/weChatMp")
//    @Transactional(rollbackFor = Exception.class)
//    public synchronized String weChatMp(String code, String state) throws Exception {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//
//        //发送get请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session
//        HttpGet httpget = new HttpGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + "appIdMp" + "&secret=" + "appSecretMp" + "&code=" + code + "&grant_type=authorization_code");
//        CloseableHttpResponse response = httpclient.execute(httpget);
//
//        HttpEntity entity = response.getEntity();
//        JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
//        System.out.println("--------------获取微信公众号用户openid返回的数据:---------------");
//
//        System.out.println("MemberInfo:" + jsonObject.toJSONString());
//        System.out.println("state:" + state);	//这里的 state 是前端传过来的自定义内容参数
//        System.out.println("openid:" + jsonObject.getString("openid"));
//
//        System.out.println("--------------获取微信公众号用户openid返回的数据:---------------");
//
//
//        return "redirect:https://hkhxjx.wegouer.com/a.html";	//在微信小程序获取完微信公众号openId之后重定向页面
//    }


}
