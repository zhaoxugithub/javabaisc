package com.serendipity.chapter04.example02;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {
    private final AtomicLong value = new AtomicLong();

    @Override
    public long incrementAndGet() {
        return value.incrementAndGet();
    }

    @Override
    public long get() {
        return value.get();
    }
}
