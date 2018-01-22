package main.java.Functions;

import javafx.collections.ObservableList;
import main.java.Calculator.Answer;
import main.java.Calculator.Data;
import main.java.Calculator.DetCalculator;

import java.math.BigDecimal;


public class Polynom extends Function {
    private int power;
    private double[]resultMatrix,resultMatrixCopy;
    private double[][]inverseMatrix;
    private double inverseFactor;

    public Polynom(int power) {
        super();
        this.power = power;
        //System.out.println("Result of polynom is "+answer.getFunction());
    }

    public Polynom(ObservableList<Data> dataList, int power) {
        this.dataList = dataList;
        this.power=power;
        try {
            matrix();
            additionalMatrix();
            calcDet();
            answer = calculate();
        }catch (Exception e){
            System.out.println("Error: "+e.getClass().getSimpleName());
        }
        System.out.println("Result of polynom is "+answer.getFunction());
    }

    @Override
    double[][] matrix() {
        matrix = new double[power+1][power+1];
        for(int i=0;i<power+1;i++){
            for (int j=0;j<power+1;j++){
                double element=0;
                for(Data d:dataList){
                    element+=Math.pow(d.getX(),(i+j));
                }
                matrix[i][j]=element;
            }
        }
        return matrix;
    }

    @Override
    double[] additionalMatrix() {
        additionalMatrix = new double[power+1];
        for(int i=0;i<power+1;i++){
            double element=0;
            for(Data d:dataList){
                element+=Math.pow(d.getX(),i)*d.getY();
            }
            additionalMatrix[i]=element;
        }
        return additionalMatrix;
    }

    @Override
    Answer calculate() {
        try {
            resultMatrixCopy = new double[resultMatrix.length];
            for(int i=0;i<resultMatrixCopy.length;i++){
                resultMatrixCopy[i]=new BigDecimal(resultMatrix[i]/inverseFactor).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
            }
            StringBuilder sb = new StringBuilder("y = ");
            for (int i=resultMatrixCopy.length-1;i>=0;i--){
                sb.append("("+resultMatrixCopy[i]+")*x^"+(i));
                if(i!=0){
                    sb.append(" + ");
                }
            }

            answer = new Answer(sb.toString(),resultMatrixCopy);

        }catch (Exception e){
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public void calcDet() {
        resultMatrix=new double[power+1];
        inverseMatrix = DetCalculator.inverseMatrix(matrix,null);
        inverseFactor = DetCalculator.getInverseFactor();
        resultMatrix = DetCalculator.multuply(inverseMatrix,additionalMatrix);
    }

    @Override
    public double getResult(double x) {
        result=0;
        for(int i=0;i<resultMatrixCopy.length;i++){
            result+=new BigDecimal(resultMatrixCopy[i]*Math.pow(x,i)).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
        }
        return result;
    }

    public double[] getResultMatrix() {
        if(resultMatrix==null){
            calcDet();
        }
        return resultMatrix;
    }

    public void setResultMatrix(double[] resultMatrix) {
        this.resultMatrix = resultMatrix;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getInverseFactor() {
        return inverseFactor;
    }

    public void setInverseFactor(double inverseFactor) {
        this.inverseFactor = inverseFactor;
    }

    public double[] getResultMatrixCopy() {
        return resultMatrixCopy;
    }

    public void setResultMatrixCopy(double[] resultMatrixCopy) {
        this.resultMatrixCopy = resultMatrixCopy;
    }

    public double[][] getInverseMatrix() {
        return inverseMatrix;
    }

    public void setInverseMatrix(double[][] inverseMatrix) {
        this.inverseMatrix = inverseMatrix;
    }

    @Override
    public String getFormula() {
        return "Formula of polynom";
    }
}
