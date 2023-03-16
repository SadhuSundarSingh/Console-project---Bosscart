package Classes;

import java.util.Comparator;

public class MinToMaxComparator implements Comparator<Product> {
    @Override
    public int compare(Product product, Product t1) {
        return product.getPrice() - t1.getPrice();
    }
}
