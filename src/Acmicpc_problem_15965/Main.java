package Acmicpc_problem_15965;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int num = sc.nextInt();
        boolean[] check = new boolean[7368788];
        for(int i = 2; i < 7368787; i++){
            if(check[i]) continue;
            int t = 7368787/i;
            for(int j = 2; j <= t; j++){
                check[i*j] = true;
            }
        }
        int i = 2;
        for( ; num != 0; i++){
            if(!check[i]) num--;
        }
        System.out.println(i-1);
    }
}

