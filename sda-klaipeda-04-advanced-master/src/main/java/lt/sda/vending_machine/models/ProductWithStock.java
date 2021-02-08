package lt.sda.vending_machine.models;

public class ProductWithStock {
    private Product product;
    private int count;

    public ProductWithStock(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }
}
