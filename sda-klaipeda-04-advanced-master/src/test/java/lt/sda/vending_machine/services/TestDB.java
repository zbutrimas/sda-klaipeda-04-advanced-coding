package lt.sda.vending_machine.services;


import lt.sda.vending_machine.models.Inventory;
import lt.sda.vending_machine.models.InventoryItem;
import lt.sda.vending_machine.models.Metadata;
import lt.sda.vending_machine.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestDB extends FileDB implements DBService {
    public TestDB(IOService ioService){
        super(ioService);
    }

    @Override
    protected Optional<Metadata> loadMetadata(){
        Metadata meta = new Metadata();
        List<Product> testProducts = new ArrayList<>();
        testProducts.add(new Product(1, "Coca-Cola", 1, "0.5l"));
        testProducts.add(new Product(2, "Lays", 2, "200g"));
        meta.setProducts(testProducts);
        return Optional.of(meta);
    }

    @Override
    protected Optional<Inventory> loadInventory(){
        Inventory inv = new Inventory();
        List<InventoryItem> productsStock = new ArrayList<>();
        productsStock.add(new InventoryItem(1, 5));
        productsStock.add(new InventoryItem(2, 4));
        List<InventoryItem> coinStock = new ArrayList<>();
        coinStock.add(new InventoryItem(1, 10));
        coinStock.add(new InventoryItem(2, 2));

        inv.setCoins(coinStock);
        inv.setProducts(productsStock);
        return Optional.of(inv);
    }

    @Override
    protected void saveInventory() {

    }
}
