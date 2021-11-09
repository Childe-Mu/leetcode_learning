package com.moon.javase._enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author moon
 */
public class EnumTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        EnumSingleton singleton1 = EnumSingleton.getInstance();
        EnumSingleton singleton2 = EnumSingleton.getInstance();
        System.out.println("正常情况下，实例化两个实例是否相同：" + (singleton1 == singleton2));
        System.out.println("正常情况下，实例化两个实例是否相同：" + (singleton1.getT() == singleton2.getT()));
        System.out.println("正常情况下，实例化两个实例是否相同：" + (singleton1.getT().getId() == singleton2.getT().getId()));
        singleton1.doSomething();

        // 反射攻击
        // 抛出异常，枚举类无法反射
        // Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor();
        // constructor.setAccessible(true);
        // EnumSingleton singleton3 = constructor.newInstance();
        // System.out.println(singleton1 + "\n" + singleton2 + "\n" + singleton3);
        // System.out.println("通过反射攻击单例模式情况下，实例化两个实例是否相同：" + (singleton1 == singleton3));

        // 反序列化攻击
        EnumSingleton instance = EnumSingleton.getInstance();
        byte[] serialize = SerializationUtils.serialize(instance);
        EnumSingleton newInstance = SerializationUtils.deserialize(serialize);
        // 结果为true，反序列化以后仍然是同一个单例
        System.out.println(instance == newInstance);
        System.out.println(instance.getT() == newInstance.getT());
        System.out.println(instance.getT().getId() == newInstance.getT().getId());
    }

    @Getter
    enum EnumSingleton {
        /**
         * 实例
         */
        INSTANCE(new T(1));

        private final T t;

        EnumSingleton(T t) {
            this.t = t;
        }

        public static EnumSingleton getInstance() {
            return INSTANCE;
        }

        public void doSomething() {
            System.out.println("doSomething…");
        }

    }
}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class T {
    private Integer id;
}

