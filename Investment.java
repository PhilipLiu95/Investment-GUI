package ePortfolio;
    
public class Investment {
    protected String type;
    protected String symbol;
    protected String name;
    protected int quantity;
    protected double price;
    protected double bookValue;

    /**
     * Public stocks() takes this. for each private variable
     */
    public Investment(){
        this.type = "";
        this.symbol = "";
        this.name = "";
        this.quantity = 0;
        this.price = 0.0;
        this.bookValue = 0.0;
    }

    /**
     * gets the type of investment
     * @return type
     */
    public String getType(){
        return this.type;
    }
    /**
     * gets the symbol
     * @return symbol
     */
    public String getSymbol(){
        return this.symbol;
    }
    /**
     * gets the name
     * @return name
     */
    public String getName(){
        return this.name;
    }
    /**
     * gets the quantity
     * @return quantity
     */
    public int getQuantity(){
        return this.quantity;
    }
    /**
     * gets the price
     * @return price
     */
    public double getPrice(){
        return this.price;
    }
    /**
     * gets the bookValue
     * @return booKValue
     */
    public double getBookValue(){
        return this.bookValue;
    }

    /**
     * sets the type
     * @param type
     */
    public void setType(String type) throws Exception{
        if (!(type instanceof String)){
            throw new Exception ("Invalid Input");
        } else {
            this.type = type;
        }
    }
    /**
     * sets the symbol
     * @param symbol
     */
    public void setSymbol(String symbol) throws Exception{
        if (symbol.isEmpty()){
            throw new Exception ("Invalid Input");
        }else {
            this.symbol = symbol;

        }
    }
    /**
     * sets the name
     * @param name
     */
    public void setName (String name) throws Exception{
        if (name.isEmpty()){
            throw new Exception("Invalid Input");
        } else {
            this.name = name;
        }
    }

    /**
     * sets the quantity
     * @param quantity
     */
    public void setQuantity (Integer quantity) throws NumberFormatException{
        if (!(quantity instanceof Integer)){
            throw new NumberFormatException("Invalid Input");
        } else {
            this.quantity = quantity;
        }
    }
    /**
     * sets the price
     * @param price
     */
    public void setPrice (Double price) throws NumberFormatException{
        if (price < 0){
            throw new NumberFormatException("Invalid Input");
        } else {
            this.price = price;
        }
    }
    /**
     * sets the bookvalue
     * @param bookValue
     */
    public void setBookValue(Double bookValue) throws NumberFormatException{
        if (!(bookValue instanceof Double)){
            throw new NumberFormatException("Invalid Input");
        } else {
            this.bookValue = bookValue;
        }
    }

    /**
     * Outputs the formatted array
     */
    public String toString(){
        return "type= " + this.type + "\nsymbol= " + this.symbol + "\nname= " + this.name + "\nquantity= " + this.quantity + "\nprice= " + this.price + "\nbookValue= " + this.bookValue + "\n";
    }
    public String toStringOutput(){
        return "\ntype= "+ "\""+type+"\""+
               "\nsymbol= "+ "\""+symbol+"\""+
               "\nname= "+ "\""+name+"\""+
               "\nquantity= "+ "\""+quantity+"\""+
               "\nprice= "+"\""+price+"\""+
               "\nbookValue= "+"\""+bookValue+"\"";
    }
}
