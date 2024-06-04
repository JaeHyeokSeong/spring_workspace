package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    public static final String APPLICATION_JSON = "application/json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if (ex instanceof UserException) {
            log.info("UserException resolver to 400");
            String acceptHeader = request.getHeader("accept");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (APPLICATION_JSON.equals(acceptHeader)) {
                HashMap<String, Object> errorResult = new HashMap<>();
                errorResult.put("ex", ex.getClass());
                errorResult.put("message", ex.getMessage());

                response.setContentType(APPLICATION_JSON);
                response.setCharacterEncoding("utf-8");

                try {
                    response.getWriter().write(objectMapper.writeValueAsString(errorResult));
                } catch (IOException e) {
                    log.error("resolver ex", e);
                }

                return new ModelAndView();
            } else {
                return new ModelAndView("error/4xx");
            }
        }

        return null;
    }
}
