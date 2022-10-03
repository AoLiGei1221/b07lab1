import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;


public class Polynomial{
    double[] coefficients;
    int[] exp;

    public Polynomial() {
        this.coefficients = new double[0];
        this.exp = new int[0];
    }

    public void Read(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine().replace("-", "+-");
        if(line.charAt(0) == '+') line = line.substring(1);
        scanner.close();
        String [] arrOfNewline = line.split("\\+");
        double[] a = new double[arrOfNewline.length];
        int[] b = new int[arrOfNewline.length];
        int idx_a = 0, idx_b = 0;
        for (String n : arrOfNewline){
            boolean flag = (n.charAt(n.length() - 1) == 'x');
            String[] sub = n.split("x");
            //check if it is just constant.(check if len of array = 1)
            if(sub.length == 1 && !flag){
                a[idx_a++] = Double.parseDouble(sub[0]);
                b[idx_b++] = 0;
                continue;
            }
            //check if its exp is 1
            if(sub.length == 1){
                a[idx_a++] = Double.parseDouble(sub[0]);
                b[idx_b++] = 1;
                flag = false;
                continue;
            }
            a[idx_a++] = Double.parseDouble(sub[0]);
            b[idx_b++] = Integer.parseInt(sub[1]);
        }
        coefficients = a;
        exp = b;
    }

    public Polynomial(double[] coefficients, int[] exp) {
        this.coefficients = coefficients;
        this.exp = exp;
    }

