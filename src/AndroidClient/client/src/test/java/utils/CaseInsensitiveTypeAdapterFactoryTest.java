package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import junit.framework.TestCase;

import net.servicestack.client.JsonSerializers;

import static org.junit.Assert.assertEquals;

enum SodaEnum
{
    Coke,
    Pepsi,
}

@RunWith(JUnitParamsRunner.class)
public class CaseInsensitiveTypeAdapterFactoryTest {

    private Gson gson;

    @Before
    public void forEachTest() {
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(new JsonSerializers.CaseInsensitiveEnumTypeAdapterFactory())
                .create();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Object[] validDeserializationTests() {
        return new Object[]{
                new Object[]{"Pascal case", "Coke", SodaEnum.Coke},
                new Object[]{"Weird mixed case", "cOKe", SodaEnum.Coke},
                new Object[]{"lowercase", "pepsi", SodaEnum.Pepsi},
                new Object[]{"UPPERCASE", "PEPSI", SodaEnum.Pepsi},
        };
    }

    @Test
    @Parameters(method = "validDeserializationTests")
    public void fromJson_withValidText_ConvertsAsExpected(String reason, String jsonText, SodaEnum expected) {
        SodaEnum actual = gson.fromJson(jsonText, SodaEnum.class);

        assertEquals(reason, expected, actual);
    }

    private Object[] invalidDeserializationTests() {
        return new Object[]{
                new Object[]{"Null should not deserialize", null},
                new Object[]{"Unknown type", "SevenUp"},
        };
    }

    @Test
    @Parameters(method = "invalidDeserializationTests")
    public void fromJson_withInvalidText_ReturnsNull(String reason, String jsonText){
        SodaEnum actual = gson.fromJson(jsonText, SodaEnum.class);
        assertEquals(reason, null, actual);
    }

    private Object[] validSerializationTests() {
        return new Object[]{
                new Object[]{SodaEnum.Coke, "\"Coke\""},
                new Object[]{SodaEnum.Pepsi, "\"Pepsi\""},
        };
    }

    @Test
    @Parameters(method = "validSerializationTests")
    public void toJson_ConvertsAsExpected(SodaEnum value, String expected) {
        String actual = gson.toJson(value);

        assertEquals("Serialized to json", expected, actual);
    }
}
