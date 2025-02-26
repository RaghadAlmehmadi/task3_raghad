import java.util.concurrent.*;

class OrderManager {
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    public void processOrder(Order order) {
        System.out.println("\nProcessing order: " + order.getOrderId());

        executor.submit(() -> {
            try {
                Thread.sleep(2000); // Simulate processing delay
                order.setOrderStatus(OrderStatus.SHIPPED);
                System.out.println("Order Shipped: " + order.getOrderId());

                Thread.sleep(3000); // Simulate delivery delay
                order.setOrderStatus(OrderStatus.DELIVERED);
                System.out.println("Order Delivered: " + order.getOrderId());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}
