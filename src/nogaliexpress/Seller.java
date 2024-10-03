package nogaliexpress;
public class Seller {

    private String userName;
    private String password;
    private Product [] productsList = new Product[1];
    private int numOfProducts = 0;

    public Seller(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Product[] getProductsList() {
        return productsList;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public static Product[] extendArray(Product[] arr){
        Product[]  newArray = new Product[arr.length*2];
        for(int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }

    public String[] getProductsNames(){
        String[] products = new String[numOfProducts];
        for(int i = 0; i < numOfProducts; i++) {
            products[i] = productsList[i].getProductName();
        }
        return products;
    }

    public void printAllProducts(){
        for (int i = 0; i < numOfProducts; i++) {
            System.out.println(i+1 + ") "+ productsList[i].getProductName() +" , $"+ productsList[i].getPrice());
        }
    }


    public int getProductIndexByName(String productName){
        for(int i = 0; i < numOfProducts; i++) {
            if(productsList[i].getProductName().equals(productName)){
                return i;
            }
        }
        return -1;
    }




    public String toString(){
        String str = "";
        for(int i = 0; i < numOfProducts; i++){
            str += + i+1 + ") " + productsList[i].toString() + "\n";
        }
        return "User Name: " + userName + ", Password:" + password +  ", num of products: "+ numOfProducts +"\nProducts: \n" + str;
    }


    public boolean addProductToSeller(Product product) {
        for (int i = 0; i < numOfProducts; i++) {
            if (productsList[i].getProductName().equals(product.getProductName())) {
                return false;
            }
        }

        if (productsList.length == numOfProducts){
            productsList = extendArray(productsList);
        }

        productsList[numOfProducts] = product;
        numOfProducts++;
        return true;
    }

    public int getNumberOfProducts(){
        return numOfProducts;
    }

    public Product[] getAllProductsByCategory(Product.Category category){
        Product[] products = new Product[1];
        int nop = 0;
        for(int i = 0; i < numOfProducts; i++) {
            if(productsList[i].getCategory().equals(category)){
                if(products.length == nop){
                    products = extendArray(products);
                }
                products[nop] = productsList[i];
                nop ++;
            }
        }
        return products;
    }

    public void printAllProductsByCategory(Product.Category category){
        Product[] products = getAllProductsByCategory(category);
        for(int i = 0; i < numOfProducts; i++) {
            System.out.println(i+1 + ") "+ products[i].getProductName() +" $"+ products[i].getPrice());
        }
    }

}
