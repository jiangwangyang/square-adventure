package com.github.jiangwangyang.square.adventure.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 极简、无池化 Kryo 工具类
 * 1. 每次 new Kryo，不缓存
 * 2. 不要求 POJO 实现 Serializable
 * 3. 线程安全：本类无状态，真正执行时各线程自己 new Kryo
 * <p>
 * 用法：
 * byte[] bytes = KryoUtil.write(obj);
 * T t = KryoUtil.read(bytes, Clazz.class);
 */
public final class KryoUtil {

    private KryoUtil() {
    }

    /**
     * 创建一个已配置好的 Kryo 实例
     */
    private static Kryo createKryo() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
        return kryo;
    }

    /**
     * 对象 -> 字节数组
     */
    public static byte[] writeObject(Object obj) {
        Kryo kryo = createKryo();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        kryo.writeObject(output, obj);
        output.close();
        return bos.toByteArray();
    }

    /**
     * 字节数组 -> 对象
     */
    public static <T> T readObject(byte[] bytes, Class<T> clazz) {
        Kryo kryo = createKryo();
        Input input = new Input(new ByteArrayInputStream(bytes));
        T t = kryo.readObject(input, clazz);
        input.close();
        return t;
    }

    /**
     * 对象 -> 字节数组（带类信息）
     */
    public static byte[] writeClassAndObject(Object obj) {
        Kryo kryo = createKryo();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        kryo.writeClassAndObject(output, obj);
        output.close();
        return bos.toByteArray();
    }

    /**
     * 字节数组 -> 对象（需配合 writeClassAndObject）
     */
    @SuppressWarnings("unchecked")
    public static <T> T readClassAndObject(byte[] bytes) {
        Kryo kryo = createKryo();
        Input input = new Input(new ByteArrayInputStream(bytes));
        T t = (T) kryo.readClassAndObject(input);
        input.close();
        return t;
    }
}
