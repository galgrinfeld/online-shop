package nogaliexpress;

import java.util.Comparator;

public class SellerProductComparator implements Comparator<Seller> {
    @Override
    public int compare(Seller seller1, Seller seller2) {
        return Integer.compare(seller2.getNumberOfProducts(), seller1.getNumberOfProducts());
    }   // Compare sellers bt their num of products
}