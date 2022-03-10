package cit.oop.source;

/**Phone class which extends Product.
 * Every single Phone have unique own
 * serial and part number. Phones has XXX<b>1</b> as identifier
 * @author Michael Beno
 * @version 20/11/2017*/
public class Phone extends Product{
	
	/**Constructor of Phone<br>
	 * adding serial number to product from super Constructor<b>XXX</b>00<b>1</b><br>
	 * Every Phone have type number 1 - Up to 10 types available<br>
	 * serial number is not limited*/
	public Phone(String make, String model, int storageSize, double price) {
		super();
		this.setProductID(_ID+1); //1 phone
		this.setMake(make+" "+model);
		this.setDescription(storageSize+"GB");
		this.setPrice(price);	
	}
	/**make (Apple, 6; Motorola, Moto; Samsung, Galaxy; etc.)
	 * @inherit name of Product
	 * @see setName()*/
	public void setMakeModel(String make, String model) {
		this.setMake(make+" "+model);
	}

	/**storage space 32, 64, 128*/
	public void setStorageSpace(int storageSize) {
		this.setDescription(storageSize+"GB");
	}
	
	/**print Phone info
	 * @Override Product*/
//	public void print() {
//		super.print();
//	}
}
