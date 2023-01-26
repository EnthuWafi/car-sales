import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bill {
    //variables
    private Customer customer;
    private Car car;
    private String transactionType;

    private boolean validity;
    private String status;
    private LocalDateTime dateTime;


    Bill(Customer customer, Car car, String transactionType) {
        this.customer = customer;
        this.car = car;
        this.transactionType = transactionType;
        validity = true;
        status = "bought";
        dateTime = LocalDateTime.now();
    }

    Bill(Bill bill) {
        this.customer = bill.customer;
        this.car = bill.car;
        this.transactionType = bill.transactionType;
        validity = bill.validity;
        status = bill.status;
        dateTime = LocalDateTime.now();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setValidity(boolean validity)
    {
        this.validity = validity;
    }
    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public boolean getValidity(){return validity;}

    public String getStatus(){return status;}
    public String getTransactionType() {
        return transactionType;
    }

    public String getDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        return dateTime.format(dateTimeFormatter);
    }
}

