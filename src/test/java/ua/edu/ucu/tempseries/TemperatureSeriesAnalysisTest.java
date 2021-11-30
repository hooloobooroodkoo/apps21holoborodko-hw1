package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis tempsOneEl;
    private TemperatureSeriesAnalysis tempsZeroEl;
    private TemperatureSeriesAnalysis tempsEl;
    private double value = 9.0;
    private double value2 = 2.0;
    @Before
    public void init() {
        double[] one = {-1.0};
        tempsOneEl = new TemperatureSeriesAnalysis(one);
        double[] zero = {};
        tempsZeroEl = new TemperatureSeriesAnalysis(zero);
        double[] several  = {3.0, -5.0, 1.0, 5.0};
        tempsEl = new TemperatureSeriesAnalysis(several);
    }
    @Test
    public void testAverageWithOneElementArray() {
        assertEquals(-1.0, tempsOneEl.average(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        tempsZeroEl.average();
    }

    @Test
    public void testAverage() {
        assertEquals(1.0, tempsEl.average(), 0.00001);
    }
    @Test
    public void testIsEmptyWithOneElementArray() {
        assertEquals(false, tempsOneEl.isEmpty());
    }

    @Test
    public void testIsEmptyWithEmptyArray() {
        assertEquals(true, tempsZeroEl.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertEquals(false, tempsEl.isEmpty());
    }
    @Test
    public void testSumWithOneElementArray() {
        assertEquals(-1.0, tempsOneEl.sum(), 0.000001);
    }

    @Test
    public void testSum() {
        assertEquals(4.0, tempsEl.sum(), 0.0000001);
    }

    @Test
    public void testDeviationWithOneElementArray() {
        assertEquals(0.0, tempsOneEl.deviation(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        tempsZeroEl.deviation();
    }

    @Test
    public void testDeviation() {
        assertEquals(3.7416573867739413, tempsEl.deviation(), 0.00001);
    }

    @Test
    public void testMinValueWithOneElementArray() {
        assertEquals(-1.0, tempsOneEl.minValue(tempsOneEl.getTempSeries()), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        tempsZeroEl.min();
    }

    @Test
    public void testMin() {
        assertEquals(-5.0, tempsEl.min(), 0.00001);
    }
    @Test
    public void testMaxWithOneElementArray() {
        assertEquals(-1.0, tempsOneEl.max(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        tempsZeroEl.max();
    }

    @Test
    public void testMax() {
        assertEquals(5.0, tempsEl.max(), 0.00001);
    }
    @Test
    public void testClosestToZeroWithOneElementArray() {
        assertEquals(-1.0, tempsOneEl.findTempClosestToZero(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClosestToZeroWithEmptyArray() {
        tempsZeroEl.findTempClosestToZero();
    }

    @Test
    public void testClosestToZero() {
        assertEquals(1.0, tempsEl.findTempClosestToZero(), 0.00001);
    }
    @Test
    public void testClosestToValueWithOneElementArray() {
        assertEquals(-1.0, tempsOneEl.findTempClosestToValue(this.value), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClosestToValueWithEmptyArray() {
        tempsZeroEl.findTempClosestToValue(this.value);
    }

    @Test
    public void testClosestToValue() {
        assertEquals(5.0, tempsEl.findTempClosestToValue(this.value), 0.00001);
    }
    @Test
    public void testFindLessWithOneElementArray() {
        assertEquals(1, tempsOneEl.findTempsLessThen(this.value2).length, 0.0001);
    }

    @Test
    public void testFindLessToValue() {
        assertEquals(2, tempsEl.findTempsLessThen(this.value2).length, 0.00001);
    }

    @Test
    public void testFindGreaterWithOneElementArray() {
        assertEquals(0, tempsOneEl.findTempsGreaterThen(this.value2).length, 0.0001);
    }

    @Test
    public void testFindGreaterToValue() {
        assertEquals(2, tempsEl.findTempsGreaterThen(this.value2).length, 0.00001);
    }
    @Test
    public void testSummaryWithOneElementArray() {
        assertEquals("Average: -1.0\n" +
                "Deviation: 0.0\n" +
                "Min: -1.0\n" +
                "Max: -1.0", tempsOneEl.summaryStatistics().toString());
    }

    @Test
    public void testSummary() {
        assertEquals("Average: 1.0\n" +
                "Deviation: 3.7416573867739413\n" +
                "Min: -5.0\n" +
                "Max: 5.0", tempsEl.summaryStatistics().toString());
    }
    @Test
    public void testAdd() {
        tempsEl.addTemps(9.0, -5.0);
        assertEquals(8, tempsEl.getTempSeries().length);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddToSmall() {
        tempsEl.addTemps(-280.0);
    }
}
