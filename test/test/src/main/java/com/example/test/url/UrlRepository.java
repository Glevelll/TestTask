package com.example.test.url;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    List<Url> findByUserId(Long userId);
}
