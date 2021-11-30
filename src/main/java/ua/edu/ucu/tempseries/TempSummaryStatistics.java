package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private TemperatureSeriesAnalysis tempAnalysis;
    private double avgTemp;
    private double devTemp;
    private double minTemp;
    private double maxTemp;


    public TempSummaryStatistics() {

    }

    public TempSummaryStatistics(TemperatureSeriesAnalysis analysis) {
        this.tempAnalysis = analysis;
        this.avgTemp = analysis.average();
        this.devTemp = analysis.deviation();
        this.minTemp = analysis.min();
        this.maxTemp = analysis.max();
    }

    public String toString() {
        return "Average: " + this.avgTemp
                + "\nDeviation: " + this.devTemp
                + "\nMin: " + this.minTemp
                + "\nMax: " + this.maxTemp;
    }
}
