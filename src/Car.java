import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Car {
    private String carID;
    private String carBrand;
    private String carBody;
    private Double carPrice;
    private String countryMade;
    private LocalDate dateCreated;

    //normal
    Car(String carID, String carBrand, String carBody, Double carPrice, String countryMade, String dateCreated){
        this.carID = carID;
        this.carBrand = carBrand;
        this.carBody = carBody;
        this.carPrice = carPrice;
        this.countryMade = countryMade;
        this.dateCreated = LocalDate.parse(dateCreated);
    }

    public void setCarID(String carID){this.carID = carID;}
    public void setCarBrand(String carBrand){this.carBrand = carBrand;}
    public void setCountryMade(String countryMade){this.countryMade = countryMade;}
    public void setCarPrice(Double carPrice){this.carPrice = carPrice;}
    public void setCarBody(String carBody){this.carBody = carBody;}
    public void setDateCreated(String dateCreated){this.dateCreated = LocalDate.parse(dateCreated);}

    public void setDateCreated(LocalDate dateCreated){this.dateCreated = dateCreated;}
    public String getCarID(){return carID;}
    public String getCarBrand(){return carBrand;}
    public Double getCarPrice(){return carPrice;}
    public String getCarBody(){return carBody;}
    public String getCountryMade(){return countryMade;}
    public String getDateCreated(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateCreated.format(dateTimeFormatter);
    }


    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("Car ID: %s%nCar Brand: %s%nCar Body: %s%nCar Price: RM%.2f%nMade In: %s%nDate Created: %s%n",
                carID, carBrand, carBody, carPrice, countryMade, dateCreated.format(dateTimeFormatter));
    }
}
