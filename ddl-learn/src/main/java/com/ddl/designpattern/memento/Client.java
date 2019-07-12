package com.ddl.designpattern.memento;

public class Client {
    public static void main(String[] args) {
        CareTaker taker = new CareTaker();

        Emp emp = new Emp("DDL", 18, 900);
        System.out.println("第一次打印对象：" + emp.getEname() + "---" + emp.getAge() + "---" + emp.getSalary());

        //备忘一次
        taker.setMemento(emp.memento());

        emp.setAge(38);
        emp.setEname("搞起");
        emp.setSalary(9000);
        System.out.println("第二次打印对象：" + emp.getEname() + "---" + emp.getAge() + "---" + emp.getSalary());

        //恢复到备忘录对象保存的状态
        emp.recovery(taker.getMemento());

        System.out.println("第三次打印对象：" + emp.getEname() + "---" + emp.getAge() + "---" + emp.getSalary());

    }
}
