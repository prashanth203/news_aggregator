package com.airtribe.news_aggregator.model;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class NewsModel {
        private String id;
        private String name;
        private String description;
        private String url;
        private String category;
        private String language;
        private String country;
    }
