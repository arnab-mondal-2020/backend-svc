package com.bp.micro.svc.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bp.micro.svc.bo.BackEndBO;
import com.bp.micro.svc.constants.BackendConstants;
import com.bp.micro.svc.constants.RequestMappings;
import com.bp.micro.svc.exception.BackendException;
import com.bp.micro.svc.teo.AdminUser;
import com.bp.micro.svc.teo.Degree;

@RestController
public class BackEndController implements RequestMappings, BackendConstants {
  @Autowired
  private BackEndBO backEndBO;

  @RequestMapping(value = REQUEST_LOAD_BACK_END, method = {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView loadPage(HttpServletRequest request) {
    String userNumber = (String) request.getAttribute(USER_NUMBER);
    AdminUser user = null;
    ModelAndView mav = new ModelAndView(RETURN_BACK_END);
    try {
      user = backEndBO.getUserInfoByNumber(userNumber);
      request.setAttribute(USER_INFO, user);
      request.setAttribute(FIRST_NAME_PROP, user.getFullName());
      CommonUtil.setWelcomeMessage(request);

      if (!user.getAuthLevel().equalsIgnoreCase("SU")) {
        return new ModelAndView("forward:/unauthorized");
      }
      List<Degree> degrees;
      degrees = backEndBO.getAllDegrees();
      request.setAttribute(BackendConstants.ALL_DEGREES_OPT, degrees);
    } catch (BackendException e) {
      System.err.println(e.getMessage());
    }

    return mav;
  }
}
