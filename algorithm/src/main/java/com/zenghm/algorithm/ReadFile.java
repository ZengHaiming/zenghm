package com.zenghm.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFile {
    class Data implements Comparable<Data>{
        String name;
        String code;
        Float price;
        Integer heat;
        String type;

        public Data(String name, String code, Float price, Integer heat, String type) {
            this.name = name;
            this.code = code;
            this.price = price;
            this.heat = heat;
            this.type = type;
        }

        public float getPrice() {
            return price;
        }

        public Integer getHeat() {
            return heat;
        }

        public String getType() {
            return type;
        }

        @Override
        public int compareTo(Data o) {
            return this.price.compareTo(o.getPrice());
        }
    }
    List<String> readFile() {
        String fileName = "smallTip.txt";
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> strList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = reader.readLine()) != null) {
                strList.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strList;
    }

    List<Data> parseData(List<String> str){
        List<Data> dataList = new ArrayList<>(str.size());
        if(str.size()<2){
            return dataList;
        }
        for (int i=1;i<str.size();i++){
            String[] strings = str.get(i).split(" ");
            Data data = new Data(strings[0],strings[1],Float.valueOf(strings[2]),Integer.valueOf(strings[3]),strings[4]);
            dataList.add(data);
        }
        return dataList;
    }

    void swap(List<Data> dataList,Data data){
        if(dataList.isEmpty()){
            dataList.add(data);
            dataList.add(data);
            dataList.add(data);
        }
        if(data.getHeat()>dataList.get(0).getHeat()){
            dataList.set(0,data);
            dataList.set(2,dataList.get(1));
            dataList.set(1,dataList.get(0));
            dataList.set(0,data);
        }
    }

    Map<String,List<Data>> top3(List<Data> data){
        Map<String,List<Data>> map = new HashMap<>();
        map.put("a股", new ArrayList<>());
        map.put("港股",new ArrayList<>());
        map.put("美股",new ArrayList<>());
        data.forEach(o->swap(map.get(o.getType()),o));
        return map;
    }

    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        Map<String,List<Data>> result = readFile.top3(readFile.parseData(readFile.readFile()));
        result.values().forEach(o->o.sort(Data::compareTo));
        result.values().forEach(System.out::println);
    }

}
