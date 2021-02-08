package lt.sda.vending_machine.services;

import lt.sda.vending_machine.models.Product;

import java.util.Map;

public class CashService {
    private IOService ioService;
    private DBService dbService;

    public CashService(IOService ioService, DBService dbService) {
        this.ioService = ioService;
        this.dbService = dbService;
    }

    private int sumCoins(Map<Integer, Integer> coins){
        return coins.entrySet().stream().mapToInt(pair -> pair.getKey() * pair.getValue()).sum();
    }

    public boolean buyProduct(Product product){
        if(dbService.getStock(product) == 0){
            ioService.displayError("No more items left in stock");
            return false;
        }

        Map<Integer, Integer> coins = dbService.getCoins();

        if(sumCoins(coins) < product.getPrice()){
            ioService.displayError("Not enough funds");
            return false;
        }

        boolean success = true;
        int leftToPay = product.getPrice();
        while(leftToPay > 0) {
            ioService.displayMessage("Left to pay: %d EUR", leftToPay);
            ioService.displayMessage("Coins you have:");
            for(Map.Entry<Integer, Integer> coin : coins.entrySet()){
                ioService.displayMessage("%d) %d EUR [%d]", coin.getKey(), coin.getKey(), coin.getValue());
            }
            ioService.displayMessage("0) Cancel request");
            int selectedCoin = ioService.askForInput("Select coin");
            if(selectedCoin == 0){
                ioService.displayMessage("Canceling request");
                success = false;
                leftToPay = leftToPay - product.getPrice();
                break;
            }
            dbService.reduceCoin(selectedCoin);
            leftToPay -= selectedCoin;
        }

        if(leftToPay < 0){
            ioService.displayMessage("Your change: %d EUR", -leftToPay);
            while(leftToPay < 0){
                if((leftToPay * -1) % 5 == 0){
                    leftToPay += 5;
                    dbService.increaseCoin(5);
                }
                else if((leftToPay * -1) % 2 == 0){
                    leftToPay += 2;
                    dbService.increaseCoin(2);
                }
                else {
                    leftToPay += 1;
                    dbService.increaseCoin(1);
                }
            }
        }

        if(success){
            dbService.reduceStock(product);
        }
        return success;
    }
}
