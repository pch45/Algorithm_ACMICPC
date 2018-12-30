package Acmicpc_problem_16235;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int N;
    static int[][] feed,t_feed;
    static tree[][] trees;
    static int[] dx = new int[]{-1,0,1,-1,1,-1,0,1};
    static int[] dy = new int[]{-1,-1,-1,0,0,1,1,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int h = sc.nextInt();
        int M = sc.nextInt();
        feed = new int[N][N];
        t_feed = new int[N][N];
        trees = new tree[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++){
                t_feed[i][j] = sc.nextInt();
                trees[i][j] = new tree();
                feed[i][j] = 5;
            }
        }

        for(int i = 0 ; i < h; i++){
            int y = sc.nextInt()-1;
            int x = sc.nextInt()-1;
            int age = sc.nextInt();
            trees[y][x].add(new Info(x,y,age));
        }

        for(int i = 0; i < M; i++){
            for(int j = 0 ; j < N; j++){
                for(int k = 0 ; k < N; k++){
                    ArrayList<Info> list = trees[j][k].list;
                    for(int s = 0; s < list.size(); s++){
                        if(!use(list.get(s))){
                            while(list.size() > s){
                                Info t = list.remove(s);
                                feed[j][k] += (t.age / 2);
                            }
                        }
                    }
                    feed[j][k] += t_feed[j][k];
                }
            }
            for(int j = 0; j < N; j++){
                for(int k = 0 ; k < N; k++){
                    ArrayList<Info> list = trees[j][k].list;
                    for(int s = 0; s < list.size(); s++){
                        if(list.get(s).age % 5 == 0){
                            eight(list.get(s));
                        }
                    }
                }
            }
        }
        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++){
                result += trees[i][j].list.size();
            }
        }
        System.out.println(result);
    }

    private static void eight(Info info) {
        for(int i = 0 ; i < 8; i++){
            int tx = info.x + dx[i];
            int ty = info.y + dy[i];
            if(tx < 0 || tx > N-1 || ty < 0 || ty > N-1) continue;
            trees[ty][tx].add(new Info(tx,ty,1));
        }
    }

    private static boolean use(Info info) {
        if(feed[info.y][info.x] >= info.age){
            feed[info.y][info.x] -= info.age;
            info.age++;
            return true;
        }
        return false;
    }

    static class tree{
        ArrayList<Info> list = new ArrayList<>();
        void add(Info info){
            list.add(0,info);
        }
    }
    static class Info{
        int x,y,age;
        Info(int x, int y, int age){
            this.x =x;
            this.y = y;
            this.age = age;
        }
    }
}
