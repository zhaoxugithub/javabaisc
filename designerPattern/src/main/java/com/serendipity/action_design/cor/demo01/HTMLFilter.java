package com.serendipity.action_design.cor.demo01;

public class HTMLFilter implements Filter {
    @Override
    public boolean filter(Request request, Response response) {
        request.str = request.str.replaceAll("<", "[").replaceAll(">", "]");
        return true;
    }
}
