package lt.sda.vending_machine.services;

import lt.sda.vending_machine.models.Product;
import lt.sda.vending_machine.models.ProductWithStock;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DBService {
    List<ProductWithStock> getProducts();

    Optional<Product> getProduct(int id);
    int getStock(Product product);
    void reduceStock(Product p);
    void reduceCoin(int key);
    void increaseCoin(int key);

    Map<Integer, Integer> getCoins();
}
