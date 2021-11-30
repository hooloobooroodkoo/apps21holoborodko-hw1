package ua.edu.ucu.tempseries;


import java.util.Arrays;
import java.util.InputMismatchException;
public class TemperatureSeriesAnalysis {
    private double[] tempSeries;
    private final double minPossible = -273.0;
    private int length;

    public TemperatureSeriesAnalysis() {
        this.tempSeries = new double[0];
        this.length = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if (temperatureSeries.length > 0 && minValue(temperatureSeries)
                < this.minPossible) {
            throw new InputMismatchException();
        }
        else {
            this.tempSeries = Arrays.copyOf(
                    temperatureSeries, temperatureSeries.length);
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

    public boolean isEmpty() {
        return this.length <= 0;
    }

    public double[] getTempSeries() {
        return this.tempSeries;
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
            deviation += (this.tempSeries[i] - average)
                    * (this.tempSeries[i] - average);
        }
        return Math.sqrt(deviation/this.length);
    }

    public double minValue(double[] arr) {
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
            return minValue(this.tempSeries);
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
            double minDistance = Math.abs(closest);
            for (int i = 1; i < this.length; i++) {
                if ((Math.abs(tempSeries[i]) < minDistance)
                        || (Math.abs(tempSeries[i]-minDistance) < 0.0000001
                        && tempSeries[i] > closest)) {
                    minDistance = Math.abs(tempSeries[i]);
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
                if ((Math.abs(tempValue - tempSeries[i]) < difference)
                        ||
                        (Math.abs(tempValue - tempSeries[i]) == difference
                                &&
                        tempSeries[i] > closest)) {
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
        for (int i = 0; i < this.length; i++) {
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
        for (int i = 0; i < this.length; i++) {
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
        else {
            return new TempSummaryStatistics(this);
        }
    }

    public int addTemps(double... temps) {
        if (minValue(temps) < this.minPossible) {
            throw new InputMismatchException();
        }
        int len = 2*temps.length;
        int lengthOfTemps = temps.length;
        if (!this.isEmpty()) {
            len = 2*this.length;
            lengthOfTemps = this.length + lengthOfTemps;
        }
        int sum = 0;
        double[] arr  = new double[len];
        for (int i = 0; i < this.length; i++) {
            arr[i] = this.tempSeries[i];
            sum += this.tempSeries[i];
        }
        int j = this.length;
        this.tempSeries = Arrays.copyOf(arr, len);
        this.length = lengthOfTemps;
        for (double value: temps) {
            this.tempSeries[j] = value;
            sum += value;
            j++;
        }
        return sum;
    }
}