    //--------------------------------------------
    //helper method for add and multiply
    public boolean check(int[] exp) {
        boolean flag = false;
        for (int i = 0; i < this.exp.length - 1; i++){
            if (this.exp[i] > this.exp[i + 1]){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public Polynomial add(Polynomial other) {
        //if they are empty
        if (this.exp.length == 0)
            return new Polynomial(other.coefficients, other.exp);
        if (other.exp.length == 0)
            return new Polynomial(this.coefficients, this.exp);

        //check if it is in ascending order
        boolean this_Flag = check(this.exp);
        boolean other_Flag = check(other.exp);
        //sort array by using selection sort
        if(this_Flag) {
            int n = this.exp.length;
            for (int i = 0; i < n - 1; i++) {
                int min_idx = i;
                for (int j = i + 1; j < n; j++)
                    if (this.exp[j] < this.exp[min_idx])
                        min_idx = j;

                int temp1 = this.exp[min_idx];
                this.exp[min_idx] = this.exp[i];
                this.exp[i] = temp1;
                double temp2 = this.coefficients[min_idx];
                this.coefficients[min_idx] = this.coefficients[i];
                this.coefficients[i] = temp2;
            }
        }
        if(other_Flag){
            int k = other.exp.length;
            for (int x = 0; x < k - 1; x++) {
                int min_idx_other = x;
                for (int y = x + 1; y < k; y++)
                    if (other.exp[y] < other.exp[min_idx_other])
                        min_idx_other = y;

                int temp3 = other.exp[min_idx_other];
                other.exp[min_idx_other] = other.exp[x];
                other.exp[x] = temp3;
                double temp4 = other.coefficients[min_idx_other];
                other.coefficients[min_idx_other] = other.coefficients[x];
                other.coefficients[x] = temp4;
            }
        }
        int max_exp = Math.max(this.exp[this.exp.length - 1], other.exp[other.exp.length -1]);
        double[] temp_coe = new double[max_exp + 1];
        for (int i = 0; i < this.coefficients.length; i++)
            temp_coe[this.exp[i]] += this.coefficients[i];

        for (int i = 0; i < other.coefficients.length; i++)
            temp_coe[other.exp[i]] += other.coefficients[i];

        // count how many non-zero coefficients and accelerate length
        int len = 0;
        for (double v : temp_coe)
            if (v != 0)
                len++;

        double[] new_coe = new double[len];
        int[] new_exp = new int[len];

        int j = 0;
        for (int i = 0; i < temp_coe.length; i++) {
            if (temp_coe[i] != 0) {
                new_coe[j] = temp_coe[i];
                new_exp[j] = i;
                j = j + 1;
            }
        }
        return new Polynomial(new_coe, new_exp);
    }

    public Polynomial multiply(Polynomial other) {
        //if they are empty
        if (this.exp.length == 0)
            return new Polynomial(other.coefficients, other.exp);
        if (other.exp.length == 0)
            return new Polynomial(this.coefficients, this.exp);

        //check if it is in ascending order
        boolean this_Flag = check(this.exp);
        boolean other_Flag = check(other.exp);
        //sort array by using selection sort
        if(this_Flag) {
            int n = this.exp.length;
            for (int i = 0; i < n - 1; i++) {
                int min_idx = i;
                for (int j = i + 1; j < n; j++)
                    if (this.exp[j] < this.exp[min_idx])
                        min_idx = j;

                int temp1 = this.exp[min_idx];
                this.exp[min_idx] = this.exp[i];
                this.exp[i] = temp1;
                double temp2 = this.coefficients[min_idx];
                this.coefficients[min_idx] = this.coefficients[i];
                this.coefficients[i] = temp2;
            }
        }
        if(other_Flag){
            int k = other.exp.length;
            for (int x = 0; x < k - 1; x++) {
                int min_idx_other = x;
                for (int y = x + 1; y < k; y++)
                    if (other.exp[y] < other.exp[min_idx_other])
                        min_idx_other = y;

                int temp3 = other.exp[min_idx_other];
                other.exp[min_idx_other] = other.exp[x];
                other.exp[x] = temp3;
                double temp4 = other.coefficients[min_idx_other];
                other.coefficients[min_idx_other] = other.coefficients[x];
                other.coefficients[x] = temp4;
            }
        }
        //find the largest exp in my array
        int max_this = this.exp[0], max_other = other.exp[0];
        for(int i = 1; i < this.exp.length; i++) {
            if(this.exp[i] > max_this)
                max_this = this.exp[i];
        }
        for(int j = 1; j < other.exp.length; j++) {
            if(other.exp[j] > max_other)
                max_other = other.exp[j];
        }
        double[] product = new double[max_this + max_other + 1];
        for(int i = 0; i < this.exp.length; i++) {
            for(int j = 0; j < other.exp.length; j++) {
                int index = this.exp[i] + other.exp[j];
                product[index] = product[index] + (this.coefficients[i] * other.coefficients[j]);
            }
        }
        //count how many none-zero element
        int count = 0;
        for (double v : product) {
            if (v == 0) {
                ;
            }
            else count++;
        }
        double[] coe = new double[count];
        int[] exp = new int[count];
        for(int i = 0, index = 0; i < product.length; i++) {
            if(product[i] != 0) {
                coe[index] = product[i];
                exp[index] = i;
                index++;
            }
        }
        return new Polynomial(coe,exp);
    }
    //--------------------------------------------
    public void saveToFile(String filename) throws IOException {
        FileWriter myWriter = new FileWriter(filename);
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < this.coefficients.length; i++){
            //if it is constant and first number
            if(exp[i] == 0 && i == 0){temp.append(coefficients[i]); continue;}
            //if it is constant and not the first number
            if(exp[i] == 0){
                if(coefficients[i] < 0) temp.append(coefficients[i]);
                else temp.append("+").append(coefficients[i]);
                continue;
            }
            //if it is negative and positive number and first number and its exp = 1
            if(coefficients[i] < 0 && i == 0 && exp[i] == 1){temp.append(coefficients[i]).append("x"); continue;}
            if(coefficients[i] > 0 && i == 0 && exp[i] == 1){temp.append(coefficients[i]).append("x"); continue;}
            //if it is negative and positive and first number but exp not 1
            if(coefficients[i] < 0 && i == 0){temp.append(coefficients[i]).append("x").append(exp[i]); continue;}
            if(coefficients[i] > 0 && i == 0){temp.append(coefficients[i]).append("x").append(exp[i]); continue;}
            //if it is not the first number but exp = 1
            if(coefficients[i] < 0 && exp[i] == 1){temp.append(coefficients[i]).append("x");continue;}
            if(coefficients[i] > 0 && exp[i] == 1){
                temp.append("+").append(coefficients[i]).append("x");
                continue;
            }
            if(coefficients[i] < 0) temp.append(coefficients[i]).append("x").append(exp[i]);
            else temp.append("+").append(coefficients[i]).append("x").append(exp[i]);
        }
        myWriter.write(temp.toString());
        myWriter.close();
    }

    public double evaluate(double x) {
        double sum = 0.0;
        for(int i = 0; i < coefficients.length; i++) {
            //get the coefficient
            double coe = coefficients[i];
            //get the exp
            int exponential = exp[i];
            //check if it is just a constant
            if(exp[i] == 0){sum += coe; continue;}
            sum = sum + coe * Math.pow(x, exponential);
        }
        return sum;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}