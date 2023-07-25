package com.serendipity.action_design.cor.demo01;

public class SensitiveFilter implements Filter {
    @Override
    public boolean filter(Request request, Response response) {
        request.str = request.str.replaceAll("996", "955");
        return true;
    }
}
