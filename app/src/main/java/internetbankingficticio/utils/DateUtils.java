package internetbankingficticio.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("America/Sao_Paulo");
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDateNow() {
        Calendar now = Calendar.getInstance(TIME_ZONE);
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }

    public static Date getDateByString(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.setTime(SIMPLE_DATE_FORMAT.parse(date));
        return calendar.getTime();
    }
}
