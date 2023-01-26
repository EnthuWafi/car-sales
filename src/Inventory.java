
public abstract class Inventory<T> {
    protected LinkedList list;
    Inventory()
    {
        list = new LinkedList();
    }

    public abstract boolean add(T t);
    public abstract T remove(T t);
    public abstract T search(String itemName);

    public abstract void display();

    public LinkedList getList(){ return list;}

}
