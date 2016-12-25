package me.kerol.controller;

import me.kerol.commen.Const;
import me.kerol.models.User;
import me.kerol.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by kevin on 25/12/2016.
 */
@Controller
public class BaseController {

    //request, session, ip
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected String getUserIp() {
        String ip = getRequest().getHeader("X-Real-Ip");
        if (StringUtils.isNotBlank(ip) && !ip.equalsIgnoreCase("unknown")) {
            return ip;
        } else {
            return getRequest().getRemoteAddr();
        }
    }

    // user info
    protected User getUser() {
        return (User) getSession().getAttribute(Const.LOGIN_SESSION);
    }

    protected String getUserPwd(String pwd) {
        try {
            return MD5Util.encrypt(pwd + Const.LOGIN_SESSION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getUserEmail() {
        String userEmail = "";
        User user = getUser();
        if (user != null) {
            userEmail = user.getEmail();
        }
        return userEmail;
    }
}

