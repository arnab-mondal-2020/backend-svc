package com.bp.micro.svc.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bp.micro.svc.constants.DBConstants;

public class CountHandler implements DBConstants, RowMapper<Integer> {

  public String getInstitutionCountQuery() {
    StringBuffer sb = new StringBuffer();
    sb.append(SELECT);
    sb.append(COUNT);
    sb.append(AS);
    sb.append(RECORD_COUNT);
    sb.append(SPACE);
    sb.append(FROM);
    sb.append(INSTITUTE_TBL);

    return sb.toString();
  }
  public String getDegreeCountQuery() {
    StringBuffer sb = new StringBuffer();
    sb.append(SELECT);
    sb.append(COUNT);
    sb.append(AS);
    sb.append(RECORD_COUNT);
    sb.append(SPACE);
    sb.append(FROM);
    sb.append(DEGREE_TBL);

    return sb.toString();
  }
  public String getMajorCountQuery() {
    StringBuffer sb = new StringBuffer();
    sb.append(SELECT);
    sb.append(COUNT);
    sb.append(AS);
    sb.append(RECORD_COUNT);
    sb.append(SPACE);
    sb.append(FROM);
    sb.append(MAJOR_TBL);

    return sb.toString();
  }

  @Override
  public Integer mapRow(ResultSet rs, int rownum) throws SQLException {
    int rowCount = rs.getInt(1);
    return rowCount;
  }
}
