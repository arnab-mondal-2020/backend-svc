package com.bp.micro.svc.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bp.micro.svc.constants.BackendConstants;
import com.bp.micro.svc.dao.BackEndDAO;
import com.bp.micro.svc.exception.BackendException;
import com.bp.micro.svc.exception.RecordAlreadyExistsException;
import com.bp.micro.svc.teo.AdminUser;
import com.bp.micro.svc.teo.Degree;
import com.bp.micro.svc.teo.Institute;
import com.bp.micro.svc.teo.Major;

@Component
public class BackEndBO implements BackendConstants {
  @Autowired
  private BackEndDAO backEndDAO;
  private static List<Institute> institutes = null;
  private static List<Degree> degrees = null;
  private static List<Major> majors = null;

  public Degree addDegree(Degree degree) throws BackendException {
    degrees = backEndDAO.getDegrees();
    for (Degree d : degrees) {
      if (d.compareTo(degree) == 0) {
        throw new RecordAlreadyExistsException(ALREADY_EXISTS_MSG);
      }
    }
    int addedRow = backEndDAO.addDegree(degree);
    if (addedRow == 1) {
      degrees.add(degree);
      return degree;
    }
    return null;
  }

  public Degree updateDegree(Degree degree) throws BackendException {
    degrees = backEndDAO.getDegrees();
    for (Degree d : degrees) {
      if (d.compareTo(degree) == 0 && !d.getId().equals(degree.getId())) {
        return new Degree(BackendConstants.ALREADY_EXISTS, d.getName());
      }
    }
    int addedRow = backEndDAO.updateDegree(degree);
    if (addedRow == 1) {
      degrees.remove(degrees.indexOf(degree));
      degrees.add(degree);
      return degree;
    }
    return null;
  }

  public List<Degree> getAllDegrees() throws BackendException {
    return backEndDAO.getDegrees();
  }

  public List<Degree> getAllDegrees(int minLimit, int maxLimit) throws BackendException {
    return backEndDAO.getDegrees(minLimit, maxLimit);
  }

  public int getTotalDegreeCount() throws BackendException {
    return backEndDAO.getDegreeCount();
  }

  public Major addMajor(Major major) throws BackendException {
    majors = backEndDAO.getAllMajors();
    for (Major m : majors) {
      if (m.compareTo(major) == 0) {
        throw new RecordAlreadyExistsException(ALREADY_EXISTS_MSG);
      }
    }
    int addedRow = backEndDAO.addMajor(major);
    if (addedRow == 1) {
      majors.add(major);
      return major;
    }
    return null;
  }

  public Major updateMajor(Major major, String[] fieldSet) throws BackendException {
    int rowCount = getTotalMajorCount();
    majors = backEndDAO.getAllMajors(0, rowCount);
    for (Major i : majors) {
      if (i.getName().equalsIgnoreCase(major.getName()) && !major.getId().equals(i.getId())) {
        major = new Major(BackendConstants.ALREADY_EXISTS, null);
        return major;
      }
    }
    for (String s : fieldSet) {
      System.out.println("fieldSet >>> " + s);
    }

    int updatedRow = backEndDAO.updateMajor(major, fieldSet);
    if (updatedRow == 1) {
      majors.remove(majors.indexOf(major));
      majors.add(major);
      return major;
    }
    return null;
  }

  public List<Major> getAllMajors() throws BackendException {
    return backEndDAO.getAllMajors();
  }

  public List<Major> getAllMajors(int minLimit, int maxLimit) throws BackendException {
    return backEndDAO.getAllMajors(minLimit, maxLimit);
  }

  public List<Major> getMajors(String degreeId) throws BackendException {
    Degree degree = new Degree(degreeId, null);
    return backEndDAO.getMajors(degree);
  }

  public List<Major> getMajors(String degreeId, int minLimit, int maxLimit)
      throws BackendException {
    Degree degree = new Degree(degreeId, null);
    return backEndDAO.getMajors(degree, minLimit, maxLimit);
  }

  public int getTotalMajorCount() throws BackendException {
    return backEndDAO.getMajorCount();
  }

  public Institute addInstitution(Institute institute) throws BackendException {
    int rowCount = getTotalInstitutionCount();
    institutes = backEndDAO.getInstitutes(0, rowCount);
    for (Institute i : institutes) {
      if (i.getName().equalsIgnoreCase(institute.getName())) {
        throw new RecordAlreadyExistsException(ALREADY_EXISTS_MSG);
      }
    }
    String instituteId = buildInstituteId(institute.getLevel());
    institute.setId(instituteId);
    int addedRow = backEndDAO.addInstitution(institute);
    if (addedRow == 1) {
      if(institutes.contains(institute)) {
        institutes.remove(institutes.indexOf(institute));
      }
      institutes.add(institute);
      return institute;
    }
    return null;
  }

  public Institute updateInstitution(Institute institute, String[] fieldSet)
      throws BackendException {
    int rowCount = getTotalInstitutionCount();
    institutes = backEndDAO.getInstitutes(0, rowCount);
    for (Institute i : institutes) {
      if (i.getName().equalsIgnoreCase(institute.getName())
          && !institute.getId().equals(i.getId())) {
        institute.setId(BackendConstants.ALREADY_EXISTS);
        return institute;
      }
    }
    for (String s : fieldSet) {
      System.out.println("fieldSet >>> " + s);
    }
    if (fieldSet[2] != null && fieldSet[2].equals("INST_ID")) {
      institute.setOldId(institute.getId());
      System.out.println("Updating ID");
      String instituteId = buildInstituteId(institute.getLevel());
      System.out.println("instituteId >>>> " + instituteId);
      institute.setId(instituteId);
    }
    int updatedRow = backEndDAO.updateInstitution(institute, fieldSet);
    if (updatedRow == 1) {
      institutes.add(institute);
      return institute;
    }
    return null;
  }

  public List<Institute> getAllInstitutes(int minLimit, int maxLimit)
      throws BackendException {
    return backEndDAO.getInstitutes(minLimit, maxLimit);
  }

  public List<Institute> getAllInstitutes() throws BackendException {
    return backEndDAO.getInstitutes();
  }

  public int getTotalInstitutionCount() throws BackendException {
    return backEndDAO.getInstitutionCount();
  }

  public List<Institute> getInstitutions() throws BackendException {
    return backEndDAO.getInstitutes();
  }

  public List<Institute> getColleges() throws BackendException {
    return backEndDAO.getColleges();
  }

  public AdminUser getUserInfoByNumber(String userNumber) throws BackendException {
    return backEndDAO.getUserInfoByNumber(userNumber);
  }

  private String buildInstituteId(String aLevel) {
    String instituteId = "INS" + aLevel;
    String lastIndex = "0001";
    List<Integer> num = new ArrayList<Integer>();
    for (Institute in : institutes) {
      String id = in.getId();
      System.out.println("id>>>>" + in.getId());
      String level = in.getLevel();
      if (level.equals(aLevel)) {
        num.add(new Integer(id.replaceAll(instituteId, "")));
      }
    }
    if (num != null) {
      Collections.sort(num);
      int last = !num.isEmpty() ? num.get(num.size() - 1) : 0;
      last++;
      lastIndex = ("0000" + last);
      lastIndex = lastIndex.substring(lastIndex.length() - 4, lastIndex.length());
    }
    System.out.println(num);
    instituteId = instituteId + lastIndex;
    return instituteId;
  }
}
