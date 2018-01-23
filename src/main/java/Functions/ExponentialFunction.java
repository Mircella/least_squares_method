package Functions;

import com.sun.corba.se.impl.encoding.MarshalInputStream;
import javafx.collections.ObservableList;
import Calculator.Answer;
import Calculator.Data;

import java.math.BigDecimal;

public class ExponentialFunction extends Function {

    public ExponentialFunction() {
        super();
        //System.out.println("Result of exponential function is "+answer.getFunction());
    }

    public ExponentialFunction(ObservableList<Data> dataList) {
        super(dataList);
        System.out.println("Result of exponential function is "+answer.getFunction());
    }

    @Override
    protected double[][] matrix() {
        matrix = new double[2][2];
        element=0;
        if(dataList!=null){
            for(int i=0;i<dataList.size();i++){
                element+=Math.pow(dataList.get(i).getX(),2);
            }
            element = new BigDecimal(element).setScale(3, BigDecimal.ROUND_CEILING).doubleValue();
            matrix[0][0]=element;
            element=0;
            for(int i=0;i<dataList.size();i++){
                element+=dataList.get(i).getX();
            }
            element = new BigDecimal(element).setScale(3, BigDecimal.ROUND_CEILING).doubleValue();
            matrix[0][1]=element;
            matrix[1][0]=element;
            matrix[1][1]=dataList.size();
        }
        /*System.out.println("Constructed matrix");
        for(int i=0;i<matrix.length;i++)
        {
            for (int j=0;j<matrix.length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        } */       return matrix;
    }

    @Override
    protected double[] additionalMatrix() {
        additionalMatrix = new double[2];
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=dataList.get(i).getX()*Math.log(dataList.get(i).getY());
        }
        element = new BigDecimal(element).setScale(3, BigDecimal.ROUND_CEILING).doubleValue();
        additionalMatrix[0]=element;
        element=0;
        for(int i=0;i<dataList.size();i++){
            element+=Math.log(dataList.get(i).getY());
        }
        element = new BigDecimal(element).setScale(3,BigDecimal.ROUND_CEILING).doubleValue();
        additionalMatrix[1]=element;
        /*System.out.println("Additional column");
        for(int i=0;i<additionalMatrix.length;i++){
            System.out.println(additionalMatrix[i]);
        }*/
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
            a = new BigDecimal(a).setScale(3,BigDecimal.ROUND_CEILING).doubleValue();
            double B = det2/mainDet;
            B = new BigDecimal(B).setScale(3,BigDecimal.ROUND_CEILING).doubleValue();
            String function = "y = exp("+String.valueOf(a)+"*x"+((B>0)?("+"+B):("-"+Math.abs(B)))+")";
            answer = new Answer(function,a,B);
        }catch (ArithmeticException e){

        }catch (Exception e){}
        return answer;
    }

    @Override
    public double getResult(double x) {
        double d = new BigDecimal(Math.exp(answer.getA()*x+answer.getB())).setScale(3,BigDecimal.ROUND_CEILING).doubleValue();
        result = d;
        return result;
    }

    public String getFormula(){
        String result = "y=e^{"+answer.getA()+"\\cdot x\\ +\\ "+answer.getB()+"}\\\\";
        return result;
    }
}
