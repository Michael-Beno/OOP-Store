package cit.oop.source;

import java.util.ArrayList;
import java.util.Scanner;
import cit.oop.source.Order;
import cit.oop.source.Phone;
import cit.oop.source.Product;
import cit.oop.source.ProductDB;
import cit.oop.source.Tv;

public class TestClass {
	
	private static final String MAIN_MENU = "Please enter a menu option\n"
				+ "1. Create a new phone\n"
				+ "2. Search for a product by supplying productID\n"
				+ "3. Display all products\n"
				+ "4. Order Products\n"
				+ "5. Display all orders\n"
				+ "6. Quit\n"
				+ "Select a number (1-6): ";
	private static final String CREATE_PHONE	= "Create Phone";
	private static final String ENTER_MAKE 		= "Enter a make: ";
	private static final String ENTER_MODEL 	= "Enter a model: ";
	private static final String ENTER_STORAGE 	= "Enter a storage size (must be number): ";
	private static final String ENTER_PRICE 	= "Enter a price (can't be 0): ";
	private static final String PRODUCT_ADDED	= "Product added: ";
	
	private static final String SEARCH_PRODUCT	= "Search product by supplying productID";
	private static final String ENTER_ID 		= "Enter product ID: ";
	private static final String ERR_ID 			= "Invalid product ID!";
	private static final String DISPLAY_ALL 	= "Display all products";
	private static final String ORDER_PRODUCT 	= "Order products";
	private static final String CUSTOMER_NAME 	= "Enter the customer name: ";
	private static final String ENTER_ID_1 		= "Enter product ID (Enter -1 to finish): ";
	private static final String QUANTITY 		= "Quantity: ";
	private static final String INVALID_ID 		= "Invalid product ID!";
	private static final String ORDERS_NOT_FOUND= "ORDERS NOT FOUND";
	private static final String ORDER_FOR 		= "The Orders for ";
	private static final String AS_FOLLOW 		= " are as follows:";
	private static final String ORDER 			= "Orders ";
	
	private static final String GOOD_BYE 		= "Good bye";
	
	
	public static void main(String[] args) {
		ArrayList<Customer> arrCust = new ArrayList<>();
		
		Product p1 = new Phone("Apple","iPhone 6", 64, 780.90);
		Product p2 = new Phone("Sony","Experia", 32, 460.20);
		Product p3 = new Phone("Samsung","Galaxy s6", 32, 540.90);
		Product p4 = new Phone("LG","350", 32, 370.20);
		Product tv = new Tv("LG", 50, "Plasma", true, 1290.80);
		Product tv2 = new Tv("Samsung", 32, "LCD", false, 249.30);
		
//		p1.print();p2.print();p3.print();p4.print();tv.print();tv2.print();
		
		ProductDB db = new ProductDB();
		db.add(p1);
		db.add(p2);
		db.add(p3);
		db.add(p4);
		db.add(tv);
		db.add(tv2);
//		db.printAllProducts();
		
		Customer c1 =new Customer("Marry", "");
		
		Order o1 = new Order();
		o1.add(p1, 12); //12 ipones 6
		o1.add(p3, 1);  //1  Galaxy s6
		//c1.addOrder(o1);
		
		Customer c2 =new Customer("Sam", "");
		Order o2 = new Order();
		o2.add(p2, 1); //1 Sony
		o2.add(p4, 5); //5 LG
		//c2.addOrder(o2);
		
		arrCust.add(c1);
		arrCust.add(c2);
		
		Scanner in = null;
		int im = 0;
		while(im!=6) {
			System.out.print(MAIN_MENU);
			in = new Scanner(System.in);
			if(in.hasNextInt())
				im = in.nextInt();
			switch (im) {
				case 1: createPhone(in,db);break;
				case 2: searchProduct(in,db);break;
				case 3: displayAllProducts(db);break;
				case 4: arrCust.add(orderProducts(in, db, arrCust));break;
				case 5: displayAllOrders(in, arrCust);break;
				case 6: System.out.println(GOOD_BYE); break;
			}
		}
		in.close();
	}
	
