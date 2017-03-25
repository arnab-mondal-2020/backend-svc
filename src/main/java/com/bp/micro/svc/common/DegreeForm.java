package com.bp.micro.svc.common;

import java.io.Serializable;

public class DegreeForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String degreeId;
	private String degreename;
	public String getDegreeId() {
		return degreeId;
	}
	public void setDegreeId(String degreeId) {
		this.degreeId = degreeId;
	}
	public String getDegreename() {
		return degreename;
	}
	public void setDegreename(String degreename) {
		this.degreename = degreename;
	}
	
}
