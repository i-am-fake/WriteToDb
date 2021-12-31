package consumer.database;

public interface Connector {

    void put(byte[] id, byte[] data);
    byte[] get(byte[] id);
    void delete(byte[] id);
    void open();
    void close();
    boolean isOpen();

}
