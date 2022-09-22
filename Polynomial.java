public class Polynomial{
    double[] coefficients;

    public Polynomial(){
        this.coefficients = new double[0];
    }

    public Polynomial(double[] coefficients){
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial other){
        int len_this = this.coefficients.length;
        int len_other = other.coefficients.length;
        int max_len = (len_this > len_other) ? len_this : len_other;

        double[] array = new double[max_len];

        for(int i = 0; i < this.coefficients.length; i++)
        {
            array[i] = array[i] + this.coefficients[i];
        }

        for(int j = 0; j < other.coefficients.length; j++)
        {
            array[j] = array[j] + other.coefficients[j];
        }

        return new Polynomial(array);
    }

    public double evaluate(double x){
        double sum = 0;
        for(int i = 0; i < coefficients.length; i++){
            double j = coefficients[i];
            sum = sum + j * Math.pow(x, i);
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }





}