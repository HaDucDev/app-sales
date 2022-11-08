package hcmute.edu.vn.mssv18110273.appbanhang.util;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringFormatUtils
{

    public static String convertToStringMoneyVND(long money) {
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(money) + " đ";
    }

    public static String getCurrentDateStr() // ham hien thi ngay gio va dinh dang cua no
    {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
        return date;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || "".equals(s.trim());
    }
}
