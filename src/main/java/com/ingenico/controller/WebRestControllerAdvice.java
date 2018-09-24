package com.ingenico.controller;

import com.ingenico.exception.TransferException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.OptimisticLockException;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler(TransferException.class)
    public String handleTransferException(TransferException ex, HttpServletResponse response) {
        try {
            ex.printStackTrace();;
            response.sendError(400); // Bad request
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex.getMessage();
    }

    @ExceptionHandler(OptimisticLockException.class)
    public String handleOptimisticLockException(OptimisticLockException ex, HttpServletResponse response) {
        try {
            ex.printStackTrace();
            response.sendError(409); // Conflict
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex.getMessage();
    }
}
