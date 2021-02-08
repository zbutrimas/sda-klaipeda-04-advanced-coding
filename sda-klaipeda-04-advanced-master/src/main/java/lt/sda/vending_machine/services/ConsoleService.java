package lt.sda.vending_machine.services;

import lt.sda.vending_machine.models.Product;
import lt.sda.vending_machine.models.ProductWithStock;

import java.util.Scanner;

public class ConsoleService implements IOService{
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMessage(String format, Object... args) {
        System.out.printf(format + "\n", args);
    }

    @Override
    public void displayObject(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void displayProduct(Product product) {
        displayMessage(
                "%d) %s - %d EUR (%s)",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getSize()
        );
    }

    @Override
    public void displayProduct(ProductWithStock productWithStock) {
        Product product = productWithStock.getProduct();
        displayMessage(
                "%d) %s - %d EUR (%s) [%d]",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getSize(),
                productWithStock.getCount()
        );
    }

    @Override
    public void displayError(String msg, Exception ex) {
        System.err.println(msg);
        ex.printStackTrace();
    }

    @Override
    public void displayError(String msg) {
        System.err.println(msg);
    }

    @Override
    public void displayError(String msg, Object... args) {
        System.err.printf(msg + "\n", args);
    }

    @Override
    public Integer askForInput(String msg) {
        displayMessage(msg + ": ");
        return scanner.nextInt();
    }
}
