package com.airtribe.news_aggregator.controller;

import com.airtribe.news_aggregator.model.NewsCategory;
import com.airtribe.news_aggregator.model.PreferencesRequestModel;
import com.airtribe.news_aggregator.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
public class PreferencesController {
    @Autowired
    PreferencesService preferencesService;

    @GetMapping
    public List<NewsCategory> getPreferences(@RequestParam long userId) {
        return preferencesService.getPreferences(userId);
    }

    @PutMapping
    public String updatePreferences(@RequestBody PreferencesRequestModel preferencesRequestModel) {
        boolean hasUpdatedPreferences = preferencesService.updatePreferences(
                preferencesRequestModel.getUserId(),
                preferencesRequestModel.getPreferences());

        if (hasUpdatedPreferences) {
            return "Updated Preferences Successfully!";
        }

        return "Failed to update preferences";
    }
}
