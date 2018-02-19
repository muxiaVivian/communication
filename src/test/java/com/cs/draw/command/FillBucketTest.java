package com.cs.draw.command;

import com.cs.draw.command.model.Canvas;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FillBucketTest {

    private static final int width = 7;
    private static final int height = 6;

    private Canvas latestCanvas;

    @Before
    public void setup() {
        latestCanvas = initCanvas();
    }

    @Test
    public void testHappyCase() {
        FillBucket fillBucket = new FillBucket.Builder().targetCol(1).targetRow(4).color("o").build();
        Canvas canvas = fillBucket.execute(latestCanvas);
        Canvas expectedCanvas = constructExpectedCanvasForHappyCase();
        canvas.print();
        expectedCanvas.print();
        assertEquals(expectedCanvas, canvas);

    }

    @Test
    public void testLatsetCanvasIsNull_then_returnNull() {
        FillBucket fillBucket = new FillBucket.Builder().targetCol(10).targetRow(3).color("o").build();
        Canvas canvas = fillBucket.execute(null);
        assertNull(canvas);
    }

    @Test
    public void testRowInputOutOfBound_then_returnNull() {
        FillBucket fillBucket = new FillBucket.Builder().targetCol(2).targetRow(10).color("o").build();
        Canvas canvas = fillBucket.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    @Test
    public void testStartColInputOutOfBound_then_returnNull() {
        FillBucket fillBucket = new FillBucket.Builder().targetCol(10).targetRow(3).color("o").build();
        Canvas canvas = fillBucket.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    private Canvas constructExpectedCanvasForHappyCase() {
        String[][] data = new String[][]{
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", "o", "o", "|"},
                {"|", "x", " ", "x", "o", "o", "|"},
                {"|", "x", "x", "x", "o", "o", "|"},
                {"|", "o", "o", "o", "o", "o", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        };
        return new Canvas(width, height, data);
    }


    private Canvas initCanvas() {
        String[][] data = new String[][]{
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", " ", " ", "|"},
                {"|", "x", " ", "x", " ", " ", "|"},
                {"|", "x", "x", "x", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        };
        return new Canvas(width, height, data);
    }
}
