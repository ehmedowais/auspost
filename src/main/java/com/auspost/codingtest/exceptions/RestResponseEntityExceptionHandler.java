package com.auspost.codingtest.exceptions;

import com.auspost.codingtest.util.RequestCorrelation;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auspost.codingtest.util.RestResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	@ExceptionHandler(value = { LocationException.class })
    protected ResponseEntity<RestResponse<Object>> handleUnknownException(LocationException ex, WebRequest request) {
       String exceptionMsg = ex.getMessage() + " HttpStatus: "+ ex.getStatus();
        RequestCorrelation.logError(LOGGER, exceptionMsg);
       return new ResponseEntity<RestResponse<Object>>(
               new RestResponse(Boolean.FALSE, ex.getMessage(), null), ex.getStatus());
     }
	
	@ExceptionHandler(value = { SQLGrammarException.class })
    protected ResponseEntity<RestResponse<Object>> handleSqlException(SQLGrammarException ex, WebRequest request) {
       

       return new ResponseEntity<RestResponse<Object>>(new RestResponse(Boolean.FALSE, ex.getSQLException().getMessage(), "Responsible: DAO Layer"), HttpStatus.INTERNAL_SERVER_ERROR);
     }
     @ExceptionHandler(value ={InvalidDataAccessApiUsageException.class})
     protected ResponseEntity<RestResponse<Object>> dataAccessException(InvalidDataAccessApiUsageException ex, WebRequest request) {
         return new ResponseEntity<RestResponse<Object>>(new RestResponse(Boolean.FALSE, ex.getMessage(), "Responsible: DAO Layer"), HttpStatus.INTERNAL_SERVER_ERROR);
     }
}
