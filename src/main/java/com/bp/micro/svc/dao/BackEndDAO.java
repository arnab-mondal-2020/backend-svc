package com.bp.micro.svc.dao;

import java.util.List;

import com.bp.micro.svc.exception.BackendException;
import com.bp.micro.svc.teo.AdminUser;
import com.bp.micro.svc.teo.Degree;
import com.bp.micro.svc.teo.Institute;
import com.bp.micro.svc.teo.Major;

public interface BackEndDAO {
  public int addDegree(Degree degree) throws BackendException;

  public int addMajor(Major major) throws BackendException;

  public int addInstitution(Institute institute) throws BackendException;

  public int updateInstitution(Institute institute, String[] fieldSet)
      throws BackendException;

  public int updateDegree(Degree degree) throws BackendException;

  public int updateMajor(Major major, String[] fieldSet) throws BackendException;

  public List<Institute> getInstitutes(int minLimit, int maxLimit) throws BackendException;

  public List<Institute> getInstitutes() throws BackendException;

  public List<Institute> getColleges() throws BackendException;

  public List<Degree> getDegrees(int minLimit, int maxLimit) throws BackendException;

  public List<Degree> getDegrees() throws BackendException;

  public List<Major> getMajors(Degree degree) throws BackendException;

  public List<Major> getMajors(Degree degree, int minLimit, int maxLimit) throws BackendException;

  public List<Major> getAllMajors() throws BackendException;

  public List<Major> getAllMajors(int minLimit, int maxLimit) throws BackendException;


  public int getInstitutionCount() throws BackendException;

  public int getDegreeCount() throws BackendException;

  public int getMajorCount() throws BackendException;

  public AdminUser getUserInfoByNumber(String number) throws BackendException;
}
