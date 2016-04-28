package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ActiveMqTestController")
public class ActiveMqTestController {
    private Logger logger = LoggerFactory.getLogger(ActiveMqTestController.class);
    
    @RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String activityList(@RequestParam(value = "key") String test) {
        String logHeader = Thread.currentThread().getStackTrace()[1].getMethodName() + "调用接口test";
        logger.info("{} -S", logHeader);
        logger.info(logHeader + "-S " + test);
        return test;
    }

}
