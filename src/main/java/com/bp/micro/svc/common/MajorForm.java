package com.bp.micro.svc.common;

import java.io.Serializable;

public class MajorForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String majorId;
	private String majorname;
	private String degreeId;
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getMajorname() {
		return majorname;
	}
	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}
	public String getDegreeId() {
		return degreeId;
	}
	public void setDegreeId(String degreeId) {
		this.degreeId = degreeId;
	}
	
	
}
