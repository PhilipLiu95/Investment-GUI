package ePortfolio;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
public class buyInvestment extends JFrame implements ActionListener {
    JLabel commandInterface = new JLabel("<html><span style='font-size:20px;color:black;'>"+"Welcome to ePortfolio"+"</span></html>");
    JLabel command= new JLabel("<html><span style='font-size:20px;color:black'>"+"Commands"+"</span></html>");
    JLabel commandInterface2 = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Buying an investment"+"</span></html>");
    JLabel Type =  new JLabel("<html><span style='font-size:15px;color:black;'>"+"Type<br/><br/>Symbol<br/><br/>Name<br/><br/>Quantity<br/><br/>Price"+"</span></html>");
    JComboBox<String> box;
    JTextField symbol = new JTextField();
    JTextField name = new JTextField();
    JTextField quantity = new JTextField();
    JTextField price = new JTextField();
    JButton submit = new JButton("Buy");
    JButton Reset = new JButton("Reset");
    JLabel commandMessage = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Messages"+"</span></html>");
    JTextArea messages = new JTextArea();
    JScrollPane text = new JScrollPane(messages);

    //declaring variables
    final double commisions = 9.99;
    String investment;
    double bookVal = 0.0;
    double newBookVal;
    String[] parsedName;
    ArrayList<Integer> index = null;
    public static ArrayList <Investment> investments = new ArrayList <Investment>();
    public static Map <String, ArrayList <Integer>> investmentHM = new HashMap<String, ArrayList <Integer>>();

