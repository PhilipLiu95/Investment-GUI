package ePortfolio;

import javax.swing.*;

import java.text.DecimalFormat;
import java.awt.event.*;


public class getGain extends JFrame implements ActionListener {
    JLabel commandInterface2 = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Getting total gain"+"</span></html>");
    JLabel Type =  new JLabel("<html><span style='font-size:15px;color:black;'>"+"Total gain"+"</span></html>");
    JTextField getValue = new JTextField();
    JLabel commandMessage = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Individual Gains"+"</span></html>");
    JTextArea messages = new JTextArea();
    JScrollPane text = new JScrollPane(messages);
    //declaring variables
    double total = 0; 
    DecimalFormat df = new DecimalFormat("#.##");

    public getGain() {
        //info for the getGain JFrame
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

        getValue.setSize(300,50);
        getValue.setLocation(150, 150);
        getValue.setEnabled(false);

        text.setSize(800,200);
        text.setLocation(50, 400);
        messages.setEnabled(false);

        commandMessage.setSize(200,100);
        commandMessage.setLocation(50, 325);

        add(commandInterface2);
        add(Type);
        add(getValue);
  
        add(text);
        add(commandMessage);

        getGains();
        
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

    public void getGains() {
        //initialize variables
        double totalStockGain = 0;
        double totalMFGain = 0;
        //if the arraylist is empty
        if (buyInvestment.investments.size() == 0){
            messages.append("There are no investments in your portfolio");
            return;
        }
        //if there is a match
        for (int i = 0; i < buyInvestment.investments.size(); i++){
            if (buyInvestment.investments.get(i).getType().equalsIgnoreCase("Stock")){
                //update the total stock gain
                totalStockGain = ((buyInvestment.investments.get(i).getQuantity() * buyInvestment.investments.get(i).getPrice() - 9.99) - buyInvestment.investments.get(i).getBookValue())+ totalStockGain;
                messages.append("\nTotal stock gain is now: " + df.format(totalStockGain)); // for testing
            } else if (buyInvestment.investments.get(i).getType().equalsIgnoreCase("Mutual Fund")){
                //update the total mutual fund gain
                totalMFGain = ((buyInvestment.investments.get(i).getQuantity() * buyInvestment.investments.get(i).getPrice() - 140.99) - buyInvestment.investments.get(i).getBookValue())+ totalMFGain;
                messages.append("\nTotal mutual fund gain is now: " + df.format(totalMFGain)); // for testing
            }
        }
        //calculate the total gain
        total = totalStockGain + totalMFGain;
        //print out the total get gain
        getValue.setText("" + df.format(total));
    }

}

