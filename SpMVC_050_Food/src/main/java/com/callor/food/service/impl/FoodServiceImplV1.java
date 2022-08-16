package com.callor.food.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.callor.food.config.FoodConfig;
import com.callor.food.config.QualifierConfig;
import com.callor.food.model.food.FoodItem;
import com.callor.food.model.food.FoodRoot;
import com.callor.food.service.FoodService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(QualifierConfig.SERVICE.FOOD_V1)
public class FoodServiceImplV1 implements FoodService {

	@Override
	public String queryString(String cat, String search) {

		String queryString = FoodConfig.API_FULL_URL;
		String encodeParams = null;
		try {
			encodeParams = "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + FoodConfig.SERVICE_KEY;
			
			// encoding 하지 않아도 됨
			encodeParams = "?serviceKey=" + FoodConfig.SERVICE_KEY;
			
//			encodeParams += "&" + URLEncoder.encode("pageNo", "UTF-8") + "="
//					+ URLEncoder.encode("1", "UTF-8"); /* 페이지번호 */
//			encodeParams += "&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
//					+ URLEncoder.encode("10", "UTF-8"); /* 한 페이지 결과 수 */
			
			encodeParams += "&resultType=json";
//			encodeParams += "&" + URLEncoder.encode("resultType", "UTF-8") + "="
//					+ URLEncoder.encode("json", "UTF-8"); /* JSON방식으로 호출 시 파라미터 resultType=json 입력 */
			

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queryString += encodeParams;
		log.debug("QueryString :{} ", queryString);
		return queryString;

	}

	@Override
	public List<FoodItem> getObjectList(String queryString) {

		URI restURI = null;
		try {
			restURI = new URI(queryString);
		} catch (URISyntaxException e) {
			log.debug("URI 문법오류");
			return null;
		}

		// Http 프로토콜에 보안 정보를 세팅하여
		HttpHeaders headers = new HttpHeaders();

		// JSON 데이터 타입으로 받겠다
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		// headers 에 추가된 정보를 Entity type 의 객체로 변환하기
		HttpEntity<String> entity = new HttpEntity<String>("parameter", headers);

		RestTemplate restTemp = new RestTemplate();

		/*
		 * VO 가 아닌 String 형으로 수신하기
		 */
		ResponseEntity<String> resString = null;
		resString = restTemp.exchange(restURI, HttpMethod.GET, entity, String.class);

		log.debug("{}", "=".repeat(100));
		log.debug("String 데이터 : {}", resString.getBody());
		log.debug("{}", "=".repeat(100));

		
		/*
		 * http://apis.data.go.kr/6260000/FoodService/getFoodKr 데이터를 JSON 으로 요청을 하면 text/plan 으로 return 된다
		 * text/plan 데이터는 겉모습을 JSON 과 같아 보이지만 실제 데이터는 String 형 문자열이다
		 * JS 에서는 JSON.parse() 함수를 사용하면 String 형 문자열을 간단히 JSON 객체로 변환 할 수 있지만
		 * Java 에서는 좀더 복잡한 과정을 거쳐야 한다.
		 * 
		 * String 형 문자열을 return 받아 restTemp 에 도달하기 전에 문자열을 가로채서 application/json type 으로 변환을 시켜줘야 한다
		 * 이러한 과정을 Message Converter 라고 한다. 
		 */
		
		// Lambda 사용
		restTemp.getInterceptors().add((request, body, execution) -> {
			ClientHttpResponse response = execution.execute(request, body);
			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			return response;
		});
		
		// text/plan to application/json MessageConvert
		ClientHttpRequestInterceptor requestIntercepter = new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
				
				ClientHttpResponse response = execution.execute(request, body);
				response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
				return response;
			}
		};
		restTemp.getInterceptors().add(requestIntercepter);

		
		// text/plan to application/json MessageConvert 익명클래스 사용
		restTemp.getInterceptors().add(new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				ClientHttpResponse response = execution.execute(request, body);
				response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
				return response;
			}
		});
		
		ResponseEntity<FoodRoot> resData = null;
		resData = restTemp.exchange(restURI, HttpMethod.GET, entity, FoodRoot.class);

		log.debug("{}", "=".repeat(100));
		log.debug("JSON 데이터 : {}", resData.getBody());
		log.debug("{}", "=".repeat(100));

		return resData.getBody().getFoodKr.item;

	}


}


