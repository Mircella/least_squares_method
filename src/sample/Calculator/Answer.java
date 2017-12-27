package sample.Calculator;

public class Answer {
    private String function;
    private double a,b,c;
    public Answer() {
    }

    public Answer(String function, double a, double b) {
        this.function = function;
        this.a = a;
        this.b = b;
    }

    public Answer(String function, double a, double b, double c) {
        this.function = function;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
}
