package lt.sda.vending_machine.models;

import java.io.Serializable;
import java.util.List;

public class Metadata implements Serializable {
    private List<Product> products;

    public Metadata(){

    }

    public Metadata(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
