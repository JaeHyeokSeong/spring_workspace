package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // start line
        response.setStatus(HttpServletResponse.SC_OK);

        // response header
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        // header 편의 메서드
        content(response);
        cookie(response);
        redirect(response);

        // read cookies
        readCookies(request);

        // message body
        response.getWriter().write("OK 안녕하세요");
    }

    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse response) {
        // key: myCookie, value: good, 유효기간 600초
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }

    /**
     * 쿠키 값 읽기
     * @param request
     */
    private void readCookies(HttpServletRequest request) {
        if(request.getCookies() != null) {
            System.out.println("--- read cookies - start ---");
            for(Cookie cookie : request.getCookies()) {
                System.out.println("cookie.getValue() = " + cookie.getValue());
            }
            System.out.println("--- read cookies - end ---");
        }
    }

    private void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/basic/hello-form.html");
    }
}
