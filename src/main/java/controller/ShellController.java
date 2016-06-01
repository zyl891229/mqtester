package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public void changeCommit(@RequestParam(value = "name") String name, @RequestParam(value = "commit") String commit,
			@RequestParam(value = "path") String path, @RequestParam(value = "fullpath") String fullpath,
			HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
		request.getHeader("REFERER");
		if (path.contains("webapp")) {
			path = "/Users/yirendai/Work/webdata" + URLDecoder.decode(path, "UTF-8").split("webapp")[1];
		} else {

			path = "/Users/yirendai/Work/webdata/ftp" + URLDecoder.decode(path, "UTF-8");
		}
		String result = "";
		BufferedReader br = null;
		BufferedReader brerr = null;
		BufferedInputStream in = null;
		BufferedInputStream inerr = null;
		try {
			// 第一种方法
			// String cmd = "sh /Users/yirendai/Work/webdata/change_commit.sh
			// "+name.trim()+" "+commit.trim().replaceAll("[\\t\\n\\r]",
			// "<br>").replaceAll("<br><br>", "<br>").replace(" ",
			// "_").replaceAll("#", "")+" "+path.trim();
			// logger.info(cmd);
			// Process p = Runtime.getRuntime().exec(cmd);
			// p.waitFor();
			// 第二种方法
			Process p = null;
			Runtime runTime = Runtime.getRuntime();
			String[] commands = new String[] { "sh", "/Users/yirendai/Work/webdata/change_commit.sh", name.trim(),
					commit.trim().replaceAll("[\\t\\n\\r]", "<br>").replaceAll("<br><br>", "<br>")
							.replaceAll(" ", "\\\\\\\\\\\\&nbsp;").replaceAll("#", "\\\\\\\\\\\\&nbsp;"),
					path.trim() };
			for (String string : commands) {
				logger.info("commands:" + string);
			}
			p = runTime.exec(commands);
			p.waitFor();
			in = new BufferedInputStream(p.getInputStream());
			inerr = new BufferedInputStream(p.getErrorStream());
			br = new BufferedReader(new InputStreamReader(in));
			brerr = new BufferedReader(new InputStreamReader(inerr));
			String lineStr;
			while ((lineStr = br.readLine()) != null) {
				result += lineStr;
			}
			while ((lineStr = brerr.readLine()) != null) {
				result += lineStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (br != null) {
					br.close();
					in.close();
				}
				if (brerr != null) {
					brerr.close();
					inerr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("ShellController.changeCommit=>result:" + result);
			response.sendRedirect(fullpath);
		}
	}
}
