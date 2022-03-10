package cit.oop.source;

/**Single order detail<br>
 *Storing object Product and its quantity 
 * @author Michael Beno
 * @version 20/11/2017*/
public class OrderDetails {
	
	private Product product;
	private int quantity;
	
	/**Adding Product object and its quantity*/
	public void add(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	/**printing quantity and product detail*/
	public void print() {
		System.out.print(quantity + "x ");
		product.print();
	}

    public Product getProduct() { return product; }

    public int getQuantity() {return quantity; }
}
