package transformer.date;

/**
 * @Author: youpengda@qq.com
 * @Description:
 */
public enum DateFormat {
    ORACLE_DATETIME_FORMAT("yyyy-MM-dd HH24:mm:ss"),
    DATETIME_FORMAT("yyyy-MM-dd HH:mm:ss"),
    DATETIME_FORMAT2("yyyyMMdd HH:mm:ss"),
    DATE_FORMAT("yyyy-MM-dd"),
    YYYYMMDD("yyyyMMdd"),
    YYYYMM("yyyyMM"),
    YYYYMMDDHHMM("yyyyMMddHHmm"),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
    yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS");

    DateFormat(String dateFromat) {
        this.dateFromat = dateFromat;
    }

    private String dateFromat;

    public String getDateFromat() {
        return dateFromat;
    }

    public void setDateFromat(String dateFromat) {
        this.dateFromat = dateFromat;
    }
}
