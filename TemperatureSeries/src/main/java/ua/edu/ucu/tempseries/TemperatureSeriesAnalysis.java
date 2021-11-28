package ua.edu.ucu.tempseries;


import java.lang.Math;
import java.util.Arrays;
import java.util.InputMismatchException;
public class TemperatureSeriesAnalysis {
    protected double[] tempSeries;
    private final double min_possible = -273.0;
    private int length;


    public TemperatureSeriesAnalysis() {
        this.tempSeries = new double[0];
        this.length = 0;
    }

    public boolean isEmpty() {
        return this.length <= 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if (temperatureSeries.length > 0 && min_value(temperatureSeries) < this.min_possible) {
            throw new InputMismatchException();
        }
        else {
            this.tempSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
            this.length = temperatureSeries.length;
        }
    }
    public double sum() {
        double sum = 0;
        for (double value : tempSeries) {
            sum += value;
        }
        return sum;
    }

    public double average() {
        if (!this.isEmpty()) {
            return sum()/this.length;
        }
        else {
            throw new IllegalArgumentException("There is no data");
        }
    }

    public double deviation() {
        double average = average();
        double deviation = 0;
        for (int i = 0; i < this.length; i++) {
            deviation += (this.tempSeries[i] - average) * (this.tempSeries[i] - average);
        }
        return Math.sqrt(deviation/this.length);
    }

    public double min_value(double[] arr) {
        double min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public double min() {
        if (this.length > 0) {
            return min_value(this.tempSeries);
        }
        else {
            throw new IllegalArgumentException("There is no data");
        }
    }

    public double max() {
        if (this.length > 0) {
            double max = tempSeries[0];
            for (int i = 1; i < this.length; i++) {
                if (tempSeries[i] > max) {
                    max = tempSeries[i];
                }
            }
            return max;
        }
        else {
            throw new IllegalArgumentException("There is no data");
        }
    }

    public double findTempClosestToZero() {
        if (this.length > 0) {
            double closest = this.tempSeries[0];
            double min_distance = Math.abs(closest);
            for (int i = 1; i < this.length; i++) {
                if ((Math.abs(tempSeries[i]) < min_distance) || (Math.abs(tempSeries[i]) == min_distance && tempSeries[i] > closest)) {
                    min_distance = Math.abs(tempSeries[i]);
                    closest = tempSeries[i];
                }
            }
            return closest;
        }
        else {
            throw new IllegalArgumentException("There is no data");
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (!this.isEmpty()) {
            double closest = this.tempSeries[0];
            double difference = Math.abs(tempValue - closest);
            for (int i = 1; i < this.length; i++) {
                if ((Math.abs(tempValue - tempSeries[i]) < difference) || (Math.abs(tempValue - tempSeries[i]) == difference && tempSeries[i] > closest)) {
                    difference = Math.abs(tempValue - tempSeries[i]);
                    closest = tempSeries[i];
                }
            }
            return closest;
        }
        else {
            throw new IllegalArgumentException("There is no data");
        }
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] lessThen = new double[this.length];
        int j = 0;
        for (int i=0; i<this.length; i++) {
            if (this.tempSeries[i] < tempValue) {
                lessThen[j] = this.tempSeries[i];
                j++;
            }
        }
        return Arrays.copyOfRange(lessThen, 0, j);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] greaterThen = new double[this.length];
        int j = 0;
        for (int i=0; i<this.length; i++) {
            if (this.tempSeries[i] >= tempValue) {
                greaterThen[j] = this.tempSeries[i];
                j++;
            }
        }
        return Arrays.copyOfRange(greaterThen, 0, j);
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.isEmpty()) {
            throw new IllegalAccessError();
        }
        else{
            return new TempSummaryStatistics(this);
        }
    }

    public int addTemps(double... temps) {
        if (min_value(temps) < this.min_possible) {
            throw new InputMismatchException();
        }
        int length = 2*temps.length;
        int length_of_temps = temps.length;
        if (!this.isEmpty()) {
            length = 2*this.length;
            length_of_temps = this.length + length_of_temps;
        }
        int sum = 0;
        double[] arr  = new double[length];
        for (int i=0; i<this.length; i++) {
            arr[i] = this.tempSeries[i];
            sum += this.tempSeries[i];
        }
        int j = this.length;
        this.tempSeries = Arrays.copyOf(arr, length);
        this.length = length_of_temps;
        for (double value: temps) {
            this.tempSeries[j] = value;
            sum += value;
            j++;
        }
        return sum;
    }

    public static void main(String[] args) {
        double[] arr = new double[]{-1.0, 0.0, 3.0};
        TemperatureSeriesAnalysis n = new TemperatureSeriesAnalysis(arr);
        System.out.printf("Average: %f%n", n.average());
        System.out.printf("Min: %f%n", n.min());
        System.out.printf("Max: %f%n", n.max());
        System.out.printf("Closest to zero: %f%n", n.findTempClosestToZero());
        System.out.printf("Closest to -5.0: %f%n", n.findTempClosestToValue(-5.0));
        System.out.printf("Deviation: %f%n", n.deviation());
        System.out.print("Temperatures less than 0.5: ");
        double[] less = n.findTempsLessThen(0.5);
        for (double value: less) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.print("Temperatures greater than 2.0: ");
        double[] larger = n.findTempsGreaterThen(2.0);
        for (double value: larger) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.print("Sum of temperatures with newly added: ");
        System.out.println(n.addTemps(-10.0, 5.0, -280.0));
        for (double value: n.tempSeries) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println(n.summaryStatistics());
    }
}
