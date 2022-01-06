package ePortfolio;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Philip Liu
//CIS 2430
//Nov 30 2021

public class GUI extends JFrame implements ActionListener{
    JLabel commandInterface = new JLabel("<html><span style='font-size:20px;color:black;'>"+"Welcome to ePortfolio"+"</span></html>");
    JLabel commandInterface3 = new JLabel("<html><span style='font-size:20px;color:black'>"+"Choose a command from the \"Commands\"menu to buy for sell an investment,update prices for all investments,getgain for the portfolio,search for relavant investments,or quit the program"+"</span></html>",SwingConstants.CENTER);
    public GUI(){
        //setting the opening GUI
        super("ePortFolio");
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

        commandInterface3.setSize(600, 300);
        commandInterface3.setLocation(175, 100);

        commandInterface.setSize(1055,50);
        commandInterface.setLocation(275,0);
        
        add(commandInterface3);
        add(commandInterface);
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
    public static void main(String[]args){
        GUI gui = new GUI();
        gui.setVisible(true);
    }
}
