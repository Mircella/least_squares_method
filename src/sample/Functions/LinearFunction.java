package sample.Functions;

import javafx.collections.ObservableList;
import sample.Calculator.Answer;
import sample.Calculator.Data;
import sample.Functions.FunctionDescriptions.LinearFunctionDescription;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class LinearFunction extends Function{

    public LinearFunction() {
        super();
        System.out.println("Result of linear function is "+answer.getFunction());
    }

    public LinearFunction(ObservableList<Data> dataList) {
        super(dataList);
    }

    @Override
    protected void createData() {
        super.createData();
    }

    @Override
    protected double[][] matrix() {
        matrix = new double[2][2];
        element=0;
        if(dataList!=null){
        for(int i=0;i<dataList.size();i++){
            element+=Math.pow(dataList.get(i).getX(),2);
        }
        element = new BigDecimal(element).setScale(3, RoundingMode.UP).doubleValue();
        matrix[0][0]=element;
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=dataList.get(i).getX();
        }
        element = new BigDecimal(element).setScale(3, RoundingMode.UP).doubleValue();
        matrix[0][1]=element;
        matrix[1][0]=element;
        matrix[1][1]=dataList.size();
        }
        System.out.println("Constructed matrix");
        for(int i=0;i<matrix.length;i++)
        {
            for (int j=0;j<matrix.length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }        return matrix;
    }

    @Override
    protected double[] additionalMatrix() {
        additionalMatrix = new double[2];
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=dataList.get(i).getX()*dataList.get(i).getY();
        }
        element = new BigDecimal(element).setScale(3, RoundingMode.UP).doubleValue();
        additionalMatrix[0]=element;
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=dataList.get(i).getY();
        }
        element = new BigDecimal(element).setScale(3,RoundingMode.UP).doubleValue();
        additionalMatrix[1]=element;
        System.out.println("Additional column");
        for(int i=0;i<additionalMatrix.length;i++){
            System.out.println(additionalMatrix[i]);
        }
        return additionalMatrix;
    }

    @Override
    protected void calcDet() {
        super.calcDet();
    }


    protected Answer calculate() {
        try {
            double a = det1/mainDet;
            a = (Math.rint(a*10000))/10000;
            double b = det2/mainDet;
            b = (Math.rint(b*10000))/10000;
            String function = "y = "+String.valueOf(a)+"*x "+(b>0?"+ ":"- ")+String.valueOf(Math.abs(b));
            answer = new Answer(function,a,b);
        }catch (ArithmeticException e){
            System.out.println("Main determinate is 0");
        }catch (Exception e){e.printStackTrace();}
        return answer;
    }

    @Override
    public JLabel getFunctionLabel() {
        functionLabel = LinearFunctionDescription.getInstance().createLabel();
        return functionLabel;
    }

    @Override
    public double getResult(double x) {
        result = answer.getA()*x+answer.getB();
        return result;
    }
}
