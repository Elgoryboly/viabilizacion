package com.samtel.bizagi.controller;

import com.samtel.bizagi.dto.RequestCreateCaseDTO;
import com.samtel.bizagi.service.CaseBizagiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequestMapping("v1/bizagi")
public class CaseBizagiController {
    private final CaseBizagiService caseBizagiService;

    @Autowired
    public CaseBizagiController(CaseBizagiService caseBizagiService) {
        this.caseBizagiService = caseBizagiService;
    }


    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createCase(@RequestBody RequestCreateCaseDTO request) throws MalformedURLException {
        return this.caseBizagiService.createCaseString(request);
    }
}
