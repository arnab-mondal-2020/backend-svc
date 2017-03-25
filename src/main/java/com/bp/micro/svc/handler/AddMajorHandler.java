package com.bp.micro.svc.handler;

import java.util.HashMap;
import java.util.Map;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.Major;

public class AddMajorHandler implements DBConstants {
  public String getQuery() {
    String query =
        "INSERT INTO BPDBS.MAJOR (MAJOR_ID, MAJOR_NM, DGRE_ID) VALUES(:MAJOR_ID, :MAJOR_NM, (SELECT DGRE_ID FROM BPDBS.DEGREE WHERE DGRE_ID=:DGRE_ID));";
    System.out.println("Quering table with [" + query + "]");

    return query;
  }

  public static void main(String[] args) {
    System.out.println(new AddInstituteHandler().getQuery());
  }

  public Map<String, String> paramMap(Major major) {
    Map<String, String> map = new HashMap<String, String>();
    map.put(MAJOR_ID, major.getId().toUpperCase());
    map.put(MAJOR_NAME, major.getName());
    map.put(DEGREE_ID, major.getDegreeId());

    return map;
  }
}
