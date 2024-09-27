package com.serendipity.myold.base;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
@SuppressWarnings("all")
public class TerminationConditionDemo {

    class Book {
        boolean checkedOut = false;

        Book(boolean checkOut) {
            checkedOut = checkOut;
        }

        void checkIn() {
            checkedOut = false;
        }

        @Override
        protected void finalize() throws Throwable {
            if (checkedOut) {
                System.out.println("Error: checked out");
            }
            // Normally, you'll also do this:
            // super.finalize(); // Call the base-class version
        }
    }

    @Test
    void test() {
        Book novel = new Book(true);
        // Proper cleanup:
        novel.checkIn();
        // Drop the reference, forget to clean up:
        new Book(true);
        // Force garbage collection & finalization:
        System.gc();
    }
}