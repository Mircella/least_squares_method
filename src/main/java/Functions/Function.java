package Functions;

import javafx.collections.ObservableList;
import Calculator.Answer;
import Calculator.Data;
import Functions.FunctionDescriptions.Description;
import Calculator.DetCalculator;

import javax.swing.*;


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
        if(this.getClass()==LinearFunction.class||
                this.getClass()==InverseRatioFunction.class||
                this.getClass()==LogarithmicFunction.class||
                this.getClass()==ExponentialFunction.class||
                this.getClass()==QuadraticFunction.class){
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

    }

    public Function(ObservableList<Data> dataList) {
        try {
            this.dataList = dataList;
            matrix();
            additionalMatrix();
            calcDet();
            answer = calculate();
        }catch (Exception e){
            System.out.println("Error: "+e.getClass().getSimpleName());
        }

    }

    public Answer getAnswer() {
        return answer;
    }

    abstract double[][]matrix();
    abstract double [] additionalMatrix();
    protected void createData(){
        data = new Data();
        dataList = data.getList();
        //data.print();
    }

    protected void calcDet(){
        mainDet = DetCalculator.det(matrix,null);

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

        det1 = DetCalculator.det(matrix1,null);
        det2 = DetCalculator.det(matrix2,null);
    }
    abstract Answer calculate();
    public double getResult(double x){
        return 0;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix1() {
        return matrix1;
    }

    public void setMatrix1(double[][] matrix1) {
        this.matrix1 = matrix1;
    }

    public double[][] getMatrix2() {
        return matrix2;
    }

    public void setMatrix2(double[][] matrix2) {
        this.matrix2 = matrix2;
    }

    public double[] getAdditionalMatrix() {
        return additionalMatrix;
    }

    public void setAdditionalMatrix(double[] additionalMatrix) {
        this.additionalMatrix = additionalMatrix;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setDataList(ObservableList<Data> dataList) {
        this.dataList = dataList;
    }

    public double getElement() {
        return element;
    }

    public void setElement(double element) {
        this.element = element;
    }

    public double getMainDet() {
        return mainDet;
    }

    public void setMainDet(double mainDet) {
        this.mainDet = mainDet;
    }

    public double getDet1() {
        return det1;
    }

    public void setDet1(double det1) {
        this.det1 = det1;
    }

    public double getDet2() {
        return det2;
    }

    public void setDet2(double det2) {
        this.det2 = det2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setFunctionLabel(JLabel functionLabel) {
        this.functionLabel = functionLabel;
    }

    public Description getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(Description functionDescription) {
        this.functionDescription = functionDescription;
    }

    public abstract String getFormula();
}

