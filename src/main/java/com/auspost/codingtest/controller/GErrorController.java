package com.auspost.codingtest.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GErrorController implements ErrorController  {
 
    
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
        
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return new ResponseEntity<String>("<html><h1 align='center'>No Matching page found HTTP Status 404<h1/></html>", HttpStatus.NOT_FOUND);
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            	return new ResponseEntity<String>("error-500", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<String>("error-500", HttpStatus.SERVICE_UNAVAILABLE);
    }
}