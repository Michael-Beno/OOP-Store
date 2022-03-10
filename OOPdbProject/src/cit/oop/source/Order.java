package cit.oop.source;

import java.util.ArrayList;
/**Order storing an Order in OrderDetails array<br>
 * @author Michael Beno
 * @version 20/11/2017*/
public class Order {

	private ArrayList<OrderDetails> aOrders;
	/**Constructor*/
	public Order() {
		aOrders = new ArrayList<>();
	}
	/**ArrayList of Orders, return OrderDetails*/
	public ArrayList<OrderDetails> getAorders() {
		return aOrders;
	}
	/**setter for OrderDetails array*/
	public void setAorders(ArrayList<OrderDetails> aOrders) {
		this.aOrders = aOrders;
	}
	
	/**adding the Product and count to to the OrderList*/
	public void add(Product product, int count) {
		OrderDetails od = new OrderDetails();
		od.add(product, count); //filling object
		aOrders.add(od); //adding to OrderDetail
	}
	/**prints all OrderList from array<br>
	 * 12x Apple...<BR>
	 * 10x LG...*/
	public void print() {
		for (int i = 0; i < aOrders.size(); i++) {
			aOrders.get(i).print();
		}
	}
}
