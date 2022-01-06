package ePortfolio;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;
public class searchInvestment extends JFrame implements ActionListener {
    JLabel commandInterface = new JLabel("<html><span style='font-size:20px;color:black;'>"+"Welcome to EPortfolio"+"</span></html>");
    JLabel command= new JLabel("<html><span style='font-size:20px;color:black'>"+"Commands"+"</span></html>");
    JLabel commandInterface2 = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Searching Investments"+"</span></html>");
    JLabel Type =  new JLabel("<html><span style='font-size:15px;color:black;'>"+"Symbol<br/><br/>Name KeyWords<br/><br/>Low price<br/><br/>High price"+"</span></html>");
    JTextField symbol = new JTextField();
    JTextField keyword = new JTextField();
    JTextField lowPrice = new JTextField();
    JTextField highPrice = new JTextField();
    JButton submit = new JButton("Search");
    JButton Reset = new JButton("Reset");
    JLabel commandMessage = new JLabel("<html><span style='font-size:15px;color:black;'>"+"Messages"+"</span></html>");
    JTextArea messages = new JTextArea();
    JScrollPane text = new JScrollPane(messages);
    public searchInvestment() {
        //info for searchInvestment GUI
        super("ePortfolio ");
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
        symbol.setLocation(150, 70);

        keyword.setSize(300,50);
        keyword.setLocation(150, 125);

        lowPrice.setSize(300,50);
        lowPrice.setLocation(150, 180);

        highPrice.setSize(300,50);
        highPrice.setLocation(150, 235);

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
        add(symbol);
        add(lowPrice);
        add(keyword);
        add(highPrice);
        add(Reset);
        add(submit);
        add(text);
        add(commandMessage);
        
     }

     private class submit implements ActionListener{
         public void actionPerformed(ActionEvent e){
             //declaring variables
            String keywords[] = new String[5];
            double botNum = 0;
            double topNum = 0;
            boolean emptySymbol = false;
            boolean emptyKeywords = false;
            boolean emptyRange = false;
            boolean searchBot = false;
            boolean searchTop = false;
            boolean ifFound = false;
            ArrayList<Investment> tempInvestments = new ArrayList<Investment>();
            ArrayList<Integer> index;
            
            String symbolInput = "";
            //getting input values
            try{    
                symbolInput = symbol.getText();
            } catch (Exception k) {
                messages.append(k.getMessage());
            }
            
            if(symbolInput.isBlank()){ 
                emptySymbol = true;
            }
            
            String keywordInput = "";
            try{
                keywordInput = keyword.getText();
            } catch (Exception k){
                messages.append(k.getMessage());
            }
            if(keywordInput.isBlank()){
                emptyKeywords = true;
            } 

            if (keywordInput.isBlank()){
                emptyKeywords = true;
            } else {
                keywords = keywordInput.split("[ ]+");
            }
            //if the user enters nothing
            if(lowPrice.getText().isBlank() && highPrice.getText().isBlank()){
                emptyRange = true;
            } else if (highPrice.getText().isBlank()){   
                //if user only inputs the bottom number
                searchBot = true;
                try {
                    botNum = Double.parseDouble(lowPrice.getText());
                } catch (NumberFormatException k){
                    messages.append(k.getMessage());
                }
            } else if(lowPrice.getText().isBlank()){
                //is user only inputs the top number
                searchTop = true;
                try {
                    topNum = Double.parseDouble(highPrice.getText());
                } catch (NumberFormatException k){
                    messages.append(k.getMessage());
                }
            } else {
                try{
                    //get both numbers
                    botNum = Double.parseDouble(lowPrice.getText());
                    topNum = Double.parseDouble(highPrice.getText());
                } catch (NumberFormatException k){
                    messages.append(k.getMessage());
                }
                
            }
     

            //if emptyKeywords returns true
            if(emptyKeywords){
                //loop through the arraylist
                for(int i = 0; i < buyInvestment.investments.size(); i++){
                    //if user inputted a symbol
                    if(!emptySymbol){ 
                        //add the symbol to the list
                        if(buyInvestment.investments.get(i).getSymbol().compareToIgnoreCase(symbol.getText()) == 0){
                            //add the investment to the temp investment list
                            tempInvestments.add(buyInvestment.investments.get(i));  
                        }
                    } else {
                        tempInvestments.add(buyInvestment.investments.get(i));
                    }
                }
            } else {   
                //if emptyKeywords returns false
                //create and set two new arraylists
                index = new ArrayList<Integer>();
                ArrayList<ArrayList<Integer>> storeIndex = new ArrayList<ArrayList<Integer>>();

                //check if keyword is in hashmap
                for(int i = 0; i < keywords.length; i++){
                    for(Map.Entry<String, ArrayList<Integer>> set : buyInvestment.investmentHM.entrySet()){
                        if(set.getKey().equalsIgnoreCase(keywords[i])){
                            index.addAll(set.getValue());
                            storeIndex.add(set.getValue());
                        }
                    }
                }
                //if the user input more than one keyword
                if(keywords.length > 1){
                    //getting intersecting indecies
                    for(int i = 0; i < storeIndex.size(); i++){
                        storeIndex.get(0).retainAll(storeIndex.get(i));
                    }

                    //assigning indecies to the temp investment list
                    for(int i = 0; i < storeIndex.get(0).size(); i++){
                        tempInvestments.add(buyInvestment.investments.get(storeIndex.get(0).get(i)));
                    }  
                } else{
                    for(int i = 0; i < index.size(); i++){
                        tempInvestments.add(buyInvestment.investments.get(index.get(i)));
                    }  
                }
            }
            
            if(!emptyRange){
                //removing any investments that dont match the search
                if(searchBot){
                    for(int i = 0; i < tempInvestments.size(); i++){
                        ifFound = false;
                        if(tempInvestments.get(i).getPrice() >= botNum){
                            ifFound = true;
                        }
                        if(!ifFound){
                            tempInvestments.remove(i);
                        }
                    }
                } else if(searchTop){
                    for(int i = 0; i < tempInvestments.size(); i++){
                        ifFound = false;
                        if(tempInvestments.get(i).getPrice() <= topNum){
                            ifFound = true;
                        }
                        if(!ifFound){
                            tempInvestments.remove(i);
                        }
                    }
                } else{
                    for(int i = 0; i < tempInvestments.size(); i++){
                        ifFound = false;
                        if(tempInvestments.get(i).getPrice() >= botNum && tempInvestments.get(i).getPrice() <= topNum){
                            ifFound = true;
                        }
                        if(!ifFound){
                            tempInvestments.remove(i);
                        }
                    }
                }
            }

            //print out the investments that match search
            messages.append("Investments Found:\n");
            for(int i = 0; i < tempInvestments.size(); i++){
                messages.append("" + tempInvestments.get(i));
            }
         }
     }

     private class Reset implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          //resetting the text boxes
          symbol.setText("");
          keyword.setText("");
          lowPrice.setText("");
          highPrice.setText("");
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
