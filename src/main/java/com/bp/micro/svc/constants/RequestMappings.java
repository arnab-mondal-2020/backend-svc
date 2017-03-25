package com.bp.micro.svc.constants;

public interface RequestMappings {
  String REQUEST_ADD_DEGREE = "/adddegree";
  String REQUEST_ADD_INSTITUTE = "/addinstitute";
  String REQUEST_SHOW_ALL_INSTITUTE = "/showallinst";
  String REQUEST_SHOW_ALL_DEGREE = "/alldeg";
  String REQUEST_ADD_MAJOR = "/addmajor";
  String REQUEST_LOAD_BACK_END = "/loadbackend";
  String REQUEST_LOAD_BACK_END_FWD = "forward:/loadbackend";
  String RETURN_BACK_END = "bpadmin/add/backend";
  String REQUEST_SHOW_MAJOR = "/allmajor";
  String RECORD_PER_PAGE = "recordPerPage";
  String RECORD_SET = "recordSet";
  String PAGE_NO = "pageNo";
  String FROM_RECORD = "fromRecord";
  String TO_RECORD = "toRecord";
  String TOTAL_RECORD = "totalRecord";
  String SUBMIT_EDIT_INSTITUTE = "submiteditinst";
  String SUBMIT_EDIT_DEGREE = "submiteditdegree";
  String SUBMIT_EDIT_MAJOR = "submiteditmajor";
  String DEGREE_ID = "degreeId";



  String REQUEST_EDIT_DEGREE = "/showeditdeg/{id}";
  String REQUEST_EDIT_MAJOR = "/showeditmajor/{id}";
  String REQUEST_EDIT_INSTITUTE = "/editinst/{id}";
}
