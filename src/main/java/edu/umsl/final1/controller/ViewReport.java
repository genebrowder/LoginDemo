package edu.umsl.final1.controller;

import edu.umsl.final1.dao.UserLoginInfoDao;
import edu.umsl.final1.domain.UserLoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by genebrowder on 5/1/16.
 */
@Controller
public class ViewReport {
    @RequestMapping(value="/viewreport", method= RequestMethod.POST)
    public ModelAndView viewReport(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("myreportpage");


        String userName= (String) request.getParameter("userName");
        String password= (String) request.getParameter("password");

        UserLoginInfo  userLoginInfo = new UserLoginInfo();
        userLoginInfo.setUserName(userName);
        userLoginInfo.setPassword(password);


        UserLoginInfoDao.saveUserLoginInfo(userLoginInfo);

        boolean inTheDatabase = UserLoginInfoDao.isValiduser(userLoginInfo);

        mav.addObject("inTheDatabase",inTheDatabase) ;
        mav.addObject("userName",userName) ;

        return mav;
    }
}
