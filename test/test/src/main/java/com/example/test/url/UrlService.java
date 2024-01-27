package com.example.test.url;

import com.example.test.account.Account;
import com.example.test.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Url> getAllUrl(){
        return urlRepository.findAll();
    }

    public String filterUrl(String oUrl){
        StringBuilder res = new StringBuilder();
        for (char ele: oUrl.toCharArray()){
            if (((int) ele > (int) 'a' && (int) ele < (int) 'z') || ((int) ele > (int) 'A' && (int) ele < (int) 'Z')){
                res.append(ele);
            }
        }
        return res.toString();
    }

    public String createUrl(String filterUrl, String originalUrl, Long idUser) {
        List<Url> allUrl = getAllUrl();
        Set<String> uniqueSet = new HashSet<>();
        allUrl.forEach(u -> uniqueSet.add(u.getShortUrl()));
        String res = "test.kzn/";
        StringBuilder sb = new StringBuilder();
        while (true) {
            for (int i = 0; i < 6; i++) {
                sb.append(filterUrl.charAt(new Random().nextInt(filterUrl.length())));
            }
            res = res + sb;
            if (!uniqueSet.contains(sb)) {
                break;
            } else {
                res = "test.kzn/";
            }
        }
        Account user = accountRepository.getById(idUser);
        Url url = new Url(originalUrl, res, user); // Создание нового экземпляра Url
        urlRepository.save(url); // Сохранение экземпляра Url
        return res;
    }

    public void updateUrl(Long id, String shortUrl) {
        Url url = urlRepository.getById(id);
        url.setShortUrl(shortUrl);
        urlRepository.save(url);
    }

    public void deleteUrl(Long id) {
        urlRepository.deleteById(id);
    }

    public List<Url> getUrlByUserId(Long userId) {
        return urlRepository.findByUserId(userId);
    }
}
