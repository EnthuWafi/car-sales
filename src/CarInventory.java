public class CarInventory extends Inventory<Car>
{
    CarInventory()
    {
        super();
    }

    public boolean add(Car car)
    {
        Car temp = search(car.getCarID());
        if (temp == null) {
            list.insertAtBack(car);
            return true;
        }
        return false;
    }
    public Car remove(Car car)
    {
        LinkedList tempList = new LinkedList();
        Car carFound = null;
        while (!list.isEmpty())
        {
            Car temp = (Car) list.removeFromFront();
            if (temp.equals(car))
            {
                carFound = temp;
                break;
            }
            tempList.insertAtBack(temp);
        }

        while (!tempList.isEmpty())
        {
            list.insertAtFront(tempList.removeFromBack());
        }

        if (carFound != null)
            return carFound;
        return null;
    }
    public Car search(String carID)
    {
        if (list.isEmpty())
        {
            return null;
        }
        Car temp = (Car) list.getFirst();
        while (temp != null)
        {
            if (temp.getCarID().equals(carID))
            {
                return temp;
            }
            temp = (Car) list.getNext();
        }
        return null;
    }

    public void display()
    {
        if (list.isEmpty())
        {
            return;
        }
        {
            System.out.printf("%-30s%-16s%10s %-16s%-10s%-10s%n", "Car ID", "Car Brand", "Car Price", "Car Body",
                    "Made In", "Date Created");
            System.out.print("---------------------------------------------------------------------------------------------\n");
            Car temp = (Car) list.getFirst();
            while (temp != null)
            {
                System.out.printf("%-30s%-16s%10.2f %-16s%-10s%-10s%n", temp.getCarID(), temp.getCarBrand(),
                        temp.getCarPrice(), temp.getCarBody(), temp.getCountryMade(), temp.getDateCreated());
                temp = (Car) list.getNext();
            }
            System.out.println();
        }
    }

}
