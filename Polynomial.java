public class Polynomial{
    double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[0];
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial other) {
        double [] array;

        int len_this = this.coefficients.length;
        int len_other = other.coefficients.length;

        array = new double[len_this > len_other ? len_this : len_other];

        for(int i = 0; i < this.coefficients.length; i++) {
            array[i] = array[i] + this.coefficients[i];
        }

        for(int j = 0; j < other.coefficients.length; j++) {
            array[j] = array[j] + other.coefficients[j];
        }

        return new Polynomial(array);
    }

    public double evaluate(double x) {
        double sum = 0.0;
        for(int i = 0; i < coefficients.length; i++) {
            double j = coefficients[i];
            sum = sum + j * Math.pow(x, i);
        }
        return sum;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }





}