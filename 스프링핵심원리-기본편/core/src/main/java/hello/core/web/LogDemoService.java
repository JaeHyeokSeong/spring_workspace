package hello.core.web;

import hello.core.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    @Autowired
    public LogDemoService(ObjectProvider<MyLogger> myLoggerObjectProvider) {
        this.myLoggerObjectProvider = myLoggerObjectProvider;
    }

    public void logic(String id) {
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
