package com.example.github_bot.handlers;

import com.example.github_bot.cache.UserDataCache;
import com.example.github_bot.model.GithubUser;
import com.example.github_bot.service.ReplyMessagesService;
import com.example.github_bot.service.SearchUserService;
import com.example.github_bot.service.SendUsersInfoService;
import com.example.github_bot.state.BotState;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Service
public class SearchUserHandler implements InputMessageHandler {

    private UserDataCache userDataCache;
    private SearchUserService searchUserService;
    private SendUsersInfoService sendUsersInfoService;
    private ReplyMessagesService messagesService;

    public SearchUserHandler(UserDataCache userDataCache, SearchUserService searchUserService,
                             SendUsersInfoService sendUsersInfoService, ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.searchUserService = searchUserService;
        this.sendUsersInfoService = sendUsersInfoService;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.SEARCH_USER)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_USERNAME_FOR_SEARCH);
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SEARCH_USER;
    }


    private SendMessage processUsersInput(Message inputMsg) {
        String usersAnswer = inputMsg.getText();
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();
        SendMessage replyToUser = messagesService.getReplyMessage(chatId, "reply.searchUser.tryAgain");

        BotState botState = userDataCache.getUsersCurrentBotState(userId);


        if (botState.equals(BotState.ASK_USERNAME_FOR_SEARCH)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.searchUser.enterUserName");

            userDataCache.setUsersCurrentBotState(userId, BotState.QUERY_FOR_SEARCH);
        }


        if (botState.equals(BotState.QUERY_FOR_SEARCH)) {

            List<GithubUser> userList = searchUserService.getGithubUserList(chatId, usersAnswer);
            if (userList.isEmpty()) {
                return messagesService.getReplyMessage(chatId, "reply.searchUser.usersNotFound");
            }


            sendUsersInfoService.sendUsersInfo(chatId, userList);

            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MAIN_MENU);

            replyToUser = messagesService.getReplyMessage(chatId, "reply.userSearch.finishedOK");

        }

        return replyToUser;
    }

}