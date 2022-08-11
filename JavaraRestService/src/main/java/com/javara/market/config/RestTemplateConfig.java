package com.javara.market.config;

import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

// ※주로 외부 라이브러리를 자바코드를 통해 빈으로 등록 시
@Configuration 
public class RestTemplateConfig {
   // 반환타입 : IOC컨테이너(스프링 컨테이너)에 등록할 빈(싱글톤)
   // 메소드명 : 생성된 빈 이름(아이디 값)
   // 예)RestTemplate restTemplate = new RestTemplate(ClientHttpRequestFactory)
   @Bean
   public RestTemplate restTemplate() {
      // 1.커넥션 풀 사용을 위한 HttpClient객체 생성 
      /*
      RestTemplate사용 시 커넥션 풀을 사용하기 위해 외부 라이브러리를 등록
      <dependency>
         <groupId>org.apache.httpcomponents</groupId>
         <artifactId>httpclient</artifactId>
         <version>4.5.13</version>
      </dependency>
      */
      CloseableHttpClient httpClient = HttpClientBuilder.create()
                              .setMaxConnTotal(50) // 서버연결 유지할 최대 Http커넥션 수
                              .setMaxConnPerRoute(50) // Route당(요청 uri주소당) 최대 Http커넥션 수
                              .setConnectionTimeToLive(5, TimeUnit.SECONDS) // 커넥션 연결 최대 유지 시간
                              .build();
      // 2.타입아웃 설정을 위한 객체 설정
      HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
      factory.setConnectionRequestTimeout(3000); // 요청 타임아웃 시간
      factory.setHttpClient(httpClient); 
      
      return new RestTemplate(factory);
   }
}