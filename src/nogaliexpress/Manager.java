package nogaliexpress;
import java.util.Arrays;


public class Manager {
    private static Seller [] sellersList = new Seller[1];
    private static int numSellers = 0;
    private static Buyer [] buyersList = new Buyer[1];
    private static int numBuyers = 0;

    public static Buyer[] getBuyersList() {
        return buyersList;
    }

    public static Seller[] getSellersList() {
        return sellersList;
    }

    public static int getNumBuyers() {
        return numBuyers;
    }
    public static int getNumSellers() {
        return numSellers;
    }

    public static void sortBuyersByName() {
        Arrays.sort(buyersList, 0, numBuyers, new BuyerNameComparator());
    }

    public static void sortSellersByNumberOfProducts() {
        Arrays.sort(sellersList, 0, numSellers, new SellerProductComparator());
    }




    public static String [] getAllSellersNames(){
        String [] sellersNames = new String[numSellers];
        for(int i = 0; i < numSellers; i++){
            if(sellersList[i] != null) {
                sellersNames[i] = sellersList[i].getUserName();
            }

        }
        return sellersNames;
    }

    public static String [] getAllBuyersNames(){
        String [] buyersNames = new String[numBuyers];
        for(int i = 0; i < numBuyers; i++){
            if(buyersList[i] != null) {
                buyersNames[i] = buyersList[i].getUserName();
            }
        }
        return buyersNames;
    }

    //CHECKED
    public static void printAllSellersNames(){

        if(numSellers == 0){
            System.out.println("No Sellers found.");
            return;
        }

        for (int i=0;i<numSellers;i++){
            System.out.println( i+1 + ") " + sellersList[i].getUserName());
        }
    }
    //CHECKED
    public static void printAllBuyersNames(){

        if(numBuyers == 0){
            System.out.println("No Buyers found.");
            return;
        }

        for (int i=0;i<numBuyers;i++){
            System.out.println( i+1 + ") " +buyersList[i].getUserName());
        }
    }



    public static Seller[] extendSellersArray(Seller[] arr){
        Seller[]  newArray = new Seller[arr.length*2];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }

    public static Buyer[] extendBuyersArray(Buyer[] arr) {
        Buyer[] newArray = new Buyer[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }

    public static void addSellerToList(Seller seller) {
        if (sellersList.length == numSellers){ // check if there is no free space in sellersNames array
            System.out.println("you reached the array limit so i doubled it:)");
            sellersList = extendSellersArray(sellersList);
        }
        sellersList[numSellers] = seller;
        numSellers ++;

    }


    public static void addBuyerToList(Buyer buyer) {
        if (buyersList.length == numBuyers){ // check if there is no free space in sellersNames array
            System.out.println("you reached the array limit so i doubled it:)");
            buyersList = extendBuyersArray(buyersList);
        }
        buyersList[numBuyers] = buyer;
        numBuyers++;
        sortBuyersByName();
    }

    public static int getSellerIndexByName(String sellerName){
        for (int i = 0; i < numSellers; i++) {
            if (sellersList[i].getUserName().equals(sellerName)){
                return i;
            }

        }
        return -1;
    }


    public static int getBuyerIndexByName(String buyerName){
        for (int i = 0; i < numBuyers; i++) {
            if (buyersList[i].getUserName().equals(buyerName)){
                return i;
            }
        }
        return -1;
    }

    public static void addProductToSellersList(Product product, int sellerIndex){
        sellersList[sellerIndex].addProductToSeller(product);
        sortSellersByNumberOfProducts();
    }

    public static void addProductToBuyerCart(Product product, int buyerIndex){
        buyersList[buyerIndex].addProductToCart(product);
    }

    public static Product.Category getEnumFromString(String categoryStr) {
        try {
            return Product.Category.valueOf(categoryStr.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public static void printAllPreviousOrdersByBuyer(int buyerIndex){
        for (int i = 0; i < buyersList[buyerIndex].getNumOfPreviousOrders(); i++) {
            System.out.println(i+1 +") " + buyersList[buyerIndex].getPreviousOrders()[i].toString());
        }
    }


}
