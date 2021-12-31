package consumer.writer;

import consumer.database.Dao;
import consumer.database.RocksConn;
import models.ProductX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class WriterController {

    static final int NUMBER_OF_WRITERS = 3;
    private final List<Worker<?>> writers = new ArrayList<>();
    private final List<Thread> threads = new ArrayList<>();

    public void start(BlockingQueue<ProductX> queue) {
        for (int i = 0; i < NUMBER_OF_WRITERS; i++) {
            Worker<ProductX> writer = new Worker<>(queue, new Dao<>(new RocksConn(System.getProperty("user.home") + "/Desktop/database")));
            writers.add(writer);
            threads.add(new Thread(writer, "Writer num: " + i + "."));
        }
        threads.forEach(Thread::start);
    }

    public void stop() {
        writers.forEach(Worker::stop);
    }
}
