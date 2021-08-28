package cp213; //ask --allowed another class?

import java.text.DecimalFormat;

public class Item {
	private String name;
	private double price;
	private int stock; // stock left

	private int stockBought;

	private String code; // code to enter to buy item

	/**
	 * constructor
	 */
	public Item() {
		this.name = "testItem";
		this.price = 0; // = 0 //debug //1
		this.stock = 0; // 1 //0 //2

		this.stockBought = 0;
		this.code = "z1";
	}

	/**
	 * constructor
	 */
	public Item(String name, double price, int stock, String code) {// public Item(String name, double price, int stock)
																	// {
		this.name = name;
		this.price = price;
		this.stock = stock;

		this.stockBought = 0;
		this.code = code;
	}

	/**
	 * increase amount of stock of an item current user is trying to buy
	 */
	public void increaseStockBought() {
		stockBought++;
	}

	/**
	 * reset amount of stock of an item for next user
	 */
	public void resetStockBought() {
		stockBought = 0;
	}

	public Item find(String code) { // how find item. based on code//traverse? //unused??
		Item itemFound = new Item();
		if (this.code.equals(code)) {
			return itemFound;
		}
		return new Item();
	}

	/**
	 * once purchased items, update how much stock of an item is left
	 */
	public void reduceStock() { // when buy a thing(s)
		stock -= stockBought;// stock--;
	}

	/**
	 * accessor method
	 * 
	 * @return name of item
	 */
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public int getStockBought() {
		return stockBought;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {

		String priceF = new DecimalFormat("#0.00").format(price); // 2 dec palce // how make 7.00-decimals formal
		return name + " $" + priceF + " (stock:" + stock + ")"; // \n not help gui// could add code-but is in lable
		// return "[Item: " + name + " $" + price + " " + stock + " ]";
	}

}
