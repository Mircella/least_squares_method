package sample.Functions;

import javafx.collections.ObservableList;
import sample.Calculator.Answer;
import sample.Calculator.Data;
import sample.Calculator.DetCalculator;
import sample.Functions.FunctionDescriptions.Description;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Function {
    protected Answer answer;
    protected Data data;
    protected ObservableList<Data> dataList;

    protected double[][]matrix;
    protected double[][]matrix1,matrix2;
    protected double[] additionalMatrix;

    protected double element;
    protected double mainDet, det1, det2;
    protected double result;

    protected JLabel functionLabel;
    protected Description functionDescription;

    public ObservableList<Data> getDataList() {
        return dataList;
    }

    public JLabel getFunctionLabel() {
        return functionLabel;
    }

    public Function() {
        if(this.getClass()==LinearFunction.class){
            System.out.println("Linear function was created\nIts data:");
        }else if(this.getClass() == InverseRatioFunction.class){
            System.out.println("InverseRatioFunction was created\nIts data:");
        }else if(this.getClass()==LogarithmicFunction.class){
            System.out.println("Logarithmic function was created.\nIts data:");
        }else if(this.getClass()==ExponentialFunction.class){
            System.out.println("Exponential function was created\nIts data:");
        }else if(this.getClass()==QuadraticFunction.class){
            System.out.println("Quadratic function was created\nIts data:");
        }
        createData();
        matrix();
        additionalMatrix();
        calcDet();
        answer = calculate();
    }

    public Function(ObservableList<Data> dataList) {
        this.dataList = dataList;
        matrix();
        additionalMatrix();
        calcDet();
        answer = calculate();
    }

    public Answer getAnswer() {
        return answer;
    }

    abstract double[][]matrix();
    abstract double [] additionalMatrix();
    protected void createData(){
        data = new Data();
        dataList = data.getList();
        data.print();
    }

    protected void calcDet(){
        mainDet = DetCalculator.det(matrix);

        matrix1 = new double[2][2];
        matrix2 = new double[2][2];
        for(int i=0;i<matrix1.length;i++){
            for(int j=0;j<matrix1.length;j++){
                if(j==0){
                    matrix1[i][j]=additionalMatrix[i];
                }else{
                    matrix1[i][j]=matrix[i][j];
                }
            }
        }

        for(int i=0;i<matrix2.length;i++){
            for(int j=0;j<matrix2.length;j++){
                if(j==1){
                    matrix2[i][j]=additionalMatrix[i];
                }else{
                    matrix2[i][j]=matrix[i][j];
                }
            }
        }

        det1 = DetCalculator.det(matrix1);
        det2 = DetCalculator.det(matrix2);
    }
    abstract Answer calculate();
    public double getResult(double x){
        return 0;
    }
}
