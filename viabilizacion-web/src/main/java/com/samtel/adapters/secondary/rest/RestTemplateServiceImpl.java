package com.samtel.adapters.secondary.rest;

import com.samtel.adapters.secondary.rest.common.HttpRequestInterceptor;
import com.samtel.core.repository.ILogOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final RestTemplate restTemplate;
    private final ILogOperationRepository logOperationRepository;

    @Autowired
    public RestTemplateServiceImpl(RestTemplateBuilder restTemplateBuilder, ILogOperationRepository logOperationRepository) {
        this.logOperationRepository = logOperationRepository;
        this.restTemplate = restTemplateBuilder
                .additionalInterceptors(Collections.singletonList(new HttpRequestInterceptor(logOperationRepository)))
                .build();
    }

    @Override
    public Optional<String> getWithPathParams(String    uri, List<String> pathParams, Optional<String> idRequest) {
        uri = uri.concat(String.join("/", pathParams));
        HttpEntity<Object> request = new HttpEntity<>((idRequest.isPresent()) ? addAdditionalHeader(addGenericHeaders(), "idRequest", idRequest.get()): addGenericHeaders() );
        return Optional.ofNullable(restTemplate.exchange(uri, HttpMethod.GET, request, String.class).getBody());
    }

    @Override
    public Optional<Object> postWithOutParams(String uri, Object objectRequest, Optional<String> idRequest) {
        HttpEntity<Object> request = new HttpEntity<>(objectRequest, (idRequest.isPresent()) ? addAdditionalHeader(addGenericHeaders(), "idRequest", idRequest.get()): addGenericHeaders() );
        return Optional.ofNullable(restTemplate.exchange(uri, HttpMethod.POST, request, String.class).getBody());
    }

    @Override
    public Optional<String> getWithOutParams(String uri, Object objectRequest,Optional<String> idRequest) {
        HttpEntity<Object> request = new HttpEntity<>(objectRequest, (idRequest.isPresent()) ? addAdditionalHeader(new HttpHeaders(), "idRequest", idRequest.get()): new HttpHeaders() );
        return Optional.ofNullable(restTemplate.exchange(uri, HttpMethod.GET, request, String.class).getBody());
    }

    @Override
    public Optional<String> getWithParams(String uri, Map<String, Object> params,Optional<String> idRequest) {
        HttpEntity<Object> request = new HttpEntity<>((idRequest.isPresent()) ? addAdditionalHeader(addGenericHeaders(), "idRequest", idRequest.get()): addGenericHeaders() );
        String url = setParamsUrl(uri, params);
        return Optional.ofNullable(restTemplate.exchange(url, HttpMethod.GET, request, String.class).getBody());
    }

    private HttpHeaders addGenericHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private HttpHeaders addAdditionalHeader(HttpHeaders headers, String key, String value){
        headers.set(key,value);
        return headers;
    }

    private String setParamsUrl(String uri, Map<String, Object> params) {
        String result = uri;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            result = setParamUrl(result, entry.getKey(), entry.getValue());
        }
        return result;
    }

    private String setParamUrl(String uri, String name, Object value) {
        return UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam(name, value)
                .toUriString();
    }
}
