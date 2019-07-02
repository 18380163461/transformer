package transformer.constants;

import java.lang.annotation.*;

/**
 * @Author: youpengda@qq.com
 * @Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConstantsConversion {
    /**
     * @Description: 指定转换的枚举类
     */
    Class<?> enumClass();

    /**
     * @Description: 指定转换的枚举类中的方法
     */
    String methodName() default "getNameBykey";

    /**
     * @Description: 转换后的值存放到此属性里
     */
    String fieldName() default "Desc";


}
