package com.zenghm.experiment.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Airlen
 * @date 2021/4/21
 * @description xxx
 */
public class ReflectTest {
    public static void main(String [] args) {
        for (Method method : MethodClass.class.getMethods()) {
            if(!method.getName().equals("getNumbers")){
                continue;
            }
            System.out.println("Method name: " + method.getName());
            Type type = method.getGenericReturnType();

            System.out.println("Return type: " + type.getTypeName());
            if (type instanceof ParameterizedType)
            {
                ParameterizedType pt = (ParameterizedType) type;
                System.out.println("Parameterized: " + pt.getRawType());
                for (Type arg : pt.getActualTypeArguments())
                {
                    System.out.println("  " + arg);
                }
            }

            Type[] paramsType  = method.getGenericParameterTypes();
            System.out.println("Method  Parameter type: " + type.getTypeName());
            for (Type type1:paramsType){
                if (type1 instanceof ParameterizedType)
                {
                    ParameterizedType pt2 = (ParameterizedType) type1;
                    System.out.println("Parameterized: " + pt2.getRawType());
                    System.out.println("判断："+pt2.getRawType().equals(List.class));
                    for (Type arg : pt2.getActualTypeArguments())
                    {
                        //System.out.println("是否基础数据类型：" + );

                        System.out.println("  " + arg);

                        System.out.println("  " + arg.equals(com.zenghm.experiment.reflect.ParamTestDto.class));
                        //try {
                            //Object obj = Class.forName(arg.getTypeName());
                            //System.out.println("参数对象："+obj);
                        //} catch (ClassNotFoundException e) {
                        //    e.printStackTrace();
                        //}
                    }
                }
            }

            Class<?>[] ParameterTypes = method.getParameterTypes();
            for (Class c:ParameterTypes){
                System.out.println("Parameterized --- : " + c.getName());
            }
        }
    }
}
