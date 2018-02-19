package com.cs.draw.command;

import com.cs.draw.command.model.Canvas;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DrawLineTest {
    private Canvas latestCanvas;

    private static final int width = 7;
    private static final int height = 6;

    @Before
    public void setup() {
        latestCanvas = new Canvas(width, height);
    }

    @Test
    public void testHappyCase_HorizontalLine() {
        DrawLine drawLine = new DrawLine.Builder().startColumn(1).startRow(2).endColumn(4).endRow(2).build();
        Canvas canvas = drawLine.execute(latestCanvas);
        Canvas expected = constructExpectedCanvasHorizontalLine();
        canvas.print();
        assertEquals(expected, canvas);
    }

    @Test
    public void testHappyCase_verticalLine() {
        DrawLine drawLine = new DrawLine.Builder().startColumn(3).startRow(1).endColumn(3).endRow(2).build();
        Canvas canvas = drawLine.execute(latestCanvas);
        Canvas expected = constructExpectedCanvasVerticalLine();
        assertEquals(expected, canvas);
        canvas.print();

    }

    @Test
    public void testLatsetCanvasIsNull_then_returnNull() {
        DrawLine drawLine = new DrawLine.Builder().startColumn(1).startRow(2).endColumn(6).endRow(2).build();
        Canvas canvas = drawLine.execute(null);
        assertNull(canvas);
    }

    @Test
    public void testInputNotInOneLine_then_returnNull() {
        DrawLine drawLine = new DrawLine.Builder().startColumn(1).startRow(3).endColumn(4).endRow(2).build();
        Canvas canvas = drawLine.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    @Test
    public void testStartRowInputOutOfBound_then_returnNull() {
        DrawLine drawLine = new DrawLine.Builder().startColumn(1).startRow(25).endColumn(1).endRow(2).build();
        Canvas canvas = drawLine.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    @Test
    public void testStartColInputOutOfBound_then_returnNull() {
        DrawLine drawLine = new DrawLine.Builder().startColumn(30).startRow(2).endColumn(1).endRow(2).build();
        Canvas canvas = drawLine.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    @Test
    public void testEndRowInputOutOfBound_then_returnNull() {
        DrawLine drawLine = new DrawLine.Builder().startColumn(1).startRow(2).endColumn(1).endRow(10).build();
        Canvas canvas = drawLine.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    @Test
    public void testEndColInputOutOfBound_then_returnNull() {
        DrawLine drawLine = new DrawLine.Builder().startColumn(1).startRow(2).endColumn(30).endRow(2).build();
        Canvas canvas = drawLine.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    private Canvas constructExpectedCanvasHorizontalLine() {
        String[][] data = new String[][]{
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", "x", "x", "x", "x", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        };
        return new Canvas(width, height, data);
    }

    private Canvas constructExpectedCanvasVerticalLine() {
        String[][] data = new String[][]{
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", "x", " ", " ", "|"},
                {"|", " ", " ", "x", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        };
        return new Canvas(width, height, data);
    }




}
