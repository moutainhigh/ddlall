package com.ddl.jvm.classloader.namespace_runpack;


public class Namespace {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = Namespace.class.getClassLoader();
        System.out.println(classLoader);
        Class<?> aClass = classLoader.loadClass("java.lang.String");
        Class<?> bClass = classLoader.loadClass("java.lang.String");
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());

    }
}
