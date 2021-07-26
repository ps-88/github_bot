package com.example.github_bot.handlers;

import com.example.github_bot.service.MainMenuService;
import com.example.github_bot.service.ReplyMessagesService;
import com.example.github_bot.state.BotState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class HelpMenuHandler implements InputMessageHandler {

    private MainMenuService mainMenuService;
    private ReplyMessagesService messagesService;

    public HelpMenuHandler(MainMenuService mainMenuService, ReplyMessagesService messagesService) {
        this.mainMenuService = mainMenuService;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(),
                messagesService.getReplyText("reply.helpMenu.welcomeMessage"));
    }

    ;


    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_HELP_MENU;
    }
}
