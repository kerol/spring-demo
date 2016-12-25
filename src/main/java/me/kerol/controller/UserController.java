package me.kerol.controller;

import me.kerol.commen.Const;
import me.kerol.repository.result.ExceptionMsg;
import me.kerol.repository.result.ResponseData;
import me.kerol.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.kerol.models.User;
import me.kerol.repository.UserRepo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevin on 24/12/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/create")
    public String create(String name, String email) {
        String userIdStr = "Null";
        try {
            User user = new User(name, email);
            userRepo.save(user);
            userIdStr = String.valueOf(user.getId());
        } catch (Exception e) {
            return "Exception while creating user" + e.toString();
        }
        return "Successfully creating user, ID:  " + userIdStr;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestParam(value = "email") String email,
                              @RequestParam(value = "password") String password) {
        try {
            //System.out.println(password);
            //System.out.println(MD5Util.encrypt(password));
            User loginUser = userRepo.findByEmail(email);
            if (loginUser == null) {
                return new ResponseData(ExceptionMsg.EmailNotRegister);
            } else if (!loginUser.getPassWord().equals(MD5Util.encrypt(password))) {
                return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
            }
            getSession().setAttribute(Const.LOGIN_SESSION, loginUser);
            String preUrl = "/";
            return new ResponseData(ExceptionMsg.SUCCESS, preUrl);
        } catch (Exception e) {
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }
}
