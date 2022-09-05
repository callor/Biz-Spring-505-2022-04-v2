package com.ballza.lunch.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ballza.lunch.config.LunchConfig;
import com.ballza.lunch.model.LunchRow;
import com.ballza.lunch.model.MealServiceDietInfo;
import com.ballza.lunch.model.SearchVO;
import com.ballza.lunch.service.LunchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LunchServiceImplV1 implements LunchService{
	
	@Override
	public String queryString(SearchVO search) {
		String queryString = LunchConfig.API_FULL_URL;
		String encodeParams = null;

		try {
			encodeParams = "?" + URLEncoder.encode("Key","UTF-8");
			encodeParams += "=" + LunchConfig.SERVICE_KEY;
			
			encodeParams += "&" + URLEncoder.encode("Type","UTF-8");
			encodeParams += "=xml";
			
			encodeParams += "&" + URLEncoder.encode("pIndex","UTF-8");
			encodeParams += "=1";
			
			encodeParams += "&" + URLEncoder.encode("pSize","UTF-8");
			encodeParams += "=1000";
			
			encodeParams += "&" + URLEncoder.encode("ATPT_OFCDC_SC_CODE","UTF-8");
			encodeParams += "=" + search.getSc_code();
			
			encodeParams += "&" + URLEncoder.encode("SD_SCHUL_CODE","UTF-8");
			encodeParams += "=" + search.getSc_num();
			
			encodeParams += "&" + URLEncoder.encode("MLSV_FROM_YMD","UTF-8");
			encodeParams += "=" + search.getSdate();
			
			encodeParams += "&" + URLEncoder.encode("MLSV_TO_YMD","UTF-8");
			encodeParams += "=" + search.getEdate();

			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		queryString += encodeParams;
		log.debug("쿼리문자열 {}",queryString);
		return queryString;
	}

	@Override
	public List<LunchRow> getLunchRows(String queryString) {
		URI lunchRestURI = null;
		try {
			lunchRestURI = new URI(queryString);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		 * HttpEntity<String> headerEntity = new
		 * HttpEntity<String>("parameter",headers);
		 */
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<String> resString = null;
		resString = restTemp.exchange(lunchRestURI, HttpMethod.GET, null, String.class);
		
		ObjectMapper xmlMapper = new XmlMapper();
		MealServiceDietInfo mealInfo = null;
		try {
			mealInfo = xmlMapper.readValue(resString.getBody(), MealServiceDietInfo.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("=".repeat(100));
		log.debug("{}",resString.getBody());
		log.debug("{}",mealInfo);
		log.debug("=".repeat(100));
		
		return mealInfo.row;
//		return null;
/*		
		ResponseEntity<LunchRoot> resLunchObject = null;
		
		ClientHttpRequestInterceptor httpIntercept
		= new ClientHttpRequestInterceptor() {
			
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		restTemp.getInterceptors().add(new ClientHttpRequestInterceptor() {
			
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				
				ClientHttpResponse response = execution.execute(request, body);
				response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
						
				return response;
			}
		});
		
		restTemp.getInterceptors().add((request, body, execution)->{
			ClientHttpResponse response = execution.execute(request, body);
			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			return response;
			
		});
		restTemp.getInterceptors().add(new HttpRequestInterceptorV1());
		resLunchObject = restTemp.exchange(lunchRestURI, HttpMethod.GET, headEntity, LunchRoot.class);
		return resLunchObject.getBody().mealInfo.row;
				
*/		
		
	}
	
}
