package transformer.utils;


import transformer.constants.ConstantsConversion;
import transformer.date.DateConversion;
import transformer.date.DateFormat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: youpengda@qq.com
 * @Description:
 */
public class TransformerVoUtils {
    private TransformerVoUtils() {
    }

    public static void transformerVo(Object valueObj) {
        Class<?> valueObjClass = valueObj.getClass();
        Field[] declaredFields = valueObjClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(DateConversion.class)) {
                DateConversion dateConversion = declaredField.getAnnotation(DateConversion.class);
                try {
                    String fieldName = declaredField.getName();
                    fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method methodGet = null;
                    try {
                        String getMethodName = "get" + fieldName;
                        methodGet = valueObjClass.getMethod(getMethodName);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (null == methodGet) {
                        continue;
                    }
                    Object value = null;
                    try {
                        value = methodGet.invoke(valueObj);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    if (!(value instanceof Date)) {
                        continue;
                    }
                    DateFormat dateFormat = dateConversion.dateFormat();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat.getDateFromat());
                    String dateDesc = simpleDateFormat.format(value);
                    Method methodSet = null;
                    try {
                        String setMethodName = "set" + fieldName + dateConversion.fieldName();
                        methodSet = valueObjClass.getMethod(setMethodName, String.class);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (null == methodSet) {
                        continue;
                    }
                    try {
                        methodSet.invoke(valueObj, dateDesc);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (declaredField.isAnnotationPresent(ConstantsConversion.class)) {
                ConstantsConversion constantsConversion = declaredField.getAnnotation(ConstantsConversion.class);
                try {
                    String fieldName = declaredField.getName();
                    fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method methodGet = null;
                    try {
                        String getMethodName = "get" + fieldName;
                        methodGet = valueObjClass.getMethod(getMethodName);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (null == methodGet) {
                        continue;
                    }
                    Object value = null;
                    try {
                        value = methodGet.invoke(valueObj);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    if (!(value instanceof String)) {
                        continue;
                    }
                    Class<?> enumClass = constantsConversion.enumClass();
                    Method methodGetNameBykey = null;
                    try {
                        methodGetNameBykey = enumClass.getMethod(constantsConversion.methodName(), String.class);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (null == methodGetNameBykey) {
                        continue;
                    }
                    Object valueName = null;
                    try {
                        valueName = methodGetNameBykey.invoke(valueObj, value);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    Method methodSet = null;
                    try {
                        String setMethodName = "set" + fieldName + constantsConversion.fieldName();
                        methodSet = valueObjClass.getMethod(setMethodName, String.class);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (null == methodSet) {
                        continue;
                    }
                    try {
                        methodSet.invoke(valueObj, valueName);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
