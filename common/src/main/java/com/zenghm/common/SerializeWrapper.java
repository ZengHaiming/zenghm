package com.zenghm.common;

/**
 * Protostuff直接序列化集合类时会报错，
 * 增加包装类可以兼容所有类型
 * @author Airlen
 * @date 2018/07/06
 */
public class SerializeWrapper<T> {
    private T content;

    public static <T> SerializeWrapper<T> builder(T content) {
        SerializeWrapper<T> wrapper = new SerializeWrapper<T>();
        wrapper.content = content;
        return wrapper;
    }

    public T getContent() {
        return content;
    }
}
