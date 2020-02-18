package com.walt.community.controller;

import com.walt.community.dto.AccessTokenDTO;
import com.walt.community.dto.GithubUser;
import com.walt.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${github.client.id}")
    private String cilentId;

    @Value("${github.cilent.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("callback")
    public String callBack(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(cilentId);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
