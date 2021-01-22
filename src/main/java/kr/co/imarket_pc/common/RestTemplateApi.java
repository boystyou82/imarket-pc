package kr.co.imarket_pc.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Service
public class RestTemplateApi<T> {
    @Value("${api.host}")
    public String host;
    private RestTemplate restTemplate = new RestTemplate();

    public T get(String url, Map<String,String> uriParams, MultiValueMap<String,String> queryParams, Class<T> returnClass) {
        try {
            URI uri;
            if (uriParams != null) {
                uri = UriComponentsBuilder.fromUriString(host + url)
                        .buildAndExpand(uriParams)
                        .toUri();
                uri = UriComponentsBuilder
                        .fromUri(uri)
                        .queryParams(queryParams)
                        .build()
                        .encode(StandardCharsets.UTF_8)
                        .toUri();
            } else {
                uri = UriComponentsBuilder
                        .fromUriString(host + url)
                        .queryParams(queryParams)
                        .build()
                        .encode(StandardCharsets.UTF_8)
                        .toUri();
            }
            return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(null, HttpHeaders.EMPTY), returnClass).getBody();
        } catch (HttpStatusCodeException exception) {
            log.error("RestTemplateApi getMessage :" + exception.getMessage());
            return null;
        }
    }

    public T put(String url, Map<String,String> uriParams, Object body, Class<T> returnClass) {
        try {
            URI uri;
            if (uriParams != null) {
                uri = UriComponentsBuilder.fromUriString(host + url)
                        .buildAndExpand(uriParams)
                        .toUri();
            } else {
                uri = UriComponentsBuilder
                        .fromUriString(host + url)
                        .build()
                        .encode(StandardCharsets.UTF_8)
                        .toUri();
            }
            return restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(body, HttpHeaders.EMPTY), returnClass).getBody();
        } catch (HttpStatusCodeException exception) {
            log.error("RestTemplateApi getMessage :" + exception.getMessage());
            return null;
        }
    }

    public T post(String url, Map<String,String> uriParams, Object body, Class<T> returnClass) {
        try {
            URI uri;
            if (uriParams != null) {
                uri = UriComponentsBuilder.fromUriString(host + url)
                        .buildAndExpand(uriParams)
                        .toUri();
            } else {
                uri = UriComponentsBuilder
                        .fromUriString(host + url)
                        .build()
                        .encode(StandardCharsets.UTF_8)
                        .toUri();
            }
            return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(body, HttpHeaders.EMPTY), returnClass).getBody();
        } catch (HttpStatusCodeException exception) {
            log.error("RestTemplateApi getMessage :" + exception.getMessage());
            return null;
        }
    }

    public T ptch(String url, Map<String,String> uriParams, Object body, Class<T> returnClass) {

        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        try {
            URI uri;
            if (uriParams != null) {
                uri = UriComponentsBuilder.fromUriString(host + url)
                        .buildAndExpand(uriParams)
                        .toUri();
            } else {
                uri = UriComponentsBuilder
                        .fromUriString(host + url)
                        .build()
                        .encode(StandardCharsets.UTF_8)
                        .toUri();
            }
            return restTemplate.exchange(uri, HttpMethod.PATCH, new HttpEntity<>(body, HttpHeaders.EMPTY), returnClass).getBody();
        } catch (HttpStatusCodeException exception) {
            log.error("RestTemplateApi getMessage :" + exception.getMessage());
            return null;
        }
    }

    public Integer ptchCode(String url, Map<String,String> uriParams, Object body, Class<T> returnClass) {

        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        try {
            URI uri;
            if (uriParams != null) {
                uri = UriComponentsBuilder.fromUriString(host + url)
                        .buildAndExpand(uriParams)
                        .toUri();
            } else {
                uri = UriComponentsBuilder
                        .fromUriString(host + url)
                        .build()
                        .encode(StandardCharsets.UTF_8)
                        .toUri();
            }
            return restTemplate.exchange(uri, HttpMethod.PATCH, new HttpEntity<>(body, HttpHeaders.EMPTY), returnClass).getStatusCodeValue();
        } catch (HttpStatusCodeException exception) {
            log.error("RestTemplateApi getMessage :" + exception.getMessage());
            return null;
        }
    }
}