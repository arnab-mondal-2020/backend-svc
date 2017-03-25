package com.bp.micro.svc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bp.micro.svc.bo.BackEndBO;
import com.bp.micro.svc.common.BackendUtil;
import com.bp.micro.svc.constants.BackendConstants;
import com.bp.micro.svc.constants.RequestMappings;
import com.bp.micro.svc.exception.BackendException;
import com.bp.micro.svc.teo.Institute;
import com.bp.micro.svc.teo.InstituteList;

@RestController
public class InstitutionController extends BaseController
implements BackendConstants, RequestMappings {
  private static List<Institute> INSTITUTES = new ArrayList<Institute>();
  @Autowired
  private BackEndBO backEndBO;

  @RequestMapping(value = REQUEST_ADD_INSTITUTE, method = RequestMethod.POST)
  public Institute addInstitution(@RequestBody Institute institute) throws BackendException {
    if (StringUtils.isEmpty(institute.getName())
        || StringUtils.isEmpty(institute.getLevel())) {
      throw new BackendException(NOTHING_TO_ADD_MSG);
    }
    try {
      institute = backEndBO.addInstitution(institute);
    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw e;
    }
    return institute;
  }

  @RequestMapping(value = REQUEST_EDIT_INSTITUTE, method = RequestMethod.GET)
  public Institute editInstitution(@PathVariable String id) throws BackendException {
    try {
      INSTITUTES = backEndBO.getAllInstitutes();

      Institute institute = new Institute(null);
      institute.setId(id);
      institute =
          INSTITUTES.contains(institute) ? INSTITUTES.get(INSTITUTES.indexOf(institute)) : null;
          return institute;
    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw new BackendException(NOTHING_FOUND);
    }
  }

  @RequestMapping(value = SUBMIT_EDIT_INSTITUTE, method = RequestMethod.POST)
  public Institute submitEditInstitution(@RequestBody Institute institute) throws BackendException {
    Institute existingInstitute =
        INSTITUTES.contains(institute) ? INSTITUTES.get(INSTITUTES.indexOf(institute)) : null;
        System.out.println("existingInstitute >>> " + existingInstitute);
        System.out.println("newInstitute >>> " + institute);
        if (existingInstitute == null) {
          throw new BackendException(NOTHING_FOUND);
        }
        String[] fieldSet = new String[3];
        if (existingInstitute != null) {
          if (!StringUtils.isEmpty(institute.getName())
              && !institute.getName().equals(existingInstitute.getName())) {
            System.out.println("Name updated>>>");
            fieldSet[0] = "INST_NM";
            fieldSet[1] = "NONE";
            fieldSet[2] = "NONE";
          }
          if (!StringUtils.isEmpty(institute.getLevel())
              && !institute.getLevel().equals(existingInstitute.getLevel())) {
            System.out.println("Level updated>>>");
            fieldSet[0] = fieldSet[0] == null ? "NONE" : fieldSet[0];
            fieldSet[1] = "INST_LVL";
            fieldSet[2] = "INST_ID";
          }
        }
        if (StringUtils.isEmpty(institute.getName()) || StringUtils.isEmpty(institute.getLevel())) {
          throw new BackendException(NOTHING_TO_ADD_MSG);
        }
        try {
          institute = backEndBO.updateInstitution(institute, fieldSet);
        } catch (BackendException e) {
          System.err.println(e.getMessage());
          throw e;
        }
        return institute;
  }

  @RequestMapping(value = REQUEST_SHOW_ALL_INSTITUTE, method = RequestMethod.POST)
  public InstituteList showAllInstitutes(@RequestBody InstituteList instituteList)
      throws BackendException {
    String pageNoStr = instituteList.getPageNoStr();
    String recordCountStr = instituteList.getRecordCountStr();
    int minLimit = 1;
    int maxLimit = 5;
    int pageNo = 1;
    try {
      int noOfRecordPerPage = 5;
      if (!StringUtils.isEmpty(recordCountStr)) {
        noOfRecordPerPage = Integer.parseInt(recordCountStr);
        if (noOfRecordPerPage < 5 || noOfRecordPerPage > 12) {
          noOfRecordPerPage = 5;
        }
      }
      if (!StringUtils.isEmpty(pageNoStr)) {
        pageNo = Integer.parseInt(pageNoStr);
      }
      int instituteCount = backEndBO.getTotalInstitutionCount();

      int recordSet = BackendUtil.populateRecordSet(instituteCount, noOfRecordPerPage);

      minLimit = (noOfRecordPerPage * (pageNo - 1));
      maxLimit = noOfRecordPerPage;

      System.out.println("minLimit >>> " + minLimit);
      System.out.println("maxLimit >>> " + maxLimit);
      int toRecord = maxLimit * pageNo > instituteCount ? instituteCount : maxLimit * pageNo;
      instituteList.setFromReord(minLimit + 1);
      instituteList.setToRecord(toRecord);
      instituteList.setToRecord(instituteCount);

      instituteList.setRecordSet(recordSet);
      instituteList.setRecordPerPage(noOfRecordPerPage);
      instituteList.setPageNo(pageNo);
      List<Institute> institutes = backEndBO.getAllInstitutes(minLimit, noOfRecordPerPage);
      instituteList.setInstitutes(institutes);
      return instituteList;
    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw e;
    }
  }
}
