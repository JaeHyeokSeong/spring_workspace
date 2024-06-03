package hello.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
public class ServletExController {

    @GetMapping("/error-ex")
    public void errorEx() {
        log.info("/error-ex 호출");
        throw new RuntimeException("예외 발생!!");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        log.info("/error-404 호출");
        response.sendError(404, "404 오류!");
    }

    @GetMapping("/error-401")
    public void error401(HttpServletResponse response) throws IOException {
        log.info("/error-401 호출");
        response.sendError(401);
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        log.info("/error-500 호출");
        response.sendError(500);
    }

    @GetMapping("/error-501")
    public void error501(HttpServletResponse response) throws IOException {
        log.info("/error-501 호출");
        response.sendError(501);
    }
}
