package com.zenghm.common;

import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.dyuproject.protostuff.Schema;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create date:2018/04/21.
 * Created by: Airlen.
 * Class name:SchemaCache.
 * @author Airlen
 */
public class SchemaCache {
    private Logger logger = LoggerFactory.getLogger(SchemaCache.class);
    private SchemaCache(){
    }
    private static class SchemaCacheHolder{
        private static SchemaCache schemaCache = new SchemaCache();
    }
    public static SchemaCache getInstance(){
        return SchemaCacheHolder.schemaCache;
    }
    //占用内存大小512M
    private Cache<Class<?>,Schema<?>> cache = CacheBuilder.newBuilder().maximumSize(1024*1024*512)
            .expireAfterAccess(1, TimeUnit.HOURS).build();

    private Schema<?> get(final Class<?> clazz,Cache<Class<?>,Schema<?>> cache){
        try {
            return cache.get(clazz, new Callable<Schema<?>>() {
                @Override
                public Schema<?> call() throws Exception {
                    return RuntimeSchema.createFrom(clazz);
                }
            });
        } catch (ExecutionException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }
    public Schema<?> get(final Class<?> clazz){
        return get(clazz,cache);
    }
}
