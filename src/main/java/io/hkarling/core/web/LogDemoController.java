package io.hkarling.core.web;

import io.hkarling.core.common.MyLogger;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final MyLogger myLogger; // request 스코프 빈을 어플리케이션 로딩 시점에 요청. 오류남
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger; // proxy 객체가 들어온다

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass()); // class io.hkarling.core.common.MyLogger$$EnhancerBySpringCGLIB$$72de8ed2
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
