package Classes;

import java.util.Comparator;

public class MaxToMinComparator implements Comparator<Product> {
    @Override
    public int compare(Product product, Product t1) {
        return t1.getPrice() - product.getPrice();
    }
}
