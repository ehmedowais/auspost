package com.auspost.codingtest.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//@RestController
public class ErrorController extends AbstractErrorController {
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @GetMapping("/error")
    public ResponseEntity<String> handleErrors()
    {
        String responseHtml = "<html><h1>There is an error in ur request</h1></html>";
        return new ResponseEntity<String>(responseHtml, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,true);
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }
    @Override
    public String getErrorPath() {
        return null;
    }
}
