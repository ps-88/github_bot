package com.example.github_bot.cache;


import com.example.github_bot.model.UserProfileData;
import com.example.github_bot.state.BotState;

public interface DataCache {
    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    UserProfileData getUserProfileData(int userId);

    void saveUserProfileData(int userId, UserProfileData userProfileData);
}
