package com.cs.draw.command.factory;

import com.cs.draw.command.Command;
import com.cs.draw.command.DrawLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cs.draw.util.StaticConfig.LINE;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LineCommandFactoryTest {

    private static final String color = "o";
    private static final String startCol = "1";
    private static final String startRow = "2";
    private static final String endCol = "6";
    private static final String endRow = "2";
    private List<String> commands;

    @Spy
    private LineCommandFactory lineCommandFactory;

    @Before
    public void setup() {
        commands = constructCommands();
    }

    @Test
    public void happyCase() {
        Command command = lineCommandFactory.createCommand(commands);
        assertTrue(command instanceof DrawLine);
        DrawLine drawLine = (DrawLine) command;
        assertEquals(Integer.valueOf(startCol).intValue(), drawLine.getStartColumn());
        assertEquals(Integer.valueOf(startRow).intValue(), drawLine.getStartRow());
        assertEquals(Integer.valueOf(endCol).intValue(), drawLine.getEndColumn());
        assertEquals(Integer.valueOf(endRow).intValue(), drawLine.getEndRow());
    }

    @Test
    public void invalidNumberOfInput_then_returnNull() {
        commands.add("test");
        Command command = lineCommandFactory.createCommand(commands);
        assertNull(command);
    }

    @Test
    public void invalidInput_then_returnNull() {
        commands.clear();
        String[] commandsArray = new String[]{LINE, startCol, "test", endCol, endRow};
        commands = Arrays.asList(commandsArray);

        Command command = lineCommandFactory.createCommand(commands);
        assertNull(command);
    }


    private List<String> constructCommands() {
        String[] commandsArray = new String[]{LINE, startCol, startRow, endCol, endRow};
        return new ArrayList<>(Arrays.asList(commandsArray));
    }
}
