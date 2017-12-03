package com.h3c.demo.restClient;

import java.nio.charset.Charset;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author m15129
 *
 */
public class RestCleintTest {

	public static void main(String[] args) {

		//API: RestTemplate api地址 ：https://docs.spring.io/spring-framework/docs/4.3.7.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html	
		
		//第一种调用方式（推荐）   参数放到Map里面 key-value形式。（url，参数map，返回值类型） 这三个参数分别代表 请求地址、请求参数、HTTP响应转换被转换成的对象类型
		
		RestTemplate  restTemplate = getRestTemplate();
		MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<String,String>();
		requestParam.set("name", "张三");
				
		ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8280/user/add", requestParam,
				JSONObject.class);
		JSONObject json = responseEntity.getBody();
		System.out.println(json);
		
		
		
		//第二种调用方式   以实体对象bean直接向服务端传参数的时候服务端需加上@RequestBody注解  
		
//		UserBean userBean = new UserBean();
//	    userBean.setName("王五");
//	    String result = restTemplate
//	        .postForObject("http://127.0.0.1:8280/user/add", userBean, String.class);
	
	}

	//得到RestTemplate实例
	public static RestTemplate getRestTemplate(){
		 
		HttpClientBuilder builder = HttpClientBuilder.create().disableAutomaticRetries();
		builder.setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(300).build());
		RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(builder.build()));
		template.getMessageConverters().add(0,
				new StringHttpMessageConverter(Charset.forName("UTF-8")));		
			return template;

	}
}