    public buyInvestment() {
        //info for the buyInvestment JFrame
        super("ePortfolio");
        String []types = {"Stock","Mutual Fund"};
        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JMenu colorMenu = new JMenu("Commands");
        JMenuItem buy = new JMenuItem("Buy Investment");
        buy.addActionListener(this);
        colorMenu.add(buy);

        JMenuItem sell = new JMenuItem("Sell Investment");
        sell.addActionListener(this);
        colorMenu.add(sell);

        JMenuItem search = new JMenuItem("Search Investment");
        search.addActionListener(this);
        colorMenu.add(search);

        JMenuItem update = new JMenuItem("Update Investment");
        update.addActionListener(this);
        colorMenu.add(update);

        JMenuItem getGain = new JMenuItem("GetGain");
        getGain.addActionListener(this);
        colorMenu.add(getGain);

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        colorMenu.add(quit);

        JMenuBar bar = new JMenuBar();
        bar.add(colorMenu)  ;
        setJMenuBar(bar);

        commandInterface2.setSize(250,50);
        commandInterface2.setLocation(50, 0);

        Type.setSize(100,250);
        Type.setLocation(50, 50);

        box = new JComboBox<>(types);
        box.setSize(200,50);
        box.setLocation(150, 50);

        symbol.setSize(300,50);
        symbol.setLocation(150, 100);
        name.setSize(300,50);
        name.setLocation(150, 150);
        quantity.setSize(300,50);
        quantity.setLocation(150, 200);
        price.setSize(300,50);
        price.setLocation(150, 250);
        

        Reset.setSize(100,50);
        Reset.setLocation(600, 100);
        Reset.addActionListener(new Reset());

        submit.setSize(100,50);
        submit.setLocation(600, 200);
        submit.addActionListener(new submit());
      
        text.setSize(800,200);
        text.setLocation(50, 400);

        commandMessage.setSize(100,100);
        commandMessage.setLocation(50, 325);

        add(commandInterface);
        add(commandInterface2);
        add(Type);
        add(box);
        add(symbol);
        add(quantity);
        add(name);
        add(price);
        add(Reset);
        add(submit);
        add(text);
        add(commandMessage);
        
     }
    private class submit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //set a boolean called exist to false
            boolean exist = false;
            messages.setText("");
            String investmentType = (String) box.getSelectedItem(); 
            //if the user selects stock
            if (investmentType.equals("Stock")){
                //loop through the arraylist and compare if a symbol already exists
                for (int i = 0; i < investments.size(); i++){ 
                    //if symbol already exists
                    if (symbol.getText().equalsIgnoreCase(investments.get(i).getSymbol())){
                        try {
                            investments.get(i).setType(investment);
                        } catch (Exception k) {
                            messages.append(k.getMessage());
                        }
                        //ask user for the quantity and the price of the stock
                        investments.get(i).setQuantity(Integer.parseInt(quantity.getText()));
                        investments.get(i).setPrice(Double.parseDouble(price.getText()));
                        //get the new bookvalue based on the quantity and price
                        newBookVal = Integer.parseInt(quantity.getText()) * Double.parseDouble(price.getText()) + commisions;
                        investments.get(i).setBookValue(newBookVal + bookVal);
                        //symbol exists so set exist to true
                        exist = true;
                    } 
                }
                //if the symbol doesn't exist
                if(exist == false) {
                    //create a class variable 
                    Stocks stockInfo = new Stocks();
                    //user inputs the stock info
                    try {
                        stockInfo.setType("Stock");
                    } catch (Exception k) {
                        messages.append(k.getMessage());
                    }
                    try {
                        stockInfo.setSymbol(symbol.getText());
                    } catch (Exception k){
                        messages.append(k.getMessage());
                    }
                    try {
                        stockInfo.setName(name.getText());
                    } catch (Exception k){
                        messages.append(k.getMessage());
                    }
                    try {
                        stockInfo.setQuantity(Integer.parseInt(quantity.getText()));
                    } catch (NumberFormatException k){
                        messages.append(k.getMessage());
                    }
                    try {
                        stockInfo.setPrice(Double.parseDouble(price.getText()));
                    } catch (NumberFormatException k){
                        messages.append(k.getMessage());
                    }
                    
                    //get the bookvalue based on the info above
                    bookVal = Integer.parseInt(quantity.getText()) * Double.parseDouble(price.getText()) + commisions;
                    try {
                        stockInfo.setBookValue(bookVal);
                    } catch (NumberFormatException k){
                        messages.append(k.getMessage());
                    }
                    //add the stock information to the array list
                    investments.add(stockInfo);
                    //splt the names in the array list
                    parsedName = name.getText().toLowerCase().split("[ ]+");
                    //loop through the split names
                    for(int j = 0; j < parsedName.length; j++){
                        index = new ArrayList<Integer>();
                        //if the hashmap contains the name
                        if(investmentHM.containsKey(parsedName[j])){
                            //set the index to the original names
                            index = investmentHM.get(parsedName[j]);
                            //add to the original names
                            index.add(investments.size() - 1);
                            //put the index back into the hashmap
                            investmentHM.put(parsedName[j], index);
                        } else{
                            //else add to hashmap 
                            index.add(investments.size() - 1);
                            investmentHM.put(parsedName[j], index);
                        }
                    }
                }
                //printing out all current arraylists
                for (int i = 0; i < investments.size(); i++){
                    messages.append(""+investments.get(i));
                }
                //if the user selects mutualfund
            } else if (investmentType.equals("Mutual Fund")){
                //get the symbol for the mutual fund
                //loop to compare all symbols in the ArrayList
                for (int i = 0; i < investments.size(); i++){
                    //if symbol already exists
                    if (symbol.getText().equalsIgnoreCase(investments.get(i).getSymbol())){
                        try {
                            investments.get(i).setType(investment);
                        } catch (Exception k) {
                            messages.append(k.getMessage());
                        }
                        //ask user for the quantity and the price of the stock
                        investments.get(i).setQuantity(Integer.parseInt(quantity.getText()));
                        investments.get(i).setPrice(Double.parseDouble(price.getText()));
                        //get the new bookvalue based on the quantity and price
                        newBookVal = Integer.parseInt(quantity.getText()) * Double.parseDouble(price.getText());
                        investments.get(i).setBookValue(newBookVal + bookVal);
                        //symbol exists so set exist to true
                        exist = true;
                    } 
                }
                //if there isn't a match
                if(exist ==false) {
                    //create a class variable
                    MutualFunds mutualFundInfo = new MutualFunds();
                    //getting the inputs from the user 
                    try {
                        mutualFundInfo.setType("Mutual Fund");
                    } catch (Exception k) {
                        messages.append(k.getMessage());
                    }
                    try {
                        mutualFundInfo.setSymbol(symbol.getText());
                    } catch (Exception k){
                        messages.append(k.getMessage());
                    }
                    try {
                        mutualFundInfo.setName(name.getText());
                    } catch (Exception k){
                        messages.append(k.getMessage());
                    }
                    try {
                        mutualFundInfo.setQuantity(Integer.parseInt(quantity.getText()));
                    } catch (NumberFormatException k){
                        messages.append(k.getMessage());
                    }
                    try {
                        mutualFundInfo.setPrice(Double.parseDouble(price.getText()));

                    } catch (NumberFormatException k){
                        messages.append(k.getMessage());
                    }
                    //get the bookvalue based on the info above
                    bookVal = Integer.parseInt(quantity.getText()) * Double.parseDouble(price.getText());
                    try {
                        mutualFundInfo.setBookValue(bookVal);
                    } catch (NumberFormatException k) {
                        messages.append(k.getMessage());
                    }
                    //add the stock information to the array list
                    investments.add(mutualFundInfo);
                    //splt the names in the array list
                    parsedName = name.getText().toLowerCase().split("[ ]+");
                    //loop through the split names
                    for(int j = 0; j < parsedName.length; j++){
                        index = new ArrayList<Integer>();
                        //if the hashmap contains the name
                        if(investmentHM.containsKey(parsedName[j])){
                            //set the index to the original names
                            index = investmentHM.get(parsedName[j]);
                            //add to the original names
                            index.add(investments.size() - 1);
                            //put the index back into the hashmap
                            investmentHM.put(parsedName[j], index);
                        } else{
                            //else add to hashmap 
                            index.add(investments.size() - 1);
                            investmentHM.put(parsedName[j], index);
                        }
                    }
                }
                //printing out all current arraylists
                for (int i = 0; i < investments.size(); i++){
                    messages.append("" + investments.get(i));
                }
              
            }
        }
    }

   private class Reset implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          //reset the text boxes to nothing
          symbol.setText("");
          name.setText("");
          box.setSelectedIndex(0);
          quantity.setText("");
          price.setText("");
          messages.setText("");
      }
   }
    public void actionPerformed(ActionEvent e){
        //when a user selects one of the menu bar options then transition to that frame
        String buttonString= e.getActionCommand();

        if(buttonString.equals("Buy Investment")){
            buyInvestment buy = new buyInvestment();
            buy.setVisible(true);
            dispose();
        }else if(buttonString.equals("Sell Investment")){
            sellInvestment sell = new sellInvestment();
            sell.setVisible(true);
            dispose();
        }else if(buttonString.equals("Search Investment")){
            searchInvestment search = new searchInvestment();
            search.setVisible(true);
            dispose();
        }else if(buttonString.equals("Update Investment")){
            updateInvestment update = new updateInvestment();
            update.setVisible(true);
            dispose();
        }else if(buttonString.equals("GetGain")){
            getGain getGain = new getGain();
            getGain.setVisible(true);
            dispose();
        }else if(buttonString.equals("Quit")){
            System.exit(0);
        
        }
    }

}

