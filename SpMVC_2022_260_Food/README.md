# 부산광역시_부산맛집정보 서비스 OpenAPI 연동
* JSON type 으로 데이터 받기

## 부산광역시_부산맛집정보 서비스 API 의 문제점 발견
* json type 으로 데이터를 요청하고 RestTemplate 를 사용하여 데이터를 변환 mapping 하려고 하는데, 데이터는 분명 JSON type 인데 MIME type 의 text/plain 으로 수신되고 있다.

* RestTemplate 은 application/json MIME type 으로 데이터가 수신되어야 VO, List<VO> type 으로 자동 변환, mapping 할수 있는데 text/plain 은 실제 데이터는 JSON 모양이지만 일반 String type 의 문자열과 같기 때문에 변환을 할수 없다.

* JavaScript 를 사용한다면 JSON.parse("JSON type 의 문자열") 힘수를 사용하여 쉽게 JSON 객체로 변환 할수 있다. 하지만 JAVA 에서는 안된다.	

* RestTemplate 클래스에는 수신된 데이터를 중간에 가로채서 사용자(개발자)가 임의로 데이터를 변환할수 있도록 통로를 만들어 놨다.

* RestTempate.intercepter 기능을 활용하면 수신된 데이터를 임의로 조작이 가능하다.

## RestTempate 의 interceptor 설정하기

### 클래스를 선언하여 실행하기
* ```ClientHttpRequestInterceptor``` 인터페이스를 implements 하여 클래스를 선언하고, intercept() method 를 재 정의하여 강제로 APPLICATION_JSON 형태로 ContentType 을 변환하기

* ```HttpRequestIntercepterV1``` 클래스를 정의하고 ```intercept``` method 재정의하기

* 이 방법은 가장 고전적인 POJO 방식의 코드이다.
* 한개의 method 를 간단하게 정의하기 위해 굳이 새로은 클래스를 만드는 것이 왠지 낭비스러운 생각이 든다.

### 인터페이스를 사용하여 객체 생성하기
* 인터페이스를 사용하여 객체 생성하기
* 한번만 사용하고 버릴 클래스 인스턴스 만들기
```
ClientHttpRequestInterceptor httpIntercept
	= new ClientHttpRequestInterceptor() {
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] 	body, ClientHttpRequestExecution execution)
	throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
};
```		

### 익명(무명) 클래스를 사용하여 직접 주입하기
* 일회용 객체를 인터페이스를 직접 사용하여 만들고 직접 주입하기
* 인스턴스 변수를 생성하지 않는다
* 딱, 한번만 사용하고 더이상 사용할 필요가 없을 경우 활용하는 방법
* 안드로이드 App 제작에서 이벤트 핸들러를 만드는 대표적인 방법
```
// 익명(무명)클래스를 사용하여 객체 주입하기
restTemp.getInterceptors()
	.add(new ClientHttpRequestInterceptor() {
		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {

			ClientHttpResponse response = execution.execute(request, body);
			response
				.getHeaders()
				.setContentType(MediaType.APPLICATION_JSON);
			return response;				
				
		}
});
```

### lambda 코드를 사용하는 객체주입
* java 1.8 이상에서 사용하는 Lambda 코드
* 인터페이스를 사용하여 Lambda 코드로 주입하기
* 무명클래스, 무명메서드 방식
* Lambda 를 사용하여 무명클래스 방식으로 주입하면 클래스, 객체, 메서드 이름 모드를 생략할수 있다

```
restTemp.getInterceptors()
	.add((request,body,execution)->{
		ClientHttpResponse response = execution.execute(request, body);
		response
			.getHeaders()
			.setContentType(MediaType.APPLICATION_JSON);
		return response;	
});
```

### Lambda
* 인터페이스에 method 가 1개 만 있을 때 Lambda 를 사용하여 객체를 만들수 있다