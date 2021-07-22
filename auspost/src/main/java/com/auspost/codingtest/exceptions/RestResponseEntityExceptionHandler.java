package com.auspost.codingtest.exceptions;

import java.sql.SQLException;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auspost.codingtest.util.RestResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { LocationException.class })
    protected ResponseEntity<RestResponse<Object>> handleUnknownException(LocationException ex, WebRequest request) {
       

       return new ResponseEntity<RestResponse<Object>>(new RestResponse(Boolean.FALSE, ex.getMessage(), null), ex.getStatus());
     }
	
	@ExceptionHandler(value = { SQLGrammarException.class })
    protected ResponseEntity<RestResponse<Object>> handleSqlException(SQLGrammarException ex, WebRequest request) {
       

       return new ResponseEntity<RestResponse<Object>>(new RestResponse(Boolean.FALSE, ex.getSQLException().getMessage(), "Responsible: DAO Layer"), HttpStatus.INTERNAL_SERVER_ERROR);
     }
}
