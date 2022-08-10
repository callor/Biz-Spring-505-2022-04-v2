# 서버프로그램 구현 프로젝트
* 과제 수행을 위한 프로젝트
* 일시 : 2022. 08. 08, 2022.08.09
* 작성 : callor@callor.com

## 프로젝트 개요
* Spring Framework : 5.2.22.RELEASE
* Spring Security : 5.3.13.RELEASE
* MySQL 8.x
* MyBatis 3.5.x


## 계층구조 Context
* Context : 하나의 어떤 Task(JOB, 작업) 구성요소가  모두 포함된 하나의 데이터 그룹, 일반적으로 운영체제와 밀접한 관계의 데이터

### Tomcat Context
* Spring MVC 기반의 프로젝트를 구현하면 프로젝트 들이 Tomcat 의 Server.xml 설정에 기록이 된다. Tomcat은 외부의 요청이 들어오면 이 Context 를 확인하여 어떤 프로젝트에게 Request 를 전달할지 판단을 한다.

### Root Context
* web.xml 의 context-param 에 설정된 영역의 Context 들
* 한 프로젝트의 공통된 설정, 공통된 기능을 정의 하는 곳
* Spring Security 와 연동되는 프로젝트는 여기에 설정 파일을 만든다

### Servlet Context
* 실제로 MVC 패턴에의해 사용자의 Request 를 수신하고, Service와 Dao 등을 사용하여 비즈니스 로직을 처리하고 결과를 사용자에게 Response 하는 영역
* 한개의 Root Context 에 다수의 Servlet Context 가 있을 수 있다.
* 실무에서는 거의 한개의 Root 한개의 Serlvet 형식으로 구현이 된다

### Spring Security 와 DBMS 가 연동되는 프로젝트

* 일반적으로 Security 설정을 Root Context 에 두고 DBMS 설정을 Servlet Context 영역에 두는데, Security 에서 DB 의 사용자 정보, 권한정보를 활용(사용)하고자 할때 상당히 많은 연결 구조가 필요해 진다.

* 여기에서는 Security 와 DBMS 설정을 Root Context 영역에 두어 연결 구조를 최소화 하였다.

* Component scan을 할때 servlet-context.xml 과 별도로 security-context.xml 에서도 실행해 주어야 한다.

* service 패키지 아래 impl 패키지와 auth 패키지를 구분하여 serlvet, security context 에서 scan 을 할때 혹시 모를 충돌을 대비했다.


### component 를 Scan 하는 곳들
* serlvet-context.xml : Controller, Service.impl => MVC 패턴의 기본 Scan
* security-context.xml : Service.auth => Security 인증과 관련된 Scan
* mybatis-context.xml : mybatis-spring:scan => Dao 를 Scan 하고 mapper.xml 과 연결하여 실질적으로 DB 에 연결하는 코드를 생성

* Scan 설정이 잘못되어 프로젝트가 실행하는 과정에서 bean 이 생성되지 않으면 "No Qualify Bean 과 같은 오류가 발생한다"