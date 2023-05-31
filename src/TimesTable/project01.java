package TimesTable;
/**
 * Jarek Yatsco
 * 1/27/21
 * Times Table Project
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class project01 extends Application implements EventHandler {
    private  boolean animPlay = false;
    final int x = 400;
    final int y = 400;
    private int points = 360;
    private double timesNum = 1;
    final int radius = 300;
    final int pointRadius = 10;
    private  int galleryNum = 0;
    private int frame=1;
    private int colorNum=0;
    Map<Integer,Circles> coordList = new HashMap();
    Map<Integer,Line> linesMap = new HashMap();
    Color color = Color.BLACK;
    private long lastUpdate;
    private String pointStr;




    /**
     * Creates the points around the circle
     * @param gc graphicsContext
     */
    public void circlePoints(GraphicsContext gc, int points){
        int xcoord =0;
        int ycoord =0;

        for (int i = 0; i < points; i++) {
            xcoord=(int)(x+Math.cos(i*(PI/(((float)points)/2)))*radius);
            ycoord=(int)(y+Math.sin(i*(PI/(((float)points)/2)))*radius);
            gc.setStroke(color);
            gc.strokeOval(xcoord-pointRadius,ycoord-pointRadius,
                    pointRadius*2,pointRadius*2);
            coordList.put(i,new Circles(i,xcoord,ycoord));



        }
    }

    /**
     * Draws a line from 1 point to another point
     * @param c1 point 1
     * @param c2 point 2
     */
    public Line lines(Circles c1, Circles c2,Pane root){
        int circleX1 = c1.getXcoord();
        int circleY1 = c1.getYcoord();
        int circleX2 = c2.getXcoord();
        int circleY2 = c2.getYcoord();

        Line line0 = new Line(circleX1,circleY1,circleX2,circleY2);
        line0.setStroke(color);

        root.getChildren().add(line0);

        return line0;







    }

    /**
     * Draws the lines on the canvas
     * @param num starting point
     * @param root the window
     */
    public void drawLines(int num, Pane root){
        Line line = new Line();

        for (int i = 0; i < points; i++) {
            Circles target = nextCircle((int)(num*timesNum));
            line = lines(nextCircle(num),target, root);
            linesMap.put(i,line);


            num++;

        }






    }

    /**
     * Finds the circle that the line needs to be drawn to
     * @param startingNum starting number
     * @return the target circle
     */
    public Circles nextCircle(int startingNum){
        Circles targetCircle = coordList.get(startingNum % points);
        return  targetCircle;



    }

    /**
     * sets the values of the animation to set numbers
     * and changes the animation to those values
     * @param multiplier the multiplier
     */
    public void gallery(Slider multiplier){
        switch(galleryNum) {
            case 0:

                pointStr="180";
                timesNum=.667;
                multiplier.setValue(0);
                animPlay=false;

                break;
            case 1:
                pointStr="333";
                timesNum=81;
                multiplier.setValue(0);
                animPlay=false;


                break;
            case 2:

                pointStr="360";
                timesNum=51;
                multiplier.setValue(0);
                animPlay=false;

                break;
            case 3:
                pointStr="360";
                timesNum=54;
                multiplier.setValue(0);
                animPlay=false;

                break;
            case 4:

                pointStr="360";
                timesNum=90;
                multiplier.setValue(0);
                animPlay=false;

                break;
            case 5:

                pointStr="360";
                timesNum=66;
                multiplier.setValue(0);
                animPlay=false;

                break;
            case 6:

                pointStr="360";
                timesNum=19;
                multiplier.setValue(0);
                galleryNum=-1;
                animPlay=false;

                break;

        }
    }


    /**
     * changes the color of the animation
     * @param colorNum color number
     */
    public void buttonColor(int colorNum){
        switch(colorNum){
            case 0:
                color=Color.BLACK;
                break;
            case 1:
                color=Color.RED;
                break;
            case 2:
                color=Color.GRAY;
                break;
            case 3:
                color=Color.GREEN;
                break;
            case 4:
                color=Color.PINK;
                break;
            case 5:
                color=Color.TURQUOISE;
                break;
            case 6:
                color=Color.ORANGE;
                break;
            case 7:
                color=Color.BLUE;
                break;
            case 8:
                color=Color.YELLOW;
                break;
            case 9:
                color=Color.PURPLE;
                break;

        }

    }

    /**
     * removes lines
     * @param root window
     */
    public void remove(Pane root){

        for (int i = 0; i <360 ; i++) {
            root.getChildren().remove(linesMap.get(i));


        }

    }

    /**
     *  calculates how fast the animation runs
     * @param fps frames
     * @return fps
     */
    public long calcFPS(int fps){
        return 1000000000/ (long)fps;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        double width  = 800;
        double height = 800;
        primaryStage.setTitle("TimesTable");
        Button animButton = new Button();
        Slider speedSlider = new Slider();
        Label speedText = new Label();
        Label incrementText = new Label();
        speedText.setText("Animation Speed: "+(int)speedSlider.getValue());

        Label pointsText = new Label();
        pointsText.setText("Points");

        TextField pointsTextField = new TextField();
        pointsTextField.setPrefSize(100,0);
        pointsTextField.setText(""+points);

        Label multText = new Label();
        multText.setText("Exact Multiplier");

        TextField multTextField = new TextField();
        multTextField.setPrefSize(100,0);
        multTextField.setText(""+(int)timesNum);

        Button changeButton = new Button();
        changeButton.setText("Change Exact Multiplier");

        speedSlider.setMax(100);
        speedSlider.setMin(1);
        speedSlider.setBlockIncrement(10);
        speedSlider.setSnapToTicks(true);

        animButton.setText("Start/Stop");
        Button colorButton = new Button();
        colorButton.setText("Switch Color");

        Canvas canvas = new Canvas(width, height);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Slider increment = new Slider();
        increment.setMin(0.0);
        increment.setMax(5.0);
        incrementText.setText("Increment : "+(int)increment.getValue());
        increment.setPrefSize(100,0);

        Button gallery = new Button();
        gallery.setText("Gallery");;
        VBox vbox = new VBox();
        vbox.getChildren().addAll(animButton,speedText,speedSlider,colorButton,
                incrementText,increment,pointsText,pointsTextField,multText,
                multTextField,changeButton,gallery);

        Label text = new Label (" Initial String ");
        Pane root = new Pane(canvas, text);

        root.getChildren().addAll(vbox);

        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animTimer = new AnimationTimer() {
            int startingNum = 1;
            @Override
            public void handle(long now) {
                pointStr = pointsTextField.getText();
                points = Integer.parseInt(pointStr);

                circlePoints(graphicsContext, points);

                if (now - lastUpdate >=calcFPS(frame)){
                    remove(root);

                    drawLines(startingNum, root);
                    timesNum += increment.getValue();

                    lastUpdate=now;
                }


                if (timesNum>=360){
                    timesNum=1;
                }

                if ((int)speedSlider.getValue()==0){
                    animPlay=false;
                }
                incrementText.setText("Increment: "+increment.getValue());

                speedText.setText("Animation Speed: "+
                        (int)speedSlider.getValue());
                    buttonColor(colorNum);



            }

        };

        changeButton.setOnAction(event -> {
            String timesStr = multTextField.getText();
            timesNum = Integer.parseInt(timesStr);
            animPlay=false;

        });
        speedSlider.setOnMouseReleased(event -> {
            frame = (int)speedSlider.getValue();

                });
        gallery.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gallery(increment);
                galleryNum++;
            }
        });

        colorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (event.getSource()==colorButton){
                    animPlay=false;
                    colorNum++;
                    if (colorNum>9){
                        colorNum=0;
                    }
                }
            }
        });
        animButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * If the button is pressed pause/play the animation
             * @param event click the pause/play button
             *
             */
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource()==animButton){


                    if (animPlay==false&&speedSlider.getValue()>0){
                        animTimer.start();
                        increment.setValue(1);
                        animPlay=true;

                    }
                    else {
                        animTimer.stop();
                        animPlay=false;
                    }
                }

            }
        });


    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {

    }
}
