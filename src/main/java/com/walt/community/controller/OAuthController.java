package com.walt.community.controller;

import com.walt.community.dto.AccessTokenDTO;
import com.walt.community.dto.GithubUser;
import com.walt.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @FileName: community
 * @Author: walt1012
 * @Description: TODO
 * @Date: 2020/2/17 10:49 下午
 */

@Controller
public class OAuthController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("callback")
    public String callBack(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id("b99638cde60f721507e9");
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setClient_secret("fb60785efd7895796d6023aec0db20966fedfa01");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
