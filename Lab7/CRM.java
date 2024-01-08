import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

class Customer implements Comparable<Customer> {
    private String customerId;
    private String name;
    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }
    public String getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return "Customer (customerId=" + customerId + ", name=" + name + ")";
    }
    public int compareTo(Customer otherCustomer) {
        return this.getCustomerId().compareTo(otherCustomer.getCustomerId());
    }
}

class Product {
    private String productId;
    private String name;
    public Product(String productId, String name) {
        this.productId = productId;
        this.name = name;
    }
    public String getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return "Product (productId=" + productId + ", name=" + name + ")";
    }
}

class Order {
    private String orderId;
    private Customer customer;
    private ArrayList<Product> products = new ArrayList<>();
    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public String toString() {
        return "Order [orderId=" + orderId + ", customer=" + customer + ", products=" + products + "]";
    }
}

public class CRM{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Customer details:");
        System.out.print("Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Customer Name: ");
        String customerName = scanner.nextLine();
        Customer customer1 = new Customer(customerId, customerName);

        System.out.println("\nEnter Product details:");
        System.out.print("Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Product Name: ");
        String productName = scanner.nextLine();
        Product product1 = new Product(productId, productName);

        System.out.println("\nEnter Order details:");
        System.out.print("Order ID: ");
        String orderId = scanner.nextLine();
        Order order1 = new Order(orderId, customer1);
        order1.getProducts().add(product1);

        ArrayList<Customer> customerList = new ArrayList<>();
        HashMap<String, Product> productMap = new HashMap<>();
        HashSet<Product> uniqueProducts = new HashSet<>();
        TreeSet<Customer> sortedCustomers = new TreeSet<>();

        //Adding user-input data to data structures
        customerList.add(customer1);
        productMap.put(product1.getProductId(), product1);
        uniqueProducts.add(product1);
        sortedCustomers.add(customer1);
        //Retrieving data from data structures
        Customer retrievedCustomer = customerList.get(0);
        Product retrievedProduct = productMap.get(product1.getProductId());
        //Displaying retrieved data
        System.out.println("\nRetrieved Customer: " + retrievedCustomer);
        System.out.println("Retrieved Product: " + retrievedProduct);
        System.out.println("\nAll Order:");
        System.out.println(order1);
        scanner.close();
    }
}