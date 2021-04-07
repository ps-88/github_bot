package com.example.github_bot.config;


import com.example.github_bot.GithubTelegramBot;
import com.example.github_bot.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;



    @Bean
    public GithubTelegramBot mySimpleTelegramBot(TelegramFacade telegramFacade) {




        GithubTelegramBot mySimpleTelegramBot = new GithubTelegramBot(telegramFacade );
        mySimpleTelegramBot.setBotUserName(botUserName);
        mySimpleTelegramBot.setBotToken(botToken);
        mySimpleTelegramBot.setWebHookPath(webHookPath);

        return mySimpleTelegramBot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}