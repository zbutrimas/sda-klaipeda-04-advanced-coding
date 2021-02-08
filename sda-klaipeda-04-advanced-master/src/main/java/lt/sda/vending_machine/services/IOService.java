package lt.sda.vending_machine.services;

import lt.sda.vending_machine.models.Product;
import lt.sda.vending_machine.models.ProductWithStock;

public interface IOService {
    void displayMessage(String format, Object... arg);
    void displayObject(Object obj);
    void displayProduct(Product product);
    void displayProduct(ProductWithStock product);
    void displayError(String msg, Exception ex);
    void displayError(String msg);
    void displayError(String msg, Object... args);

    Integer askForInput(String msg);
}
