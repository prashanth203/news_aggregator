package com.airtribe.news_aggregator.service;

import com.airtribe.news_aggregator.model.NewsModel;

import java.util.List;

public class NewsService {
    public List<NewsModel> getNews(long userId) {
        return List.of(new NewsModel());
    }
}