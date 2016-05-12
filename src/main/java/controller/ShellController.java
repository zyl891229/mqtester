package controller;

import java.io.BufferedInputStream;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ShellController")
public class ShellController {
    private Logger logger = LoggerFactory.getLogger(ActiveMqTestController.class);
    
    @RequestMapping(value = "/change_commit", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Object changeCommit(@RequestParam(value = "name") String name,@RequestParam(value = "commit") String commit,HttpServletRequest request ) throws IOException, InterruptedException {
	       logger.info("ShellController.changeCommit=>"+name+"   "+commit+"  "+request.getRequestURL().toString());
	       String fullpath = "/User/yirendai/Work/webdata"+request.getRequestURL().toString().split("webapp")[1];
	       String cmd1 = "shell "+name.trim()+" "+commit.trim()+" "+fullpath.trim();
    	      String cmd = "pwd";
    	      String result = "";
    	      BufferedReader br=null;
    	      BufferedInputStream in=null;
    	      try {
	    	       Process p = Runtime.getRuntime().exec(cmd);
	    	       if(p.waitFor() != 0){  
	    	        result+="没有进程号";
	    	              return "没有进程号";  
	    	       }    
	    	       in = new BufferedInputStream(p.getInputStream());
	    	       br = new BufferedReader(new InputStreamReader(in));
	    	       String lineStr;
	    	       while ((lineStr = br.readLine()) != null) {
	    	             result += lineStr;
	    	          }
    	      } catch (Exception e) {
	    	       e.printStackTrace();
	    	       return "异常";
    	      }finally{
	    	       if(br!=null){
	    	           try {
				    	      br.close();
				    	      in.close();
		    	     } catch (IOException e) {
		    	    	 	  e.printStackTrace();
		    	     }
    	       }
    	       logger.info("ShellController.changeCommit=>"+result);
    	      }
//    	      return result;
    	      return cmd1;
    }
}
