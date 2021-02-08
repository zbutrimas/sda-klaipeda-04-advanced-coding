package lt.sda.vending_machine.models;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    private int id;
    private int count;

    public InventoryItem(){

    }

    public InventoryItem(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
