package nogaliexpress;

import java.util.Comparator;

public class BuyerNameComparator implements Comparator<Buyer> {
    @Override
    public int compare(Buyer buyer1, Buyer buyer2) {
        return buyer1.getUserName().compareTo(buyer2.getUserName());
    }
}
