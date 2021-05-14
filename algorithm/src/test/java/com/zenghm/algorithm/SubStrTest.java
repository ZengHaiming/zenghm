package com.zenghm.algorithm;

/**
 * @author Airlen
 * @date 2021/5/13
 * @description xxx
 */
public class SubStrTest {
    private static String TOPPACKAGE = "com.fingard.ats.";
    public static void main(String[] args) {
        Test1();
    }

    public static void Test1(){
        String defaultGenName = "baseCategoryController";
        String className = "com.fingard.ats.financing.web.controller.basicsettings.BaseCategoryController";
        String microserviceCode = "sys";
        int topPackageIndex = className.indexOf(TOPPACKAGE);
        if(topPackageIndex>=0){
            int microserviceIndex = className.indexOf(".",topPackageIndex+TOPPACKAGE.length());
            if(microserviceIndex>0){
                microserviceCode = className.substring(topPackageIndex+TOPPACKAGE.length(),microserviceIndex);
            }
        }
        String beanName = String.format("%s_%s",microserviceCode,defaultGenName);
        System.out.println(beanName);
    }
}
