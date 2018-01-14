package sample.Calculator;

import java.util.ArrayList;

public class DetCalculator {

    private static double inverseFactor;

    public DetCalculator() {
    }

    public static double getInverseFactor() {
        return inverseFactor;
    }

    public static void setInverseFactor(double inverseFactor) {
        DetCalculator.inverseFactor = inverseFactor;
    }

    public static double det(double[][]matrix, ArrayList<Minor>solutionList){
        double result = 0;
        if(matrix.length==2){
            result=matrix[0][0]*matrix[1][1]- matrix[0][1]*matrix[1][0];

        }
        else{
            int k=-1;
            for(int i=0;i<matrix.length;i++){
                result+=Math.pow(k,i)*matrix[0][i]*det(minor(matrix,0,i,solutionList),solutionList);
            }
        }
        return result;
    }
    public static double[][] minor(double[][]matrix,int row,int column,ArrayList<Minor>solutionList){
        double element = matrix[row][column];
        int minorSize = matrix.length - 1;
        int matrixSize = matrix.length;
        ArrayList<Double> list = new ArrayList<>();
        double[][] minor = new double[minorSize][minorSize];
        for (int i = 0; i < matrixSize; i++) {
            if (i == row) {
                continue;
            }
            for (int k = 0; k < matrixSize; k++) {
                if (k == column) {
                    continue;
                }
                list.add(matrix[i][k]);
            }
        }
        for(int i=0;i<minorSize;i++){
            for(int k=0;k<minorSize;k++){
                minor[i][k]=list.get(minorSize*i+k);
            }
        }
        if(solutionList!=null){
        createMinor(minor,element,solutionList);
        }
        return minor;
    }

    public static double[][]inverseMatrix(double[][]matrix,ArrayList<Minor>solutionList){
        double[][]inverseMatrix=new double[matrix.length][matrix.length];
        inverseFactor = det(matrix,solutionList);
        double element=0;
        double[][]minor;

        if(inverseFactor!=0){
        for(int i=0;i<inverseMatrix.length;i++){
            for(int j=0;j<inverseMatrix.length;j++){
                minor = minor(matrix,i,j,solutionList);
                element = det(minor,solutionList);
                if((i+j)%2==0){
                    inverseMatrix[i][j]=element;
                }else {
                    inverseMatrix[i][j]=0-element;
                }
            }
        }
        inverseMatrix = trans(inverseMatrix);
        }else return null;

        return inverseMatrix;
    }

    public static double[][]multuply(double[][]matrix1,double[][]matrix2){
        int m = matrix1.length;
        int m2=matrix1[0].length;
        int n = matrix2[0].length;
        int n2 = matrix2.length;
        double[][] result;
        if(m2==n2){
            result = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n2; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        }
        else return null;
        return result;
    }

    public static double[]multuply(double[][]matrix1,double[]matrix2){
        int m = matrix1.length;
        int n = matrix2.length;

        double[] result;
        if(m==n){
            result = new double[m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    //for (int k = 0; k < o; k++) {
                        result[i] += matrix1[i][j] * matrix2[j];
                    //}
                }
            }
        }
        else return null;
        return result;
    }

    public static double[][] trans (double m[][])
    {
        double transMatrix[][] = new double [m.length][m.length];
        for (int i=0; i<m.length; i++)
        {
            for (int j=0; j<m[i].length; j++)
            {
                transMatrix[j][i]=m[i][j];
            }
        }
        return transMatrix;
    }

    public static void createMinor(double[][]minor,double element,ArrayList<Minor>solutionList) {
        solutionList.add(new Minor(minor,element));

    }

    public static class Minor{

        private double[][]minor;
        private double element;
        public Minor() {
        }

        public Minor(double[][] minor, double element) {
            this.minor = minor;
            this.element = element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<minor.length;i++){
                for(int j=0;j<minor.length;j++){
                    sb.append(minor[i][j]+" ");
                }
                sb.append("\n");
            }
            return "Minor{\n" +
                    "minor=\n" + sb.toString() +
                    "element=" + element +
                    "\n}";
        }

        public double[][] getMinor() {
            return minor;
        }

        public void setMinor(double[][] minor) {
            this.minor = minor;
        }

        public double getElement() {
            return element;
        }

        public void setElement(double element) {
            this.element = element;
        }
    }

}
