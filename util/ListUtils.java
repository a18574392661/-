package com.itheima.util;

import java.util.HashSet;
import java.util.Set;

public class ListUtils {

	
	//两个集合的二差集
		public static  Set<String> setCha(Set nodbImage,Set dbImage) {
			
			
			  Set<String> result = new HashSet<String>();  
		        result.clear();
		        result.addAll(nodbImage);
		        result.removeAll(dbImage);
		        return result ;
		}
		
		
	
}
