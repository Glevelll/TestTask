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

    /**
    Метод filterUrl принимает строку oUrl и фильтрует ее, оставляя только символы от 'a' до 'z' и от 'A' до 'Z'.
    Создается StringBuilder для хранения результата.
    В цикле проходятся все символы строки oUrl.
    Если символ это буква (заглавная или прописная), то он добавляется в StringBuilder.
    Возвращается результат в виде строки.
     **/
    public String filterUrl(String oUrl){
        StringBuilder res = new StringBuilder();
        for (char ele: oUrl.toCharArray()){
            if (((int) ele > (int) 'a' && (int) ele < (int) 'z') || ((int) ele > (int) 'A' && (int) ele < (int) 'Z')){
                res.append(ele);
            }
        }
        return res.toString();
    }

    /**
        Метод createUrl принимает строку filterUrl, строку originalUrl и идентификатор пользователя idUser.
        Получаем все объекты Url и сохраняем их в списке allUrl.
        Создается HashSet uniqueSet для хранения уникальных значений сокращенных URL.
        В цикле проходимся по каждому объекту Url в списке allUrl и добавляем их сокращенные URL в uniqueSet.
        Инициализируется строка res с начальным значением "test.kzn/".
        Создается StringBuilder sb для генерации случайных символов из filterUrl.
        В бесконечном цикле генерируем случайные символы длиной 6 из filterUrl и добавляем их к sb.
        Затем добавляем sb в конец строки res.
        Если uniqueSet не содержит сгенерированную строку sb, то выходим из цикла.
        В противном случае, переинициализируем res с начальным значением "test.kzn/" и продолжаем генерацию новых случайных символов.
        Получаем учетную запись пользователя по идентификатору idUser из репозитория accountRepository.
        Создаем новый экземпляр Url, используя оригинальный URL, сгенерированный сокращенный URL и учетную запись пользователя.
        Сохраняем экземпляр Url в репозитории urlRepository.
     **/
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
