package com.example.test.url;

import com.example.test.account.Account;
import jakarta.persistence.*;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url_original")
    private String originalUrl;

    @Column(name = "url_short")
    private String shortUrl;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Account user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", user=" + user +
                '}';
    }


    public Url(String originalUrl, String shortUrl, Account user) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.user = user;
    }
    public Url(){
        super();
    }
}
