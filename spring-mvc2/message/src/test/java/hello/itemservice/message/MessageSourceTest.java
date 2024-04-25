package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest{

    @Autowired
    MessageSource ms;

    @Test
    @DisplayName("code 존재 O")
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        Locale defaultLanguage = Locale.getDefault();
        Assertions.assertThat(result).isEqualTo("hello");
    }

    @Test
    @DisplayName("code 존재 X")
    void notFoundMessageCode() {
        Assertions.assertThatThrownBy(() -> {ms.getMessage("no_code", null, null);})
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @DisplayName("code 존재 X 기본 메시지 사용")
    void notfoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "basic message", null);
        Assertions.assertThat(result).isEqualTo("basic message");
    }

    @Test
    @DisplayName("매개변수 사용")
    void argumentMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        Assertions.assertThat(result).isEqualTo("hello Spring");
    }

    @Test
    @DisplayName("여러 매개변수 사용")
    void multiArgumentsMessage() {
        String result = ms.getMessage("hello.names", new Object[]{"1", "2", "3"}, null);
        Assertions.assertThat(result).isEqualTo("hello 1, 2, 3");
    }

    @Test
    @DisplayName("기본 언어 그리고 한국어로 조회")
    void defaultLang() {
        Assertions.assertThat(ms.getMessage("hello", null, null)).isEqualTo("hello");
        Assertions.assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }

    @Test
    @DisplayName("한국어로 조회")
    void koLang() {
        Assertions.assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    @DisplayName("여러 매개변수 사용 - 한국어")
    void koLangMultiArgumentsMessage() {
        String result = ms.getMessage("hello.names", new Object[]{"1", "2", "3"}, Locale.KOREA);
        Assertions.assertThat(result).isEqualTo("안녕 1, 2, 3");
    }
}
