/**
 * Created by Ruari on 01/03/2016.
 */
public enum Statas {
    //enum, returns botht the coulour opposite colout and direction
    RED(-1,"Red"), WHITE(1,"White"),EMPTY(0,"nothing");
    private int value;
    private String text;
    private Statas opp;
    Statas(int value,String text){
        this.value=value;
        this.text=text;
    }
    static {
        RED.opp=WHITE;
        WHITE.opp=RED;
    }
    public int getDir(){
        return value;
    }
    public String getText(){
        return text;
    }

    public Statas getopp() {
        return opp;
    }

}
