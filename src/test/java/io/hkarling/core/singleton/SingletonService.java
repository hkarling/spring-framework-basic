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
 */