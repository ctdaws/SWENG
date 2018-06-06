import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PWSMetaTest {


    private PWSMeta pwsMeta;

    @Before
    public void setUp() throws Exception {
        String key = "key";
        String value = "value";
        pwsMeta = new PWSMeta(key, value);
    }

    @Test
    public void getKeyTest() {
        assertEquals(pwsMeta.getKey(), "key");
    }

    @Test
    public void getValueTest() {
        assertEquals(pwsMeta.getValue(), "value");
    }

    @Test
    public void toStringTest() {
        assertEquals(pwsMeta.toString(), "PWSMeta: key = 'key'\n         value = 'value'");
    }
}