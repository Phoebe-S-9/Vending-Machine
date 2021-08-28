/*next:
- find an item based of code entered
- update lables (total, stock)



*/

package cp213;

import java.util.ArrayList;

/**
 * Vending machine model. Contains the algorithms for vending products, handling
 * change and inventory, and working with credit. Should not perform any GUI
 * work of any kind.
 *
 * @author Phoebe Schulman
 * @version 2021-03-31
 */
public class VendModel {

	// vend modle -- as skelteon? sub classes as each step??

	double total; // might go in dif class //shoulb set in constrcutro
	double cashInputted;
	double change; // change owed
	double changeLeft; // in machine

	boolean enteredLetter, enteredNum;
	String codeEntered;

	// boolean enteredFinish, enteredCash; // can add later

	ArrayList<Item> itemsInInventory; // fill machine with items
	ArrayList<Item> itemsPurchased; // each time buy a thing, add to list??// Item[] itemsPurchased;
	// once letter then number{ code true ; prucahse if possible; set false}

	/**
	 * constructor
	 */
	public VendModel() {
		total = 0.0;
		cashInputted = 0.0;
		change = 0.0;
		changeLeft = 5;// 20.0; // 20$ to first purchase $ --or make random to start

		enteredLetter = false;
		enteredNum = false;
		codeEntered = "";

		itemsInInventory = new ArrayList<Item>();
		itemsPurchased = new ArrayList<Item>();

	}

	/**
	 * add itme to inventory - array list
	 */
	public void addToInventory(Item item) {
		itemsInInventory.add(item);

		// debug
		// for (Item it : itemsInInventory)System.out.println(it);
	}

	/**
	 * click a button with letter. helps create a code
	 * 
	 * @param letter
	 *            the letter on the button
	 */
	public void clickLetter(String letter) {// clickLetter() {
		// eg- cant do leetter,cash, num// or letter, quit, num--use reset valid cod
		// case: letter leeter, number--retry

		if (enteredLetter == true) { // try again
			resetValidCode();
		}

		enteredLetter = true;
		enteredNum = false;
		codeEntered += letter;// "a"; // add whatever letter

		System.out.println("leeter " + enteredLetter + enteredNum + codeEntered); // + validCode); // deubg
	}

	/**
	 * click a button with number. helps create a code.
	 * 
	 * @param num
	 *            the number on the button
	 * @return True if no stock error(went smoothly). else false(not enough stock to
	 *         provide item)
	 */

	public boolean clickNum(String num) {// void clickNum(String num) {// clickNum() { //how use code to find item...
		enteredNum = true;

		boolean noStockErorr = true;
		if (enteredLetter == true) { // make sure only right after..
			codeEntered += num;

			// based on code entedred-make an item
			// new Item(code)--- to access it?? //getItem(code) --based on code??
			// or can pass item??

			// Item item = item.find(codeEntered); //how do ?
			// Item item = find(items.)
			// Item item = new Item();// new Item(); // debug

			Item item = new Item(); // defualt itm

			for (Item i : itemsInInventory) {
				if (i.getCode().equals(codeEntered)) { // if found
					item = i;
					break;
				}
			}

			if (item.getName() == "testItem") { // should be impposibel to get here since buttosn
				System.out.println("ERROR:   not an item");// defualt name --do nothing //debug

			} else {
				System.out.println("stock/ stock already bought: " + item.getStock() + " / " + item.getStockBought());// debu
				if (item.getStock() == 0 || item.getStockBought() + 1 > item.getStock()) {
					System.out.println("ERROR:    out of stock");

					// could make boolean - fale??-- if false- stock erro
					noStockErorr = false;
				} else {
					item.increaseStockBought();
					itemsPurchased.add(item);
					total += item.getPrice();
				}

				/*
				 * if (item.getStock() != 0 && item.getStock() <= item.getStockBought() + 1) {
				 * // checkValidStock(item);//< item.increaseStockBought();
				 * itemsPurchased.add(item); total += item.getPrice(); } else {// if stock = 0
				 * or stock is less than amoutn trying to buy
				 * System.out.println("out of stock");// tot not updated }
				 */
			}
		}

		System.out.println("number " + enteredLetter + enteredNum + codeEntered); // deubg
		resetValidCode();// either way reset after

		return noStockErorr;
	}

	/*
	 * helper method. to ensure the object code is ok
	 */
	public void resetValidCode() { // can make private?
		System.out.println("resetValidCode"); // debug
		enteredLetter = false;
		enteredNum = false;
		codeEntered = "";
	}

	/*
	 * clicking quit/cancle button refund money inputted if need.
	 */
	public void clickQuit() {
		System.out.println("quit"); // debug
		resetValidCode();

		if (cashInputted > 0.0001) { // refund amount paid if any// 0.5 is lowest tho
			System.out.println("refund"); // debug
			// System.out.println("returning " + cashInputted); // deb

			dispenseChange(cashInputted);// can do dispense change insteda??--for newe
			// window
			// where change = cash in??
		}
		// reset();//to early

	}

