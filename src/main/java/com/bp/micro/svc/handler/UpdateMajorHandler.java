package com.bp.micro.svc.handler;

import java.util.HashMap;
import java.util.Map;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.Major;

public class UpdateMajorHandler implements DBConstants {
  public String getQuery(String[] fielSet) {
    StringBuilder sb = new StringBuilder();
    sb.append(UPDATE);
    sb.append(MAJOR_TBL);
    sb.append(SET);
    if (fielSet[0] != null) {
      sb.append(MAJOR_NAME);
      sb.append(EQUALS);
      sb.append(PARAM);
      sb.append(MAJOR_NAME);
      if (fielSet[1] != null) {
        sb.append(COMMA);
      }
    }
    if (fielSet[1] != null) {
      sb.append(DEGREE_ID);
      sb.append(EQUALS);
      sb.append(PARAM);
      sb.append(DEGREE_ID);
    }
    sb.append(WHERE);
    sb.append(MAJOR_ID);
    sb.append(EQUALS);
    sb.append(PARAM + MAJOR_ID);
    System.out.println(sb.toString());
    return sb.toString();
  }

  public Map<String, String> paramMap(Major major) {
    Map<String, String> map = new HashMap<String, String>();
    map.put(MAJOR_ID, major.getId());
    map.put(MAJOR_NAME, major.getName());
    map.put(DEGREE_ID, major.getDegreeId());
    System.out.println("ParamMap >>>> " + map);
    return map;
  }
}
