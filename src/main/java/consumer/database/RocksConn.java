package consumer.database;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksConn implements Connector {

    private RocksDB database;
    private final String name;

    public RocksConn(String name) {
        this.name = name;
    }

    public void put(byte[] id, byte[] data) {
        try {
            database.put(id, data); // <- PUT SERIALIZED PRODUCT TO DB
        } catch (RocksDBException e) { e.printStackTrace(); }
    }

    public byte[] get(byte[] id) {
        return new byte[0]; // irrelevant for the question
    }

    public void delete(byte[] id) {
        // irrelevant for the question
    }

    public void open() {
        Options options = new Options();
        options.setCreateIfMissing(true);
        try {
            database = RocksDB.open(options, name);
        } catch (RocksDBException e) { e.printStackTrace(); }
    }
    public void close() {
        if (database != null) {
            database.close();
            database = null;
        }
    }
    public boolean isOpen() {
        return database != null;
    }
}
