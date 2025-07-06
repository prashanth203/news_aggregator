package com.airtribe.news_aggregator.model;

import lombok.Data;

import java.util.List;

@Data
public class PreferencesRequestModel {
    private long userId;
    private List<NewsCategory> preferences;
}
