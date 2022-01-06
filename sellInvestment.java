package ePortfolio;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class sellInvestment extends JFrame implements ActionListener {
    JLabel commandInterface2 = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Selling an investment"+"</span></html>");
    JLabel Type =  new JLabel("<html><span style='font-size:15px;color:black;'>"+"Symbol<br/><br/>Quantity<br/><br/>Price"+"</span></html>");
   
    JTextField symbol = new JTextField();
    JTextField quantity = new JTextField();
    JTextField price = new JTextField();
    JButton submit = new JButton("Sell");
    JButton Reset = new JButton("Reset");
    JLabel commandMessage = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Messages"+"</span></html>");
    JTextArea messages = new JTextArea();
    JScrollPane text = new JScrollPane(messages);

    //declaring variables
    double total = 0;
    String[] parsedName;
    ArrayList<Integer> index = null;
    DecimalFormat df = new DecimalFormat("#.##");

    public sellInvestment() {
      //info for sellInvestment GUI
        super("ePortfolio");
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

        symbol.setSize(300,50);
        symbol.setLocation(150, 100);
        quantity.setSize(300,50);
        quantity.setLocation(150, 150);

        price.setSize(300,50);
        price.setLocation(150, 200);

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

        add(commandInterface2);
        add(Type);
        add(symbol);
        add(quantity);
        add(price);
        add(Reset);
        add(submit);
        add(text);
        add(commandMessage);
        
     }

     private class submit implements ActionListener{
         public void actionPerformed(ActionEvent e){
            //setting local variables
            boolean exist = false;
            int i = 0;
            String symbolStr = "";
            //getting the input values
            try{
                symbolStr = symbol.getText();
            } catch (Exception k){
                messages.append(k.getMessage());
            }

            int quantityInput = 0;
            try {   
                quantityInput = Integer.parseInt(quantity.getText());
            } catch (NumberFormatException k) {
                messages.append(k.getMessage());
            }

            double priceInput = 0.0;
            
            try {
                priceInput = Double.parseDouble(price.getText());
            } catch (NumberFormatException k) {
                messages.append(k.getMessage());
            }

            messages.setText("");
            int ifExist = 0;
            //loop through the arraylist to see if their is a match
            for (i = 0; i < buyInvestment.investments.size(); i++){
                if (symbolStr.equalsIgnoreCase(buyInvestment.investments.get(i).getSymbol())){
                    exist = true;
                    ifExist = i;
                    break;
                }
            }
            //if there is a match
            if (exist == true){
                if (buyInvestment.investments.get(ifExist).getType().equalsIgnoreCase("Stock")){
                    if (buyInvestment.investments.get(ifExist).getQuantity() > quantityInput){
                      //use the stock class to get the arraylist then calculate the sell portion or sell all
                        total = ((Stocks)buyInvestment.investments.get(ifExist)).sellPortion(quantityInput, priceInput);
                        //printing out the profit
                        messages.setText("The overal profit is + " + df.format(total));
                        //if the user sells all the stocks
                    } else if (buyInvestment.investments.get(ifExist).getQuantity() == quantityInput){
                        total = ((Stocks)buyInvestment.investments.get(ifExist)).sellAll(priceInput);
                        //calculate the overal profit
                        messages.setText("The overal profit is + " + df.format(total));
                        //splitting the name 
                        parsedName = buyInvestment.investments.get(ifExist).getName().toLowerCase().split("[ ]+");
                        index = new ArrayList<Integer>();
    
                        //updating the any index that is higher than the value being deleted
                        for (String key : buyInvestment.investmentHM.keySet()){
                            index = buyInvestment.investmentHM.get(key);
                            for (int j = 0; j < index.size(); j++){
                                if (index.get(j) > ifExist){
                                    int val = 0;
                                    val = index.get(j);
                                    index.set(j, val - 1);
                                }
                            }
                        }
    
                        //deleting the index associated with the name
                        for(int j = 0; j < parsedName.length; j++){
                            if (buyInvestment.investmentHM.containsKey(parsedName[j])){
                                index = buyInvestment.investmentHM.get(parsedName[j]);
                                for (int k = 0; k < index.size(); k++){
                                    if (index.get(j) == ifExist){
                                        index.remove(j);
                                    }
                                }
                            }
                            buyInvestment.investmentHM.put(parsedName[j], index);
                        }
    
                        //taking away the index if the name is empty
                        for (int j = 0; j < parsedName.length; j++){
                            if (buyInvestment.investmentHM.containsKey(parsedName[j])){
                                index = buyInvestment.investmentHM.get(parsedName[j]);
                                if (index.size() == 0){
                                    buyInvestment.investmentHM.remove(parsedName[j]);
                                }
                            }
                        }
                        buyInvestment.investments.remove(ifExist);
                    } else {
                        messages.append("Not enough shares");
                    }
                } else if (buyInvestment.investments.get(ifExist).getType().equalsIgnoreCase("Mutual Fund")){
                  //use the class variables to get the arraylist and then calculate the amount to sell
                    MutualFunds sellMF = ((MutualFunds)buyInvestment.investments.get(ifExist));
                    if (buyInvestment.investments.get(ifExist).getQuantity() > quantityInput){
                        total = sellMF.sellPortion(quantityInput, priceInput);
                        //calculate the overal profit
                        messages.append("The overal profit is + " + df.format(total));
                        //if the user selects all
                    } else if (buyInvestment.investments.get(ifExist).getQuantity() == quantityInput){
                        total = sellMF.sellAll(priceInput);
                        //calculate the overal profit
                        messages.append("The overal profit is + " + df.format(total));
                        //splitting the name 
                        parsedName = buyInvestment.investments.get(ifExist).getName().toLowerCase().split("[ ]+");
                        index = new ArrayList<Integer>();
    
                        //updating the any index that is higher than the value being deleted
                        for (String key : buyInvestment.investmentHM.keySet()){
                            index = buyInvestment.investmentHM.get(key);
                            for (int j = 0; j < index.size(); j++){
                                if (index.get(j) > ifExist){
                                    int val = 0;
                                    val = index.get(j);
                                    index.set(j, val - 1);
                                }
                            }
                        }
    
                        //deleting the index associated with the name
                        for(int j = 0; j < parsedName.length; j++){
                            if (buyInvestment.investmentHM.containsKey(parsedName[j])){
                                index = buyInvestment.investmentHM.get(parsedName[j]);
                                for (int k = 0; k < index.size(); k++){
                                    if (index.get(j) == ifExist){
                                        index.remove(j);
                                    }
                                }
                            }
                            buyInvestment.investmentHM.put(parsedName[j], index);
                        }
    
                        //taking away the index if the name is empty
                        for (int j = 0; j < parsedName.length; j++){
                            if (buyInvestment.investmentHM.containsKey(parsedName[j])){
                                index = buyInvestment.investmentHM.get(parsedName[j]);
                                if (index.size() == 0){
                                    buyInvestment.investmentHM.remove(parsedName[j]);
                                }
                            }
                        }
                        //remove the hash
                        buyInvestment.investments.remove(ifExist);
                      }
                  }
                 
            } else {
              //print out the message if the symbol isn't found
              messages.append("Symbol not found");
            } 
         }
     }

     private class Reset implements ActionListener {
       //reset the text boxes
      public void actionPerformed(ActionEvent e) {
          symbol.setText("");
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


