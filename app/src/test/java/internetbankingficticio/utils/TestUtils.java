package internetbankingficticio.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestUtils {
    public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("America/Sao_Paulo");

    public static Date getDateNow() {
        Calendar now = Calendar.getInstance(TIME_ZONE);
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }

    public static ObjectMapper configureObjectMapper() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TIME_ZONE);
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).setDateFormat(simpleDateFormat);
    }

    public static <T> T parseResponse(ObjectMapper mapper, MvcResult result, Class<T> responseClass) {
        try {
            String contentAsString = result.getResponse().getContentAsString();
            return mapper.readValue(contentAsString, responseClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseResponse(ObjectMapper mapper, MvcResult result, TypeReference<T> valueTypeRef) {
        try {
            String contentAsString = result.getResponse().getContentAsString();
            return mapper.readValue(contentAsString, valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String asJsonString(ObjectMapper mapper, final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
