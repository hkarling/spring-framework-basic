package io.hkarling.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");

        /* 생성자 호출, url = null
         * connect:null
         * call:null message:초기화 연결 메시지 */
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect:" + url);
    }

    public void call(String message) {
        System.out.println("call:" + url + " message:" + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close:" + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }

    /**
     * 초기화, 소멸 인터페이스 단점
     *  - 스프링 전용 인터페이스에 의존한다
     *  - 초기화, 소멸 메서드의 이름 변경할 수 없다
     *  - 내가 코드를 고칠 수 없는 외부 라이브러리에 적용 불가
     */
}
