package com.bp.micro.svc.common;

import java.io.Serializable;

public class InstituteForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String instituteName;
	private String instituteId;
	private String instituteLevel;
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}
	public String getInstituteLevel() {
		return instituteLevel;
	}
	public void setInstituteLevel(String instituteLevel) {
		this.instituteLevel = instituteLevel;
	}
}
