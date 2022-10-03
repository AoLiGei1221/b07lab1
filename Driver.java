import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        double [] c1 = {-9, 6, 5};
        int [] e1 = {2, 1, 4};
        Polynomial p1 = new Polynomial(c1, e1);
        double[] c2 = {-9, 8, 4, -5};
        int[]    e2 = {2,  3, 4,  5};
        Polynomial p2 = new Polynomial(c2, e2);
        System.out.println("ADD");
        Polynomial np = p1.add(p2);
        for (int i = 0; i < np.coefficients.length; i++)
            System.out.print(np.coefficients[i] + " ");
        System.out.println();
        for (int i = 0; i < np.exp.length; i++)
            System.out.print(np.exp[i] + " ");
        System.out.println();

        System.out.println("MULTIPLY");
        np = p1.multiply(p2);
        for (int i = 0; i < np.coefficients.length; i++)
            System.out.print(np.coefficients[i] + " ");
        System.out.println();

        for (int i = 0; i < np.exp.length; i++)
            System.out.print(np.exp[i] + " ");


        System.out.println();

        double [] b1 = {-2,-6,5};
        int [] b2 = {1,0,4};
        Polynomial f = new Polynomial(b1,b2);
        double [] a1 = {-3,4,3,-4};
        int [] a2 = {2,3,1,4};
        Polynomial p3 = new Polynomial(a1,a2);
        Polynomial s = f.multiply(p2);
        System.out.println("Test multiply:");
        for(int i = 0; i < s.coefficients.length; i++) {
            System.out.println(s.coefficients[i]);
            System.out.println(s.exp[i]);
        }
    }
}
