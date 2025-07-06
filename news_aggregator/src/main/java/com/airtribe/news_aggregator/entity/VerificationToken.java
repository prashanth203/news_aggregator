package com.airtribe.news_aggregator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    private Date expirationDate;

    @OneToOne
    @JoinColumn(name = "userId")
    private AuthUser user;

    public VerificationToken(String token, AuthUser user) {
        long milis = System.currentTimeMillis() + 24 * 60 * 60 * 1000;

        this.token = token;
        this.expirationDate = new Date(milis);
        this.user = user;
    }
}
