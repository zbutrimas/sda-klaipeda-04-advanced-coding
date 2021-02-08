package lt.sda.vending_machine;

import lt.sda.vending_machine.models.Product;
import lt.sda.vending_machine.models.ProductWithStock;
import lt.sda.vending_machine.services.*;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args){
        IOService ioService = new ConsoleService();
        DBService dbService = new FileDB(ioService);
        CashService cashService = new CashService(ioService, dbService);

        while(true){
            List<ProductWithStock> productList = dbService.getProducts();

            ioService.displayMessage("Products Menu:");
            for(ProductWithStock p : productList){
                ioService.displayProduct(p);
            }

            ioService.displayMessage("0) To Exit");

            int productId = ioService.askForInput("Please select product");
            if(productId == 0){
                break;
            }

            Optional<Product> result = dbService.getProduct(productId);
            if(result.isPresent()){
                if(cashService.buyProduct(result.get())){
                    ioService.displayMessage("Here you go!");
                }
                else {
                    ioService.displayError("Unable to buy product.");
                }
            }
            else {
                ioService.displayError("Incorrect product Id");
            }
        }
        ioService.displayMessage("Good bye!");
    }
}
