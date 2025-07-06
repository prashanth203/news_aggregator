package com.airtribe.news_aggregator.service;

import com.airtribe.news_aggregator.entity.AuthUser;
import com.airtribe.news_aggregator.entity.Preferences;
import com.airtribe.news_aggregator.model.NewsCategory;
import com.airtribe.news_aggregator.repository.AuthUserRepository;
import com.airtribe.news_aggregator.repository.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferencesService {
    @Autowired
    PreferencesRepository preferencesRepository;
    @Autowired
    AuthUserRepository authUserRepository;

    public List<NewsCategory> getPreferences(long userId) {
        AuthUser authUser = authUserRepository.findByUserId(userId);

        return preferencesRepository.findPreferencesByUser(authUser).getPreferences();
    }

    public boolean updatePreferences(long userId, List<NewsCategory> preferences) {
        AuthUser user = authUserRepository.findByUserId(userId);
        Preferences userPreferences = new Preferences(preferences, user);

        preferencesRepository.save(userPreferences);

        return true;
    }
}