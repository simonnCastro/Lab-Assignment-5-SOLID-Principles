// Single Responsibility: Separate responsibilities into different interfaces
public interface OrderProcessor {
    void calculateTotal(double price, int quantity);
    void placeOrder(String customerName, String address);
}

public interface InvoiceGenerator {
    void generateInvoice(String fileName);
}

public interface EmailNotifier {
    void sendEmailNotification(String email);
}

// Implement OrderProcessor
public class BasicOrderProcessor implements OrderProcessor {
    private double total;

    @Override
    public void calculateTotal(double price, int quantity) {
        total = price * quantity;
        System.out.println("Order total: $" + total);
    }

    @Override
    public void placeOrder(String customerName, String address) {
        System.out.println("Order placed for " + customerName + " at " + address);
    }
}

// Implement InvoiceGenerator
public class PdfInvoiceGenerator implements InvoiceGenerator {
    @Override
    public void generateInvoice(String fileName) {
        System.out.println("Invoice generated: " + fileName);
    }
}

// Implement EmailNotifier
public class EmailService implements EmailNotifier {
    @Override
    public void sendEmailNotification(String email) {
        System.out.println("Email notification sent to: " + email);
    }
}

// Order Service that uses dependency injection
public class OrderService {
    private final OrderProcessor orderProcessor;
    private final InvoiceGenerator invoiceGenerator;
    private final EmailNotifier emailNotifier;

    public OrderService(OrderProcessor orderProcessor, InvoiceGenerator invoiceGenerator, EmailNotifier emailNotifier) {
        this.orderProcessor = orderProcessor;
        this.invoiceGenerator = invoiceGenerator;
        this.emailNotifier = emailNotifier;
    }

    public void processOrder(double price, int quantity, String customerName, String address, String fileName, String email) {
        orderProcessor.calculateTotal(price, quantity);
        orderProcessor.placeOrder(customerName, address);
        invoiceGenerator.generateInvoice(fileName);
        emailNotifier.sendEmailNotification(email);
    }
}

// Test class
public class OrderTest {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new BasicOrderProcessor();
        InvoiceGenerator invoiceGenerator = new PdfInvoiceGenerator();
        EmailNotifier emailNotifier = new EmailService();

        OrderService orderService = new OrderService(orderProcessor, invoiceGenerator, emailNotifier);
        orderService.processOrder(10.0, 2, "John Doe", "123 Main St", "order_123.pdf", "johndoe@example.com");
    }
}
