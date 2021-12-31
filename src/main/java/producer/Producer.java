package producer;

import models.ProductX;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer {

    private static final long TEN_BILLION = 10000000000L;
    private final BlockingQueue<ProductX> queue;

    public Producer(BlockingQueue<ProductX> queue) {
        this.queue = queue;
    }

    private String produce() {
        //long start = System.nanoTime(); while(System.nanoTime() - start < 1); // dont need this cause my producer is this fast (my producer basically reads a file and transform it into a java object)
        int count = ThreadLocalRandom.current().nextInt(30, 301);
        return RandomStringUtils.randomAlphanumeric(count);
    }

    public void start() {
        long count = 0L;
        while (count < TEN_BILLION) {
            try {
                queue.put(new ProductX("Prod " + count, produce()));
                int size = queue.size(); if (size % 100000 == 0 && size != 10000000) System.out.println("queue size: " + size);
            } catch (InterruptedException e) { e.printStackTrace(); }
            count++;
        }
    }
}
