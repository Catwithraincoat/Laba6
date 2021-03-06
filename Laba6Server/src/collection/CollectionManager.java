package collection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashSet;


public class CollectionManager implements Serializable {
    private static HashSet<Product> goods;
    private static CollectionManager instance;
    private final LocalDateTime localDateTime;
    public final String PATH = "final.csv";


    public CollectionManager() {
        goods = new HashSet<>();
        localDateTime = LocalDateTime.now();
    }

    public static CollectionManager getCollection() {
        if (instance == null) {
            instance = new CollectionManager();
        }
        return instance;
    }
    public HashSet<Product> getAllElements() {
        return goods;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void add(Product product){
        goods.add(product);
    }
    public void clear() {
        goods.clear();
    }
    public void remove(Product product){
        goods.remove(product);
    }
    public String getManufacturer(){
        String s = "";
        for (Product product :goods){
            s += product.getManufacturer();
        }
        return s;
    }
    public void saveToFile(){
        File outputFile = new File(PATH);
        try {
            outputFile.createNewFile();
            StringBuilder csv = new StringBuilder();
            for (Product product : goods) {
                String[] row = product.getAll();
                csv.append(String.join(",", row));
                csv.append("\n");
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(PATH));
            out.write(String.join("", csv).getBytes(StandardCharsets.UTF_8));
            out.close();
        } catch (IOException e){
            System.out.println("Не удалось сохранить в файл");
        }
    }
    public boolean isEmpty(){
        return goods.isEmpty();
    }
}