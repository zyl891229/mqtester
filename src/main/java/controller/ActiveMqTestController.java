package controller;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

@Controller
@RequestMapping("/ActiveMqTestController")
public class ActiveMqTestController {
    private Logger logger = LoggerFactory.getLogger(ActiveMqTestController.class);
    
    @Resource
    private JmsTemplate jmsTemplate;
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
    	System.out.println("setJmsTemplate");
        this.jmsTemplate = jmsTemplate;
    }
    
    
    @RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String activityList(@RequestParam(value = "key") String test) {
        String logHeader = Thread.currentThread().getStackTrace()[1].getMethodName() + "调用接口test";
        logger.info("{} -S", logHeader);
        logger.info(logHeader + "-S " + test);
        if (test.equals("1")) {
        	System.out.println("开始发送");
        	sendMqMessage(null,"spring activemq queue type message !");
			return test;
		}
        return "sorry not match";
    }
    
    /**
     * 说明:发送的时候如果这里没有显示的指定destination.将用spring xml中配置的destination
     * @param destination
     * @param message
     */
    public void sendMqMessage(Destination destination, final String message){
        System.out.println("开始...");
        jmsTemplate.send(jmsTemplate.getDefaultDestination(), new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
        System.out.println("spring send message...");
    }

    @RequestMapping(value = "/json", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String activityList() {
    		String reString = "{ \"records\":[ {\"Name\":\"Alfreds Futterkiste\",\"City\":\"Berlin\",\"Country\":\"Germany\"}, {\"Name\":\"Ana Trujillo Emparedados y helados\",\"City\":\"México D.F.\",\"Country\":\"Mexico\"}, {\"Name\":\"Antonio Moreno Taquería\",\"City\":\"México D.F.\",\"Country\":\"Mexico\"}, {\"Name\":\"Around the Horn\",\"City\":\"London\",\"Country\":\"UK\"}, {\"Name\":\"B's Beverages\",\"City\":\"London\",\"Country\":\"UK\"}, {\"Name\":\"Berglunds snabbk?p\",\"City\":\"Lule?\",\"Country\":\"Sweden\"}, {\"Name\":\"Blauer See Delikatessen\",\"City\":\"Mannheim\",\"Country\":\"Germany\"}, {\"Name\":\"Blondel père et fils\",\"City\":\"Strasbourg\",\"Country\":\"France\"}, {\"Name\":\"Bólido Comidas preparadas\",\"City\":\"Madrid\",\"Country\":\"Spain\"}, {\"Name\":\"Bon app'\",\"City\":\"Marseille\",\"Country\":\"France\"}, {\"Name\":\"Bottom-Dollar Marketse\",\"City\":\"Tsawassen\",\"Country\":\"Canada\"}, {\"Name\":\"Cactus Comidas para llevar\",\"City\":\"Buenos Aires\",\"Country\":\"Argentina\"}, {\"Name\":\"Centro comercial Moctezuma\",\"City\":\"México D.F.\",\"Country\":\"Mexico\"}, {\"Name\":\"Chop-suey Chinese\",\"City\":\"Bern\",\"Country\":\"Switzerland\"}, {\"Name\":\"Comércio Mineiro\",\"City\":\"S?o Paulo\",\"Country\":\"Brazil\"} ] }";
    		String reString1 = "{ \"records\":[ {\"Name\":\"Alfreds Futterkiste\",\"City\":\"Berlin\",\"Country\":\"Germany\"}]}";

    		return reString1;
    }
}
