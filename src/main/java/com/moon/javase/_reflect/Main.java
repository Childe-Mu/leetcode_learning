package com.moon.javase._reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author admin
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        // 获取TargetObject类的Class对象并且创建TargetObject类实例
        Class<?> targetClass = Class.forName("com.moon.javase._reflect.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();
        // 获取所有类中所有定义的方法
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        // 获取指定方法并调用
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        try {
            publicMethod.invoke(targetObject, "JavaGuide");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 获取指定参数并对参数进行修改
        Field field = targetClass.getDeclaredField("value");
        //为了对类中的参数进行修改我们取消安全检查
        field.setAccessible(true);
        field.set(targetObject, "JavaGuide1");
        // 调用 private 方法
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        //为了调用private方法我们取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
    }
}
