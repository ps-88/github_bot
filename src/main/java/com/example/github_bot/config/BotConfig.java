package com.example.github_bot.config;


import com.example.github_bot.GithubTelegramBot;
import com.example.github_bot.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    @Bean
    public GithubTelegramBot githubTelegramBot(TelegramFacade telegramFacade) {

        GithubTelegramBot githubTelegramBot = new GithubTelegramBot(telegramFacade);
        githubTelegramBot.setBotUserName(botUserName);
        githubTelegramBot.setBotToken(botToken);
        githubTelegramBot.setWebHookPath(webHookPath);

        return githubTelegramBot;
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