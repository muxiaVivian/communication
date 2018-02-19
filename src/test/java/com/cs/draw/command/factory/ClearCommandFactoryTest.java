package com.cs.draw.command.factory;

import com.cs.draw.command.Clear;
import com.cs.draw.command.Command;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cs.draw.util.StaticConfig.BUCKET;
import static com.cs.draw.util.StaticConfig.CLEAR;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClearCommandFactoryTest {

    private static final String col = "10";
    private static final String row = "3";
    private List<String> commands;

    @Spy
    private ClearCommandFactory clearCommandFactory;

    @Before
    public void setup() {
        commands = constructCommands();
    }

    @Test
    public void happyCase() {
        Command command = clearCommandFactory.createCommand(commands);
        assertTrue(command instanceof Clear);
        Clear clear = (Clear) command;
        assertEquals(12, clear.getColumns());
        assertEquals(5, clear.getRows());
    }

    @Test
    public void invalidNumberOfInput_then_returnNull() {
        commands.add("test");
        Command command = clearCommandFactory.createCommand(commands);
        assertNull(command);
    }

    @Test
    public void invalidInput_then_returnNull() {
        commands.clear();
        String[] commandsArray = new String[]{BUCKET, col, "test"};
        commands = Arrays.asList(commandsArray);

        Command command = clearCommandFactory.createCommand(commands);
        assertNull(command);
    }


    private List<String> constructCommands() {
        String[] commandsArray = new String[]{CLEAR, col, row};
        return new ArrayList<>(Arrays.asList(commandsArray));
    }
}
