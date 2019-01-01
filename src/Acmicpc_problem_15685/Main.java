package Acmicpc_problem_15685;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static boolean[][] check = new boolean[101][101];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for(int i = 0; i < N; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            check[y][x] = true;
            int direction = sc.nextInt();
            int generation = sc.nextInt();
            ArrayList<Point> list = new ArrayList<>();
            if(direction == 0) {
                check[y][x+1] = true;
                list.add(new Point(x+1,y,direction));
            }
            else if(direction == 1) {
                check[y - 1][x] = true;
                list.add(new Point(x,y-1,direction));
            }
            else if(direction == 2){
                check[y][x-1] = true;
                list.add(new Point(x-1,y,direction));
            }
            else if(direction == 3) {
                check[y+1][x] = true;
                list.add(new Point(x,y+1,direction));
            }
            for(int j = 1; j <= generation; j++){
                curve(list);
            }
        }
        int result = 0;
        for(int i = 0 ; i < 100; i++){
            for(int j = 0; j < 100; j++){
                if(check[i][j] && check[i][j+1]
                        && check[i+1][j] && check[i+1][j+1]) result++;
            }
        }
        System.out.println(result);
    }

    private static void curve(ArrayList<Point> list) {
        int size = list.size();
        Point point = list.get(size-1);
        int x = point.x;
        int y = point.y;
        for(int i = 0; i < size; i++){
            Point p = list.get(size-1-i);
            int d = p.direction;
            if(d == 0){
                check[y-1][x] = true;
                list.add(new Point(x,y-1,1));
                y--;
            }
            else if(d == 1){
                check[y][x-1] = true;
                list.add(new Point(x-1,y,2));
                x--;
            }
            else if(d == 2){
                check[y+1][x] = true;
                list.add(new Point(x,y+1,3));
                y++;
            }
            else{
                check[y][x+1] = true;
                list.add(new Point(x+1,y,0));
                x++;
            }
        }
    }

    static class Point{
        int x;
        int y;
        int direction;
        Point(int x, int y, int direction){
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}