	/*
	 * clicking finish button
	 */
	public void clickFinish() {
		System.out.println("finish"); // debug
		resetValidCode();

		// if (itemsPurchased == empty{System.out.println("nothing bougth");}
		// no itmes bough

		// reset();
		// if all went well: for each itme: purcahseitem()
	}

	/*
	 * clicking cash button
	 */
	public void clickCash() { // not really do much - other than resete code
		System.out.println("cash"); // debug
		resetValidCode();
	}

	/**
	 * helper method for pressing cash/bills buttons
	 * 
	 * @return 0 if not paid enough. 1 if paid enough and enough change. 2 if change
	 *         error ( not enough change in machine)
	 */
	private int checkAmount() {

		int val = 0;
		// boolean val = false; // mkae int ?
		// 0: not enough-do nothing. 1 ok - items and change. 2 - change error-change

		if (cashInputted >= total) { // enough in = true
			// val = true; // should dispsene itmes and / or coins

			change = cashInputted - total;// computeChange();
			System.out.println(total + " " + cashInputted + " " + change); // debug

			if (change <= changeLeft) {// (checkValidChange()) {
				changeLeft = changeLeft + cashInputted - change;

				System.out.println("change ok" + change + " " + changeLeft); // deb
				// changeLeft += cashInputted; // after paying??

				dispenseItems();
				dispenseChange(change);
				// changeLeft -= change; // lose change in macheni after giving

				val = 1; // ok
			} else { // not enough chaneg in machine
				System.out.println("change error" + change + " " + changeLeft);
				dispenseChange(cashInputted);// clickQuit();// refund(); // give money back

				itemsPurchased = new ArrayList<Item>(); // cant get antyhing
				val = 2; // err
			}

		}
		return val;

	}

	private boolean checkAmountOLD() { /// bad logic // void checkAmount() { //DELTE
		boolean val = false;

		change = cashInputted - total;// computeChange();
		System.out.println(total + " " + cashInputted + " " + change); // debug

		// swithc ifs. check enoguh in . then change-- cuz valid if chnage error- to
		// dispsen
		if (change <= changeLeft) {// (checkValidChange()) {
			System.out.println("change ok" + change + " " + changeLeft);

			// val = true;
			if (cashInputted >= total) { // enough in = true;// check paymentvalid()
				changeLeft += cashInputted; // after paying??

				dispenseItems();
				dispenseChange(change);

				changeLeft -= change; // lose change in macheni after giving

				// if all ok - add to change left
				// change remianing = + (inputed amount - change) ??
				// eg: 20$ + 25c in but return 10 c = 20.15??

				// changeLeft += total - change;
				// reset();--reset was too early
				// val = true;
			}

		} else { // not enough chaneg in machine
			System.out.println("change error" + change + " " + changeLeft);
			dispenseChange(cashInputted);// clickQuit();// refund(); // give money back
		}

		return val;

	}

	/**
	 * button for clicking 5 cents
	 * 
	 * @return 0 if not paid enough. 1 if paid enough and enough change. 2 if change
	 *         error ( not enough change in machine)
	 */
	public int clickFiveCents() { // boolean clickFiveCents() { // void clickFiveCents() {
		// System.out.println("5c"); // debug
		resetValidCode();

		cashInputted += 0.05;// 0.5;
		return checkAmount();// checkAmount();
	}

	public int clickTenCents() {
		// System.out.println("5c"); // debug
		resetValidCode();
		cashInputted += 0.10;
		return checkAmount();
	}

	public int clickTwentyFiveCents() {
		// System.out.println("5c"); // debug
		resetValidCode();
		cashInputted += 0.25;
		return checkAmount();
	}

	public int clickOneDollars() {
		// System.out.println("5c"); // debug
		resetValidCode();
		cashInputted += 1;
		return checkAmount();
	}

	public int clickTwoDollars() {
		// System.out.println("5c"); // debug
		resetValidCode();
		cashInputted += 2;
		return checkAmount();
	}

	public int clickFiveDollars() {
		// System.out.println("5c"); // debug
		resetValidCode();
		cashInputted += 5;
		return checkAmount();
	}

	public int clickTenDollars() {
		// System.out.println("5c"); // debug
		resetValidCode();
		cashInputted += 10;
		return checkAmount();
	}

