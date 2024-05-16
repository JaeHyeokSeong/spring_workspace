package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String MY_SESSION_ID = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    public void createSession(Object value, HttpServletResponse response) {

        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        Cookie mySessionCookie = new Cookie(MY_SESSION_ID, sessionId);
        response.addCookie(mySessionCookie);
    }

    public Object getSession(HttpServletRequest request) {
        Cookie cookie = findCookie(request, MY_SESSION_ID);
        if (cookie == null) {
            return null;
        }

        return sessionStore.get(cookie.getValue());
    }

    public void expire(HttpServletRequest request) {
        Cookie cookie = findCookie(request, MY_SESSION_ID);
        if (cookie != null) {
            sessionStore.remove(cookie.getValue());
        }
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName) {

        /*Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }

        return null;*/

        if (request.getCookies() == null) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

}
