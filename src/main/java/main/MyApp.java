package main;

import models.ProductX;
import consumer.writer.WriterController;
import producer.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyApp {

    public static void main(String[] args) {
        BlockingQueue<ProductX> queue = new ArrayBlockingQueue<>(10000000, false);

        WriterController writer = new WriterController();
        Producer producer = new Producer(queue);

        writer.start(queue);
        producer.start();
    }

}
