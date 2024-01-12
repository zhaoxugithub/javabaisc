package com.serendipity.myold.base;
// functional/Strategize.java

import org.junit.Test;

public class StrategizeDemo {
    private interface Strategy {
        String approach(String msg);
    }

    private class Soft implements Strategy {
        @Override
        public String approach(String msg) {
            return msg.toLowerCase() + "?";
        }
    }

    private class Unrelated {
        static String twice(String msg) {
            return msg + " " + msg;
        }
    }

    private class Strategize {
        Strategy strategy;
        String msg;

        Strategize(String msg) {
            strategy = new Soft(); // [1]
            this.msg = msg;
        }

        void communicate() {
            System.out.println(strategy.approach(msg));
        }

        void changeStrategy(Strategy strategy) {
            this.strategy = strategy;
        }
    }

    @Test
    void test01() {
        // [2]
        Strategy[] strategies = {msg -> msg.toUpperCase() + "!", msg -> msg.substring(0, 5), // [3]
                Unrelated::twice // [4]
        };
        Strategize s = new Strategize("Hello there");
        s.communicate();
        for (Strategy newStrategy : strategies) {
            s.changeStrategy(newStrategy); // [5]
            s.communicate(); // [6]
        }
    }
}
