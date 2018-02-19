package com.cs.draw.command.Input.processor;

import com.cs.draw.command.Command;
import com.cs.draw.command.parser.CommandParser;
import com.cs.draw.command.model.Canvas;
import com.cs.draw.input.processor.InpurProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.cs.draw.util.StaticConfig.CLEAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InputProcessorTest {
    private static final int width = 7;
    private static final int height = 6;

    @Mock
    private CommandParser commandParser;
    @Mock
    private Command command;
    @InjectMocks
    private InpurProcessor inputProcessor;

    @Test
    public void testValidInput() throws Exception {
        when(commandParser.parse(CLEAR)).thenReturn(command);
        when(command.execute(any())).thenReturn(new Canvas(width, height));
        inputProcessor.process(CLEAR);
        assertEquals(inputProcessor.getLatestCanvas(), new Canvas(width, height));
    }

    @Test
    public void testInvalidInput_then_nullCommand() throws Exception {
        when(commandParser.parse(CLEAR)).thenReturn(null);
        assertNull(inputProcessor.getLatestCanvas());
    }

    @Test
    public void testInvalidInput_then_nullCanvas() throws Exception {
        when(commandParser.parse(CLEAR)).thenReturn(command);
        when(command.execute(any())).thenReturn(null);
        assertNull(inputProcessor.getLatestCanvas());
    }


}
