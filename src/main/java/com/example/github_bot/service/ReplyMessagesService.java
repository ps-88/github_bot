package com.example.github_bot.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ReplyMessagesService {

    private final MessageSource messageSource;

    public ReplyMessagesService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage) {

        return new SendMessage(chatId,   messageSource.getMessage(replyMessage,null,null));
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage, Object... args) {
        return new SendMessage(chatId,  messageSource.getMessage(replyMessage,args,null));
    }
     public String getReplyText(String replyText, Object... args) {
         return messageSource.getMessage(replyText, args,null);
     }

    public String getReplyText(String replyText) {
        return   messageSource.getMessage(replyText,null,null);
    }


 }