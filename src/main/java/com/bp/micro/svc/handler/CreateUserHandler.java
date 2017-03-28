package com.bp.micro.svc.handler;

import java.util.HashMap;
import java.util.Map;

import com.bp.micro.svc.teo.AdminUser;

public class CreateUserHandler {
  public String getQuery() {
    String query =
        "INSERT INTO BPDBS.BACKEND_USER "
            + "(USER_NUMBER, FULL_NAME, ADDRESS, PIN, PHONE, "
            + "EMAIL, DP_PATH, OCCUPATION, AUTH_LEVEL, HIGHEST_DEGREE) "
            + "VALUES(:USER_NUMBER, :FULL_NAME, :ADDRESS, :PIN, :PHONE, "
            + ":EMAIL, :DP_PATH, :OCCUPATION, :AUTH_LEVEL, :HIGHEST_DEGREE);";
    return query;
  }

  public Map<String, String> paramMap(AdminUser user) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("USER_NUMBER", user.getUserNumber());
    map.put("FULL_NAME", user.getFullName());
    map.put("ADDRESS", user.getAddress());
    map.put("PIN", user.getPin());
    map.put("PHONE", user.getContactNo());
    map.put("EMAIL", user.getEmail());
    map.put("DP_PATH", user.getDpPath());
    map.put("OCCUPATION", user.getOccupation());
    map.put("AUTH_LEVEL", user.getAuthLevel());
    map.put("HIGHEST_DEGREE", user.getHighestDegree());
    return map;
  }
}
