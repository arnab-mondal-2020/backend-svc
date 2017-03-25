package com.bp.micro.svc.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.AdminUser;

public class GetUserHandler implements RowMapper<AdminUser>, DBConstants {
  public String getQuery() {
    StringBuilder sb = new StringBuilder();
    sb.append(SELECT);
    sb.append(USER_NUMBER);
    sb.append(COMMA);
    sb.append(FULLNAME);
    sb.append(COMMA);
    sb.append(ADDRESS);
    sb.append(COMMA);
    sb.append(PIN);
    sb.append(COMMA);
    sb.append(PHONE);
    sb.append(COMMA);
    sb.append(EMAIL);
    sb.append(COMMA);
    sb.append(DP_PATH);
    sb.append(COMMA);
    sb.append(OCCUPATION);
    sb.append(COMMA);
    sb.append(HIGHESTDEGREE);
    sb.append(COMMA);
    sb.append(AUTHLEVEL);
    sb.append(FROM);
    sb.append(BACKEND_USER_TBL);
    sb.append(WHERE);
    sb.append(USER_NUMBER);
    sb.append(EQUALS);
    sb.append(PARAM);
    sb.append(USER_NUMBER);
    System.out.println("getuser query " + sb.toString());
    return sb.toString();
  }

  public Map<String, String> getParamMap(String number) {
    Map<String, String> map = new HashMap<String, String>();
    map.put(USER_NUMBER, number);

    return map;
  }
  @Override
  public AdminUser mapRow(ResultSet rs, int arg1) throws SQLException {
    AdminUser user = new AdminUser();
    user.setAddress(rs.getString(ADDRESS));
    user.setAuthLevel(rs.getString(AUTHLEVEL));
    user.setContactNo(rs.getString(PHONE));
    user.setDpPath(rs.getString(DP_PATH));
    user.setEmail(rs.getString(EMAIL));
    user.setFullName(rs.getString(FULLNAME));
    user.setHighestDegree(rs.getString(HIGHESTDEGREE));
    user.setOccupation(rs.getString(OCCUPATION));
    user.setPin(rs.getString(PIN));
    user.setUserNumber(rs.getString(USER_NUMBER));
    return user;
  }

}
