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

    @RequestMapping(value = "/json", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String activityList() {
    		String reString = "{ \"records\":[ {\"Name\":\"Alfreds Futterkiste\",\"City\":\"Berlin\",\"Country\":\"Germany\"}, {\"Name\":\"Ana Trujillo Emparedados y helados\",\"City\":\"México D.F.\",\"Country\":\"Mexico\"}, {\"Name\":\"Antonio Moreno Taquería\",\"City\":\"México D.F.\",\"Country\":\"Mexico\"}, {\"Name\":\"Around the Horn\",\"City\":\"London\",\"Country\":\"UK\"}, {\"Name\":\"B's Beverages\",\"City\":\"London\",\"Country\":\"UK\"}, {\"Name\":\"Berglunds snabbk?p\",\"City\":\"Lule?\",\"Country\":\"Sweden\"}, {\"Name\":\"Blauer See Delikatessen\",\"City\":\"Mannheim\",\"Country\":\"Germany\"}, {\"Name\":\"Blondel père et fils\",\"City\":\"Strasbourg\",\"Country\":\"France\"}, {\"Name\":\"Bólido Comidas preparadas\",\"City\":\"Madrid\",\"Country\":\"Spain\"}, {\"Name\":\"Bon app'\",\"City\":\"Marseille\",\"Country\":\"France\"}, {\"Name\":\"Bottom-Dollar Marketse\",\"City\":\"Tsawassen\",\"Country\":\"Canada\"}, {\"Name\":\"Cactus Comidas para llevar\",\"City\":\"Buenos Aires\",\"Country\":\"Argentina\"}, {\"Name\":\"Centro comercial Moctezuma\",\"City\":\"México D.F.\",\"Country\":\"Mexico\"}, {\"Name\":\"Chop-suey Chinese\",\"City\":\"Bern\",\"Country\":\"Switzerland\"}, {\"Name\":\"Comércio Mineiro\",\"City\":\"S?o Paulo\",\"Country\":\"Brazil\"} ] }";
    		String reString1 = "{ \"records\":[ {\"Name\":\"Alfreds Futterkiste\",\"City\":\"Berlin\",\"Country\":\"Germany\"}]}";

    		return reString1;
    }
}
