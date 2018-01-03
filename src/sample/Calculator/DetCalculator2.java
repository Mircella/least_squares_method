

package sample.Calculator;

import java.util.ArrayList;

public class DetCalculator2 {
    private static String line;

    public DetCalculator2() {
    }

    public static double det(double[][]matrix,ArrayList<String>solutionList){

        double result = 0;
        if(matrix.length==2){
            result=matrix[0][0]*matrix[1][1]- matrix[0][1]*matrix[1][0];
            line = "Determinant is equal to: "+matrix[0][0]+" * "+matrix[1][1]+" - "+matrix[0][1]+" * "+matrix[1][0];
            solutionList.add(line);
            line = "Result is "+result;
            solutionList.add(line);

        }
        else{
            int k=-1;
            for(int i=0;i<matrix.length;i++){
                result+=Math.pow(k,i)*matrix[0][i]*det(minor(matrix,0,i,solutionList),solutionList);
            }
        }

        return result;

    }
    private static double[][] minor(double[][]matrix,int row,int column,ArrayList<String>solutionList){
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

        createMinor(minor,element,solutionList);
        return minor;
    }

    public static void createMinor(double[][]minor,double element,ArrayList<String>solutionList) {
        line ="Minor and element:";
        solutionList.add(line);
        line="Element: "+element;
        solutionList.add(line);
        line="Minor:";

        solutionList.add(line);
        for(int i=0;i<minor.length;i++){
            StringBuilder sb = new StringBuilder();
            for(int k=0;k<minor.length;k++){
                sb.append(minor[i][k]+" ");
            }
            solutionList.add(sb.toString());
        }
    }

}

