package lt.sda.vending_machine.models;

import java.io.Serializable;
import java.util.List;

public class Inventory implements Serializable{
    public List<InventoryItem> products;
    public List<InventoryItem> coins;

    public Inventory(){

    }

    public List<InventoryItem> getProducts() {
        return products;
    }

    public void setProducts(List<InventoryItem> products) {
        this.products = products;
    }

    public List<InventoryItem> getCoins() {
        return coins;
    }

    public void setCoins(List<InventoryItem> coins) {
        this.coins = coins;
    }
}
