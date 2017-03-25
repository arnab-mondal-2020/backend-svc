package com.bp.micro.svc.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bp.micro.svc.common.CountHandler;
import com.bp.micro.svc.exception.BackendException;
import com.bp.micro.svc.handler.AddDegreeHandler;
import com.bp.micro.svc.handler.AddInstituteHandler;
import com.bp.micro.svc.handler.AddMajorHandler;
import com.bp.micro.svc.handler.GetDegreeHandler;
import com.bp.micro.svc.handler.GetInstitutionHandler;
import com.bp.micro.svc.handler.GetMajorHandler;
import com.bp.micro.svc.handler.GetUserHandler;
import com.bp.micro.svc.handler.UpdateDegreeHandler;
import com.bp.micro.svc.handler.UpdateInstitutionHandler;
import com.bp.micro.svc.handler.UpdateMajorHandler;
import com.bp.micro.svc.teo.AdminUser;
import com.bp.micro.svc.teo.Degree;
import com.bp.micro.svc.teo.Institute;
import com.bp.micro.svc.teo.Major;

@Repository
public class BackEndDAOImpl implements BackEndDAO {
  private static final Logger logger = LoggerFactory.getLogger(BackEndDAOImpl.class);

  @Autowired
  private NamedParameterJdbcTemplate template;
  @Override
  public int addDegree(Degree degree) throws BackendException {
    AddDegreeHandler handler = new AddDegreeHandler();
    try {
      int updated = template.update(handler.getQuery(), handler.paramMap(degree));
      return updated;
    } catch(Exception e) {
      throw new BackendException(e.getMessage());
    }
  }

  @Override
  public int addMajor(Major major) throws BackendException {
    AddMajorHandler handler = new AddMajorHandler();
    try {
      int updated = template.update(handler.getQuery(), handler.paramMap(major));
      return updated;
    } catch(Exception e) {
      throw new BackendException(e.getMessage());
    }
  }

  @Override
  public int addInstitution(Institute institute) throws BackendException {
    AddInstituteHandler handler = new AddInstituteHandler();
    try{
      int updated = template.update(handler.getQuery(), handler.paramMap(institute));
      return updated;
    } catch(Exception e) {
      throw new BackendException(e.getMessage());
    }
  }

  @Override
  public int updateInstitution(Institute institute, String[] fieldSet) throws BackendException {
    UpdateInstitutionHandler handler = new UpdateInstitutionHandler();
    try{
      int updated = template.update(handler.getQuery(fieldSet), handler.paramMap(institute));
      System.out.println("updated row = " + updated);
      return updated;
    } catch(Exception e) {
      throw new BackendException(e.getMessage());
    }
  }

  @Override
  public int updateDegree(Degree degree) throws BackendException {
    UpdateDegreeHandler handler = new UpdateDegreeHandler();
    try{
      int updated = template.update(handler.getQuery(), handler.paramMap(degree));
      System.out.println("updated row = " + updated);
      return updated;
    } catch(Exception e) {
      throw new BackendException(e.getMessage());
    }
  }

  @Override
  public int updateMajor(Major major, String[] fieldSet) throws BackendException {
    UpdateMajorHandler handler = new UpdateMajorHandler();
    try {
      int updated = template.update(handler.getQuery(fieldSet), handler.paramMap(major));
      System.out.println("updated row = " + updated);
      return updated;
    } catch (Exception e) {
      throw new BackendException(e.getMessage());
    }
  }

  @Override
  public List<Institute> getInstitutes(int minLimit, int maxLimit) throws BackendException {
    GetInstitutionHandler handler = new GetInstitutionHandler();
    List<Institute> institutes = null;
    try {
      institutes = template.query(handler.getSql(minLimit, maxLimit), handler);
      institutes = institutes == null ? new ArrayList<>() : institutes;
    } catch (DataAccessException e) {
      throw new BackendException();
    } catch (Exception e) {
      throw new BackendException();
    }
    return institutes;
  }

  @Override
  public List<Institute> getInstitutes() throws BackendException {
    int minLimit = 0;
    int maxLimit = getInstitutionCount();
    return getInstitutes(minLimit, maxLimit);
  }

