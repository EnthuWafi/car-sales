public class CustomerList extends Inventory<Customer>{
    CustomerList()
    {
        super();
    }

    public boolean add(Customer customer)
    {
        Customer temp = search(customer.getCustomerIC());
        if (temp == null) {
            list.insertAtBack(customer);
            return true;
        }
        return false;
    }
    public Customer remove(Customer customer)
    {
        LinkedList tempList = new LinkedList();
        Customer customerFound = null;
        while (!list.isEmpty())
        {
            Customer temp = (Customer) list.removeFromFront();
            if (temp.equals(customer))
            {
                customerFound = temp;
                break;
            }
            tempList.insertAtBack(temp);
        }

        while (!tempList.isEmpty())
        {
            list.insertAtFront(tempList.removeFromBack());
        }

        if (customerFound != null)
            return customerFound;
        return null;
    }
    public Customer search(String customerIC)
    {
        if (list.isEmpty())
        {
            return null;
        }
        Customer temp = (Customer) list.getFirst();
        while (temp != null)
        {
            if (temp.getCustomerIC().equals(customerIC))
            {
                return temp;
            }
            temp = (Customer) list.getNext();
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
            Customer temp = (Customer) list.getFirst();
            int index = 0;
            System.out.printf("%n%-5s %-30s %-5s %-20s %-20s%n", "No", "Full Name", "Age", "Customer IC", "Cars Count");
            System.out.print("---------------------------------------------------------------------------------------------\n");
            while (temp != null){
                System.out.printf("%-5s %-30s %-5d %-20s %20d%n", (index + 1), temp.getFullName(), temp.getAge(),
                        temp.getCustomerIC(), temp.getCarOwned().getList().size());
                index++;
                temp = (Customer) list.getNext();
            }
        }
        System.out.println();

    }
}
