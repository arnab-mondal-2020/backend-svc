package com.bp.micro.svc.init;

public interface InitConstants {
  String DB_USER = "${spring.datasource.username}";
  String DB_PASSWORD = "${spring.datasource.password}";
  String DB_URL = "${spring.datasource.url}";
}
