package Functions;

import javafx.collections.ObservableList;
import Calculator.Answer;
import Calculator.Data;

import java.math.BigDecimal;

public class LogarithmicFunction extends Function {

    public LogarithmicFunction() {
        super();
    }

    public LogarithmicFunction(ObservableList<Data> dataList) {
        super(dataList);
        System.out.println("Result of Logarithmic function is "+answer.getFunction());
    }

    @Override
    protected double[][] matrix() {
        matrix = new double[2][2];
        element=0;
        if(dataList!=null){
            for(int i=0;i<dataList.size();i++){
                element+=Math.pow(Math.log(dataList.get(i).getX()),2);
            }
            element = new BigDecimal(element).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
            matrix[0][0]=element;
            element=0;
            for(int i=0;i<dataList.size();i++){
                element+=Math.log(dataList.get(i).getX());
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
            element+=dataList.get(i).getY()*Math.log(dataList.get(i).getX());
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
    protected void createData() {
        super.createData();
    }

    @Override
    protected void calcDet() {
        super.calcDet();
    }

    @Override
    protected Answer calculate() {
        try {
            double a = det1/mainDet;
            a = new BigDecimal(a).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            double b = det2/mainDet;
            b = new BigDecimal(b).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            String function = "y = "+String.valueOf(a)+"*ln(x) " + (b>0?"+ ":"- ")+String.valueOf(Math.abs(b));
            answer = new Answer(function,a,b);
        }catch (ArithmeticException e){
            System.out.println("Main determinate is 0");
        }catch (Exception e){e.printStackTrace();}
        return answer;
    }

    @Override
    public double getResult(double x) {
        double d = new BigDecimal(Math.log(x)).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
        result=answer.getA()*d+answer.getB();
        return result;
    }

    public String getFormula(){
        String result = "y="+answer.getA()+"\\cdot \\ln x\\ +\\ "+answer.getB()+"\\\\";
        return result;
    }
}
