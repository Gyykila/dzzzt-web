package com.cqjysoft.common.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect {
	
	@SuppressWarnings("unchecked")
	@Around(value="execution(* com.cqjysoft.webservice.controller.*.*(..))")
	@CrossOrigin(origins = "*", maxAge = 3600)
    public String around(ProceedingJoinPoint pjp) throws JsonProcessingException{
    	Map<String,Object> map = null;
 	    ObjectMapper mapper = new ObjectMapper();
 	    
 		Signature signature = pjp.getSignature();    
	    MethodSignature methodSignature = (MethodSignature)signature;
	    Method targetMethod = methodSignature.getMethod();  
    	try {
 	       	if(targetMethod.isAnnotationPresent(Ignore.class)){
 	       		return (String) pjp.proceed();
 	       	}else{
	 	       	Object[] args = pjp.getArgs();
	 	 	    String token = (String) args[1];
	 	 	    if(!StringUtils.isBlank(token)){
	 	 	    	return (String) pjp.proceed();
	 	 	    }else {
	 	 	    	map = new HashMap<String,Object>();
	 	 	    	map.put("code", "EXPIRE");
	 	 			map.put("msg", "用户过期");
	 	 			map.put("data", mapper.writeValueAsString(null));
	 	 	    }
 	       	}
    	} catch (Throwable e) {
    		//方法异常
    		e.printStackTrace();
 			map = new HashMap<String,Object>();
 			map.put("code", "FAILURE");
 			map.put("msg", ExceptionUtils.getRootCauseMessage(e));
 			map.put("data", mapper.writeValueAsString(null));
    	}
    	return mapper.writeValueAsString(map);
    }
}