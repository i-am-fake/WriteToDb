package consumer.database;

import models.Product;
import org.apache.commons.lang3.SerializationUtils;

public class Dao<T extends Product> {

    private final Connector db;

    public Dao(Connector db) {
        this.db = db;
    }

    public void openDb() {
        if (!db.isOpen()) db.open();
    }

    public void addProduct(T product) { // <- SERIALIZED CONSUMED PRODUCT
        db.put(product.getId().getBytes(), SerializationUtils.serialize(product));
    }

    // ... there are more methods (get, delete, ...) but not relevant for the question

    public void closeDb() {
        db.close();
    }
}
