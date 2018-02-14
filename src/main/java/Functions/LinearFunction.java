package Functions;

import javafx.collections.ObservableList;
import Calculator.Answer;
import Calculator.Data;

import javax.swing.*;
import java.math.BigDecimal;

public class LinearFunction extends Function{

    public LinearFunction() {
        super();
    }

    public LinearFunction(ObservableList<Data> dataList) {
        super(dataList);
        System.out.println("Result of linear function is "+answer.getFunction());
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
        element = new BigDecimal(element).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
        matrix[0][0]=element;
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=dataList.get(i).getX();
        }
        element = new BigDecimal(element).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
        matrix[0][1]=element;
        matrix[1][0]=element;
        matrix[1][1]=dataList.size();
        }
        return matrix;
    }

    @Override
    protected double[] additionalMatrix() {
        additionalMatrix = new double[2];
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=dataList.get(i).getX()*dataList.get(i).getY();
        }
        element = new BigDecimal(element).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
        additionalMatrix[0]=element;
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=dataList.get(i).getY();
        }
        element = new BigDecimal(element).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
        additionalMatrix[1]=element;
        return additionalMatrix;
    }

    @Override
    protected void calcDet() {
        super.calcDet();
    }


    protected Answer calculate() {
        try {
            double a = det1/mainDet;
            a = new BigDecimal(a).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            double b = det2/mainDet;
            b = new BigDecimal(b).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            String function = "y = "+String.valueOf(a)+"*x "+(b>0?"+ ":"- ")+String.valueOf(Math.abs(b));
            answer = new Answer(function,a,b);
        }catch (Exception e){
            System.out.println("Main determinate is 0");
            }
        return answer;
    }

    @Override
    public JLabel getFunctionLabel() {
        return functionLabel;
    }

    @Override
    public double getResult(double x) {
        try {
            result = answer.getA()*x+answer.getB();
        }catch (NullPointerException e){
            result = 0.0;
        }
        return result;
    }

    public String getFormula(){
        String result = "y="+answer.getA()+"\\cdot x\\ +\\ "+answer.getB()+"\\\\";
        return result;
    }
}
