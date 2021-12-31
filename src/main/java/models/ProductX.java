package models;

import java.io.Serializable;

public class ProductX extends Product implements Serializable {

    private final String data;

    public ProductX(String id, String data) {
        super(id);
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
