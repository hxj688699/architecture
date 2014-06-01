package com.feinno.architecture.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

	private JsonHelper(){};
	
	/**
	 * 对象转Json串
	 * @param obj
	 * @return
	 */
	public static String object2str(Object obj){
		String retStr = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			retStr = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retStr;
	}
	
	public static Object str2object(String str, Class<?> clazz){
		Object obj = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			obj = mapper.readValue(str, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}
}
