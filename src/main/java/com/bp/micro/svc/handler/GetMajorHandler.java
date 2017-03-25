package com.bp.micro.svc.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.Degree;
import com.bp.micro.svc.teo.Major;

public class GetMajorHandler implements RowMapper<Major>, DBConstants {
  public static final Logger logger = LoggerFactory.getLogger(GetMajorHandler.class);
  private static final String PS_DEGREE_ID = "degreeId";

  public String getSql(int minLimit, int maxLimit) {
    String sql =
        "SELECT MAJOR_ID, MAJOR_NM, DGRE_ID FROM MAJOR WHERE DGRE_ID = :degreeId ORDER BY MAJOR_NM ASC LIMIT "
            + minLimit + "," + maxLimit;
    System.out.println("GetMajorHandler.getSql >> " + sql);
    return sql;
  }
  @Override
  public Major mapRow(ResultSet rs, int arg1) throws SQLException {
    Major major = new Major(rs.getString(MAJOR_ID), rs.getString(MAJOR_NAME));
    major.setDegreeId(rs.getString(DEGREE_ID));
    logger.info("major.Id >>>>> {}", major.getId());
    logger.info("major.name >>>>> {}", major.getName());
    System.out.println(major);
    return major;
  }

  public Map<String, String> getParamMap(Degree degree){
    Map<String, String> map = new HashMap<String, String>();
    map.put(PS_DEGREE_ID, degree.getId());

    return map;
  }
  public String getAllSql(int minLimit, int maxLimit) {
    String sql = "SELECT * FROM MAJOR ORDER BY MAJOR_NM ASC LIMIT " + minLimit + "," + maxLimit;
    return sql;
  }

  public static void main(String[] args) {
    System.out.println(new GetMajorHandler().getSql(0, 0));
  }

}
