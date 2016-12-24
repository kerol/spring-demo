package me.kerol.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.kerol.config.Redis;

@Controller
public class RedisController {

    @Autowired
    private Redis redis;

    @RequestMapping("/redis")
    public String index(@RequestParam(value="name", required=false, defaultValue="World")
                                    String name, Model model) {
        String redisKey = "hello";
        redis.set(redisKey, name);
        String redisValue = redis.get(redisKey);
        redisValue += "  -- from redis";
        model.addAttribute("name", redisValue);
        //model.addAttribute("name", name);
        return "index";
    }

}
