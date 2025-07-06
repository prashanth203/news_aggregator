package com.airtribe.news_aggregator.model;
import com.airtribe.news_aggregator.entity.AuthUser;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegistrationResponseModel {
    private AuthUser user;
    private String applicationUrl;
}
