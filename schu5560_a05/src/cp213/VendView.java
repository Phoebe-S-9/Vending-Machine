//milk. milk again -- stock is updating werid?--for display -- try use mdoel item in inventory insted of view imtes //fixed
//could change so only using model inventory instead of items array in view?

/*
 * test cases--all seem ok : buy 1 item.
 * buy 2 dif itmes.
 * buy 2 of same item.
 * change. no change. 
 * change error. 
 * stock eror. 
 * quit
 * mutlipe valid uses.
 * mulite uses-- some erros--test everything
 * 
 * 
 * could aslo do 2 of 1 item. 2 of another item. etc
 */

/*
 * test object. cookie. coke. pepis
 *
 * ----1 cookie: 
 * stock 3 to 2. 
 * tot 1.45. padi 5$-- change : 5-1.45 = 3.55, change left = 5+5-3.55 = 6.45
 * 
 * ------1 cookie. 1 coke
 * 
 * stock 3 to 2.  1 to 0 
 *tot 4.95. padi 5$-- change : 0.05, change left = 9.95
 * 
 * ------1 cookie. 1 cookie. 1 coke .
 * 
 * stock 3 to 1.  stock 1 to 0 
 *tot 6.4. padi 5+2=7$-- change : 0.6, change left =5+7-0.6 = 11.4
 * * ------1 coke. 
 * 
 * stock 1 to 0.  
 *tot 3.50 . padi 2+1+.25+.25 = 3.50-- change : 0, change left =5+3.50 = 8.50
 *
 *
 *
 ** ------1 cookie. 
 * 
 * stock3 to 3.  
 *tot 1.45. padi 10-- change : 10, change left 5
 *
 *** ------1 cookie. 
 * 
 * stock3 to 3.  
 *tot 1.45. padi 1+.10=1.10-- change : 1.10, change left 5
 *
 * ------1 cookie. 
 * 
 * stock3 to 2.  
 *tot 1.45. padi 2- change :0.55, change left 6.45
 *
 *then:
 *1 coke
 * * stock1 to 0.  
 *tot 3.50. padi 5- change :1.50, change left 6.45+5-1.50=9.95
 *
 *then:
 * 1 pepsi 
 * 
 ** stock2 to 1  
 *tot 3.65. padi 2+2 = 4 - change :0.35, change left 9.95+4-.035=13.6
 *
 *
 *then:
 *
 *1 cookie. 1 pepsi
 * * stock2 to 1. stock 1 to 0   
 *tot 5.10. padi 10- change :4.9, change left 13.6+10-4.9=18.7
 *
 *
 *then:
 *1 pepeise
 *
 *stock 0 to 0 (stock error)
 *tot 0 p 0 . c 0. cl 18.7
 *
 *then
 *
 *1 cookie. 1 cookie
 *
 *t 1.45. p 0 . c 0. cl 18.7
stock error . stock 1 to 0 
paid-2. change .055 . cl 20.15
 *
 *
 *===============
 *1 coke. 1 coke. 1 cookie 
 *
 *stock 1 to 0 . stock 3 to 2.
 *tot 4.95. padi 5. - change :0.05, change left 9.95
 * (errro message should show)
 *
 *=======
 *1 coke. paid 0.25 -quit-(message)
 *change 0.25. change left 5
 *
 *

 *
 */

//to fix-- size change --done
//to fix--after 1 person buys- reset total and paid lables--to 0 --maybe--is ok how is 
//to fix-- clean up code overall
//to fix: documentation- Make-- and prjeoct html files thing 
//to fix: clean up overall look of front-colors. sizes
package cp213;

//print debug to eclipse is ok?--yes-can try remove debug messae when submit

//choice- same amount of items or dif- choice 

//not another frame inside vending--another??ok -can do - not usalyanotehjframe- befinge another threat
//create naothe jfream- pop up message box- here itesm -do instead
//ppo up dif from jfrmae open
//pop up message box-- differnt 
//prcies have to do stuff- 5c and 10 c -- price user put in 

//fgird- image- a0 tex t- another compornnet 
//panle- add compont

//extra jframe??

