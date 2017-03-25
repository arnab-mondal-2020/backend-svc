package com.bp.micro.svc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bp.micro.svc.exception.ExceptionHandlerInterface;
import com.bp.micro.svc.teo.ErrorMessage;

@Component
public class BaseController {
  @Autowired
  private ExceptionHandlerInterface exceptionHandlerInterface;

  @ExceptionHandler
  public ErrorMessage handleException(Exception e) {
    ErrorMessage message = new ErrorMessage();
    message.setExceptionId(exceptionHandlerInterface.getId(e));
    message.setExceptionMessage(e.getMessage());
    message.setType(e.getClass().getName());
    message.setTime(new Date().toString());
    return message;
  }
}
