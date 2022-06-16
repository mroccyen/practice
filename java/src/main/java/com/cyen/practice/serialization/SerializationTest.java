package com.cyen.practice.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import com.cyen.practice.serialization.proto.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author qingshanpeng
 * @date 2022/6/15 11:00
 * @since 标果工厂
 */
public class SerializationTest {

    public static interface Serialization {
        // 序列化
        byte[] serialize(Object obj) throws IOException;

        //反序列化
        <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException, ClassNotFoundException;
    }

    public static class Person implements Serializable {
        private String name;
        private String uid;
        private String pwd;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", uid='" + uid + '\'' + ", pwd='" + pwd + '\'' + '}';
        }
    }

    public static class JdkSerialization implements Serialization {
        @Override
        public byte[] serialize(Object obj) throws IOException {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.flush();
            return bos.toByteArray();
        }

        @Override
        public <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException, ClassNotFoundException {
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (T) in.readObject();
        }
    }

    public static class FastJsonSerialization implements Serialization {

        static final String charsetName = "UTF-8";

        @Override
        public byte[] serialize(Object obj) {
            SerializeWriter out = new SerializeWriter();
            JSONSerializer serializer = new JSONSerializer(out);
            serializer.config(SerializerFeature.WriteEnumUsingToString, true);
            serializer.config(SerializerFeature.WriteClassName, true);
            serializer.write(obj);
            return out.toBytes(charsetName);
        }

        @Override
        public <T> T deserialize(byte[] bytes, Class<T> clazz) {
            return JSON.parseObject(new String(bytes), clazz);
        }
    }

    public static class KryoSerialization implements Serialization {
        @Override
        public byte[] serialize(Object obj) {
            Kryo kryo = kryoLocal.get();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Output output = new Output(byteArrayOutputStream);
            kryo.writeObject(output, obj);
            output.close();
            return byteArrayOutputStream.toByteArray();
        }

        @Override
        public <T> T deserialize(byte[] bytes, Class<T> clazz) {
            Kryo kryo = kryoLocal.get();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Input input = new Input(byteArrayInputStream);
            input.close();
            return kryo.readObject(input, clazz);
        }

        private final ThreadLocal<Kryo> kryoLocal = ThreadLocal.withInitial(() -> {
            Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.setRegistrationRequired(false);
            return kryo;
        });
    }

    public static class Hessian2Serialization implements Serialization {
        @Override
        public byte[] serialize(Object obj) throws IOException {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Hessian2Output out = new Hessian2Output(bos);
            out.writeObject(obj);
            out.flush();
            return bos.toByteArray();
        }

        @Override
        public <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
            Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(bytes));
            return (T) input.readObject(clazz);
        }
    }

    private static void jdkTest(Person person) throws IOException, ClassNotFoundException {
        Serialization serialization = new JdkSerialization();
        byte[] bytes = serialization.serialize(person);
        FileOutputStream fileOutputStream = new FileOutputStream("D:/jdk-serialization.txt");
        fileOutputStream.write(bytes);
        Person readPerson = serialization.deserialize(bytes, Person.class);
        System.out.println(readPerson);
    }

    private static void hessianTest(Person person) throws IOException, ClassNotFoundException {
        Serialization serialization = new Hessian2Serialization();
        byte[] bytes = serialization.serialize(person);
        FileOutputStream fileOutputStream = new FileOutputStream("D:/hessian-serialization.txt");
        fileOutputStream.write(bytes);
        Person readPerson = serialization.deserialize(bytes, Person.class);
        System.out.println(readPerson);
    }

    private static void kryoTest(Person person) throws IOException, ClassNotFoundException {
        Serialization serialization = new KryoSerialization();
        byte[] bytes = serialization.serialize(person);
        FileOutputStream fileOutputStream = new FileOutputStream("D:/kryo-serialization.txt");
        fileOutputStream.write(bytes);
        Person readPerson = serialization.deserialize(bytes, Person.class);
        System.out.println(readPerson);
    }

    private static void fastjsonTest(Person person) throws IOException, ClassNotFoundException {
        Serialization serialization = new FastJsonSerialization();
        byte[] bytes = serialization.serialize(person);
        FileOutputStream fileOutputStream = new FileOutputStream("D:/json-serialization.txt");
        fileOutputStream.write(bytes);
        Person readPerson = serialization.deserialize(bytes, Person.class);
        System.out.println(readPerson);
    }

    private static void protobufTest() throws IOException {
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setName("namea");
        builder.setUid("uida");
        builder.setPwd("pwda");

        PersonModel.Person person = builder.build();
        System.out.println("before name: " + person.getName());
        System.out.println("before uid: " + person.getUid());
        System.out.println("before pwd: " + person.getPwd());

        System.out.println("===Person Byte===");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }

        byte[] byteArray = person.toByteArray();
        PersonModel.Person p2 = PersonModel.Person.parseFrom(byteArray);
        System.out.println("after name: " + p2.getName());
        System.out.println("after uid: " + p2.getUid());
        System.out.println("after pwd: " + p2.getPwd());

        FileOutputStream fileOutputStream = new FileOutputStream("D:/protobuf-serialization.txt");
        fileOutputStream.write(byteArray);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setName("namea");
        person.setUid("uida");
        person.setPwd("pwda");
        jdkTest(person);
        kryoTest(person);
        hessianTest(person);
        fastjsonTest(person);
        protobufTest();
    }
}
