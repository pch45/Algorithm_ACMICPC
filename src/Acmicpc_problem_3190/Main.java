package Acmicpc_problem_3190;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[][] array = new int[size][size];
        int apples = sc.nextInt();
        int clock = 0;
        int direct = 1;
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(0,0));
        for(int i = 0 ; i < apples; i++){
            array[sc.nextInt()-1][sc.nextInt()-1] = 1;
        }
        int change = sc.nextInt();
        for(int i = 0 ; i < change; i++){
            int sec = sc.nextInt();
            for( ; clock < sec; clock++){
                if(!go(stack,array,direct)) {
                    System.out.println(clock+1);
                    return;
                }
            }
            if(sc.next().equals("D")) direct = (direct+1)%4;
            else direct = (3 + direct) % 4;
        }
        while(go(stack,array,direct)){
            clock++;
        }
        System.out.println(clock+1);
    }

    private static boolean go(Stack<Point> stack, int[][] array, int direct) {
        Point point = stack.pop();
        if(direct == 0){
            if(point.y-1 >= 0) {
                stack.push(point);
                Point new_point = new Point(point.y-1, point.x);
                if(stack.contains(new_point)) return false;
                if (array[point.y-1][point.x] == 0) {
                    stack.remove(0);
                }
                array[point.y-1][point.x] = 0;
                stack.push(new Point(point.y-1, point.x));
                return true;
            }
            else return false;
        }

        else if(direct == 1){
            if(point.x+1 < array[0].length) {
                stack.push(point);
                Point new_point = new Point(point.y, point.x + 1);
                if(stack.contains(new_point)) return false;
                if (array[point.y][point.x + 1] == 0) {
                    stack.remove(0);
                }
                array[point.y][point.x + 1] = 0;
                stack.push(new Point(point.y, point.x + 1));
                return true;
            }
            else return false;
        }

        else if(direct == 2){
            if(point.y+1 < array.length) {
                stack.push(point);
                Point new_point = new Point(point.y+1, point.x);
                if(stack.contains(new_point)) return false;
                if (array[point.y+1][point.x] == 0) {
                    stack.remove(0);
                }
                array[point.y+1][point.x] = 0;
                stack.push(new Point(point.y+1, point.x));
                return true;
            }
            else return false;
        }
        else{
            if(point.x-1 >= 0) {
                stack.push(point);
                Point new_point = new Point(point.y, point.x - 1);
                if(stack.contains(new_point)) return false;
                if (array[point.y][point.x - 1] == 0) {
                    stack.remove(0);
                }
                array[point.y][point.x - 1] = 0;
                stack.push(new Point(point.y, point.x - 1));
                return true;
            }
            else return false;
        }
    }

    static class Point{
        int x;
        int y;
        Point(int y, int x){
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o){
            Point t = (Point) o;
            return (this.x == t.x && this.y == t.y);
        }
    }
}