//make another copy?
//next week-can ask abou assingment - quesiton -course materi or assing 
//identicla

//can hardcode item and prices andstock??--yes
//can create more classes- yes-no limites- reasonble

//creae dispensror --eg if do a1, finish, 25c 25c-- only diepnse at end  //fix --size keep chanigng 
//eorr weh n trying to diplsne same item in dif runs.. a1- finsih. a1.??

//eror: out of stck - error not neough change-- show lalbel on diplay flap?- and change color to red?
//have to update lables of stock 
//how show 2.5 as 2.50
//paint and validiated- lec 12
//dispose-- rid a window wihtout close it -- for dispense??
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Handles the GUI portion of the vending machine. May use other GUI classes for
 * individual elements of the vending machine. Should use the VendModel for all
 * control logic.
 *
 * @author Phoebe Schulman
 * @version 2021-03-27
 */
@SuppressWarnings("serial")
public class VendView extends JPanel {

	private VendModel model = null;

	// INSTANCE VARS

	private final String[] LETTERS = { "A", "B", "C", "D" }; // letters in key pad
	private final String[] NUMBERS = { "1", "2", "3", "Quit" }; // nums and quit in key pad
	private final String amts[] = { "10c", "25c", "1$", "2$", "5$", "10$" }; // for money options //5c handled dif
	// private final String[] items = {"cookie $3.5 6"}

	private Item[] items = { new Item("cookie", 1.45, 3, "A1"), new Item("chip", 3.50, 7, "A2"),
			new Item("pepsi", 3.65, 2, "A3"), new Item("coke", 3.50, 1, "B1"), new Item("fish", 3.50, 4, "B2"),
			new Item("leg", 2.50, 3, "B3"), new Item("rice", 1.50, 9, "C1"), new Item("chocolate", 2.6, 6, "C2"),
			new Item("cake", 7.25, 3, "C3"), new Item("milk", 3.50, 6, "D1"), new Item("teeth", 3.5, 5, "D2"),
			new Item("juice", 4.55, 3, "D3") }; // need 12 itesm
	// can add actual random values later?-no need

	// might need to make stock instance var?-get stock??-- need update lable...

	// diplay money lable- ned update each time--and how print
	// 5.00--round?--.format?

	private int WIDTH = 500, HEIGHT = 700;// private static final int WIDTH =
	// 500, HEIGHT = 700;

	private JPanel displayPanel; // hold asll display itesm
	private JPanel keyboardPanel; // lletter and numbers
	private JPanel moneyPanel; // payment options ( coin/bill amoints)
	private JPanel finishPanel;
	private JPanel cashPanel; // cash option
	private JPanel displayMoneyPanel; // display tot,paid,change(changeleft);
	private JPanel flapPanel; // --look at lab for how open another window?

	private JLabel displayTotal;
	// private JPanel displayTotal;
	// private double displayTotal;// must update //make private
	// jlabled(Double.getTotal().toString) insdtead??
	private JLabel displayCashInputted;
	private JLabel displayChange;

	private JTextField despenseText; // for disnpsensing

