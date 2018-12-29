package Acmicpc_problem_16236;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N,x,y,size;
    static int[][] array;
    static int count,feed;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        array = new int[N][N];
        size = 2;
        count = 0;
        feed = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                array[i][j] = sc.nextInt();
                if(array[i][j] == 9) {
                    y = i;
                    x = j;
                    array[i][j] = 0;
                }
            }
        }
        BFS();
        System.out.println(count);
    }

    private static void BFS() {
        boolean[][] check = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y,0));
        while(!queue.isEmpty()){
            int tx = x; int ty = y;
            int cc = Integer.MAX_VALUE;
            int ccc = 0;
            while(!queue.isEmpty()){
                Point p = queue.poll();
                if(check[p.y][p.x]) continue;
                check[p.y][p.x] = true;
                if(p.count > cc) break;
                if(p.y > 0 && array[p.y-1][p.x] <= size &&!check[p.y-1][p.x]){
                    if(array[p.y-1][p.x] > 0 &&array[p.y-1][p.x] < size){
                        if(p.count == cc){
                            if(p.y-1 < y){
                                y = p.y-1;
                                x = p.x;
                            }
                            else if(p.y-1 == y && p.x < x){
                                x = p.x;
                            }
                        }
                        else{
                            y = p.y-1;
                            x = p.x;
                            ccc = p.count+1;
                            cc = ccc-1;
                            continue;
                        }
                    }
                    queue.add(new Point(p.x,p.y-1,p.count+1));
                }

                if(p.x > 0 && array[p.y][p.x-1] <= size &&!check[p.y][p.x-1]){
                    if(array[p.y][p.x-1] > 0 &&array[p.y][p.x-1] < size){
                        if(p.count == cc){
                            if(p.y < y){
                                y = p.y;
                                x = p.x-1;
                            }
                            else if(p.y == y && p.x-1 < x){
                                x = p.x-1;
                            }
                        }
                        else{
                            y = p.y;
                            x = p.x-1;
                            ccc = p.count+1;
                            cc = ccc-1;
                            continue;
                        }
                    }
                    queue.add(new Point(p.x-1,p.y,p.count+1));
                }

                if(p.x < N-1 && array[p.y][p.x+1] <= size  &&!check[p.y][p.x+1]){
                    if(array[p.y][p.x+1] > 0 &&array[p.y][p.x+1] < size){
                        if(p.count == cc){
                            if(p.y < y){
                                y = p.y;
                                x = p.x+1;
                            }
                            else if(p.y == y && p.x+1 < x){
                                x = p.x+1;
                            }
                        }
                        else{
                            y = p.y;
                            x = p.x+1;
                            ccc = p.count+1;
                            cc = ccc-1;
                            continue;
                        }
                    }
                    queue.add(new Point(p.x+1,p.y,p.count+1));
                }

                if(p.y < N-1 && array[p.y+1][p.x] <= size && !check[p.y+1][p.x]){
                    if(array[p.y+1][p.x] > 0 &&array[p.y+1][p.x] < size){
                        if(p.count == cc){
                            if(p.y+1 < y){
                                y = p.y+1;
                                x = p.x;
                            }
                            else if(p.y+1 == y && p.x < x){
                                x = p.x;
                            }
                        }
                        else{
                            y = p.y+1;
                            x = p.x;
                            ccc = p.count+1;
                            cc = ccc-1;
                            continue;
                        }
                    }
                    queue.add(new Point(p.x,p.y+1,p.count+1));
                }
            }
            if(tx == x && ty == y) break;
            count += ccc;
            queue = new LinkedList<>();
            queue.add(new Point(x,y,0));
            array[y][x] = 0;
            feed++;
            if(feed == size){
                size++;
                feed = 0;
            }
            check = new boolean[N][N];
        }
    }

    static class Point{
        int x,y,count;
        Point(int x,int y,int count){
            this.x = x; this.y = y; this.count = count;
        }
    }
}

