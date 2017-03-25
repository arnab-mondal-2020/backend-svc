package com.bp.micro.svc.exception;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

@Component
public class HandleException implements ExceptionHandlerInterface {

  private static final String DEFAULT_CODE = "EXD9900";
  @Override
  public String getId(Exception e) {
    return exceptionMap.get(e.getClass().getSimpleName()) == null ? DEFAULT_CODE
        : exceptionMap.get(e.getClass().getSimpleName());
  }

  private static Map<String, String> exceptionMap;
  static{
    exceptionMap = new HashMap<>();
    exceptionMap.put(Exception.class.getSimpleName(), "EXR9901");
    exceptionMap.put(NullPointerException.class.getSimpleName(), "EXR9902");
    exceptionMap.put(ArrayIndexOutOfBoundsException.class.getSimpleName(), "EXR9903");
    exceptionMap.put(SQLException.class.getSimpleName(), "EXS9600");
    exceptionMap.put(CommunicationsException.class.getSimpleName(), "EXR9904");
    exceptionMap.put(BackendException.class.getSimpleName(), "EXB9950");
    exceptionMap.put(RecordAlreadyExistsException.class.getSimpleName(), "EXR9880");
  }

}
