package com.serendipity.myold.base;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 16:33
 * FileName: Streams
 * Description: com.java8.base
 */
public class Streams {

    private enum Status{
        OPEN,CLOSED
    }

    private static final class Task{

        private final Status status;
        private final Integer ponints;

        Task(final Status status,final Integer points){
            this.ponints = points;
            this.status = status;
        }

        public Integer getPonints() {
            return ponints;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "status=" + status +
                    ", ponints=" + ponints +
                    '}';
        }
    }

    public static void main(String[] args) {

        final List<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8)
        );

        final int sum = tasks
                .stream()
                .filter(task -> task.status == Status.OPEN)
                .mapToInt(Task::getPonints).sum();
        System.out.println("Total points: "+ sum);
    }
}
