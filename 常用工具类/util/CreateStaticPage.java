package com.itheima.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/*
 * 生成对应的静态页面
 */
@Component
public class CreateStaticPage {
  
	
	
	
	@Autowired
	private TemplateEngine templateEngine;
	
	/*
	 * url生成的文件路径 tempName模板名字
	 */
	public  void createPage(String url,String tempName,String staticPageName,Map<String, Object> map) {
		System.out.println(templateEngine+"//"+url);
		
		 Context context = new Context(); context.setVariables(map); // 输出流 
		 File dest
		  = new File(url,staticPageName); if (dest.exists()) { dest.delete(); } try {
		  PrintWriter writer = new PrintWriter(dest, "UTF-8");
		  
		 templateEngine.process(tempName, context, writer); } catch (Exception e) { //
		  e.printStackTrace();
		 
		 }
		 
		
		
			}
}
