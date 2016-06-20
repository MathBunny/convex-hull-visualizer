package ca.horatiu.convex_hull_visualizer;

/**
 * Created by Horatiu on 19/06/2016.
 */
import java.util.*;
import java.lang.*;


class Main{

    public static void main (String [] args){
        new Main();
    }

    public Main(){
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(1, 1);
        Coordinate e = new Coordinate(3, 3);
        Coordinate c = new Coordinate(2, 2);
        Coordinate d = new Coordinate(2, 1);
        Coordinate f = new Coordinate(4, 4);


        Coordinate [] arr = new Coordinate[6];
        arr[0] = a;
        arr[1] = e;
        arr[2] = c;
        arr[3] = d;
        arr[4] = b;
        arr[5] = f;

        int N = arr.length;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int value = 0; //minimum value
        for(int x = 0; x < arr.length; x++){
            if (arr[x].getY() < minY || (arr[x].getY() == minY && arr[x].getX() < minX)){
                minY = arr[x].getY();
                minX = arr[x].getX();
                value = x;
            }
        }
        //You got the starting point, now you have to sort everything by the y coordinate!

        for(int x = 0; x < arr.length; x++){
            if (x == value)
                continue;
            if (arr[x].getX() == minX){
                arr[x].setAngle(90);
            }
            //else if (arr[x].getX() < minX){ //negative! <-- error is here
            //      arr[x].setAngle(Math.atan((double)(arr[x].getY()-minY)/(minX - arr[x].getX())));
            //}
            else{
                arr[x].setAngle(Math.atan((double)(arr[x].getY()-minY) /(arr[x].getX() - minX)));
            }
        }

        Arrays.sort(arr);
        for(int x = 0; x < arr.length; x++){
            if (arr[x].getAngle() != -Double.MAX_VALUE){
                //System.out.println(arr[x]);
            }
            else{
                //System.out.println("START");
            }
        }

        Stack<Coordinate> Q = new Stack<Coordinate>(); //perhaps remove the point (5,0)?
        Q.push(arr[0]);
        Q.push(arr[1]);
        for(int x = 2; x < N; x++){
            Q.push(arr[x]);
            while(true){
                //check to see if arr[0]arr[1] vector angle with arr[1][2] is a right
                if (Q.size() < 3)
                    break;
                Coordinate p2 = Q.pop();
                Coordinate p1 = Q.pop();
                Coordinate p0 = Q.pop();
                Vector p0p1 = new Vector(p1.getX()-p0.getX(), p1.getY()-p0.getY());
                Vector p1p2 = new Vector(p2.getX()-p1.getX(), p2.getY()-p1.getY());
                if (p0p1.turnsLeft(p1p2)){
                    Q.push(p0);
                    Q.push(p1);
                    Q.push(p2);
                    break;
                }
                else{
                    System.out.println("Remove: " + p1 + " with: " + p0 + ", " + p1 + " = " + p2 + "\n\n");
                    //remove the last candidate
                    Q.push(p0);
                    Q.push(p2);
                }
            }
        }

        System.out.println("Convex Hull:");
        int size = Q.size();
        for(int x = 0; x < size; x++)
            System.out.println(Q.pop());


        //System.out.println(arr[value]);
    }
}
