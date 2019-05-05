package com.dztt.demoio.serial;

import java.io.*;

/**
 * 对象的序列化和反序列
 */
public class ObjSerializeAndDeserialize {

    public static  final String PRE_FILE_PATH ="/Users/zhoutaotao/Downloads/";

    public static void main(String[] args) throws Exception {
        SerializePerson();
        Person p = DeserializePerson();
        System.out.println(String.format("name:[%S],age:[%S],sex:[%S]",
                p.getName(), p.getAge(), p.getSex()));
    }

    /**
     * 序列化Person对象
     * @throws IOException
     */
    private static void SerializePerson() throws
            IOException {
        Person person = new Person();
        person.setName("name");
        person.setAge(20);
        person.setSex("男");

        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File(PRE_FILE_PATH+"Person.txt")));
        oo.writeObject(person);
        System.out.println("Person对象序列化成功");
        oo.close();
    }

    /**
     * 反序列Perons对象
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static Person DeserializePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File(PRE_FILE_PATH+"Person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功");
        return person;
    }

}