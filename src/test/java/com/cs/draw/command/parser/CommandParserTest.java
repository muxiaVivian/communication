package com.cs.draw.command.parser;

import com.cs.draw.command.Command;
import com.cs.draw.command.factory.BucketCommandFactory;
import com.cs.draw.command.factory.ClearCommandFactory;
import com.cs.draw.command.factory.LineCommandFactory;
import com.cs.draw.command.factory.RectangleCommandFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.cs.draw.util.StaticConfig.QUIT;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandParserTest {
    @Mock
    private LineCommandFactory lineCommandFactory;
    @Mock
    private ClearCommandFactory clearCommandFactory;
    @Mock
    private RectangleCommandFactory rectangleCommandFactory;
    @Mock
    private BucketCommandFactory bucketCommandFactory;

    @InjectMocks
    private CommandParser commandParser;

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private List<String> commands = new ArrayList<>();

    @Test
    public void testNullCommand_then_returnNull() {
        Command command = commandParser.parse(null);
        assertNull(command);
    }

    @Test
    public void testQuitCommand_then_SystemExit() {
        exit.expectSystemExit();
        Command command = commandParser.parse(QUIT);
    }

    @Test
    public void testInvalidNumberOfCommand_then_returnNull() {
        String input = "L 1 2 3";
        Command command = commandParser.parse(input);
        assertNull(command);
    }

    @Test
    public void testCreateDrawLineCommand() {
        String input = "L 1 2 6 2";
        commandParser.parse(input);
        verify(lineCommandFactory).createCommand(any());
    }

    @Test
    public void testCreateClearCommand() {
        String input = "C 20 4";
        commandParser.parse(input);
        verify(clearCommandFactory).createCommand(any());
    }

    @Test
    public void testCreateDrawRectangleCommand() {
        String input = "R 1 3 6 2";
        commandParser.parse(input);
        verify(rectangleCommandFactory).createCommand(any());
    }

    @Test
    public void testCreateFillBucketCommand() {
        String input = "B 10 3 o";
        commandParser.parse(input);
        verify(bucketCommandFactory).createCommand(any());
    }


}
