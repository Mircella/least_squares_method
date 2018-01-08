package sample.Functions;

import javafx.collections.ObservableList;
import sample.Calculator.Answer;
import sample.Calculator.Data;
import sample.Calculator.DetCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class QuadraticFunction extends Function {
    private double det3;
    private double[][]matrix3;

    public QuadraticFunction() {
        super();
        System.out.println("Result of quadratic function is "+answer.getFunction());
    }

    public QuadraticFunction(ObservableList<Data> dataList) {
        super(dataList);
        System.out.println("Result of quadratic function is "+answer.getFunction());
    }

    @Override
    double[][] matrix() {
        matrix = new double[3][3];
        element=0;
        if(dataList!=null){
            for(int i=0;i<dataList.size();i++){
                element+=Math.pow(dataList.get(i).getX(),4);
            }
            element = new BigDecimal(element).setScale(3, RoundingMode.UP).doubleValue();
            matrix[0][0]=element;
            element=0;
            for(int i=0;i<dataList.size();i++){
                element+=Math.pow(dataList.get(i).getX(),3);
            }
            element = new BigDecimal(element).setScale(3, RoundingMode.UP).doubleValue();
            matrix[0][1]=element;
            matrix[1][0]=element;
            element=0;
            for(int i=0;i<dataList.size();i++){
                element+=Math.pow(dataList.get(i).getX(),2);
            }
            element = new BigDecimal(element).setScale(3, RoundingMode.UP).doubleValue();
            matrix[0][2]=element;
            matrix[1][1]=element;
            matrix[2][0]=element;
            element=0;
            for(int i=0;i<dataList.size();i++){
                element+=dataList.get(i).getX();
            }
            element = new BigDecimal(element).setScale(3, RoundingMode.UP).doubleValue();
            matrix[1][2]=element;
            matrix[2][1]=element;
            matrix[2][2]=dataList.size();
        }
        return matrix;
    }

    @Override
    double[] additionalMatrix() {
        additionalMatrix = new double[3];
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=Math.pow(dataList.get(i).getX(),2)*dataList.get(i).getY();
        }
        element = new BigDecimal(element).setScale(3, RoundingMode.UP).doubleValue();
        additionalMatrix[0]=element;
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=dataList.get(i).getX()*dataList.get(i).getY();
        }
        element = new BigDecimal(element).setScale(3, RoundingMode.UP).doubleValue();
        additionalMatrix[1]=element;
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=dataList.get(i).getY();
        }
        element = new BigDecimal(element).setScale(3,RoundingMode.UP).doubleValue();
        additionalMatrix[2]=element;

        return additionalMatrix;
    }

    @Override
    protected void calcDet() {
        mainDet = DetCalculator.det(matrix,null);

        matrix1 = new double[3][3];
        matrix2 = new double[3][3];
        matrix3 = new double[3][3];
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

        for(int i=0;i<matrix3.length;i++){
            for(int j=0;j<matrix3.length;j++){
                if(j==2){
                    matrix3[i][j]=additionalMatrix[i];
                }else{
                    matrix3[i][j]=matrix[i][j];
                }
            }
        }

        det1 = DetCalculator.det(matrix1,null);
        det2 = DetCalculator.det(matrix2,null);
        det3 = DetCalculator.det(matrix3,null);
    }

    @Override
    Answer calculate() {
        try {
            double a = det1/mainDet;
            a = new BigDecimal(a).setScale(3,RoundingMode.UP).doubleValue();
            double b = det2/mainDet;
            b = new BigDecimal(b).setScale(3,RoundingMode.UP).doubleValue();
            double c = det3/mainDet;
            c = new BigDecimal(c).setScale(3,RoundingMode.UP).doubleValue();
            double moduleB = Math.abs(b);
            double moduleC = Math.abs(c);
            String s1 = "y = "+String.valueOf(a)+"*x^2";
            String s2 = ""+((b>0)?(" + "+moduleB):(" - "+moduleB))+"*x";
            String s3 = ""+((c>0)?(" + "+moduleC):(" - "+moduleC));
            String function = s1+s2+s3;
            answer = new Answer(function,a,b,c);
        }catch (ArithmeticException e){
            System.out.println("Main determinate is 0");
        }catch (Exception e){e.printStackTrace();}
        return answer;
    }

    @Override
    public double getResult(double x) {
        double d1 = new BigDecimal(answer.getA()*Math.pow(x,2)).setScale(3,RoundingMode.UP).doubleValue();
        double d2 = new BigDecimal(answer.getB()*x).setScale(3,RoundingMode.UP).doubleValue();
        result = d1+d2+answer.getC();
        return result;
    }

    public double getDet3() {
        return det3;
    }

    public void setDet3(double det3) {
        this.det3 = det3;
    }

    public double[][] getMatrix3() {
        return matrix3;
    }

    public void setMatrix3(double[][] matrix3) {
        this.matrix3 = matrix3;
    }
}
