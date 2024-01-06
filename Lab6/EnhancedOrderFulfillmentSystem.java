import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Item {
    private String itemName;
    private int quantity;
    public Item(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
    public String getItemName() {
        return itemName;
    }
    public int getQuantity() {
        return quantity;
    }
}

class Order {
    private int orderId;
    private List<Item> items;
    public Order(int orderId, List<Item> items) {
        this.orderId = orderId;
        this.items = items;
    }
    public int getOrderId() {
        return orderId;
    }
    public List<Item> getItems() {
        return items;
    }
}

class InsufficientInventoryException extends Exception {
}

class OrderCancellationException extends Exception {
}

class EnhancedOrderFulfillmentSystem {
    private List<Order> orders = new ArrayList<>();
    private List<Item> inventory = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public synchronized void placeOrder(Order order) {
        orders.add(order);
    }
    public synchronized void startProcessing() {
        for (Order order : orders) {
            executorService.submit(() -> processOrder(order));
        }
    }
    public synchronized void waitForCompletion() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void processOrder(Order order) {
        try {
            updateInventory(order);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            trackOrderStatus(order);
        } catch (InsufficientInventoryException | OrderCancellationException e) {
            e.printStackTrace();
        }
    }

    private synchronized void updateInventory(Order order) throws InsufficientInventoryException, OrderCancellationException {
        for (Item item : order.getItems()) {
            if (!checkInventoryAvailability(item)) {
                throw new InsufficientInventoryException();
            }
        }
    }

    private synchronized boolean checkInventoryAvailability(Item item) throws OrderCancellationException {
        return true;  
    }

    private synchronized void trackOrderStatus(Order order) {
        System.out.println("Order " + order.getOrderId() + " processed successfully");
    }

    public static void main(String[] args) {
        EnhancedOrderFulfillmentSystem orderSystem = new EnhancedOrderFulfillmentSystem();
    
        // Create and submit orders
        List<Item> items1 = new ArrayList<>();
        items1.add(new Item("Product1", 2));
        items1.add(new Item("Product2", 1));
        Order order1 = new Order(1, items1);
    
        List<Item> items2 = new ArrayList<>();
        items2.add(new Item("Product3", 3));
        items2.add(new Item("Product4", 2));
        Order order2 = new Order(2, items2);
    
        orderSystem.placeOrder(order1);
        orderSystem.placeOrder(order2);
    
        // Start order processing
        orderSystem.startProcessing();
    
        // Wait for a while to allow order processing to complete
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        // Wait for completion
        orderSystem.waitForCompletion();
    }    
    
}
