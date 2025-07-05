package com.atguigu.groovylearn.base

class BaseDemo01 {

    static void test1() {
        def map = [:]

        map."an identifier with a space and double quotes" = "ALLOWED"
        map.'with-dash-signs-and-single-quotes' = "ALLOWED"

        assert map."an identifier with a space and double quotes" == "ALLOWED"
        assert map.'with-dash-signs-and-single-quotes' == "ALLOWED"

        map.'single quote'
        map."double quote"
        map.'''triple single quote'''
        map."""triple double quote"""
        map./slashy string/
        map.$/dollar slashy string/$

        println(map)
    }

    static void test2() {
        def map = [:]
        def firstname = "Homer"
        map."Simpson-${firstname}" = "Homer Simpson"
        assert map.'Simpson-Homer' == "Homer Simpson"
    }

    static void test3() {
        println("cmd /C dir")
    }

    static void main(String[] args) {
        test1()
        test2()
        test3()
    }
}
