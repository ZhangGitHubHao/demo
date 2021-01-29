package com.example.study.analysisexcel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author zhangh
 * @date 2019/10/21
 */
public class ReflectionInitValue {

    private static int threadHashCodeKey=Thread.currentThread().toString().hashCode();

    /**
     * 通过反射动态将Excel读取的信息设置到对应的bean中
     *
     * @param object-存储对象bean
     * @param key-属性参数名
     * @param value-属性值
     * @throws Exception
     */
    public static void setValue(Object object, String key, String value) throws LRUCacheException {
        System.out.println("key:"+key+" ;value:"+value);
        String methodName = null;
        String paramType = null;
        Method[] methods = null;
        if (LRUCache.get(threadHashCodeKey) == null) {
            Class<?> clazz = object.getClass();
            methods = clazz.getDeclaredMethods();
            LRUCache.set(threadHashCodeKey, methods);
        } else {
            methods = LRUCache.get(threadHashCodeKey);
        }
        for (Method method : methods) {
            methodName = method.getName();
            if (methodName.startsWith("set") && methodName.toLowerCase().equals("set" + key.toLowerCase())) {
                Type[] types = method.getGenericParameterTypes();
                for (Type type : types) {
                    paramType = type.toString();
                    // 根据参数类型转化value，并进行set操作
                    excuteInvokeSetvalue(object, method, paramType, value, 0);
                }
                // 该属性已经执行setValue操作，无需循环
                break;
            }
        }
    }

    /**
     * 初始化对象bean
     *
     * @param object
     * @throws Exception
     */
    public static void initBeans(Object object) throws ResolveFileException, LRUCacheException {
        String methodName = null;
        String paramType = null;
        Method[] methods = LRUCache.get(threadHashCodeKey);
        try {
            for (Method method : methods) {
                methodName = method.getName();
                if (methodName.startsWith("set")) {
                    Type[] types = method.getGenericParameterTypes();
                    for (Type type : types) {
                        paramType = type.getClass().getName();
                    }
                    // 根据参数类型转化value，并进行set初始化属性值
                    excuteInvokeSetvalue(object, method, paramType, "", 1);
                }
            }
        } catch (Exception e) {
            throw new ResolveFileException("初始化bean错误！Method:[ " + methodName + " ]");
        }
    }

    /**
     * 根据参数类型转化value，并进行set操作
     *
     * @param object-存储对象bean
     * @param method-执行的set对应属性的方法
     * @param paramType-属性参数类型
     * @param value-属性值
     * @param operationType-操作类型(0-设置属性，1-初始化bean)
     * @throws Exception
     */
    public static void excuteInvokeSetvalue(Object object, Method method, String paramType, String value,
                                            int operationType){
        System.out.println("根据参数类型转化value，并进行set操作");
        try {
            System.out.println("paramType:"+paramType+"; value:"+value);
            switch (paramType) {
                case Common.DATA_TYPE_long:
                case Common.DATA_TYPE_Long: {
                    Long temp = Long.valueOf(operationType == 0 && value !=null ? value : "0");
                    method.invoke(object, temp);
                    break;
                }
                case Common.DATA_TYPE_boolean: {
                    // 参数属性boolean
                    boolean temp = (operationType == 0 && (Boolean.parseBoolean(value != null ? value : "false")));
                    method.invoke(object, temp);
                    break;
                }
                case Common.DATA_TYPE_int:
                case Common.DATA_TYPE_Integer: {
                    int temp = Integer.parseInt(operationType == 0 && value!=null ? value : "0");
                    method.invoke(object, temp);
                    break;
                }
                case Common.DATA_TYPE_float: {
                    // 参数属性float
                    float temp = Float.parseFloat(operationType == 0 && value !=null ? value : "0");
                    method.invoke(object, temp);
                    break;
                }
                case Common.DATA_TYPE_double: {
                    // 参数属性double
                    double temp = Double.parseDouble(operationType == 0 && value !=null ? value : "0");
                    method.invoke(object, temp);
                    break;
                }
                case Common.DATA_TYPE_Date: {
                    // 时间单独处理
                    System.out.println("calendar方法执行");
                    int temp = Integer.parseInt(operationType == 0 && value!=null ? value : "0");

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = sdf.parse("1899-12-30");
                    if (temp<60){
                        d = sdf.parse("1899-12-31");
                    }
                    if (temp==60){
                        System.out.println("1990年没有2/29号,将日期定为2/28号,这应该是Excel的bug");
                    }

                    calendar.setTime(d);

                    System.out.println(temp);
                    calendar.add(Calendar.DAY_OF_YEAR, temp);
                    Date date = calendar.getTime();
                    System.out.println("calendar方法执行");
                    method.invoke(object, date);
                    System.out.println(calendar.getTime());
                    break;
                }
                default: {
                    // 参数属性String
//                    if (value !=null && value.contains(".")){
//                        value=value.substring(0,value.lastIndexOf("."));
//                    }
                    method.invoke(object, operationType == 0 ? value : null);
                    break;
                }
            }

        } catch ( IllegalAccessException | InvocationTargetException e ) {
            throw new ResolveFileException("invoke方法错误！[Method:" + method.getName() + " [value:" + value + " ]");
        } catch (Exception e) {
            throw new ResolveFileException("字段属性错误！[Method:" + method.getName() + " [value:" + value + " ]");
        }


    }

    /**
     *
     * @param object
     * @param methodName
     * @return
     * @throws ResolveFileException
     */
    public static Object invokeClone (Object object,String methodName){
        Class clazz=object.getClass();
        try {
            Method method=clazz.getMethod(methodName);
            return method.invoke(object);
        }catch ( Exception e ){
            throw new ResolveFileException("解析Excel，反射执行set操作异常！");
        }

    }

}
