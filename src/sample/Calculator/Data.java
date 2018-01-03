package sample.Calculator;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import org.omg.CORBA.DATA_CONVERSION;

import java.util.ArrayList;

public class Data {
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private ObservableList<Data>list;

    private DataGenerator dataGenerator;
    public Data() {
        dataGenerator = new DataGenerator();
        list = dataGenerator.generate();
    }

    public Data(double x, double y) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
    }

    public double getX() {
        return xProperty.get();
    }

    public void setX(double x) {
        this.xProperty.set(x);
    }


    public DoubleProperty xPropertyProperty() {
        return xProperty;
    }

    public DoubleProperty yPropertyProperty() {
        return yProperty;
    }

    public double getY() {
        return this.yProperty.get();
    }

    public void setY(double y) {
        this.yProperty.set(y);
    }

    public ObservableList<Data> getList() {
        return list;
    }

    private static class DataGenerator extends Generator{
        public DataGenerator() {
        }

        @Override
        protected ObservableList<Data> generate() {
            return super.generate();
        }
    }

    public void print(){
        if(list!=null){
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i).getX()+" "+list.get(i).getY());
            }
        }else{
            System.out.println("list is empty");
        }
    }
}
