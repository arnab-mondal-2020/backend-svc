package com.bp.micro.svc.handler;

import java.util.HashMap;
import java.util.Map;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.Institute;

public class AddInstituteHandler implements DBConstants {
  public String getQuery() {
    StringBuilder sb = new StringBuilder();
    sb.append(INSERT);
    sb.append(INSTITUTE_TBL);
    sb.append(OPEN_BRACE);
    sb.append(INSTITUTE_ID);
    sb.append(COMMA);
    sb.append(INSTITUTE_NAME);
    sb.append(COMMA);
    sb.append(INSTITUTE_LEVEL);
    sb.append(CLOSE_BRACE);
    sb.append(VALUES);
    sb.append(OPEN_BRACE);
    sb.append(PARAM + INSTITUTE_ID);
    sb.append(COMMA);
    sb.append(PARAM + INSTITUTE_NAME);
    sb.append(COMMA);
    sb.append(PARAM + INSTITUTE_LEVEL);
    sb.append(CLOSE_BRACE);

    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(new AddInstituteHandler().getQuery());
  }
  public Map<String, String> paramMap(Institute institute) {
    Map<String, String> map = new HashMap<String, String>();
    map.put(INSTITUTE_ID, institute.getId());
    map.put(INSTITUTE_NAME, institute.getName());
    map.put(INSTITUTE_LEVEL, institute.getLevel());

    return map;
  }
}
