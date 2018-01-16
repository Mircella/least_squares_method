package sample.Calculator;

import javafx.collections.ObservableList;
import sample.Functions.Function;
import sample.Functions.QuadraticFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Analyse {
    private Function function;
    private double mean=0;
    private double determination=0;
    private double approximation=0;
    private double correlation=0;
    private double deviation=0;
    private double fisher=0;
    private double studentA=0;
    private double studentB=0;
    private  double studentC=0;
    private double studentR=0;
    private double aMistake=0;
    private double bMistake=0;
    private double rMistake=0;
    private double cMistake=0;
    private double[] valuesFunc,valuesMean,valuesDeviation,xMean;
    private ObservableList<Data>dataList;

    public Analyse(Function function) {
        System.out.println("Analyse of "+function.getClass().getSimpleName());
        this.function = function;
        this.dataList = function.getDataList();
        this.valuesFunc = calcValuesFunc(dataList,function);
        this.mean = calcMean(dataList);
        this.xMean = calcXMean(dataList,mean);
        this.valuesDeviation = calcValuesDeviation(dataList,valuesFunc);
        this.valuesMean = calcValuesMean(dataList,mean);
        this.correlation = calcCorrelation(valuesDeviation,valuesMean);
        this.determination = calcDetermination(correlation);
        double[]values = new double[dataList.size()];
        for(int i=0;i<values.length;i++){
            values[i]=dataList.get(i).getY();
        }
        this.approximation = calcApproximation(valuesDeviation,values);
        this.deviation = calcDeviation(valuesDeviation);
        this.fisher = calcFisher(dataList,correlation);
        this.aMistake = calcAMistake(valuesDeviation,xMean);
        double[]x2 = new double[dataList.size()];
        for(int i=0;i<dataList.size();i++){
            x2[i]=new BigDecimal(Math.pow(dataList.get(i).getX(),2)).setScale(2,RoundingMode.CEILING).doubleValue();
        }
        this.bMistake = calcBMistake(valuesDeviation,xMean,x2);
        this.rMistake=calcRMistake(correlation,dataList.size());
        this.studentA=calcStudentA(function.getAnswer().getA(),aMistake);
        this.studentB=calcStudentB(function.getAnswer().getB(),bMistake);
        this.studentR=calcStudentR(correlation,rMistake);
    }

    private double calcCorrelation(double[]valuesDeviation,double[]valuesMean){
        double sum1=0;
        double sum2=0;
        for(double d:valuesDeviation){
            double summand = new BigDecimal(Math.pow(d,2)).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
            sum1+=summand;
        }
        for(double d:valuesMean){
            double summand = new BigDecimal(Math.pow(d,2)).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
            sum2+=summand;
        }
        double ratio = new BigDecimal(sum1/sum2).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
        correlation = new BigDecimal(Math.sqrt(1-ratio)).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
        return correlation;
    }
    private double calcDetermination(double correlation){
        determination = new BigDecimal(Math.pow(correlation,2)).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
        return determination;
    }
    private double calcApproximation(double[]valuesDeviation,double[]values){
        double sum=0;
        for(int i=0;i<values.length;i++){
            double ratio = new BigDecimal(Math.abs(valuesDeviation[i]/values[i])).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
            sum+=ratio; 
        }
        approximation = new BigDecimal(sum/values.length).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
        return approximation;
    }
    private double calcDeviation(double[]valuesDeviation){
        for(double d:valuesDeviation){
            double sqr = new BigDecimal(Math.pow(d,2)).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
            deviation+=sqr;
        }
        return new BigDecimal(deviation).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
    }
    private double[]calcValuesMean(ObservableList<Data>dataList,double mean){
        valuesMean = new double[dataList.size()];
        for(int i=0;i<valuesMean.length;i++){
            valuesMean[i]=new BigDecimal(dataList.get(i).getY()-mean).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
        }
        return valuesMean;
    }

    private double[]calcXMean(ObservableList<Data>dataList,double mean){
        xMean = new double[dataList.size()];
        for(int i=0;i<xMean.length;i++){
            xMean[i]= new BigDecimal(Math.pow((dataList.get(i).getX()-mean),2)).setScale(2,RoundingMode.CEILING).doubleValue();
        }
        return xMean;
    }
    private double[]calcValuesFunc(ObservableList<Data>dataList,Function function){
        valuesFunc = new double[dataList.size()];
        for(int i=0;i<valuesFunc.length;i++){
            valuesFunc[i]=new BigDecimal(function.getResult(dataList.get(i).getX())).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
        }
        return valuesFunc;
    }
    private double[]calcValuesDeviation(ObservableList<Data>dataList,double[]valuesFunc){
        valuesDeviation = new double[dataList.size()];
        for(int i=0;i<valuesDeviation.length;i++){
            valuesDeviation[i]=new BigDecimal(dataList.get(i).getY()- valuesFunc[i]).setScale(2,BigDecimal.ROUND_CEILING).doubleValue();
        }
        return valuesDeviation;
    }
    private double calcMean(ObservableList<Data>dataList){
        for(Data d:dataList){
            mean+=d.getY();
        }
        mean=new BigDecimal(mean/dataList.size()).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        return mean;
    }

    private double calcFisher(ObservableList<Data>dataList, double correlation){
        double d = new BigDecimal(Math.pow(correlation,2)).setScale(2,RoundingMode.CEILING).doubleValue();
        fisher = new BigDecimal((d/(1-correlation))*(dataList.size()-2)).setScale(2,RoundingMode.CEILING).doubleValue();
        return  fisher;
    }

    private double calcStudentA(double a,double aMistake){
        studentA =new BigDecimal(a/aMistake).setScale(2,RoundingMode.CEILING).doubleValue();
        return studentA;
    }
    private double calcStudentB(double b,double bMistake){
        studentB =new BigDecimal(b/bMistake).setScale(2,RoundingMode.CEILING).doubleValue();
        return studentB;
    }
    private double calcStudentC(double c,double cMistake){
        studentC =new BigDecimal(c/cMistake).setScale(2,RoundingMode.CEILING).doubleValue();
        return studentC;
    }
    private double calcStudentR(double correlation,double rMistake){
        studentR =new BigDecimal(correlation/rMistake).setScale(2,RoundingMode.CEILING).doubleValue();
        return studentR;
    }

    private double calcAMistake(double[]valuesDeviation,double[]xMean){
        double sum1 = 0;
        for(int i=0;i<valuesDeviation.length;i++){
            sum1+=Math.pow(valuesDeviation[i],2);
        }
        double sum2 = 0;
        for(int i=0;i<xMean.length;i++){
            sum2+=xMean[i];
        }
        double root = (sum1/sum2)/(valuesDeviation.length-2);
        aMistake = new BigDecimal(Math.sqrt(root)).setScale(2,RoundingMode.CEILING).doubleValue();
        return aMistake;
    }

    private double calcBMistake(double[]valuesDeviation,double[]xMean,double[]x2){
        double sum1 = 0;
        for(int i=0;i<valuesDeviation.length;i++){
            sum1+=Math.pow(valuesDeviation[i],2);
        }
        double sum2 = 0;
        for(int i=0;i<xMean.length;i++){
            sum2+=xMean[i];
        }
        double sum3=0;
        for(int i=0;i<x2.length;i++){
            sum3+=x2[i];
        }
        double root = (sum1*sum3/sum2)/(valuesDeviation.length*(valuesDeviation.length-2));
        bMistake = new BigDecimal(Math.sqrt(root)).setScale(2,RoundingMode.CEILING).doubleValue();
        return bMistake;
    }

    private double calcRMistake(double correlation, int size){
        double root = (1-Math.pow(correlation,2))/(size-2);
        bMistake = new BigDecimal(Math.sqrt(root)).setScale(2,RoundingMode.CEILING).doubleValue();
        return bMistake;
    }

    public void setStudentA(double studentA) {
        this.studentA = studentA;
    }

    public double getStudentB() {
        return studentB;
    }

    public void setStudentB(double studentB) {
        this.studentB = studentB;
    }

    public double getStudentC() {
        return studentC;
    }

    public void setStudentC(double studentC) {
        this.studentC = studentC;
    }

    public double getStudentR() {
        return studentR;
    }

    public void setStudentR(double studentR) {
        this.studentR = studentR;
    }

    public double getcMistake() {
        return cMistake;
    }

    public void setcMistake(double cMistake) {
        this.cMistake = cMistake;
    }

    public double getDetermination() {
        return determination;
    }

    public void setDetermination(double determination) {
        this.determination = determination;
    }

    public double getCorrelation() {
        return correlation;
    }

    public void setCorrelation(double correlation) {
        this.correlation = correlation;
    }

    public double[] getValuesFunc() {
        return valuesFunc;
    }

    public void setValuesFunc(double[] valuesFunc) {
        this.valuesFunc = valuesFunc;
    }

    public double[] getValuesMean() {
        return valuesMean;
    }

    public void setValuesMean(double[] valuesMean) {
        this.valuesMean = valuesMean;
    }

    public double[] getValuesDeviation() {
        return valuesDeviation;
    }

    public void setValuesDeviation(double[] valuesDeviation) {
        this.valuesDeviation = valuesDeviation;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public ObservableList<Data> getDataList() {
        return dataList;
    }

    public void setDataList(ObservableList<Data> dataList) {
        this.dataList = dataList;
    }

    public double getApproximation() {
        return approximation;
    }

    public void setApproximation(double approximation) {
        this.approximation = approximation;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public double getDeviation() {
        return deviation;
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }

    public double getFisher() {
        return fisher;
    }

    public void setFisher(double fisher) {
        this.fisher = fisher;
    }

    public double getStudentA() {
        return studentA;
    }

    public void setStudent(double studentA) {
        this.studentA = studentA;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("function values from function:\n");
        for(double d:valuesFunc){
            sb.append(d+" ");
        }
        sb.append("\nFunction values deviation\n");
        for(double d:valuesDeviation){
            sb.append(d+" ");
        }
        sb.append("\nFunction mean values\n");
        for(double d:valuesMean){
            sb.append(d+" ");
        }
        return "Analyse{" +
                "mean=" + mean +"\n"+
                "determination=" + determination +"\n"+
                "approximation=" + approximation +"\n"+
                "deviation=" + deviation +"\n"+
                "correlation=" + correlation + "\n"+
                sb.toString()+
                '}';
    }

    public double getaMistake() {
        return aMistake;
    }

    public void setaMistake(double aMistake) {
        this.aMistake = aMistake;
    }

    public double getbMistake() {
        return bMistake;
    }

    public void setbMistake(double bMistake) {
        this.bMistake = bMistake;
    }

    public double getrMistake() {
        return rMistake;
    }

    public void setrMistake(double rMistake) {
        this.rMistake = rMistake;
    }
}
