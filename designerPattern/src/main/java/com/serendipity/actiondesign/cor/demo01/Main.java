package com.serendipity.actiondesign.cor.demo01;

public class Main {
    public static void main(String[] args) {


        Request request = new Request();
        request.str = "大家好:)，<script>，欢迎访问 mashibing.com ，大家都是996 ";
        Response response = new Response();
        response.str = "";

        FilterChain chain = new FilterChain();
        chain.addFilter(new HTMLFilter()).addFilter(new SensitiveFilter());
        chain.filter(request, response);
        System.out.println(request.str);
    }
}
