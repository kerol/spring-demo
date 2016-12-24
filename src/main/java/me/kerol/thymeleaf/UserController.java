package me.kerol.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.kerol.repository.User;
import me.kerol.repository.UserRepo;

/**
 * Created by kevin on 24/12/2016.
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping("/create")
    public String create(String name, String email) {
        String userIdStr = "Null";
        try {
            User user = new User(name, email);
            userRepo.save(user);
            userIdStr = String.valueOf(user.getId());
        }
        catch (Exception e) {
            return "Exception while creating user" + e.toString();
        }
        return "Successfully creating user, ID:  " + userIdStr;
    }
}
