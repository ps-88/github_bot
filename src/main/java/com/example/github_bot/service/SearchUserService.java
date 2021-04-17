package com.example.github_bot.service;

import com.example.github_bot.GithubTelegramBot;
import com.example.github_bot.model.GithubUser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Getter
@Setter
public class SearchUserService {

    @Value("${searchUserService.searchUserRequest}")
    private String searchUserRequest;

    private final RestTemplate restTemplate;
    private final GithubTelegramBot telegramBot;


    private static final String URI_PARAM_SEARCH_USER_QUERY = "SEARCH_USER_QUERY";

    public SearchUserService(RestTemplate restTemplate, @Lazy GithubTelegramBot telegramBot, ReplyMessagesService messagesService) {
        this.restTemplate = restTemplate;
        this.telegramBot = telegramBot;

    }

    @SneakyThrows
    public List<GithubUser> getGithubUserList(long chatId, String usersAnswer) {

        List<GithubUser> githubUserList = null;


        URL url = new URL("https://api.github.com/search/users?q=" + usersAnswer);
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(url, Map.class);



//        ResponseEntity<String> gitHubResp
//                = restTemplate.getForEntity(searchUserRequest, String.class,
//                usersAnswer);
//        System.out.println("gitHubResp " + gitHubResp);


        JsonNode gitHubNode = objectMapper.readTree(url).findPath("items");
        githubUserList = Arrays.asList(objectMapper.readValue(gitHubNode.toString(), GithubUser[].class));


        return githubUserList;
    }
}
