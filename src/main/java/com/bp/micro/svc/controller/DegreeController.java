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
import org.springframework.web.bind.annotation.RestController;

import com.bp.micro.svc.bo.BackEndBO;
import com.bp.micro.svc.common.BackendUtil;
import com.bp.micro.svc.constants.BackendConstants;
import com.bp.micro.svc.constants.RequestMappings;
import com.bp.micro.svc.exception.BackendException;
import com.bp.micro.svc.teo.Degree;
import com.bp.micro.svc.teo.DegreeList;

@RestController
public class DegreeController extends BaseController implements BackendConstants, RequestMappings {

  private static List<Degree> DEGREES = new ArrayList<Degree>();
  @Autowired
  private BackEndBO backEndBO;

  @RequestMapping(value = REQUEST_ADD_DEGREE, method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Degree addDegree(@RequestBody Degree degree) throws BackendException {
    if (StringUtils.isEmpty(degree.getName()) || StringUtils.isEmpty(degree.getId())) {
      throw new BackendException(NOTHING_TO_ADD_MSG);
    }
    System.out.println("[degree] >>> " + degree.getId() + " >>> " + degree.getName());
    try {
      degree = backEndBO.addDegree(degree);
    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw e;
    }
    return degree;
  }

  @RequestMapping(value = REQUEST_SHOW_ALL_DEGREE, method = RequestMethod.POST)
  public DegreeList showAllDegrees(@RequestBody DegreeList degreeList) throws BackendException {
    String pageNoStr = degreeList.getPageNoStr();
    String recordCountStr = degreeList.getRecordCountStr();
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
      int degreeCount = backEndBO.getTotalDegreeCount();

      int recordSet = BackendUtil.populateRecordSet(degreeCount, noOfRecordPerPage);

      minLimit = (noOfRecordPerPage * (pageNo - 1));
      maxLimit = noOfRecordPerPage;

      System.out.println("minLimit >>> " + minLimit);
      System.out.println("maxLimit >>> " + maxLimit);
      int toRecord = maxLimit * pageNo > degreeCount ? degreeCount : maxLimit * pageNo;
      degreeList.setFromReord(minLimit + 1);
      degreeList.setToRecord(toRecord);
      degreeList.setTotalRecord(degreeCount);

      degreeList.setRecordSet(recordSet);
      degreeList.setRecordPerPage(noOfRecordPerPage);
      degreeList.setPageNo(pageNo);
      List<Degree> degrees = backEndBO.getAllDegrees(minLimit, maxLimit);
      degreeList.setDegrees(degrees);
    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw e;
    }
    return degreeList;
  }

  @RequestMapping(value = REQUEST_EDIT_DEGREE, method = RequestMethod.GET)
  public Degree editDegree(@PathVariable String id) throws BackendException {
    System.out.println("Degree id >>> " + id);
    try {
      DEGREES = backEndBO.getAllDegrees();
      System.out.println("All degrees >>> " + DEGREES);
      Degree degree = new Degree(id);
      degree = DEGREES.contains(degree) ? DEGREES.get(DEGREES.indexOf(degree)) : null;
      System.out.println("Found degree >>> " + degree);
      if (degree == null) {
        throw new BackendException(NOTHING_FOUND);
      }
      return degree;
    } catch (BackendException e) {
      System.err.println(e.getMessage());
      throw e;
    }
  }

  @RequestMapping(value = SUBMIT_EDIT_DEGREE, method = RequestMethod.POST)
  public Degree submitEditDegree(@RequestBody Degree degree) throws BackendException {
    Degree existingDegree = DEGREES.contains(degree) ? DEGREES.get(DEGREES.indexOf(degree)) : null;
    System.out.println("existingDegree >>> " + existingDegree);
    System.out.println("newDegree >>> " + degree);
    if (existingDegree == null) {
      throw new BackendException(NOTHING_FOUND);
    }
    if (existingDegree != null) {
      if (!StringUtils.isEmpty(degree.getName())
          && !degree.getName().equals(existingDegree.getName())) {
        System.out.println("Name updated>>>");
      }
    }
    if (StringUtils.isEmpty(degree.getName())) {
      throw new BackendException(NOTHING_TO_ADD_MSG);
    }
    try {
      degree = backEndBO.updateDegree(degree);
    } catch (BackendException e) {
      System.err.println(e.getMessage());
    }
    return degree;
  }
}
