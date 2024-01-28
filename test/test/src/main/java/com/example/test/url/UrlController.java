package com.example.test.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/urls")
public class UrlController {

    @Autowired
    UrlService urlService;

    // Метод с аннотацией @GetMapping обрабатывает GET-запросы на эндпоинте "/getAllUrl"
    // с целью получения ВСЕХ url, которые есть в таблице url
    @GetMapping("/getAllUrl")
    @ResponseBody
    public List<Url> getAllUrl(){
        return urlService.getAllUrl();
    }

    // Метод с аннотацией @PostMapping обрабатывает POST-запросы на эндпоинте "/createUrl"
    // с целью создания нового url в таблице. На входе он принимает входной url для преобразования
    // и id пользователя, который захотел создать новую запись
    @PostMapping("/createUrl")
    @ResponseBody
    public String createUrl(@RequestParam("inUrl") String inUrl, @RequestParam("id_user") Long idUser) {
        String temp = urlService.filterUrl(inUrl);
        return urlService.createUrl(temp, inUrl, idUser);
    }

    // Метод с аннотацией @PutMapping обрабатывает PUT-запросы на эндпоинте "/updateUrl/{id}"
    // с целью обновления записи по ее id, обновляя сокращенную версию ссылки
    @PutMapping("/updateUrl/{id}")
    public ResponseEntity<String> updateUrl(@PathVariable Long id, @RequestParam("shortUrl") String shortUrl) {
        urlService.updateUrl(id, shortUrl);
        return ResponseEntity.ok("Запись успешно обновлена");
    }

    // Метод с аннотацией @DeleteMapping обрабатывает DELETE-запросы на эндпоинте "/deleteUrl/{id}"
    // с целью удаления записи по ее id
    @DeleteMapping("/deleteUrl/{id}")
    public ResponseEntity<String> deleteUrl(@PathVariable Long id) {
        urlService.deleteUrl(id);
        return ResponseEntity.ok("Запись успешно удалена");
    }

    // Метод с аннотацией @GetMapping обрабатывает GET-запросы на эндпоинте "/getUrlByUserId/{userId}"
    // с целью получения url, которые соответствуют id запрашиваемого пользователя
    // Это нужно для реализации авторизации, что каждому авторизованному пользователю
    // соответствуют ссылки, которые создал именно он
    @GetMapping("/getUrlByUserId/{userId}")
    @ResponseBody
    public List<Url> getUrlByUserId(@PathVariable Long userId) {
        return urlService.getUrlByUserId(userId);
    }
}