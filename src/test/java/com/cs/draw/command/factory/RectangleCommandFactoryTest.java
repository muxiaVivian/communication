package com.cs.draw.command.factory;

import com.cs.draw.command.Command;
import com.cs.draw.command.DrawRectangle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cs.draw.util.StaticConfig.RECTANGLE;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RectangleCommandFactoryTest {

    private static final String color = "o";
    private static final String startCol = "1";
    private static final String startRow = "2";
    private static final String endCol = "6";
    private static final String endRow = "3";
    private List<String> commands;

    @Spy
    private RectangleCommandFactory rectangleCommandFactory;

    @Before
    public void setup() {
        commands = constructCommands();
    }

    @Test
    public void happyCase() {
        Command command = rectangleCommandFactory.createCommand(commands);
        assertTrue(command instanceof DrawRectangle);
        DrawRectangle drawRectangle = (DrawRectangle) command;
        assertEquals(Integer.valueOf(startCol).intValue(), drawRectangle.getStartColumn());
        assertEquals(Integer.valueOf(startRow).intValue(), drawRectangle.getStartRow());
        assertEquals(Integer.valueOf(endCol).intValue(), drawRectangle.getEndColumn());
        assertEquals(Integer.valueOf(endRow).intValue(), drawRectangle.getEndRow());
    }

    @Test
    public void invalidNumberOfInput_then_returnNull() {
        commands.add("test");
        Command command = rectangleCommandFactory.createCommand(commands);
        assertNull(command);
    }

    @Test
    public void invalidInput_then_returnNull() {
        commands.clear();
        String[] commandsArray = new String[]{RECTANGLE, startCol, "test", endCol, endRow};
        commands = Arrays.asList(commandsArray);

        Command command = rectangleCommandFactory.createCommand(commands);
        assertNull(command);
    }


    private List<String> constructCommands() {
        String[] commandsArray = new String[]{RECTANGLE, startCol, startRow, endCol, endRow};
        return new ArrayList<>(Arrays.asList(commandsArray));
    }
}
