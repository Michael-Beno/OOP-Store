package cit.oop.source;

/**TV class which extends Product.
 * Every single TV have unique own
 * serial and part number. TV has XXX<b>2</b> as identifier
 * @author Michael Beno
 * @version 20/11/2017*/
public class Tv extends Product{
	
	private final String CAPABLE_3D = "3D ready";
	
	/**Constructor of TV<br>
	 * adding serial number to product from super Constructor<b>XXX</b>00<b>2</b><br>
	 * Every TV have type number 2 - Up to 10 types available<br>
	 * serial number is not limited*/
	public Tv(String make, int size, String type, boolean capable3D, double price) {
		super();
		this.setProductID(_ID+2); //2 TV
		this.setMake(make + " " + type+ " TV");
		String s3Dcap ="";
		if(capable3D) s3Dcap = CAPABLE_3D;
		setDescription(size+"inch "+ s3Dcap);
		this.setPrice(price);
	}
	/**make (SONY, LG, Samsung, Bush etc.)</br>
	 * type (plasma, LCD, LED etc..) 
	 * @inherit name of Product
	 * @see setName()*/
	public void setMakeModel(String make, String type) {
		this.setMake(make+" "+type);
	}
	/**setter for size a capability of 3D
	 * @inherit description of Product
	 * @see setDescription()*/
	public void setSize3D(int size, boolean capable3D) {
		String s3Dcap ="";
		if(capable3D) s3Dcap = CAPABLE_3D;
		setDescription(size+"inch "+ s3Dcap);
	}

	/**print TV info
	 * @Override Product*/
//	public void print() {
//		super.print();
//	}

}
