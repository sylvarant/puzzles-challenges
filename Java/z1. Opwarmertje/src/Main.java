import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Main {

        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                List<Integer> list = new ArrayList<Integer>();
                for(int i=0; i < n; i++){
                        list.add(sc.nextInt());
                }
                Collections.sort(list);
                
                //sort(list);
                System.out.print(list.get(0));
                System.out.print(" ");
                System.out.println(list.get(n -1 ));
                
        }
}