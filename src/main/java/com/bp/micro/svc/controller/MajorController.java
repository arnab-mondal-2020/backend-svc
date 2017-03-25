package com.bp.micro.svc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bp.micro.svc.bo.BackEndBO;
import com.bp.micro.svc.common.BackendUtil;
import com.bp.micro.svc.constants.BackendConstants;
import com.bp.micro.svc.constants.RequestMappings;
import com.bp.micro.svc.exception.BackendException;
import com.bp.micro.svc.teo.Major;
import com.bp.micro.svc.teo.MajorList;

@RestController
public class MajorController extends BaseController implements BackendConstants, RequestMappings {

  @Autowired
  private BackEndBO backEndBO;
  private static List<Major> MAJORS = new ArrayList<Major>();

  @RequestMapping(value = REQUEST_ADD_MAJOR, method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Major addMajor(@RequestBody Major major)
      throws BackendException {
    if (StringUtils.isEmpty(major.getName()) || StringUtils.isEmpty(major.getId())) {
      throw new BackendException(NOTHING_TO_ADD_MSG);
    }
    try {
      major = backEndBO.addMajor(major);
    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw e;
    }
    return major;
  }

  @RequestMapping(value = REQUEST_SHOW_MAJOR, method = RequestMethod.POST)
  public MajorList showAllMajors(@RequestBody MajorList majorList) throws BackendException {
    String recordCountStr = majorList.getRecordCountStr();
    String pageNoStr = majorList.getPageNoStr();
    Major major = majorList.getMajors().get(0);

    int minLimit = 1;
    int maxLimit = 5;
    int pageNo = 1;
    List<Major> majors = null;
    try {
      int noOfRecordPerPage = 5;
      if (!StringUtils.isEmpty(recordCountStr)) {
        if (recordCountStr.matches("\\d+")) {
          noOfRecordPerPage = Integer.parseInt(recordCountStr);
        } else {
          noOfRecordPerPage = 5;
        }
        if (noOfRecordPerPage < 5 || noOfRecordPerPage > 12) {
          noOfRecordPerPage = 5;
        }
      }
      if (!StringUtils.isEmpty(pageNoStr)) {
        pageNo = Integer.parseInt(pageNoStr);
      }
      int majorCount = backEndBO.getTotalMajorCount();

      int recordSet = BackendUtil.populateRecordSet(majorCount, noOfRecordPerPage);

      minLimit = (noOfRecordPerPage * (pageNo - 1));
      maxLimit = noOfRecordPerPage;

      System.out.println("minLimit >>> " + minLimit);
      System.out.println("maxLimit >>> " + maxLimit);
      int toRecord = maxLimit * pageNo > majorCount ? majorCount : maxLimit * pageNo;
      majorList.setFromReord(minLimit + 1);
      majorList.setToRecord(toRecord);
      majorList.setTotalRecord(majorCount);

      majorList.setRecordSet(recordSet);
      majorList.setRecordPerPage(noOfRecordPerPage);
      majorList.setPageNo(pageNo);

      if (!StringUtils.isEmpty(major.getDegreeId())) {
        majors = backEndBO.getMajors(major.getDegreeId(), minLimit, maxLimit);
      } else {
        majors = backEndBO.getAllMajors(minLimit, maxLimit);
      }

    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw e;
    }
    majorList.setMajors(majors);
    return majorList;
  }

  @RequestMapping(value = REQUEST_EDIT_MAJOR, method = RequestMethod.GET)
  public Major editMajor(@PathVariable("id") String id) throws BackendException {
    System.out.println("Major id >>> " + id);
    Major major = new Major(id, null);
    try {
      MAJORS = backEndBO.getAllMajors();
      System.out.println("All majors >>> " + MAJORS);
      major = MAJORS.contains(major) ? MAJORS.get(MAJORS.indexOf(major)) : null;
      System.out.println("Found major >>> " + major);
    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw e;
    }
    return major;
  }

  @RequestMapping(value = SUBMIT_EDIT_MAJOR, method = RequestMethod.POST)
  public Major submitEditDegree(@RequestParam Major major) throws BackendException {
    Major existingMajor = MAJORS.contains(major) ? MAJORS.get(MAJORS.indexOf(major)) : null;
    System.out.println("existingMajor >>> " + existingMajor);
    System.out.println("newMajor >>> " + major);
    if (existingMajor == null) {
      throw new BackendException(MAJOR_NOT_EXIST);
    }
    if (StringUtils.isEmpty(major.getName())) {
      throw new BackendException(NOTHING_TO_ADD_MSG);
    }
    try {
      String[] fieldSet = new String[2];
      if (!existingMajor.getName().equalsIgnoreCase(major.getName())) {
        fieldSet[0] = "MAJOR_NAME";
      }
      if (!StringUtils.isEmpty(existingMajor.getDegreeId())
          && !existingMajor.getDegreeId().equals(major.getDegreeId())) {
        fieldSet[1] = "DEG_ID";
      }
      major = backEndBO.updateMajor(major, fieldSet);

    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw e;
    }
    return major;

  }
}
