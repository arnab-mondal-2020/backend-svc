package com.bp.micro.svc.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.Degree;

public class GetDegreeHandler implements RowMapper<Degree>, DBConstants {
  public String getSql(int minLimit, int maxLimit) {
    String sql = "SELECT * FROM DEGREE ORDER BY DGRE_NM ASC LIMIT " + minLimit + "," + maxLimit;
    return sql;
  }

  @Override
  public Degree mapRow(ResultSet rs, int i) throws SQLException {
    Degree d = new Degree(rs.getString(DEGREE_ID), rs.getString(DEGREE_NAME));
    System.out.println("[degree from DB ] " + d);
    return d;
  }

  public static void main(String[] args) {
    System.out.println(new GetDegreeHandler().getSql(0, 0));
  }
}
