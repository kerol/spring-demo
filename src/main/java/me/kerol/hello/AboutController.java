package me.kerol.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class AboutController {

    @Value("${myapp.author}")
    public String appAuthor;
    @Value("${myapp.name}")
    public String appName;

    @RequestMapping("/about")
    public String about() {
        return "App Author:  " + appAuthor + " and App Name:  " + appName;
    }
}
