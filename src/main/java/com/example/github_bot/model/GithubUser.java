package com.example.github_bot.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser {


    @JsonProperty(value = "login")
    private String name;
    @JsonProperty(value = "html_url")
    private String link;


}
