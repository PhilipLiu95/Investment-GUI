package ePortfolio;
public class MutualFunds extends Investment{
    private double gain;

    /**
     * Public stocks() takes this. for each private variable
     */
    public MutualFunds(){
        this.gain = 0.0;
    }


    /**
     * gets the gain
     * @return gain
     */
    public double getGain(){
        return gain;
    }

    /**
     * sets the gain
     * @param gain
     */
    public void setGain (double gain){
        this.gain = gain;
    }
    /**
     * Outputs the formatted array
     */
    @Override
    public String toString(){
        return "Symbol: " + this.symbol + "\nName: " + this.name + "\nquantity: " + this.quantity + "\nPrice: $" + this.price + "\nbookValue: $" + this.bookValue + "\n";
    }
    /**
     *  uses specified calcualtions to return the profit gained from the quantity and price
     * @param quantity
     * @param price
     * @return profit
     */
    public double sellPortion(int quantity, double price){
        double commision = 45;
        double payment = 0;
        double profit = 0;

        payment = (quantity * price) - commision;
        profit = payment - this.bookValue * (quantity/this.quantity);
        this.bookValue = this.bookValue * (quantity / this.quantity);
        this.quantity = this.quantity - quantity;

        this.gain = profit;
        return profit;
    }

    /**
     * uses specified calculations to return the profit gained from the price
     * @param price
     * @return profit
     */
    public double sellAll(double price){
        double commision = 45;
        double payment = 0;
        double profit = 0;

        payment = (this.quantity * price) - commision;
        profit = payment - this.bookValue;

        this.gain = profit;
        
        return profit;
    }
}