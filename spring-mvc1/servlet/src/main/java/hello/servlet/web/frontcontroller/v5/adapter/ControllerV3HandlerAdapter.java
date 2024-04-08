package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    /**
     * parameter로 들어온 객체가 ControllerV3 타입인지 확인
     * @param object
     * @return ControllerV3 타입이면 true 아니면 false
     */
    @Override
    public boolean supports(Object object) {
        return (object instanceof ControllerV3);
    }

    /**
     * front controller에서 해당 handler를 처리할수 있는 어뎁터가 있는경우 해당 어뎁터를 통해서 호출
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);

        return controller.process(paramMap);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            paramMap.put(paramName, request.getParameter(paramName));
        }
        return paramMap;
    }
}
