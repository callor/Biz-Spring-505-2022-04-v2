# Project Logger
## Logging 또는 Logger 라고 불리는 것
* 개발자가 코드의 곳곳에 실제 업무(비즈니스)와 관련없는 코드를 추가하여 코드의 흐름을 추적하고, 데이터의 흐름을 추적하여 지금 작성하고 있는 코드의 흐름이 잘 진행되고 있는지 데이터가 원하는 대로 잘 흘러 가고 있는지를 살펴보기 위한 도구

## Debuging
* 코드의 문법적 오류를 수정하는 것 : 대부분의 IDE(개발툴)에서 기본적으로 제공
* 데이터의 흐름에서 문제를 찾아서 수정하는 것과 어떤 기능이 정확히 잘 작동하여 데이터가 잘 가공 되고 결과거 정확한지 파악하고 문제를 찾아서 수정하는 것 : Debuging Tools 을 사용하여 자동으로 추적하고 코드의 흐름을 잠시 멈추고 데이터를 살펴볼수 있다.
* Debuging tools 들의 사용법이 다소 까다롭고 번거로운 것이 있어서 보통 일반적인 개발 환경에서는 단순한 Logger 를 사용하여 추적을 많이 한다.

## Logging
* console 에 개발자가 임의로 기록을 남겨서 Console 에 나타나는 메시지를 보면서 직접 코드의 흐름과 데이터의 흐름을 추적하는 것
* Java 관련된 코드에서는 가장 많이 사용하는 것이 현재 logback 이다
* 개발과정에서 Level 을 설정하여두고 Logger 를 추적한 후 실제 배포과정에서 Level 을 다시 설정하여 추적 메시지를 모두 감출수 있는 장점이 있다

* Spring과 Java 환경에서는 logback-classic 을 설정하여 logger 를 추적한다

## Spring Seucurity 설정
* Spring 의 filter 기능을 활용하여 Security 를 적용한다

## Spring filter
* 사용자(web browser)가 Request 를 요청하면 Dispatcher Servlet 이라는 친구가 먼저 사용자의 요청을 분석한다. 분석된 URL 에 따라서 적절한 Controller 에게 전달한다.

* filter 는 Dispatcher Serlvet 의 앞에서 Request 를 가로채고 사용자의 요청에 담긴 데이터 등을 분석하여 서버를 다른 방식으로 작동하도록 하는 클래이다

* filter 는 프로젝티의 web.xml 에 설정하여 서버가 작동될때 자동으로 설정을 한다


## Http Status Code
* 200 : 정상 요청, 정상 응답, 데이터가 많을 경우는 준비하고 있으니 기다려라
* 404 : Not found 요청을 찾을수 없다, 요청을 처리할 Mapping 이 없다. JSP 파일이 없다
* 500 : 서버의 내부에서 처리중 오류가 발생했다
* 400 : 요청된 데이터중에 처리할수 없는 데이터가 있다, Bad Request
* 405 : RequestMapping 은 있는데 GET, POST 등의 처리 방법이 없다

* 301, 303 : Redirect
* 303 : 로그인이 필요한 페이지에 접근했을때 로그인이 되어 있지 않으면 로그인 페이지로 redirect 한다 이때 서버는 브라우저에게 먼저 303 코드를 보내고 다시 접속할 url 을 전달한다.
* 301 : 현재 접속하고자 하는 페이지가 없어지고 다른 페이지로 접속해 달라 라는 요청

* 304 : Request 를 받았는데 방금전에 Reponse 한 결과와 똑같은 결과를 또 보내야 하는 경우 좀점에 준 데이터를 그냥 사용해 라는 의미


## 보안과 관련된 Http status Code
### 403 오류
* 접근금지!! 오류
* Request를 요청을 했는데 서버가 해당 요청을 이해는 했다.
* Request 처리할 권한이 없는 경우
* Spring Security 에서 form mathod를 POST 로 서버 데이터를 전송하였는데 csrf 권한 Token 이 없어서 처리할수 없다는 메시지


### csrf : Cross Site Request forgery : 사이트간 요청 변조



