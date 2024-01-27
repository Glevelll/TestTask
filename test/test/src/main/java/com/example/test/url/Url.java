package com.example.test.url;

import com.example.test.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
