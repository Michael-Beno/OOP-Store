package cit.oop.source;

/**Product class which storing product
 * every single product have unique own
 * serial and part number. 
 * @author Michael Beno
 * @version 20/11/2017*/
public class Product {
	private String category;
        private String make;
        private String model;
        private String size;
	private String description;
	private double price;
	private final int SERIAL_NO = 1000;
	protected static int _ID;
	private int productID;
	
	/**Constructor of Product<br>
	 * adding serial number to product <b>XXX</b>000<br>
	 * serial number is not limited*/
	public Product() {
            Product._ID+=SERIAL_NO;
            category = "";
            make = "";
            model ="";
            size ="";
            description="";
            price = 0;
	}
	/**Name of product<br>
	 *adding type number 000<b>XX</b>0 up to 100 different products numbers*/
	public void setCategory(String category) {
		_ID+=10;				  //each product have different number
		this.category = category;
	}
        
         public void setMake(String make) {
		this.make = make;
	}
        
        public void setModel(String model) {
		this.model = model;
	}
        
         public void setSize(String size) {
		this.size = size;
	}
        
	/**description of product*/
	public void setDescription(String description) {
		this.description = description;
	}
	/**description of product*/
	public void setPrice(double price2) {
		this.price = price2;
	}
	/**unique serial number of product<br>
	 * every item have different number*/
	public void setProductID(int productID) {
		this.productID = productID;
	}
	/**Name of product*/
	public String getCategory() {
		return category;
	}
        
        public String getMake() {
		return make;
	}
        
        public String getModel() {
		return model;
	}
        
        public String getSize() {
		return size;
	}
        
	/**description of product*/
	public String getDescription() {
		return description;
	}
	/**price of product*/
	public double getPrice() {
		return price;
	}
	/**unique serial number of product<br>
	 * every item have different number*/
	public int getProductID() {
		return productID;
	}
	/**method for printing the product*/
	public void print() {
		System.out.println("PID: " + productID +" "+ category  + " "+description+ " $"+price);
	}

}
