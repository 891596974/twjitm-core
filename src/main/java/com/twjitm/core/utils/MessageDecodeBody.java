package com.twjitm.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ��Ϣ���봦����
 */
public class MessageDecodeBody {
    //�����ȡproto�����������
    private static String clzzName="com.twjitm.core.proto.BaseMessageProto";

    public static void init() {
        try {
            Class clzz = ClassLoader.getSystemClassLoader().loadClass(clzzName);
            Class[]  clzzarray = clzz.getClasses();
            for(Class clzz1:clzzarray){
                Method[] meth = clzz1.getMethods();
               for(Method method:meth){
                   System.out.println( method.getName());
               }
            }
            Field[] array = clzz.getDeclaredFields();
                  for (Field field:array){

                  }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init();
    }
}
