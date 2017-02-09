package lul.gatsbysimulator;

/**
 * Created by James on 2/8/2017.
 */

public class Gatsby {
    private int xBox, yBox;  // coords of the topleft corner
    private int xText, yText; // coordinates of the text"gatsby"

    Gatsby(){
        xBox = 100;
        yBox = 500;
        xText = 120;
        yText = 600;
    }

    public void moveGatsby(){
        xBox += 20;
        xText += 20; // move gatsby right 20 pixels
    }

    public void feelsBadMan(){ // move him back based off number of taps
        xBox -= 10;
        xText -= 10; // move gatsby back 20 pixels
        System.out.println(xBox + " = boxPos");
    }

    public int getXBox(){
        return xBox;
    }
    public int getYBox(){
        return yBox;
    }
    public int getXText(){
        return xText;
    }
    public int getYText(){
        return yText;
    }
}
