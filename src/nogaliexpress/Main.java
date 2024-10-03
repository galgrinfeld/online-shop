package nogaliexpress;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    static Scanner s = new Scanner(System.in);
    public static String[] extendArray(String[] arr){
        String[]  newArray = new String[arr.length*2];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }

    public static void printMenu(){
        System.out.println();
        System.out.println("0- EXIT");
        System.out.println("1- Add Seller");
        System.out.println("2- Add Buyer");
        System.out.println("3- Add Product To Seller");
        System.out.println("4- Add Product To Buyer");
        System.out.println("5- Process Payment");
        System.out.println("6- View All Buyers");
        System.out.println("7- View All Sellers");
        System.out.println("8- View All Products in category");
        System.out.println("9- Create Order From Previous Order");
        System.out.println("\nEnter your choice: ");
    }

    public static boolean stringExistsInArray(String[] arr, String targetString) {
        for (String element : arr) {
            if (element != null && element.equalsIgnoreCase(targetString)) {
                return true; // String found, return true
            }
        }
        return false; // String not found, return false
    }

    // CHECKED: Works perfectly
    public static void addSeller(){
        s.nextLine();
        System.out.println("Enter Seller's UserName: ");
        String sellerName = s.nextLine();

        while (stringExistsInArray(Manager.getAllSellersNames(), sellerName)) {
            System.out.println("The Name " + sellerName + " is already exists. Please enter another name: ");
            sellerName = s.nextLine();
        }

        System.out.println("Enter Seller's Password: ");
        String password = s.nextLine();

    /// If we are here so everything is ok ///
        Seller seller = new Seller(sellerName, password);
        Manager.addSellerToList(seller);
    }

    // FIXME
    public static void viewAllSellers(){

        if(Manager.getNumSellers() == 0){
            System.out.println("No Sellers found.");
            return;
        }

        for (int i = 0; i < Manager.getNumSellers(); i++) {
            System.out.println(Manager.getSellersList()[i].toString());
        }
    }
    // CHECKED: Works perfectly
    public static void addBuyer() throws InputMismatchException{
        s.nextLine();
        System.out.println("Enter buyer's Name: ");
        String buyerName = s.nextLine();

        while (stringExistsInArray(Manager.getAllBuyersNames(), buyerName)) {
            System.out.println("The Name " + buyerName + " is already exists. Please enter another name: ");
            buyerName = s.nextLine();
        }

        System.out.println("Enter Buyer's Password: ");
        String password = s.nextLine();

        //TODO get all address details

        System.out.println("Enter your street: ");
        String street = s.nextLine();
        System.out.println("Enter your city: ");
        String city = s.nextLine();
        System.out.println("Enter your state: ");
        String state = s.nextLine();
        System.out.println("Enter your building Number: ");
        int buildingNumber;
        while(true) {
            try {
                buildingNumber = s.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer:");
                s.nextLine();
            }
        }

        /// If we are here so everything is ok ///
        Buyer buyer = new Buyer(buyerName, password, street, city, state, buildingNumber);
        Manager.addBuyerToList(buyer);


    }
    // TODO: Works perfectly
    public static void viewAllBuyers(){

        if(Manager.getNumBuyers() == 0){
            System.out.println("No Buyers found.");
            return;
        }

        for (int i = 0; i < Manager.getNumBuyers(); i++) {
            if ( Manager.getBuyersList()[i] != null) {
                System.out.println( Manager.getBuyersList()[i].toString());
            }
        }
    }


    // CHECKED Works perfectly
    public static void addProductToSeller(){
        s.nextLine();
        if(Manager.getNumSellers() == 0){
            System.out.println("No Sellers found.");
            return;
        }


        System.out.println("Sellers list: ");
        Manager.printAllSellersNames();
        System.out.println("Enter sellers's Name: ");
        String sellerName = s.nextLine();

        while (!stringExistsInArray(Manager.getAllSellersNames(), sellerName)) {
            System.out.println("The seller " + sellerName + " doesn't exists. Please enter a valid seller name: ");
            sellerName = s.nextLine();
        }
        int sellerIndex = Manager.getSellerIndexByName(sellerName);

        System.out.println("Choose category from the given list: \n1) KIDS \n2) ELECTRICITY \n3) OFFICE \n4) CLOTHING");
        String productCategory = s.nextLine();
        Product.Category category = Manager.getEnumFromString(productCategory);

        while (category == null) {
            System.out.println("The category " + productCategory + " doesn't exist. Please enter a valid category: ");
            productCategory = s.nextLine();
        category = Manager.getEnumFromString(productCategory);
        }
        System.out.println("Enter products's Name: ");
        String productName;
        boolean isSpecialPackage;
        do {
             productName = s.nextLine();
             isSpecialPackage = false;
            System.out.println("Do you want to add a special package? [Y/N]: ");
            String specialPackage = s.nextLine();
            if (specialPackage.equalsIgnoreCase("Y")) {
                productName += " (Special Package)";
                isSpecialPackage = true;
            }
            if (stringExistsInArray(Manager.getSellersList()[sellerIndex].getProductsNames(), productName)){
                System.out.println("The product " + productName + " already exists. Please enter a valid product name: ");
            }
        } while (stringExistsInArray(Manager.getSellersList()[sellerIndex].getProductsNames(), productName));





        System.out.println("Enter product's Price: ");
        double productPrice;
        while(true) {
            try {
                productPrice = s.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid price for the product: ");
                s.nextLine();
            }
        }
        /// SPECIAL PACKAGE SECTION
        s.nextLine();
        boolean hasSpecialPackage = false;
        if (isSpecialPackage) {
            SpecialProduct product = new SpecialProduct(productName,productPrice, Manager.getSellersList()[sellerIndex],category);
            Manager.addProductToSellersList(product, sellerIndex);
            System.out.println("The product " + productName + " has been added to the list.");
        }
        else{
           Product product = new Product(productName,productPrice, Manager.getSellersList()[sellerIndex],category);
            Manager.addProductToSellersList(product, sellerIndex);
            System.out.println("The product " + productName + " has been added to the list.");
        }

    }

    // TODO section 4
    // get buyer name
    // get seller name
    // select product from the given list
    // add product to buyer cart

    public static void addProductToBuyer(){
        s.nextLine();
        if(Manager.getNumBuyers() == 0){
            System.out.println("No Buyers found.");
            return;
        }

        if (Manager.getNumSellers() == 0) {
            System.out.println("No Sellers found.");
            return;
        }

        System.out.println("choose buyer from the list: " );
        Manager.printAllBuyersNames();
        System.out.println("Enter buyer's Name: ");
        String buyerName = s.nextLine();


        while(!stringExistsInArray(Manager.getAllBuyersNames(), buyerName)) {
            System.out.println("The Buyer " + buyerName + " doesn't exists. Please enter a valid buyer name: ");
            buyerName = s.nextLine();
        }
        System.out.println("choose seller from the list: " );
        Manager.printAllSellersNames();
        System.out.println("Enter seller's Name: ");
        String sellerName = s.nextLine();
        while (!stringExistsInArray(Manager.getAllSellersNames(), sellerName)) {
            System.out.println("The seller " + sellerName + " doesn't exists. Please enter a valid seller name: ");
            sellerName = s.nextLine();
        }

        int sellerIndex = Manager.getSellerIndexByName(sellerName);

        if (Manager.getSellersList()[sellerIndex].getNumberOfProducts() == 0) {
            System.out.println("No Products found for this seller.");
            return;
        }

        System.out.println("Choose product from the list: ");
        Manager.getSellersList()[Manager.getSellerIndexByName(sellerName)].printAllProducts();
        String productName = s.nextLine();
        while (!stringExistsInArray(Manager.getSellersList()[Manager.getSellerIndexByName(sellerName)].getProductsNames(), productName)) {
            System.out.println("The product " + productName + " doesn't exists. Please enter a valid product name: ");
            productName = s.nextLine();
        }

        int buyerIndex = Manager.getBuyerIndexByName(buyerName);
        int productIndex = Manager.getSellersList()[sellerIndex].getProductIndexByName(productName);
        Product product = Manager.getSellersList()[buyerIndex].getProductsList()[productIndex];
        Manager.addProductToBuyerCart(product, buyerIndex);

        System.out.println("The product " + productName + " has been added to the cart successfully.");
    }


    // TODO Section 5 - PROCESS PAYMENT
    public static void processPayment(){
//        s.nextLine();
        if( Manager.getNumBuyers() == 0){
            System.out.println("No Buyers found.");
            return;
        }

        System.out.println("choose buyer from the list: " );
        Manager.printAllBuyersNames();

        System.out.println("Enter buyer's Name: ");
        String buyerName = s.nextLine();

        while (!stringExistsInArray(Manager.getAllBuyersNames(), buyerName)) {
            System.out.println("The Buyer " + buyerName + " doesn't exists. Please enter a valid seller name: ");
            buyerName = s.nextLine();
        }

        int buyerIndex = Manager.getBuyerIndexByName(buyerName);


        /// Throw exception
        try {
            if (Manager.getBuyersList()[buyerIndex].getCurrentOrder().isEmpty()) {
                throw new Exception("No Order found for buyer " + buyerName);
            }
        } catch (Exception e) {
            System.out.println("Payment failed, cart is empty.");
            return;
        }


        double totalPrice = Manager.getBuyersList()[buyerIndex].getCurrentOrder().getTotalPrice();
        System.out.println(buyerName + " your total price is: " + totalPrice);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        Manager.getBuyersList()[buyerIndex].getCurrentOrder().setOrderDate(formattedDateTime);
        Manager.getBuyersList()[buyerIndex].moveOrderToPreviousOrders();
        System.out.println( "Thank you for your purchase!");
        }

    public static void viewAllProductByCategory() {
        s.nextLine();
        System.out.println("Choose category from the given list: \n1) KIDS \n2) ELECTRICITY \n3) OFFICE \n4) CLOTHING");
        String productCategory = s.nextLine();
        Product.Category category = Manager.getEnumFromString(productCategory);

        while (category == null) {
            System.out.println("The category " + productCategory + " doesn't exist. Please enter a valid category: ");
            productCategory = s.nextLine();
            category = Manager.getEnumFromString(productCategory);
        }
        int c = 0;
        for (int i = 0; i< Manager.getNumSellers();i ++) {
            if(Manager.getSellersList()[i].getAllProductsByCategory(category)[0]!= null){
                System.out.println(Manager.getSellersList()[i].getUserName() +  " is selling: ");
                Manager.getSellersList()[i].printAllProductsByCategory(category);
                c++;
            }
        }
        if(c==0){
            System.out.println("No Products found for the catrgory " + productCategory + ".");
        }
    }


    public static void createOrderFromPreviousOrder() throws InputMismatchException{
        s.nextLine();
        if( Manager.getNumBuyers() == 0){
            System.out.println("No Buyers found.");
            return;
        }

        System.out.println("choose buyer from the list: " );
        Manager.printAllBuyersNames();

        System.out.println("Enter buyer's Name: ");
        String buyerName = s.nextLine();

        while (!stringExistsInArray(Manager.getAllBuyersNames(), buyerName)) {
            System.out.println("The Buyer " + buyerName + " doesn't exists. Please enter a valid seller name: ");
            buyerName = s.nextLine();
        }
        int buyerIndex = Manager.getBuyerIndexByName(buyerName);

        if(Manager.getBuyersList()[buyerIndex].getNumOfPreviousOrders() == 0){
            System.out.println("No previous orders found.");
            return;
        }

        /// Check if current order is empty
        if(!Manager.getBuyersList()[buyerIndex].getCurrentOrder().isEmpty()){
            System.out.println("Current order is NOT empty. Do you want to replace it with a previous order? [Y/N]");
            String answer = s.nextLine();
            if (answer.equalsIgnoreCase("N")) {
                return;
            }
        }
        System.out.println("Choose an order from the previous orders: ");
        Manager.printAllPreviousOrdersByBuyer(buyerIndex);
        int choice;
        while(true) {
            try {
                System.out.println("select order number: ");
                choice = s.nextInt();
                while (choice < 1 || choice > Manager.getBuyersList()[buyerIndex].getNumOfPreviousOrders()) {
                    System.out.println("Invalid order number. Please enter a valid order number: ");
                    choice = s.nextInt();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer:");
                s.nextLine();
            }
        }

        Order choosenOrder = Manager.getBuyersList()[buyerIndex].getPreviousOrders()[choice-1];
        Manager.getBuyersList()[buyerIndex].setCurrentOrder(choosenOrder);

    }




    public static void main(String[] args) {
        System.out.println("Welcome to our shop");
        int choice;

        do {
            printMenu();
            while(true) {
                try {
                    choice = s.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer");
                    s.nextLine();
                }
            }
            switch (choice) {
                case 0:
                    System.out.println("good bye");
                    break;
                case 1:
                    addSeller();
                    break;
                case 2:
                    addBuyer();
                    break;
                case 3:
                    addProductToSeller();
                    break;
                case 4:
                    addProductToBuyer();
                    break;
                case 5:
                    processPayment();
                    break;
                case 6:
                    viewAllBuyers();
                    break;
                case 7:
                    viewAllSellers();
                    break;
                case 8:
                    viewAllProductByCategory();
                    break;
                case 9:
                    createOrderFromPreviousOrder();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}



