package transformer.date;

import java.lang.annotation.*;

/**
 * @Author: youpengda@qq.com
 * @Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateConversion {
    /**
     * @Description: 指定转换的日期格式
     */
    DateFormat dateFormat() default DateFormat.DATETIME_FORMAT;

    /**
     * @Description: 转换后的值存放到此属性里
     */
    String fieldName() default "Desc";
}
