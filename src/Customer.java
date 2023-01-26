
public class Customer {
    private String fullName;
    private String customerIC;
    private int age;
    private CarInventory carOwned;

    Customer(){
        fullName = "";
        age = 0;
        customerIC = "";
        carOwned = new CarInventory();
    }
    Customer(String fullName, int age, String ic){
        this.fullName = fullName;
        this.age = age;
        this.customerIC = ic;
        carOwned = new CarInventory();
    }

    public String getFullName(){return fullName;}
    public int getAge(){return age;}
    public String getCustomerIC() {return customerIC;}
    public CarInventory getCarOwned(){return carOwned;};

    public void setFullName(String fullName){this.fullName = fullName;}
    public void setAge(int age){this.age = age;}
    public void setCustomerIC(String customerIC){ this.customerIC = customerIC;}

}
