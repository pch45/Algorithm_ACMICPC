package Acmicpc_problem_15686;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    static int M;
    static int count;
    static int result;
    static Stack<Point> stack = new Stack<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        M = sc.nextInt();
        int[][] array = new int[N][N];
        count = 0;
        result = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++){
                array[i][j] = sc.nextInt();
                if(array[i][j] == 2) count++;
            }
        }
        if(count == M){
            result = find_min(array);
        }
        else{
            go(array,0, 0, 0);
        }
        System.out.println(result);
    }

    private static void go(int[][] array,int count, int y, int x) {
        if(count == M){
            int min = find_min(array,stack);
            result = result < min ? result : min;
            stack.pop();
            return;
        }
        for(int i = y; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if((i != y || j > x) && array[i][j] == 2){
                    stack.push(new Point(i,j));
                    go(array,count+1,i,j);
                }
            }
        }
        if(!stack.isEmpty()) stack.pop();
    }

    static class Point{
        int x;
        int y;
        Point(int y, int x){
            this.x = x;
            this.y = y;
        }
    }
    private static int find_min(int[][] array, Stack<Point> stack) {
        int min = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 1) {
                    int t = Integer.MAX_VALUE;
                    for(int k = 0 ; k < stack.size(); k++){
                        int r = Math.abs(stack.get(k).y - i) + Math.abs(stack.get(k).x - j);
                        t = t < r ? t : r;
                    }
                    min += t;
                }
            }
        }
        return min;
    }

    private static int find_min(int[][] array) {
        int min = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0 ; j < array[i].length; j++){
                if(array[i][j] == 1){
                    int r = Integer.MAX_VALUE;
                    for(int k = 0 ; k < array.length; k++){
                        for(int l = 0; l < array[k].length; l++){
                            if(array[k][l] == 2){
                                int t = Math.abs(k-i) + Math.abs(l-j);
                                r = r < t ? r : t;
                            }
                        }
                    }
                    min += r;
                }
            }
        }
        return min;
    }
}

