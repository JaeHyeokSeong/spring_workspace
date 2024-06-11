package hello.upload.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

public class HashMapNullTest {

    @Test
    void HashMapNull() {
        HashMap<Long, String> map = new HashMap<>();

        map.put(null, "null");
        map.put(1L, "one");

        assertThat(map.get(null)).isEqualTo("null");

        map.put(null, "updated");

        assertThat(map.get(null)).isNotEqualTo("null");
        assertThat(map.get(null)).isEqualTo("updated");
    }
}