	/**
	 * constructor for view
	 * 
	 * @param model
	 *            a vending machine model
	 */
	public VendView(VendModel model) {
		// super();
		this.model = model;
		// your code here

		// System.out.println(Math.round(4.3 * 100.0) / 100.0);
		// System.out.println(new DecimalFormat("##.00").format(4.3));//round 2 digs

		for (Item item : items) {// stock up machine
			model.addToInventory(item);
		}

		// SET THIS winodw? panel? --actual back of viding machine
		// this.setTitle("title");
		// this.setSize(WIDTH, HEIGHT); // or jsut setsize() //not seem to change...
		this.setBackground(Color.BLUE);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel machineName = new JLabel("Vendors R Us");
		// machineName.setSize(300, 200);
		// this.add(machineName);

		// set instance vars - as panels
		displayPanel = new JPanel();// displayPanel = new JPanel("display");
		keyboardPanel = new JPanel();
		moneyPanel = new JPanel();
		finishPanel = new JPanel();
		cashPanel = new JPanel();
		displayMoneyPanel = new JPanel();
		flapPanel = new JPanel();

		// displayTotal = model.getTotal();
		// Double temp = model.getTotal();
		// displayTotal = new JPanel(Double.temp.toString());
		// new JPanel(Double.model.getTotal().toString());
		// cast double to stirng// panel not have a string arg

		// double amt = model.getTotal();
		displayTotal = new JLabel("total: $" + new DecimalFormat("#0.00").format((model.getTotal()))); // round 2 palces
		displayCashInputted = new JLabel("paid: $" + new DecimalFormat("#0.00").format((model.getCashInputted())));
		displayChange = new JLabel("change: $" + new DecimalFormat("#0.00").format(model.getChange())
				+ " (left over change): $" + new DecimalFormat("#0.00").format((model.getChangeLeft())));

		// double . to stirn g(new dec...

		// displayTotal = new JLabel("total: $" + new
		// DecimalFormat("##.00").format(Double.toString(model.getTotal())));

		// displayTotal = new JLabel("total: $" + Double.toString(model.getTotal()));

		// displayCashInputted = new JLabel("paid: $" +
		// Double.toString(model.getCashInputted()));// model.getCashInputted();

		// displayChange = new JLabel("change: $" + Double.toString(model.getChange()) +
		// " (left over change): $" + Double.toString(model.getChangeLeft()));

		despenseText = new JTextField();
		// displayPanel.setSize(WIDTH, HEIGHT); // not do...

		// SET COLORS--can change later
		// machineName.setBackground(Color.WHITE);
		displayPanel.setBackground(Color.CYAN);// (Color.CYAN);// (Color.RED);
		keyboardPanel.setBackground(Color.GREEN);
		moneyPanel.setBackground(Color.YELLOW);
		finishPanel.setBackground(Color.GRAY);
		cashPanel.setBackground(Color.GRAY);// (Color.ORANGE);
		displayMoneyPanel.setBackground(Color.ORANGE);// (Color.PINK);
		flapPanel.setBackground(Color.RED);// (Color.GREEN);

		// SET LAYOUTS
		displayPanel.setLayout(new GridLayout(8, 6));
		// double it 3 by 4 - since adding text and lables

		JTextField field;
		JLabel displayLabel;

		int counter = 0;
		int n = 3; // 3 itesm a row

		for (int j = 0; j < 4; j++) { // 4 rows
			for (int i = 0; i < n; i++) {

				field = new JTextField(items[counter].toString());
				counter++;
				field.setEditable(false);// not editible-only dislays stuff
				displayPanel.add(field);
			} // 1 row of itesm

			for (int i = 0; i < n; i++) {
				String lab = LETTERS[j] + NUMBERS[i];// A + 1
				displayLabel = new JLabel(lab);
				displayPanel.add(displayLabel);
			} // 1 row of lables
		}

		keyboardPanel.setLayout(new GridLayout(4, 2));
		moneyPanel.setLayout(new GridLayout(3, 3));
		// finihs, cash, disp, flap -- flow latyout by defualt
		displayMoneyPanel.setLayout(new BorderLayout());

		// ADD BUTTONS (and money panels and display lables)
		// need actionlisters for buttons

		n = 4;
		JButton keyboardButton;
		for (int i = 0; i < n; i++) {
			String lab = LETTERS[i];
			keyboardButton = new JButton(lab);

			keyboardButton.addActionListener(new LetterListener());
			keyboardPanel.add(keyboardButton);
		}

		for (int i = 0; i < n; i++) {
			String lab = NUMBERS[i];
			keyboardButton = new JButton(lab);

			if (lab.equals("Quit")) // special case--dif listenre?
				// inner class-defien outidse of construcator //call inner calass
				keyboardButton.addActionListener(new QuitListener());
			else
				keyboardButton.addActionListener(new NumberListener());

			keyboardPanel.add(keyboardButton);
		}

		// first row of money panel -is dif
		JButton finishButton = new JButton("Finish");
		finishButton.addActionListener(new FinishListener()); // call private meth
		finishPanel.add(finishButton);
		moneyPanel.add(finishPanel);

		JButton moneyButton = new JButton("5c");
		moneyButton.addActionListener(new AmountListener());
		moneyPanel.add(moneyButton);

		JButton cashButton = new JButton("Cash");
		cashButton.addActionListener(new CashListener());
		cashPanel.add(cashButton);
		moneyPanel.add(cashPanel);

		int amtsCounter = 0;
		n = 6;
		for (int i = 0; i < n; i++) {
			// moneyButton = new JButton("amt");
			moneyButton = new JButton(amts[amtsCounter]);
			amtsCounter++;

			moneyButton.addActionListener(new AmountListener());
			moneyPanel.add(moneyButton);
		}

		// call update instead??

		displayMoneyPanel.add(displayTotal, BorderLayout.NORTH);
		displayMoneyPanel.add(displayCashInputted, BorderLayout.CENTER);
		displayMoneyPanel.add(displayChange, BorderLayout.SOUTH);

		// ADD TO FLAP
		JPanel smallFlap = new JPanel();
		smallFlap.add(new JLabel("Enjoy Your Meal"));
		flapPanel.add(smallFlap);

		// flapPanel.add(new JLabel("Enjoy Your Meal"));

		// ADD PANELS and lable TO THIS thing //xecpct for money??

		c.fill = GridBagConstraints.HORIZONTAL; // vert?
		c.weightx = 0.5;
		c.gridx = 10;
		c.gridy = 0;
		// c.ipady = 40; // taller
		c.gridheight = 3;// 4 4 // 2 rows ?
		c.gridwidth = 2; // 2 cols ?

		JPanel smallNameFlap = new JPanel();
		smallNameFlap.add(machineName);
		this.add(smallNameFlap, c);
		// this.add(machineName, c);

		c.fill = GridBagConstraints.HORIZONTAL; // vert?
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		// c.ipady = 40; // taller
		c.gridheight = 10; // 2 rows ?
		c.gridwidth = 5; // 2 cols ?
		this.add(displayPanel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 13;
		c.gridy = 2;
		c.gridheight = 10; // 2 rows wide?
		c.gridwidth = 5;
		this.add(keyboardPanel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 12;// c.gridx = 2;
		c.gridy = 12;// c.gridy = 0;
		c.gridheight = 3; // 2 rows wide?
		c.gridwidth = 5;
		// c.ipady = 20;
		this.add(moneyPanel, c);

		// moneyPanel.add(finishPanel);
		// moneyPanel.add(cashPanel);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 13;// 13
		c.gridy = 11;// 11;
		c.ipady = 10;// tall
		c.gridheight = 1; // 2 rows wide?
		c.gridwidth = 5;
		this.add(displayMoneyPanel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 12;
		c.ipady = 100;// 40; // tall
		c.gridheight = 2; // 2 rows wide?
		c.gridwidth = 5;
		this.add(flapPanel, c);

	}

	/**
	 * 
	 * inner class. to listen for a letter button clicked
	 *
	 */
	// INNER CLASS- to listen to buttons
	private class LetterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.clickLetter(e.getActionCommand());// model.clickLetter();
		}
	}

	/**
	 * 
	 * inner class. to listen for a quit button clicked
	 *
	 */
	// UPDATE LABLES??--in each corresponding??--for quit , amts, number
	private class QuitListener implements ActionListener { // innet class
		public void actionPerformed(ActionEvent e) {
			// model.func(); // call model class

			model.clickQuit();
			updateDisplay();

			showWindow(0);// showWind();
			model.reset(); // after display
		}
	}

	/**
	 * 
	 * inner class. to listen for a number button clicked
	 *
	 */
	private class NumberListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			boolean noStockErr = model.clickNum(e.getActionCommand());

			if (noStockErr == false) { // was error
				showWindow(3);
			}
			// model.clickNum(e.getActionCommand());
			// model.clickNum();

			// despenseText = new JTextField("dt");// "dt";
			// dont add it to machine
			updateDisplay();
		}
	}

	/**
	 * 
	 * inner class. to listen for a finish button clicked
	 *
	 */
	private class FinishListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.clickFinish();
		}
	}

	/**
	 * 
	 * inner class. to listen for a cash button clicked
	 *
	 */
	private class CashListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.clickCash();
		}
	}

	/**
	 * 
	 * inner class. to listen for a button with a cash/bill amount clicked
	 *
	 */
	private class AmountListener implements ActionListener { // for money to input
		public void actionPerformed(ActionEvent e) {
			// dispense(); // only once balid...

			int valid = 0;
			// boolean valid = false;
			String in = e.getActionCommand();
			System.out.println(in);// debug

			if (in.equals("5c"))
				valid = model.clickFiveCents();// model.clickFiveCents();
			else if (in.equals("10c"))
				valid = model.clickTenCents();
			else if (in.equals("25c"))
				valid = model.clickTwentyFiveCents();
			else if (in.equals("1$"))
				valid = model.clickOneDollars();
			else if (in.equals("2$"))
				valid = model.clickTwoDollars();
			else if (in.equals("5$"))
				valid = model.clickFiveDollars();
			else if (in.equals("10$"))
				valid = model.clickTenDollars();

			else
				System.out.println("ERR");
			updateDisplay();
			// updateDisplayStock();
			// only after pruchase-cuz stock not go down til end

			// how update jtext

			if (valid == 1) { // || valid == 2) {// if (valid) {
				showWindow(1);// showWind();
				updateDisplayStock();
				model.reset();// reset after display
				// updateDisplayStock();

			} else if (valid == 2) {
				showWindow(2);
				updateDisplayStock(); // should look sameif works
				model.reset();
			}

			// Dispenser.show(); // show();
			// add actionlistern to call it?/--cuz action perfomed...--but then need
			// button...
			// dispense(); // only once valid...(payed enough)
		}
	}

	/**
	 * opens a new window to dispense change and or items bought
	 * 
	 * @param state
	 *            0 if called by quit button
	 * 
	 *            1 if valid purchase-can dispense moeny and change
	 * 
	 *            2 if change error(not enough change in machine)- dispense cash
	 *            inputted
	 * 
	 *            3 if stock error(not enough stock of an item) - just to notify
	 *            user. can keep going
	 */
	public void showWindow(int state) {// showWind() {// show windoe //can make private? //could catch errors instead?
		// quit -- dispense change - state 0
		// valid purhcase. - items and change-- 1
		// change error - change -- 2
		// stcok erro - 3 -- can keep going tho

		// no-- make pop up instead -- cant add window to conatiner
		// despenseText = new JTextField("dttt");// debug

		// ArrayList<Item> ip = model.getItemsPurchased();// no-- reapets
		// System.out.println(ip);
		String s = "";
		// String s = "ITEMS BOUGHT:\n\n";

		if (state == 0) {
			s = "QUIT. dispensing cash inputted";
		} else if (state == 1) {
			s = "ITEMS BOUGHT:\n\n";
			for (Item i : items) {// for (Item i : ip) {
				if (i.getStockBought() > 0)
					s += i.getName() + " * " + i.getStockBought() + ",\n"; // wont add new line
				// System.out.println("\n\nS \n\n" + s);
			}
			// s[s.length()] = "";// s -= ",";// rid last comma?
		} else if (state == 2) {
			s = "CHANGE ERROR. not enough change in machine. dispensing cash inputted";

			/*
			 * } else if (state == 3) { s =
			 * "STOCK ERROR. that item is out of stock. cost wont be added to total. contiue"
			 * ; // return;
			 * 
			 */

		} else if (state != 3) {// shouldnt get
			s = "ERR??";
		}

		s += "\n\n\n\nCHANGE:   $";

		if (model.getChange() >= 0)
			s += new DecimalFormat("#0.00").format(model.getChange());// s += model.getChange();
		else// dont show neg--sholdnt be posible?
			s += "0.00";

		// System.out.println("\n\nS \n\n" + s);// debu
		if (state == 3) { // overirde whatever in s
			s = "STOCK ERROR. that item is out of stock. cost wont be added to total. contiue";
		}

		despenseText = new JTextField(s);// "dt";

		JFrame f = new JFrame("dispensing...");

		f.setSize(WIDTH, HEIGHT);// f.pack;
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// (JFrame.DO_NOTHING_ON_CLOSE);
		f.getContentPane().setBackground(Color.MAGENTA);// (Color.CYAN);// f.setBackground(Color.CYAN);

		JLabel lable = new JLabel(despenseText.getText()); // can callby name
		f.add(lable);// add(lable);

		// border laytou -- add to nroth -- items(if any)--
		// add to south-- change(0 if neg)---no cuz is same text??--or 2 instance vars
		// can still scenter center thos
		f.setVisible(true);
		// this.add(f);//cant add window to conatiner
		// this.setVisible(true);

	}

	// public JTextField getDespenseText() { return despenseText;}

	/**
	 * update display of displayMoneyPanel (with total,cash, change) for gui front
	 */
	private void updateDisplay() { // totoal paid and chagen on front
		// panel remove each labled
		displayMoneyPanel.remove(displayTotal);
		displayMoneyPanel.remove(displayCashInputted);
		displayMoneyPanel.remove(displayChange);
		// vending machine remove panel
		this.remove(displayMoneyPanel);

		// new values for panels
		displayMoneyPanel.setLayout(new BorderLayout()); // put over old??
		/*
		 * displayTotal = new JLabel("total: $" + Double.toString(model.getTotal()));
		 * displayCashInputted = new JLabel("paid: $" +
		 * Double.toString(model.getCashInputted()));// model.getCashInputted();
		 * displayChange = new JLabel("change: $" + Double.toString(model.getChange()) +
		 * " (left over change): $" + Double.toString(model.getChangeLeft()));
		 * 
		 */
		displayTotal = new JLabel("total: $" + new DecimalFormat("#0.00").format((model.getTotal()))); // round 2 palces
		displayCashInputted = new JLabel("paid: $" + new DecimalFormat("#0.00").format((model.getCashInputted())));
		displayChange = new JLabel("change: $" + new DecimalFormat("#0.00").format(model.getChange())
				+ " (left over change): $" + new DecimalFormat("#0.00").format((model.getChangeLeft())));

		displayMoneyPanel.add(displayTotal, BorderLayout.NORTH);
		displayMoneyPanel.add(displayCashInputted, BorderLayout.CENTER);
		displayMoneyPanel.add(displayChange, BorderLayout.SOUTH);

		// put panel exactly where it was
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 13;// 13
		c.gridy = 11;// 11;
		c.ipady = 10;// tall
		c.gridheight = 1; // 2 rows wide?
		c.gridwidth = 5;
		this.add(displayMoneyPanel, c);

		// update gui
		this.validate();
		this.repaint(); // repaint making updatee disply bad??

		updateDisplayStock();
	}

	// detele old panel
	/**
	 * update display of displayPanel (with item stock ) for gui front
	 */
	private void updateDisplayStock() { // works but panel shirnks??-even tho constrains same--

		System.out.print("THERE ");// debug // for each item in machine-- update its stock--held in label
		for (Item item : items) { // debug
			System.out.print(item.getStock() + " "); // update this on front
		}

		// this.remove(displayPanel);
		helper();

	}

	private void helper() {// add diplsay back in //can make 1 func??

		for (int i = 0; i < 12; i++) { // array vs arraylist
			items[i] = model.getItemsInInventory().get(i);
		}
		// items = model.getItemsInInventory(); // update view items array

		// this.remove(displayPanel); //rid this-- fixe d size. but something else?
		displayPanel = new JPanel();
		displayPanel.setBackground(Color.RED);// not do?
		displayPanel.setLayout(new GridLayout(8, 6));

		JTextField field; // dont need as instnace vars?
		JLabel displayLabel;

		int counter = 0;
		int n = 3; // 3 itesm a row

		for (int j = 0; j < 4; j++) { // 4 rows
			for (int i = 0; i < n; i++) {

				// field = new JTextField(model.getItemsInInventory()[counter].toString());
				// want model items. not view items??
				field = new JTextField(items[counter].toString());// + "UPDATED");
				counter++;
				field.setEditable(false);// not editible-only dislays stuff
				displayPanel.add(field);
			} // 1 row of itesm

			for (int i = 0; i < n; i++) {
				String lab = LETTERS[j] + NUMBERS[i];// A + 1
				displayLabel = new JLabel(lab);
				displayPanel.add(displayLabel);
			} // 1 row of lables
		}

		// put panel exactly where it was
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL; // vert?
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		// c.ipady = 40; // taller
		c.gridheight = 10; // 2 rows ?
		c.gridwidth = 5; // 2 cols ?
		this.add(displayPanel, c);

		// displayPanel.validate();
		// displayPanel.repaint();
		// update gui

		// this.setVisible(true);
		// this.pack();
		this.validate();
		this.repaint();

	}

}

