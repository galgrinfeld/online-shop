package nogaliexpress;

public class Buyer {

    private String userName;
    private String password;
    private String street;
    private String city;
    private String state;
    private int buildingNumber;
    private Order[] previousOrders = new Order[1];
    private int numOfPreviousOrders = 0;
    private Order currentOrder = new Order();


    public Buyer(String userName, String password, String street, String city, String state, int buildingNumber) {
        this.userName = userName;
        this.password = password;
        this.street = street;
        this.city = city;
        this.state = state;
        this.buildingNumber = buildingNumber;

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getNumOfPreviousOrders() {
        return numOfPreviousOrders;
    }

    public void addProductToCart(Product product) {
        currentOrder.addProductToCart(product);
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void clearCurrentOrder(){
        currentOrder = new Order();
    }

    public void moveOrderToPreviousOrders() {
        if (numOfPreviousOrders == previousOrders.length) {
            previousOrders = extendPreviousOrdersArray(previousOrders);
        }
        previousOrders[numOfPreviousOrders] = currentOrder;
        numOfPreviousOrders++;
        clearCurrentOrder();
    }

    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

    public static Order[] extendPreviousOrdersArray(Order[] arr) {
        Order[] newArray = new Order[arr.length * 2];
         for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }


    public String toString(){
        String address = this.city + " " + this.state + " " +this.street + " " +this.buildingNumber;
        String str = "";

        if(numOfPreviousOrders ==0){
            str = "No previous orders found";
        }
        else {
            for (int i = 0; i < numOfPreviousOrders; i++) {
                str += i + 1 + ") Ordered On: " + previousOrders[i].getOrderDate() + "\n " + previousOrders[i].toString();
            }
        }
        String currOrder;
        if(currentOrder.isEmpty()){
            currOrder = "Shopping cart is empty at the moment.";
        }
        else{
            currOrder = currentOrder.toString();
        }
        return "Buyer Username: " + userName + "\nBuyer Password: " + password + "\nBuyer Address: " + address + "\nCorrent Order: \n" + currentOrder.toString() + "\nPreviousOrders: \n" + str;
    }

    public Order[] getPreviousOrders() {
        return previousOrders;
    }
}

