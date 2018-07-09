package com.zenghm.common;


import java.math.BigDecimal;
import java.util.*;

/**
 * @author Airlen
 * @date 2018/07/06
 * Class name:SerializationUtilTest
 */
public class SerializationUtilTest {
    @org.junit.Test
    public void serialize() throws Exception {
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("1234","1213213");
        map.put("adasdas",123213);
        map.put("dsadas",new Date());
        map.put("dadasd",new BigDecimal(12312));
        map.put("dsadade",1213.123f);
        list.add(map);
        byte[] bArr = SerializationUtil.serialize(list);
        List<Map<String,Object>> result = SerializationUtil.deserialize(bArr);
        System.out.print(result);
    }

    @org.junit.Test
    public void deserialize() throws Exception {
        //List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("1234","1213213");
        map.put("adasdas",123213);
        map.put("dsadas",new Date());
        map.put("dadasd",new BigDecimal(12312));
        map.put("dsadade",1213.123f);
        //list.add(map);
        byte[] bArr = SerializationUtil.serialize(map);
        Map<String,Object> result = SerializationUtil.deserialize(bArr);
        System.out.print(result);
    }

    @org.junit.Test
    public void deserialize2() throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("1234","1213213");
        map.put("adasdas",123213);
        map.put("dsadas",new Date());
        map.put("dadasd",new BigDecimal(12312));
        map.put("dsadade",1213.123f);
        //list.add(map);
        byte[] bArr = SerializationUtil.serialize(map);
        Map<String,Object> result2 = SerializationUtil.deserialize(bArr);
        System.out.print(result2);

        Obj1 obj1 = new Obj1();
        obj1.setStr1("str11");
        obj1.setStr2("str12");
        obj1.setStr3("str13");
        obj1.setStr4("str14");
        obj1.setStr5("str15");
        obj1.setInt1(1);
        obj1.setInt2(2);
        obj1.setDate1(new Date());
        Obj2 obj2 = new Obj2();
        obj2.setStr1("str21");
        obj2.setStr2("str22");
        obj2.setStr3("str23");
        obj2.setStr4("str24");
        obj2.setStr5("str25");
        obj2.setInt1(1);
        obj2.setInt2(2);
        obj2.setDate1(new Date());
        obj1.setObj2(obj2);
        Obj3 obj3 = new Obj3();
        obj3.setStr1("str31");
        obj3.setStr2("str32");
        obj3.setStr3("str33");
        obj3.setStr4("str34");
        obj3.setStr5("str35");
        obj3.setInt1(1);
        obj3.setInt2(2);
        obj3.setDate1(new Date());
        obj1.setObj3(obj3);
        Obj1 result = SerializationUtil.deserialize(SerializationUtil.serialize(obj1));
        System.out.println(result);
    }

}
