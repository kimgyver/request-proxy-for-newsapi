package com.jason.requestproxy.controllers;

import com.jason.requestproxy.models.Article;
import com.jason.requestproxy.models.Articles;
import com.jason.requestproxy.models.ClientRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
//@CrossOrigin(origins = { "http://localhost", "http://localhost:3000", "http://localhost:4200", "https://momentum-clone-jason.netlify.app" })
@CrossOrigin(origins = "*")
public class RequestProxyController {
    @RequestMapping("/articles")
    @PostMapping
    public Article [] getArticles(@RequestBody ClientRequest clientRequest) {
        //String url = "https://newsapi.org/v2/top-headlines?sources=bbc-news&country=&apiKey=dbd63c6023db48bcb79a14e20d25e360";
        String url = clientRequest.getUrl();
        Article[] results = null;

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Article> request = new HttpEntity<>(new Article());
            ResponseEntity<Articles<Article>> response = restTemplate.exchange(url,
                    HttpMethod.GET, request, new ParameterizedTypeReference<Articles<Article>>(){});
            results = response.getBody().getArray();
        } catch(Exception e) {
            Article errorResult = new Article();
            Article[] errorResultArray = new Article[1];
            errorResultArray[0] = errorResult;
            errorResult.setAuthor(e.getMessage());
            return errorResultArray;
        }
        return results;
    }
}
