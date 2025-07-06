package com.airtribe.news_aggregator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
public class AuthUserModel {
    private String userName;
    private String password;
    private String email;
}