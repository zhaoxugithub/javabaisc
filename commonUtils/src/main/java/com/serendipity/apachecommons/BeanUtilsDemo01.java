package com.serendipity.apachecommons;

import com.serendipity.pojo.Address;
import com.serendipity.pojo.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 11931
 * @createTime: 2024/04/01 23:47
 */
public class BeanUtilsDemo01 {
    /*
        PropertyUtils 方法的使用
     */
    @Test
    public void test01() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person person = new Person();
        person.setName("Tom");
        String name = (String) PropertyUtils.getProperty(person, "name");
        System.out.println("name = " + name);

        PropertyUtils.setProperty(person, "age", 20);
        System.out.println(person);

        person.setAddress(new Address("江苏", "南京"));
        String province = (String) PropertyUtils.getProperty(person, "address.province");
        System.out.println("province = " + province);

        Address address = (Address) PropertyUtils.getProperty(person, "address");
        System.out.println("address = " + address);

        PropertyUtils.setProperty(person, "address", new Address("江苏", "苏州"));
        System.out.println(person);
    }

    //  PropertyUtils.copyProperties 是浅拷贝
    @Test
    public void test02() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person source = new Person();
        source.setName("Tom");
        source.setAge(20);
        source.setAddress(new Address("江苏", "南京"));

        Person target = new Person();
        PropertyUtils.copyProperties(source, target);
        System.out.println(target);
        System.out.println(target.getAddress() == source.getAddress());
    }

    @Test
    public void test03() throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Person person = new Person();
        person.setName("Tom");
        person.setAge(20);
        person.setAddress(new Address("江苏", "南京"));
        Person p2 = (Person) BeanUtils.cloneBean(person);
        System.out.println(p2);
        System.out.println(p2.getAddress().hashCode() == person.getAddress().hashCode());
        person.getAddress().setCity("苏州");
        // 浅拷贝
        System.out.println(p2.getAddress());
        //
        System.out.println(person.getAddress());
    }

    // map 转 bean
    @Test
    public void test04() throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 20);
        map.put("address", new Address("江苏", "南京"));

        Person person = new Person();
        BeanUtils.populate(person, map);
        System.out.println(person);
    }

    // bean 转 map
    @Test
    public void test05() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person person = new Person();
        person.setName("Tom");
        person.setAge(20);
        person.setAddress(new Address("江苏", "南京"));
        Map<String, String> describe = BeanUtils.describe(person);
        System.out.println(describe);

        describe.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
    }


    public void test06() {

    }
}
