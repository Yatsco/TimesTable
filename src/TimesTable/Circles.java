package TimesTable;

public class Circles {

    private int circleNum=0;
    private int xcoord;
    private int ycoord;

    public Circles(int circleNum ,int xcoord, int ycoord ){

        this.circleNum = circleNum;
        this.xcoord = xcoord;
        this.ycoord = ycoord;


    }

    public int getXcoord() {
        return xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public int getCircleNum() {
        return circleNum;
    }


}
