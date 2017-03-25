package com.bp.micro.svc.exception;

public class RecordAlreadyExistsException extends BackendException {
  private static final long serialVersionUID = 1L;

  public RecordAlreadyExistsException() {
    super();
  }

  public RecordAlreadyExistsException(String message) {
    super(message);
  }

  public RecordAlreadyExistsException(Throwable cause) {
    super(cause);
  }

  public RecordAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
