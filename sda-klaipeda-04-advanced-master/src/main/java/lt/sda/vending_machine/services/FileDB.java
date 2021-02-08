package lt.sda.vending_machine.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lt.sda.vending_machine.exceptions.NotEnoughCoinsException;
import lt.sda.vending_machine.models.*;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class FileDB implements DBService {
    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    private IOService ioService;
    private List<Product> products = Collections.EMPTY_LIST;
    private Map<Integer, Product> productById = new HashMap<>();
    private Map<Integer, Integer> coins = new HashMap<>();
    private Map<Product, Integer> stockByProduct = new HashMap<>();

    public FileDB(IOService ioService){
        this.ioService = ioService;
        load();
    }

    private void load(){
        Optional<Metadata> meta = loadMetadata();
        Optional<Inventory> inventory = loadInventory();
        meta.ifPresent( m -> {
            products = m.getProducts();
            productById = products.stream().collect(Collectors.toMap(p -> p.getId(), p -> p));
        });

        inventory.ifPresent( i -> {
            coins = i.coins.stream().collect(Collectors.toMap(c -> c.getId(), c -> c.getCount()));
            stockByProduct = i.products.stream().collect(Collectors.toMap(p -> productById.get(p.getId()), p -> p.getCount()));
        });
    }

    private<T> Optional<T> loadFile(String fileName, Class<T> klass) {
        URL url = getClass().getClassLoader().getResource(fileName);
        if(url == null){
            ioService.displayError("%s is not found in resources", fileName);
            return Optional.empty();
        }
        String path = url.getPath();

        try{
            FileReader fr = new FileReader(path);
            T result = gson.fromJson(fr, klass);
            if(result == null){
                ioService.displayError("Failed to load %s", fileName);
                return Optional.empty();
            }
            return Optional.of(result);
        }
        catch(FileNotFoundException fex){
            ioService.displayError("Failed to load %s", fileName);
            return Optional.empty();
        }
    }

    protected Optional<Metadata> loadMetadata(){
        return loadFile("metadata.json", Metadata.class);
    }

    protected Optional<Inventory> loadInventory(){
        return loadFile("inventory.json", Inventory.class);
    }

    protected void saveInventory() {
        URL url = getClass().getClassLoader().getResource("inventory.json");
        Inventory inventory = new Inventory();
        inventory.setProducts(stockByProduct
                .entrySet()
                .stream()
                .map( pair -> {
                    return new InventoryItem(pair.getKey().getId(), pair.getValue());
                })
                .collect(Collectors.toList())
        );
        inventory.setCoins(coins
                .entrySet()
                .stream()
                .map( pair -> {
                    return new InventoryItem(pair.getKey(), pair.getValue());
                })
                .collect(Collectors.toList())
        );

        try(Writer writer = new OutputStreamWriter(new FileOutputStream(url.getPath()), "UTF-8")) {
            gson.toJson(inventory, writer);
            writer.flush();
        }
        catch (IOException ex){
            ioService.displayError("Cannot create file", ex);
        }
    }

    @Override
    public List<ProductWithStock> getProducts() {
        return products
                .stream()
                .map( p -> new ProductWithStock(p, stockByProduct.getOrDefault(p, 0)) )
                //.filter( pw -> pw.getCount() > 0 )
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProduct(int id) {
        Product p = productById.getOrDefault(id, null);
        if(p != null){
            if(stockByProduct.get(p) == 0){
                ioService.displayError("Sorry, product: %s is not available!", p);
                return Optional.empty();
            }
            return Optional.of(p);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public int getStock(Product product) {
        return stockByProduct.getOrDefault(product, 0);
    }

    @Override
    public void reduceStock(Product p){
        int originalStock = stockByProduct.get(p);
        stockByProduct.put(p, originalStock - 1);
        saveInventory();
    }

    @Override
    public void reduceCoin(int key) {
        int originalCount = coins.getOrDefault(key, 0);
        if(originalCount == 0){
            throw new NotEnoughCoinsException();
        }

        coins.put(key, originalCount - 1);
        saveInventory();
    }

    @Override
    public void increaseCoin(int key) {
        int originalCount = coins.getOrDefault(key, 0);
        coins.put(key, originalCount + 1);
        saveInventory();
    }

    @Override
    public Map<Integer, Integer> getCoins() {
        return Collections.unmodifiableMap(coins);
    }
}
