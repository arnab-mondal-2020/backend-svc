package com.bp.micro.svc.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bp.micro.svc")
public class BackendSvcApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendSvcApplication.class, args);
  }
}
