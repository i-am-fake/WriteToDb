package consumer.writer;

import consumer.database.Dao;
import models.Product;

import java.util.concurrent.BlockingQueue;

public class Worker<T extends Product> implements Runnable {

    private boolean running;
    private final BlockingQueue<T> queue;
    private final Dao<T> dao;

    public Worker(BlockingQueue<T> queue, Dao<T> dao) {
        running = false;
        this.queue = queue;
        this.dao = dao;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        running = true;
        dao.openDb();
        while (running) {
            try {
                dao.addProduct(queue.take()); // <- CONSUMING FROM QUEUE
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dao.closeDb();
    }
}
