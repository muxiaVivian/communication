package com.cs.draw.command;

import com.cs.draw.command.model.Canvas;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DrawRectangleTest {
    private Canvas latestCanvas;

    private static final int width = 7;
    private static final int height = 6;

    @Before
    public void setup() {
        latestCanvas = new Canvas(width, height);
    }

    @Test
    public void testHappyCase() {
        DrawRectangle drawRectangle = new DrawRectangle.Builder().startColumn(1).startRow(3).endColumn(4).endRow(1).build();
        Canvas canvas = drawRectangle.execute(latestCanvas);
        Canvas expected = constructExpectedCanvasHorizontalLine();
        canvas.print();
        expected.print();
        assertEquals(expected, canvas);

    }

    @Test
    public void testLatsetCanvasIsNull_then_returnNull() {
        DrawRectangle drawRectangle = new DrawRectangle.Builder().startColumn(1).startRow(3).endColumn(6).endRow(2).build();
        Canvas canvas = drawRectangle.execute(null);
        assertNull(canvas);
    }

    @Test
    public void testStartRowInputOutOfBound_then_returnNull() {
        DrawRectangle drawRectangle = new DrawRectangle.Builder().startColumn(1).startRow(25).endColumn(1).endRow(2).build();
        Canvas canvas = drawRectangle.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    @Test
    public void testStartColInputOutOfBound_then_returnNull() {
        DrawRectangle drawRectangle = new DrawRectangle.Builder().startColumn(30).startRow(2).endColumn(1).endRow(2).build();
        Canvas canvas = drawRectangle.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    @Test
    public void testEndRowInputOutOfBound_then_returnNull() {
        DrawRectangle drawRectangle = new DrawRectangle.Builder().startColumn(1).startRow(2).endColumn(1).endRow(10).build();
        Canvas canvas = drawRectangle.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    @Test
    public void testEndColInputOutOfBound_then_returnNull() {
        DrawRectangle drawRectangle = new DrawRectangle.Builder().startColumn(1).startRow(2).endColumn(30).endRow(2).build();
        Canvas canvas = drawRectangle.execute(latestCanvas);
        assertEquals(latestCanvas, canvas);
    }

    private Canvas constructExpectedCanvasHorizontalLine() {
        String[][] data = new String[][]{
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", "x", " ", "|"},
                {"|", "x", " ", " ", "x", " ", "|"},
                {"|", "x", "x", "x", "x", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        };
        return new Canvas(width, height, data);
    }

}
