package com.cs.draw.command;

import com.cs.draw.command.model.Canvas;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClearTest {
    private static final int width = 7;
    private static final int height = 6;

    @Test
    public void testClear() {
        Clear clear = new Clear.Builder().columns(width).rows(height).build();
        Canvas canvas = clear.execute(null);
        Canvas expected = constructExpectedCanvas();
        assertEquals(expected, canvas);
    }

    private Canvas constructExpectedCanvas() {
        String[][] data = new String[][]{
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        };
        return new Canvas(width, height, data);
    }

}
