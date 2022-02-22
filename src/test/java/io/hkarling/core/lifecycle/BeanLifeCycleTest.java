package io.hkarling.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        /**
         * 설정 정보 사용
         *  - 메서드 이름을 자유롭게 줄 수 있다
         *  - 스프링 빈이 스프링 코드에 의존하지 않는다
         *  - 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드 사용 가능
         *
         * 종료 메서드 추론
         *  - 라이브러리는 대부분 close, shutdown 이라는 이르믕로 종료 메서드를 사용한다
         *  - @Bean 의 destroyMethod 의 기본 값은 (inferred)으로 등록되어 있다
         *  - 이를 통해 close, shutdown 을 자동으로 호출해 준다 따라서 스프링 빈 등록 시 종료 메서드는 따로 넣을 필요없다
         */
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://hello-spring.dev");
            return networkClient;
        }
    }

    /* 스프링 빈은 객체 생성 -> 의존관계 주입 의 순서를 가진다 (생성자 주입 제외)
     *  - 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해 초기화 시점을 알려준다
     *  - 스프링 컨테이너가 종료되기 직저에 소멸 콜백을 통해 안전하게 종료 작업을 진행할 수 있다
     * 스프링 빈의 이벤트 라이프사이클
     *  - 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
     *
     * 객체의 생성과 초기화를 분리하자
     */

}
