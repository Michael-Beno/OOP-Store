package cit.oop.source;


import java.util.ArrayList;


import cit.oop.source.Order;
/**Customer class which store name, address and Order List<br>
 * @author Michael Beno*/

public class Customer {
	
        private int custID;
	private String name;
	private String address;
	private ArrayList<Order> aCustOrders;
	private int lastOrder;
        
        public Customer(){
            custID = -1;
            name = "";
            address = "";
            aCustOrders = new ArrayList<>();
            lastOrder = 0;
        }
        
	/**name and address of Customer (Constructor)*/
	public Customer(String name, String address) {
            this();
            aCustOrders = new ArrayList<>();
		this.name = name;
		this.address = address;	
	}
	
	/**Customers Arraylist*/
	public ArrayList<Order> getAcustOrders() {
		return aCustOrders;
	}
	/**Customers Arraylist*/
	public void setAcustOrder(ArrayList<Order> aOrders) {
		this.aCustOrders = aOrders;
	}
        public int getCustID(){
            return custID;
        }
	/**name of Customer*/
	public String getName() {
		return name;
	}
	/**name of Customer*/
	public void setName(String name) {
		this.name = name;
	}
	/**name of Customer*/
	public void setName(String name, String address) {
		this.name = name;
		this.address = address;	
	}
	/**address of Customer*/
	public String getAddress() {
		return address;
	}
	/**address of Customer*/
	public void setAddress(String address) {
		this.address = address;
	}
	/**add order to Customers array list*/
	public void addOrder(Order order, DatabaseConnect dbc) {
            
		this.aCustOrders.add(order);

            for (int i = 0; i < aCustOrders.get(0).getAorders().size(); i++) {
                OrderDetails od = aCustOrders.get(0).getAorders().get(i);
                
                System.out.println(od.getProduct().getProductID()+" "+od.getQuantity()+" "+custID+" "+(lastOrder+1));
                dbc.putOrders(od.getProduct().getProductID(),od.getQuantity(),custID,lastOrder+1);
            }
	}

    public void setCustID(int custID) {
        this.custID = custID;
    }
    public void setLastOrder(int orderNo) {
        this.lastOrder = orderNo;
    }
 

 
	
}
