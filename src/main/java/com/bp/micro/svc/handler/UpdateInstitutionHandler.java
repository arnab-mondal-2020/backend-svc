package com.bp.micro.svc.handler;

import java.util.HashMap;
import java.util.Map;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.Institute;

public class UpdateInstitutionHandler implements DBConstants {
  private static final String OLD_INSTITUTE_ID = "OLD_INSTITUTE_ID";
  public String getQuery(String[] fielSet) {
    StringBuilder sb = new StringBuilder();
    sb.append(UPDATE);
    sb.append(INSTITUTE_TBL);
    sb.append(SET);
    if (fielSet[0] != null && !fielSet[0].equals("NONE")) {
      sb.append(INSTITUTE_NAME);
      sb.append(EQUALS);
      sb.append(PARAM);
      sb.append(INSTITUTE_NAME);
      if (fielSet[1] != null) {
        sb.append(COMMA);
      }
    }
    if (fielSet[1] != null && !fielSet[1].equals("NONE")) {
      sb.append(INSTITUTE_LEVEL);
      sb.append(EQUALS);
      sb.append(PARAM);
      sb.append(INSTITUTE_LEVEL);
      sb.append(COMMA);
      sb.append(INSTITUTE_ID);
      sb.append(EQUALS);
      sb.append(PARAM);
      sb.append(INSTITUTE_ID);
    }
    sb.append(WHERE);
    sb.append(INSTITUTE_ID);
    sb.append(EQUALS);
    if (fielSet[1] != null && !fielSet[1].equals("NONE")) {
      sb.append(PARAM + OLD_INSTITUTE_ID);
    } else {
      sb.append(PARAM + INSTITUTE_ID);
    }
    System.out.println(sb.toString());
    return sb.toString();
  }

  public Map<String, String> paramMap(Institute institute) {
    Map<String, String> map = new HashMap<String, String>();
    map.put(INSTITUTE_ID, institute.getId());
    map.put(OLD_INSTITUTE_ID, institute.getOldId());
    map.put(INSTITUTE_NAME, institute.getName());
    map.put(INSTITUTE_LEVEL, institute.getLevel());
    System.out.println("ParamMap >>>> " + map);
    return map;
  }
}
