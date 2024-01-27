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

    @GetMapping("/getAllUrl")
    @ResponseBody
    public List<Url> getAllUrl(){
        return urlService.getAllUrl();
    }

    @PostMapping("/createUrl")
    @ResponseBody
    public String createUrl(@RequestParam("inUrl") String inUrl, @RequestParam("id_user") Long idUser) {
        String temp = urlService.filterUrl(inUrl);
        return urlService.createUrl(temp, inUrl, idUser);
    }

    @PutMapping("/updateUrl/{id}")
    public ResponseEntity<String> updateUrl(@PathVariable Long id, @RequestParam("shortUrl") String shortUrl) {
        urlService.updateUrl(id, shortUrl);
        return ResponseEntity.ok("Запись успешно обновлена");
    }

    @DeleteMapping("/deleteUrl/{id}")
    public ResponseEntity<String> deleteUrl(@PathVariable Long id) {
        urlService.deleteUrl(id);
        return ResponseEntity.ok("Запись успешно удалена");
    }

    @GetMapping("/getUrlByUserId/{userId}")
    @ResponseBody
    public List<Url> getUrlByUserId(@PathVariable Long userId) {
        return urlService.getUrlByUserId(userId);
    }
}