package com.bp.micro.svc.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.Institute;

public class GetInstitutionHandler implements RowMapper<Institute>, DBConstants {
  private static final Logger logger = LoggerFactory.getLogger(GetInstitutionHandler.class);
  private static final String COLLEGE = "'C'";

  public String getSql(int minLimit, int maxLimit) {
    String sql =
        "SELECT *  FROM INSTITUTION ORDER BY INST_NM ASC LIMIT " + minLimit + "," + maxLimit;
    System.out.println("GetInstitutionHandler query >>> "+ sql);
    return sql;
  }

  public String getSqlForCollege() {
    String sql = SELECT + ALL + FROM + INSTITUTE_TBL + WHERE + INSTITUTE_LEVEL + EQUALS + COLLEGE + ORDER_BY
        + INSTITUTE_NAME + ASC;
    logger.info("GetInstitutionHandler query >>> {}", sql);
    System.out.println("GetInstitutionHandler query >>> " + sql);
    return sql;
  }

  @Override
  public Institute mapRow(ResultSet rs, int i) throws SQLException {
    Institute institute = new Institute(rs.getString(INSTITUTE_NAME));
    institute.setId(rs.getString(INSTITUTE_ID));
    institute.setLevel(rs.getString(INSTITUTE_LEVEL));
    logger.info("Name >>> {}", institute.getName());
    return institute;
  }

  public Map<String, String> paramMap() {
    return null;
  }
}
