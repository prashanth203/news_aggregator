package com.airtribe.news_aggregator.entity;

import com.airtribe.news_aggregator.model.NewsCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private List<NewsCategory> preferences;

    @OneToOne
    @JoinColumn(name = "userId")
    private AuthUser user;

    public Preferences(List<NewsCategory> preferences, AuthUser user) {
        this.user = user;
        this.preferences = preferences;
    }
}