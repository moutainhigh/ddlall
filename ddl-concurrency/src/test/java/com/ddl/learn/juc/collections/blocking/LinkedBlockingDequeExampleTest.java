package com.ddl.learn.juc.collections.blocking;

import com.ddl.learn.concurrency.juc.collections.blocking.LinkedBlockingDequeExample;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


/**
 * This class is used for unit test {@link LinkedBlockingDeque}
 * <p>
 * <p>
 * Double-End-Queue
 */
public class LinkedBlockingDequeExampleTest {

    @Test
    public void testAddFirst() {
        LinkedBlockingDeque<String> deque = LinkedBlockingDequeExample.create();
        deque.addFirst("Java");
        deque.addFirst("Scala");
        assertThat(deque.removeFirst(), equalTo("Scala"));
        assertThat(deque.removeFirst(), equalTo("Java"));

    }

    @Test
    public void testAdd() {
        LinkedBlockingDeque<String> deque = LinkedBlockingDequeExample.create();
        deque.add("Java");
        deque.add("Scala");
        assertThat(deque.remove(), equalTo("Java"));
        assertThat(deque.remove(), equalTo("Scala"));
    }

    @Test
    public void testTakeFirst() throws InterruptedException {
        LinkedBlockingDeque<String> deque = LinkedBlockingDequeExample.create();

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> deque.add("Deque"), 1, TimeUnit.SECONDS);
        service.shutdown();
        long currentTime = System.currentTimeMillis();
        assertThat(deque.takeFirst(), equalTo("Deque"));
        assertThat((System.currentTimeMillis() - currentTime) >= 1000, equalTo(true));
    }


}