/*
 * 
 * private class Dispenser extends JFrame implements ActionListener { // no
 * 
 * public Dispenser() { // new window super("dispensing...");
 * this.setSize(WIDTH, HEIGHT);
 * this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// no () at end //
 * so not exit both //or dipsose // (WindowConstants.DO_NOTHING_ON_CLOSE);//
 * (JFrame.EXIT_ON_CLOSE()); // DO_NOTHING_ON_CLOSE //not working?? cuz outer
 * class not have jfraem??-is ok }
 * 
 * public void actionPerformed(ActionEvent e) { // instance var-text JLabel
 * lable = new JLabel(despenseText.getText()); // can callby name add(lable);
 * this.setVisible(true); }
 * 
 * public void show() { JLabel lable = new JLabel(despenseText.getText()); //
 * can callby name add(lable); this.setVisible(true);
 * 
 * } }
 */
/*
 * public void dispense() { // debug--make new winodw //no
 * System.out.println("GUI DISP"); for (Item item : items) { // get stock bough
 * was ..reset System.out.println(item.getStockBought() + "*" + item.getName());
 * 
 * } }
 */

/*
 * 
 * private void updateDisplayOLD() { // totoal paid and chagen on front
 * 
 * this.remove(displayMoneyPanel); displayMoneyPanel.setLayout(new
 * BorderLayout()); // put over old??
 * 
 * displayTotal = new JLabel("total: $" + Double.toString(model.getTotal()));
 * displayCashInputted = new JLabel("paid: $" +
 * Double.toString(model.getCashInputted()));// model.getCashInputted();
 * displayChange = new JLabel("change: $" + Double.toString(model.getChange()) +
 * " (left over change): $" + Double.toString(model.getChangeLeft()));
 * 
 * displayMoneyPanel.add(displayTotal, BorderLayout.NORTH);
 * displayMoneyPanel.add(displayCashInputted, BorderLayout.CENTER);
 * displayMoneyPanel.add(displayChange, BorderLayout.SOUTH);
 * 
 * GridBagConstraints c = new GridBagConstraints();
 * 
 * c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 0.5; c.gridx = 13;// 13
 * c.gridy = 11;// 11; c.ipady = 10;// tall c.gridheight = 1; // 2 rows wide?
 * c.gridwidth = 5;
 * 
 * // displayMoneyPanel.validate(); // displayMoneyPanel.repaint(); //
 * this.add(displayMoneyPanel); this.add(displayMoneyPanel, c);
 * 
 * System.out.print("HERE " + model.getTotal()); // deb
 * 
 * this.validate(); this.repaint(); }
 */
// view: 3 * 4 selction options
// buttons: keypad - 2 * 4
// display: totla, cash in, change ( change left--debug)
// button: finish
// button: cash
// buttons: each cash amount

// display : items dispensed
/*
 * 
 * //rough ideas
 * 
 * grid lable- with product name lablle - code
 * 
 * 
 * enter coinds: 5 c etc enter bills 5$,10$
 * 
 * each time pushed-- add another
 * 
 * //eg // num dimes * dime amoutn = 6dimes * 0.10 $ = 60c //num fivebills * 5$
 * 
 * dialy tot - (so far?) cancel button finish button - done
 * 
 * letter and digit button - together make a code-// invalid eg aa or a11??
 * //after click 2 buttons check if a valid code? 1 letter a to f and a num 1 to
 * 9
 * 
 * 
 * 
 * ----- itesm
 * 
 * 
 * --test with 3 x 2 to start?? choclate 3.23 chips gum pepsi coke water
 * 
 * etc
 * 
 * 
 */
