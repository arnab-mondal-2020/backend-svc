package com.bp.micro.svc.handler;

import java.util.HashMap;
import java.util.Map;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.Degree;

public class UpdateDegreeHandler implements DBConstants {
  public String getQuery() {
    StringBuilder sb = new StringBuilder();
    sb.append(UPDATE);
    sb.append(DEGREE_TBL);
    sb.append(SET);
    sb.append(DEGREE_NAME);
    sb.append(EQUALS);
    sb.append(PARAM);
    sb.append(DEGREE_NAME);
    sb.append(WHERE);
    sb.append(DEGREE_ID);
    sb.append(EQUALS);
    sb.append(PARAM + DEGREE_ID);
    System.out.println(sb.toString());
    return sb.toString();
  }

  public Map<String, String> paramMap(Degree degree) {
    Map<String, String> map = new HashMap<String, String>();
    map.put(DEGREE_ID, degree.getId());
    map.put(DEGREE_NAME, degree.getName());
    System.out.println("ParamMap >>>> " + map);
    return map;
  }
}
