package com.serendipity.actiondesign.cor.demo01;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {

    List<Filter> filterList = new ArrayList<>();

    public FilterChain addFilter(Filter filter) {
        filterList.add(filter);
        return this;
    }

    @Override
    public boolean filter(Request request, Response response) {
        for (Filter filter : filterList) {
            filter.filter(request, response);
        }
        return true;
    }
}
