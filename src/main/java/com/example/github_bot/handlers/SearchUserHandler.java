package com.example.github_bot.handlers;

import com.example.github_bot.state.BotState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class SearchUserHandler implements InputMessageHandler{
    @Override
    public SendMessage handle(Message message) {
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SEARCH_USER;
    }
}
