package com.airtribe.news_aggregator.repository;

import com.airtribe.news_aggregator.entity.AuthUser;
import com.airtribe.news_aggregator.entity.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {
    Preferences findPreferencesByUser(AuthUser user);
}