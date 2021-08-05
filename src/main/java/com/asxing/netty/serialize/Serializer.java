package com.asxing.netty.serialize;


import com.asxing.netty.serialize.impl.JSONSerializer;

public interface Serializer {
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return 字节
     */
    byte getSerializerAlgorithm();

    /**
     * Java 对象转化成二进制
     *
     * @param object Object 对象
     * @return 二进制数组
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成Java对象
     *
     * @param clazz Java类
     * @param bytes 二进制
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
