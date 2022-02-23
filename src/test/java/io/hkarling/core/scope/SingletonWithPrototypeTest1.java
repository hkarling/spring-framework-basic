package io.hkarling.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

        context.close();
    }

    @Test
    void singletonClientUsePrototype() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = context.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = context.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
//        assertThat(count2).isEqualTo(2); // ClientBean 내부 prototypeBean 은 clientBean 싱글턴이 생성될 때 만들어서 주입되었다
        assertThat(count1).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

//        @Autowired
//        private ApplicationContext ac;

//        private final PrototypeBean prototypeBean;
//
//        ClientBean(PrototypeBean prototypeBean) {
//            System.out.println("ClientBean.ClientBean");
//            this.prototypeBean = prototypeBean;
//        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
//            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
        /* Dependency Lookup(DL) 의존관계 탐색
         * - 의존관계를 외부에서 주입(DI)받는게 아니라 직접 필요한 의존관계를 찾는 것
         * - 이런 식으로 어플리케이션 컨텍스트 전체를 주입받게 되면, 스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트도 어려워진다
         * - ObjectProvider 를 사용한다 (ObjectFactory 에서 편이기능 추가)
         *
         * ObjectProvider
         * - 스프링 컨테이너를 통해 빈을 찾아서 반환한다(DL)
         * - 스프링이 제공하는 기능을 사용하지만, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기 쉬워진다
         *
         * javax.inject.Provider
         * - JSR-330 Provider: 자바 표준
         * - javax.inject:javax.inject:1 라이브러리를 gradle 에 추가해야 한다
         * - get() 메서드 하나로 기능이 매우 단순하며, 표준이므로 다른 컨테이너에서도 사용할 수 있다
         */
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    /* 스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용하게 된다. 이때 싱글톤
     * 빈은 생성 시점에만 의존관계를 주입을 받기 때문에, 프로토타입 빈이 새로 생성되기는 하지만, 싱글톤 빈과
     * 함께 유지되는 것이 문제이다
     */
}
