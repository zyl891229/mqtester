package controller;

import java.io.BufferedInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URLDecoder;

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
    public void changeCommit(@RequestParam(value = "name") String name,@RequestParam(value = "commit") String commit,@RequestParam(value = "path") String path,@RequestParam(value = "fullpath") String fullpath,HttpServletRequest request,HttpServletResponse response) throws IOException, InterruptedException {
   			  String referer = request.getHeader("REFERER");
   			  if (path.contains("webapp")) {
   		          path = "/Users/yirendai/Work/webdata"+URLDecoder.decode(path,"UTF-8").split("webapp")[1];
			}
   			  else {
   				  
   				path = "/Users/yirendai/Work/webdata/ftp"+URLDecoder.decode(path,"UTF-8")                                                                                                                                              ;
			}
	          String cmd = "sh /Users/yirendai/Work/webdata/chang_commit.sh "+name.trim()+" "+commit.trim().replaceAll("[\\t\\n\\r]", "<br>").replaceAll("<br><br>", "<br>")+" "+path.trim();  
	          logger.info(cmd);
    	      String result = "";
    	      BufferedReader br=null;
    	      BufferedInputStream in=null;
    	      try {
	    	       Process p = Runtime.getRuntime().exec(cmd);
	    	       if(p.waitFor() != 0){  
	    	        result+="没有进程号";
	    	       }    
	    	       in = new BufferedInputStream(p.getInputStream());
	    	       br = new BufferedReader(new InputStreamReader(in));
	    	       String lineStr;
	    	       while ((lineStr = br.readLine()) != null) {
	    	             result += lineStr;
	    	          }
    	      } catch (Exception e) {
	    	       e.printStackTrace();
    	      }finally{
	    	       if(br!=null){
	    	           try {
				    	      br.close();
				    	      in.close();
		    	     } catch (IOException e) {
		    	    	 	  e.printStackTrace();
		    	     }
    	       }
	    	   logger.info("ShellController.changeCommit=>\nname:"+name.trim()+"   \ncommit:"+commit.trim().replaceAll("[\\t\\n\\r]", "<br>")+"   \npath:"+path+"   \nreferer:"+referer+"   \nfullpath:"+fullpath);
    	       logger.info("ShellController.changeCommit=>result:"+result);
    	       response.sendRedirect(fullpath);
    	      }
    }
}