	private void dispenseItems() { // for debugging?- not need in final??--do need reduce stock tho...
		// open window ?-print names of each itme purchased?
		System.out.println("dispense items"); // debug

		/*
		 * for (Item item : itemsPurchased) { System.out.println(item.getStockBought() +
		 * "*" + item.getName()); // item.reduceStock();// update stock --erorr when buy
		 * mulitples.... }
		 */
		for (Item item : itemsInInventory) {// travers all items jsut once-inccase mutliepls
			if (item.getStockBought() > 0) { // bought at least 1 of an item
				System.out.println(item.getStockBought() + "*" + item.getName());
				item.reduceStock();// update stock
			}
		}

		// dispenseChange(change);//alreadly done
		// reset();

		// - list all item names--arralist?--
		// what if order many things-- how diplsy it so it look nice
		// could do (1* cookie, 2* pop, 1* chip) etc??

		// Item item = new Item(); // find itme()
		// item.reduceStock();
		// System.out.println(item.getName());

	}

	private void dispenseChange(double amt) {// dispenseChange() { //not need?? debug??
		change = amt; // can be cash in --refund// or change -- if success

		System.out.println("dispenseChange"); // debug
		// print: change owed
		System.out.println(change); // open window?

		// changeLeft -=change;
	}

	/**
	 * reset machine for next personto use
	 */
	public void reset() {
		System.out.println("reset "); // debug
		// debug- traver boht array s and print?

		// debug-----
		System.out.println("checking arrays===== ");
		for (Item item : itemsPurchased) {
			System.out.println(item);

		}
		System.out.println("===== ");
		for (Item item : itemsInInventory) {
			System.out.println(item);
		}
		// ----end deub

		total = 0.0;
		cashInputted = 0.0;
		change = 0.0;

		enteredLetter = false;
		enteredNum = false;
		codeEntered = "";

		itemsPurchased = new ArrayList<Item>();

		for (Item item : itemsInInventory) {// reset stock bough -for next person
			item.resetStockBought();
		}
		// reset total , change owed, cash inputed
		// but keep stock chnged and change left for next person

	}

	// access meths

	/**
	 * accessor method
	 * 
	 * @return current total that needs to be paid
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * accessor method
	 * 
	 * @return current cash to pay for items
	 */
	public double getCashInputted() {
		return cashInputted;
	}

	/**
	 * accessor method
	 * 
	 * @return change provided by machine
	 */

	public double getChange() { // change owed
		return change;
	}

	/**
	 * accessor method
	 * 
	 * @return change remaining in machine
	 */
	public double getChangeLeft() {
		return changeLeft;
	}

	/**
	 * accessor method
	 * 
	 * @return items current person bought from machine
	 */
	public ArrayList<Item> getItemsPurchased() {
		return itemsPurchased;
	}

	/**
	 * accessor method
	 * 
	 * @return items machine has stored
	 */
	public ArrayList<Item> getItemsInInventory() {// dont need?-but good to have
		return itemsInInventory;

	}

	/**
	 * print the object nicely
	 */
	@Override
	public String toString() {
		return "[vendmodel: " + total + " " + cashInputted + " " + change + " " + changeLeft + "]";
	}

}

/*
 * //rough idea
 * 
 * for 5 x 6 matrix -- 3 x 4
 * 
 * code : if is firts row: a + __. if 2nd row: b+___,etc
 * 
 * code: if 1st col: _ + 0, 2nd col: _ + 1, etc
 * 
 * amt in stock - rand num
 * 
 * each elem make object fo rpurache - name, code, price, amount in stock - eg
 * choclate a0 3.10$ 6
 * 
 * 
 * 
 * item(name, code, prcie, stock){}
 * 
 * 
 * 
 * ----- if item is out of stock: reject() //requested amount> stock amount:
 * return money
 * 
 * --------
 * 
 * payment type = coins or payment type = bills
 * 
 * if coins: (1c??), 5c,10c,1$,2$
 * 
 * if bils: 5$,10$. reject 20,50,100, (and any other)
 * 
 * ----- give change
 * 
 * change starting wiht = rand
 * 
 * if paid success: change contains added if not enough change: reject //as an
 * expetion?
 * 
 * ----
 * 
 * loop(yes) { enter itme to buy ; total price +=selected item price ; another
 * selection(y n q)? - if no: quit and give toltal; if quit: quit}
 * 
 * 
 * 
 * 
 * 
 * 
 */

// methds needed
/*
 * 
 * 
 * create item
 * 
 * clikcer letter
 * 
 * click num
 * 
 * check valid keypad (letter, num , quit)
 * 
 * check valid code
 * 
 * 
 * check valid stock
 * 
 * click finsih
 * 
 * click cash
 * 
 * enter fivecent enter ten cent ... enter tenbill
 * 
 * compute cash enters so far
 * 
 * compute total
 * 
 * compute change owed
 * 
 * cehck payment valid
 * 
 * 
 * 
 * displense item - list themall return change
 * 
 * dsipense change
 * 
 * 
 * reset // total , change owed, cash inputed //but keep stock chnged and change
 * left
 * 
 * 
 * class item:
 * 
 * item(name, price, stock)
 * 
 * 
 * 
 * 
 */