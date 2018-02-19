package com.cs.draw.command.Util;

import com.cs.draw.util.CommandValidator;
import com.cs.draw.command.model.Canvas;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class CommandValidatorTest {
    private static final int width = 7;
    private static final int height = 6;

    private Canvas canvas;

    @Before
    public void setup(){
        canvas = new Canvas(width, height);
    }

    @Test
    public void testRowHappyCase(){
        assertTrue(CommandValidator.validateCommandBound(2, canvas, true));
    }

    @Test
    public void testColumnHappyCase(){
        assertTrue(CommandValidator.validateCommandBound(2, canvas, false));
    }

    @Test
    public void testRowSmallerThanOne_then_failValidation(){
        assertFalse(CommandValidator.validateCommandBound(0, canvas, true));
    }

    @Test
    public void testRowLargerThenHeight_then_failValidation(){
        assertFalse(CommandValidator.validateCommandBound(5, canvas, true));
    }

    @Test
    public void testColumnSmallerThanOne_then_failValidation(){
        assertFalse(CommandValidator.validateCommandBound(0, canvas, false));
    }

    @Test
    public void testColumnLargerThenWidth_then_failValidation(){
        assertFalse(CommandValidator.validateCommandBound(6, canvas, false));
    }
}
