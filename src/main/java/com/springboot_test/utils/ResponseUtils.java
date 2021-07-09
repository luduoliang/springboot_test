package com.springboot_test.utils;

import java.util.HashMap;

/**
 * 接口返回类
 * @author Administrator
 *
 */
public class ResponseUtils {


    /**
     * 请求成功
     * @param data
     * @return
     */
	public static HashMap<String, Object> success(Object data) {
        HashMap<String, Object> returnData = new HashMap<String, Object>();
        
        returnData.put("status", true);
        returnData.put("message", "操作成功");
        returnData.put("data", data);
        
        return returnData;
    }
    
    /**
     * 请求失败
     * @param data
     * @return
     */
	public static HashMap<String, Object> fail(String message) {
        HashMap<String, Object> returnData = new HashMap<String, Object>();
        
        returnData.put("status", false);
        returnData.put("message", message != "" ? message : "未知错误");
        returnData.put("data", null);
        
        return returnData;
    }

}
