public class TransactionHistory
{
    private LinkedList list;

    TransactionHistory(){
        list = new LinkedList();
    }

    public LinkedList getList(){return list;}

    public void print()
    {
        if (list.isEmpty())
        {
            return;
        }
        Bill temp = (Bill) list.getFirst();
        int index = 0;
        System.out.printf("%n%-5s %-30s %-20s %-20s %-20s %-20s %-10s%n", "No", "Full Name", "Customer IC", "Car ID","Transaction Type", "Transaction Date", "Status");
        System.out.print("---------------------------------------------------------------------------------------------\n");
        while (temp != null){
            System.out.printf("%-5s %-30s %-20s %-20s %-20s %-20s %-10s%n", (index + 1), temp.getCustomer().getFullName(), temp.getCustomer().getCustomerIC(),
                    temp.getCar().getCarID(), temp.getTransactionType(), temp.getDateTime(), temp.getStatus());
            index++;
            temp = (Bill) list.getNext();
        }
        System.out.println();
    }
}
