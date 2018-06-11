package com.zenghm.common;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.google.common.base.Preconditions;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;


/**
 * Create date:2018/04/20.
 * Created by: Airlen.
 * Class name:SerializationUtil.
 * Description: 序列化工具
 */
public class SerializationUtil {
    private static SchemaCache schemaCache = SchemaCache.getInstance();
    private static Objenesis objenesis = new ObjenesisStd(true);
    private SerializationUtil(){}
    private static <T> Schema<T> getSchema(Class<T> clazz){
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) schemaCache.get(clazz);
        return schema;
    }
    public static <T> byte[] serialize(T obj){
        Preconditions.checkNotNull(obj);
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer =LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj,schema,buffer);
        } finally {
            buffer.clear();
        }
    }
    public static <T> T deserialize(byte[] data,Class<T> clazz){
        Preconditions.checkNotNull(data);
        Preconditions.checkNotNull(clazz);
        @SuppressWarnings("unchecked")
        T obj = (T) objenesis.newInstance(clazz);
        Schema<T> schema = getSchema(clazz);
        ProtostuffIOUtil.mergeFrom(data,obj,schema);
        return obj;
    }
}
