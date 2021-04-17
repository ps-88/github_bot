package com.example.github_bot.service;

import com.example.github_bot.GithubTelegramBot;
import com.example.github_bot.cache.UserDataCache;
import com.example.github_bot.model.GithubUser;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Service
public class SendUsersInfoService {

    private ReplyMessagesService messagesService;
    private UserDataCache userDataCache;
    private GithubTelegramBot telegramBot;

    public SendUsersInfoService(ReplyMessagesService messagesService, UserDataCache userDataCache,@Lazy GithubTelegramBot telegramBot) {
        this.messagesService = messagesService;
        this.userDataCache = userDataCache;
        this.telegramBot = telegramBot;
    }

    public void sendUsersInfo(long chatId, List<GithubUser> userList) {

        StringBuilder usersInfo = new StringBuilder();
        usersInfo.append("Result of searching:" +"\n" );
        for (GithubUser githubUser : userList) {

            usersInfo.append(messagesService.getReplyText("reply.userSearch.usersInfo",
                    githubUser.getName(),githubUser.getLink()));



        String listUsers =   messagesService.getReplyText("reply.userSearch.usersInfo",
                    githubUser.getName(),githubUser.getLink());
//
//       SendMessage message =  new SendMessage(chatId, String.format("%s%n -------------------%n"
//                ,  githubUser.getName(),githubUser.getLink()  ));


        }
        String str = usersInfo.toString();
        telegramBot.sendMessage(chatId,str);

    }
}
