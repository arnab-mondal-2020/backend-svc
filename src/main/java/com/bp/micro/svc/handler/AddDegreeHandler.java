package com.bp.micro.svc.handler;

import java.util.HashMap;
import java.util.Map;

import com.bp.micro.svc.constants.DBConstants;
import com.bp.micro.svc.teo.Degree;

public class AddDegreeHandler implements DBConstants {
	public String getQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append(INSERT);
		sb.append(DEGREE_TBL);
		sb.append(OPEN_BRACE);
		sb.append(DEGREE_ID);
		sb.append(COMMA);
		sb.append(DEGREE_NAME);
		sb.append(CLOSE_BRACE);
		sb.append(VALUES);
		sb.append(OPEN_BRACE);
		sb.append(PARAM + DEGREE_ID);
		sb.append(COMMA);
		sb.append(PARAM + DEGREE_NAME);
		sb.append(CLOSE_BRACE);
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new AddInstituteHandler().getQuery());
	}
	public Map<String, String> paramMap(Degree degree) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(DEGREE_ID, degree.getId().toUpperCase());
		map.put(DEGREE_NAME, degree.getName());

		return map;
	}
}