	private static void createPhone(Scanner in, ProductDB db) {
		int storageSize = -1;
		double price = 0;
		System.out.println("\n"+CREATE_PHONE);
		System.out.print(ENTER_MAKE);
		String make = in.next();
		System.out.print(ENTER_MODEL);
		String model = in.next();
		
		while(storageSize < 0) {	System.out.print(ENTER_STORAGE);
			if(in.hasNextInt())
			storageSize = in.nextInt();
			else in = new Scanner(System.in);
		}
		while(price <= 0) {System.out.print(ENTER_PRICE);
			if(in.hasNextDouble())
				price = in.nextDouble();
			else in = new Scanner(System.in);
		}
		Product p = new Phone(make,model, storageSize, price);
		db.add(p);
		System.out.print(PRODUCT_ADDED);p.print();System.out.println();
	}
	
	private static void searchProduct(Scanner in, ProductDB db) {
		int productID = 0;
		Product product = null;
		System.out.println(SEARCH_PRODUCT);
		System.out.print(ENTER_ID);
		
		if(in.hasNextInt())
			productID = in.nextInt();
		else in = new Scanner(System.in);
		product = db.find(productID);
		
		if(product != null) {product.print();System.out.println();}
		else System.out.println(ERR_ID+"\n");
	}
	
	private static void displayAllProducts(ProductDB db) {
		System.out.println("\n"+DISPLAY_ALL);
		for (int i = 0; i < db.getArrProduct().size(); i++) {
			db.getArrProduct().get(i).print();
		}
		
		System.out.println();
	}
	
	private static Customer orderProducts(Scanner in, ProductDB db, ArrayList<Customer> arrCust) {
		String name, address = "";
		System.out.println(ORDER_PRODUCT);
		System.out.print(CUSTOMER_NAME);
		name = in.next();
//		System.out.println("Enter the customer address: ");
//		address = in.next();
		Customer cust = null;
		for (int i = 0; i < arrCust.size(); i++) {
			if(arrCust.get(i).getName().compareToIgnoreCase(name) == 0) cust = arrCust.get(i);
		}

		if(cust == null) cust = new Customer(name, address);
		Order o = new Order();
		int productID = 0;
		int quantity;
		Product product = null;
		in.nextLine();

		while(productID!=-1 || (product == null)) {	
			System.out.print(ENTER_ID_1);

			if(in.hasNextInt())
				productID = in.nextInt();
			else in = new Scanner(System.in);
			
			product = db.find(productID);
			
			//if(productID == -1) {cust.addOrder(o); return cust;}
			quantity = 0;
			
			if(product != null) {
				while(quantity <= 0) {
					System.out.print(QUANTITY);
					if(in.hasNextInt()) {
						quantity = in.nextInt();
						if(quantity > 0) o.add(product, quantity);
					}
					else in = new Scanner(System.in);
				}
			}
			else System.out.println(INVALID_ID+"\n");
		}
		return cust;
	}
	
	private static void displayAllOrders(Scanner in, ArrayList<Customer> arrCust) {
		System.out.print(CUSTOMER_NAME);
		String custName = in.next();
		boolean isFound = false;
		
		for (int c = 0; c < arrCust.size(); c++) {
			if(arrCust.get(c).getName().compareToIgnoreCase(custName) == 0) {
				System.out.println("\n" + ORDER_FOR + custName + AS_FOLLOW);
				isFound = true;
				for (int i = 0; i < arrCust.get(c).getAcustOrders().size(); i++) {
					System.out.println(ORDER+(i+1));
					arrCust.get(c).getAcustOrders().get(i).print();
				}
			}
		}
		if(!isFound) System.err.println("\n"+ORDERS_NOT_FOUND);
		System.out.println();
	}
}
