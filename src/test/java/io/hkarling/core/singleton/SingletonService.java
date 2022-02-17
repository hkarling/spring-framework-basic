package io.hkarling.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private SingletonService() {

    }

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 생성");
    }

}

/* 싱글톤 패턴
 * - 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
 * - 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다
 *   - private 생성자를 사용해서 외부에서 임의의 new 키워드를 사용하지 못하게 막는다
 *
 * - 싱글턴 패턴 문제점
 *   - 코드 자체가 많이들어감
 *   - 의존관계상 클라이언트가 구체 클래스에 의존 -> DIP 위반
 *   - 테스트하기 어렵다
 *   - 내부 속성을 변경하거나 초기화 하기 어렵다
 *   - private 생성자로 자식클래스를 만들기 어렵다
 *   - 결론적으로 유연성이 떨어진다
 *   - 안티패턴으로 분류되기도 한다
 *
 * 싱글톤 방식의 주의점
 * - 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는
 *   싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱그톤 객체는 상태를
 *   유지(stateful)하게 설계하면 안된다
 * - 무상태(stateless)로 설계해야 한다
 *   - 특정 클라이언트에 의존적인 필드가 있으면 안된다
 *   - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다
 *   - 가급적 읽기만 가능해야 한다
 *   - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다
 */