  @Override
  public List<Degree> getDegrees(int minLimit, int maxLimit) throws BackendException {
    GetDegreeHandler handler = new GetDegreeHandler();
    List<Degree> degrees = null;
    try {
      degrees = template.query(handler.getSql(minLimit, maxLimit), handler);
      degrees = degrees == null ? new ArrayList<>() : degrees;
    } catch (DataAccessException e) {
      throw new BackendException();
    } catch (Exception e) {
      throw new BackendException();
    }
    return degrees;
  }

  @Override
  public List<Degree> getDegrees() throws BackendException {
    int minLimit = 0;
    int maxLimit = getDegreeCount();
    System.out.println("MinLimit : " + minLimit + " MaxLimit : " + maxLimit);
    return getDegrees(minLimit, maxLimit);
  }

  @Override
  public List<Major> getMajors(Degree degree) throws BackendException {
    new GetMajorHandler();
    int minLimit = 0;
    int maxLimit = getMajorCount();
    return getMajors(degree, minLimit, maxLimit);
  }

  @Override
  public List<Major> getMajors(Degree degree, int minLimit, int maxLimit)
      throws BackendException {
    GetMajorHandler handler = new GetMajorHandler();
    List<Major> majors = null;
    try {
      majors = template.query(handler.getSql(minLimit, maxLimit),
          handler.getParamMap(degree), handler);
      majors = majors == null ? new ArrayList<>() : majors;
    } catch (DataAccessException e) {
      logger.error("DataAccessException while getting majors >>>>>>>{}", e);
      throw new BackendException(e.getMessage());
    } catch (Exception e) {
      logger.error("Exception while getting majors >>>>>>>{}", e);
      throw new BackendException(e.getMessage());
    }
    return majors;
  }

  @Override
  public List<Major> getAllMajors() throws BackendException {
    int minLimit = 0;
    int maxLimit = getMajorCount();
    System.out.println("MinLimit : " + minLimit + " MaxLimit : " + maxLimit);
    return getAllMajors(minLimit, maxLimit);
  }

  @Override
  public List<Major> getAllMajors(int minLimit, int maxLimit) throws BackendException {
    GetMajorHandler handler = new GetMajorHandler();
    List<Major> majors = null;
    try {
      majors = template.query(handler.getAllSql(minLimit, maxLimit), handler);
    } catch (DataAccessException e) {
      logger.error("DataAccessException while getting majors >>>>>>>{}", e);
      throw new BackendException(e.getMessage());
    } catch (Exception e) {
      logger.error("Exception while getting majors >>>>>>>{}", e);
      throw new BackendException(e.getMessage());
    }
    return majors;
  }

  @Override
  public int getInstitutionCount() throws BackendException {
    CountHandler handler = new CountHandler();
    List<Integer> counts = null;
    try {
      counts = template.query(handler.getInstitutionCountQuery(), handler);
    } catch (DataAccessException e) {
      throw new BackendException(e.getMessage());
    }
    return counts.get(0);
  }

  @Override
  public int getDegreeCount() throws BackendException {
    CountHandler handler = new CountHandler();
    List<Integer> counts = null;
    try {
      counts = template.query(handler.getDegreeCountQuery(), handler);
    } catch (DataAccessException e) {
      throw new BackendException(e.getMessage());
    }
    return counts.get(0);
  }

  @Override
  public int getMajorCount() throws BackendException {
    CountHandler handler = new CountHandler();
    List<Integer> counts = null;
    try {
      counts = template.query(handler.getMajorCountQuery(), handler);
    } catch (DataAccessException e) {
      throw new BackendException(e.getMessage());
    }
    return counts.get(0);
  }


  @Override
  public List<Institute> getColleges() throws BackendException {
    GetInstitutionHandler handler = new GetInstitutionHandler();
    List<Institute> institutes = null;
    try {
      institutes = template.query(handler.getSqlForCollege(), handler);
    } catch (DataAccessException e) {
      throw new BackendException(e.getMessage());
    } catch (Exception e) {
      throw new BackendException(e.getMessage());
    }
    return institutes;
  }

  @Override
  public AdminUser getUserInfoByNumber(String number) throws BackendException {
    System.out.println("getUserInfoByNumber >> " + number);
    GetUserHandler handler = new GetUserHandler();
    List<AdminUser> result = null;
    try {
      result = template.query(handler.getQuery(), handler.getParamMap(number),
          handler);
    } catch (DataAccessException e) {
      throw new BackendException(e.getMessage());
    } catch (Exception e) {
      throw new BackendException(e.getMessage());
    }
    return result.get(0);
  }
}
