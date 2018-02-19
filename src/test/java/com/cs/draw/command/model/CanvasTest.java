package com.cs.draw.command.model;

import org.junit.Test;

import static com.cs.draw.util.StaticConfig.COLUMN_BORDER;
import static com.cs.draw.util.StaticConfig.ROW_BORDER;
import static com.cs.draw.util.StaticConfig.SPACE;
import static org.junit.Assert.assertEquals;

public class CanvasTest {

    @Test
    public void testCanvasConstruction() {
        Canvas canvas = new Canvas(3, 2);
        String[][] canvasData = canvas.getCanvas();
        for (int i = 0; i < canvas.getHeight(); i++) {
            for (int j = 0; j < canvas.getWidth(); j++) {
                if (i == 0 || i == canvas.getHeight() - 1){
                    assertEquals(ROW_BORDER, canvasData[i][j]);
                }else if(j == 0 || j == canvas.getWidth() - 1){
                    assertEquals(COLUMN_BORDER, canvasData[i][j]);
                }else{
                    assertEquals(SPACE, canvasData[i][j]);
                }
            }
        }
    }
}
