package ePortfolio;

import javax.swing.*;
import java.awt.event.*;
public class updateInvestment extends JFrame implements ActionListener {
    JLabel commandInterface = new JLabel("<html><span style='font-size:20px;color:black;'>"+"Welcome to EPortfolio"+"</span></html>");
    JLabel command= new JLabel("<html><span style='font-size:20px;color:black'>"+"Commands"+"</span></html>");
    JLabel commandInterface2 = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Updating investment"+"</span></html>");
    JLabel Type =  new JLabel("<html><span style='font-size:15px;color:black;'>"+"Symbol<br/><br/>Name<br/><br/>Price"+"</span></html>");
    JTextField symbol = new JTextField();
    JTextField name = new JTextField();
    JTextField price = new JTextField();
    JButton prev = new JButton("Prev");
    JButton next = new JButton("Next");
    JButton save = new JButton("Save");
    JLabel commandMessage = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Messages"+"</span></html>");
    JTextArea messages = new JTextArea();
    JScrollPane text = new JScrollPane(messages);
    private int index = 0;

    public updateInvestment() {
        //info for updateInvestment GUI
        super("ePorfolio");
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
        symbol.setEnabled(false);

        name.setSize(300,50);
        name.setLocation(150, 150);
        name.setEnabled(false);

        price.setSize(300,50);
        price.setLocation(150, 200);

        prev.setSize(100,50);
        prev.setLocation(600, 100);
        prev.addActionListener(new prev());

         next.setSize(100,50);
        next.setLocation(600, 200);
        next.addActionListener(new next());

        save.setSize(100,50);
        save.setLocation(600,150);
        save.addActionListener(new submit());
      
        text.setSize(800,200);
        text.setLocation(50, 400);

        commandMessage.setSize(100,100);
        commandMessage.setLocation(50, 325);

        add(commandInterface);
        add(commandInterface2);
        add(Type);
        add(symbol);
        add(name);
        add(price);
        add(prev);
        add(next);
        add(text);
        add(commandMessage);
        add(save);
        
     }
     private class submit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //declaring variables
            double update = 0;
            String updatePrice = "";
            double newBookVal = 0.0;
            //get the user input 
            try {
                updatePrice = price.getText();
            } catch (NumberFormatException k) {
                messages.append(k.getMessage());
            }
            
            try {
                //set update to the inputted price
                update = Double.parseDouble(updatePrice);
                if(update>=0){
                    //set it as the new price
                    buyInvestment.investments.get(index).setPrice(update);
                    //loop through the arraylist and if there is a match then set the new book value
                    for (int i = 0; i < buyInvestment.investments.size(); i++){
                        if(buyInvestment.investments.get(i).getType().equalsIgnoreCase("Stock")){
                            newBookVal = buyInvestment.investments.get(index).getPrice() * buyInvestment.investments.get(index).getQuantity() + 9.99;
                        } else if (buyInvestment.investments.get(i).getType().equalsIgnoreCase("Mutual Fund")){
                            newBookVal = buyInvestment.investments.get(index).getPrice() * buyInvestment.investments.get(index).getQuantity();
                        }
                    }
                    buyInvestment.investments.get(index).setBookValue(newBookVal);
                    //print out the new arraylist with updated info
                    messages.setText(buyInvestment.investments.get(index).toString());
                } else {
                    messages.setText("Price must be greater or equal than 0");  
                }
            
            } catch (NumberFormatException ignore) {
                messages.setText("Invalid Price");
            }
        }
     }
     private class prev implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //decrease index by one each time the prev button is pressed
            index--;
            if(index<=0){
                try {
                    //set index to 0 and the the first value in the array list
                    index = 0;
                    symbol.setText(buyInvestment.investments.get(index).getSymbol());
                    name.setText(buyInvestment.investments.get(index).getName());
                    //then set the buttons to off or on
                    prev.setEnabled(false);
                    next.setEnabled(true);
                } catch (Exception k) {
                    messages.append(k.getMessage());
                }
                
            }else{
                try {
                    //print out the previous investment in the arraylist
                    symbol.setText(buyInvestment.investments.get(index).getSymbol());
                    name.setText(buyInvestment.investments.get(index).getName());
                    prev.setEnabled(true);
                    next.setEnabled(true);
                } catch (Exception k) {
                    messages.append(k.getMessage());
                }
                
            }
        }
     }
     private class next implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //increase the index by 1
            index++;
            if(index >= buyInvestment.investments.size()-1){
                try {
                    //print out the last investment in the arraylist
                    index = buyInvestment.investments.size()-1;
                    symbol.setText(buyInvestment.investments.get(index).getSymbol());
                    name.setText(buyInvestment.investments.get(index).getName());
                    next.setEnabled(false);
                    prev.setEnabled(true);
                } catch (Exception k) {
                    messages.append(k.getMessage());
                }
            }else{
                try {
                    //print out the next investment in the array list
                    symbol.setText(buyInvestment.investments.get(index).getSymbol());
                    name.setText(buyInvestment.investments.get(index).getName());
                    next.setEnabled(true);
                    prev.setEnabled(true);
                }catch (Exception k) {
                    messages.append(k.getMessage());
                }
                
            }
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

