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
        System.out.println("Additional column");
        for(int i=0;i<additionalMatrix.length;i++){
            System.out.println(additionalMatrix[i]);
        }
        return additionalMatrix;
    }

    @Override
    protected void calcDet() {
        mainDet = DetCalculator.det(matrix);

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

        det1 = DetCalculator.det(matrix1);
        det2 = DetCalculator.det(matrix2);
        det3 = DetCalculator.det(matrix3);
    }

    @Override
    Answer calculate() {
        try {
            double a = det1/mainDet;
            a = (Math.rint(a*10000))/100;
            double b = det2/mainDet;
            b = (Math.rint(b*10000))/10000;
            double c = det3/mainDet;
            c = new BigDecimal(c).setScale(3,RoundingMode.UP).doubleValue();
            double moduleB = Math.abs(b);
            double moduleC = Math.abs(c);
            String function = "y = "+String.valueOf(a)+"*x^2"+
                    ((b>0)?(" + "+String.valueOf(b)):(" - "+moduleB)+"*x"+
                            ((c>0)?(" + "+String.valueOf(c)):(" - "+moduleC)));
            answer = new Answer(function,a,b);
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
}
