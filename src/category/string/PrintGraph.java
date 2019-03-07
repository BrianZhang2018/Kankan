package category.string;

/**
 * Created by brianzhang on 3/6/19.
 */
public class PrintGraph {
    public static void main(String[] args) {
        int max = 10;
        int mid = max/2+1;
        for(int i=1; i<=max; i++){
            for(int j=1; j<= (i>=mid? max-i:i-1) ;j++){
                System.out.print(" ");
            }
            System.out.println(i);
        }
    }
}
