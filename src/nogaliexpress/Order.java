package nogaliexpress;

public class Order {

    private double totalPrice = 0;
    private Product[] productsList = new Product[1];
    private int numOfProducts = 0;
    private String orderDate;



    public void addProductToCart(Product product) {
        if (productsList.length == numOfProducts){
            productsList = extendArray(productsList);
        }
        productsList[numOfProducts] = product;
        numOfProducts++;
        totalPrice += product.getPrice();

    }

    public double getTotalPrice(){
        return totalPrice;
    }


    public Product[] extendArray(Product[] arr){
        Product[]  newArray = new Product[arr.length*2];
        for(int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }

    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }

    public String toString() {
        String str = "";
        for(int i = 0; i < numOfProducts; i++){
            str += i+1 +") "+ productsList[i].toString() + "\n";
        }
        return "Order Details: \n " + str;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public boolean isEmpty(){
        return numOfProducts == 0;
    }
}
