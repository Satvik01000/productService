package com.learning.springboot.productservice.advice;

import com.learning.springboot.productservice.DTOs.ErrorDTO;
import com.learning.springboot.productservice.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
//    //Deal with product not found exception
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException exception){
//        ErrorDTO errorDTO = new ErrorDTO();
//        errorDTO.setMessage(exception.getMessage());
//
//        return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.NOT_FOUND);
//    }
}
