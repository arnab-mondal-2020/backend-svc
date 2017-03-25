package com.bp.micro.svc.init;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
@ComponentScan
public class CommonDBConfig implements InitConstants {
  private static final Logger logger = LoggerFactory.getLogger(CommonDBConfig.class);
  @Value(DB_USER)
  private String username;
  @Value(DB_PASSWORD)
  private String password;
  @Value(DB_URL)
  private String url;

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
    logger.info("Creating database configuration>>>>>");
    System.out.println("Creating database configuration>>>>>");
    NamedParameterJdbcTemplate template = null;
    template = new NamedParameterJdbcTemplate(dataSource);
    return template;
  }

  @Bean
  public DataSource dataSource() {
    try {
      MysqlDataSource dataSource = new MysqlDataSource();
      dataSource.setUser(username);
      dataSource.setPassword(password);
      dataSource.setURL(url);
      return dataSource;
    } catch (Exception e) {
      System.err.println("Exception while creating datasource - [" + e.getMessage() + "]");
    }
    return null;
  }



}
