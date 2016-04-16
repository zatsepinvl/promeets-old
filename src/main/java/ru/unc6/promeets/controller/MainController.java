package ru.unc6.promeets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vladimir on 06.02.2016.
 */
@Controller
public class MainController {

    private static final String INDEX_PATH = "/static/index.html";

    @RequestMapping(value = "/")
    public String index() {
        return INDEX_PATH;
    }

    @RequestMapping(value = "/login")
    public String login() {
        return INDEX_PATH;
    }

    @RequestMapping(value = "/signup")
    public String signUp() {
        return INDEX_PATH;
    }

    @RequestMapping(value = "/group")
    public String groups() {
        return INDEX_PATH;
    }

    @RequestMapping(value = "/group/**")
    public String groupPage() {
        return INDEX_PATH;
    }

    @RequestMapping(value = "/venue/*")
    public String venueByMeetId() {
        return INDEX_PATH;
    }
    
    @RequestMapping(value = "/rtc")
    public String rtcExample() {
        return "/static/rtc.html";
    }
    
    @RequestMapping(value = "/rtc1")
    public String rtcExample1() {
        return "/static/rtc1.html";
    }
}
