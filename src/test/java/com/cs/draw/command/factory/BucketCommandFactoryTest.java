package com.cs.draw.command.factory;

import com.cs.draw.command.Command;
import com.cs.draw.command.FillBucket;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cs.draw.util.StaticConfig.BUCKET;

@RunWith(MockitoJUnitRunner.class)
public class BucketCommandFactoryTest {

    private static final String color = "o";
    private static final String targetColumn = "10";
    private static final String targetRow = "3";
    private List<String> commands;

    @Spy
    private BucketCommandFactory bucketCommandFactory;

    @Before
    public void setup() {
        commands = constructCommands();
    }

    @Test
    public void happyCase() {
        Command command = bucketCommandFactory.createCommand(commands);
        assertTrue(command instanceof FillBucket);
        FillBucket fillBucket = (FillBucket) command;
        assertEquals(color, fillBucket.getColor());
        assertEquals(Integer.valueOf(targetColumn).intValue(), fillBucket.getTargetCol());
        assertEquals(Integer.valueOf(targetRow).intValue(), fillBucket.getTargetRow());
    }

    @Test
    public void invalidNumberOfInput_then_returnNull() {
        commands.add("test");
        Command command = bucketCommandFactory.createCommand(commands);
        assertNull(command);
    }

    @Test
    public void invalidInput_then_returnNull() {
        commands.clear();
        String[] commandsArray = new String[]{BUCKET, targetColumn, "test", color};
        commands = Arrays.asList(commandsArray);

        Command command = bucketCommandFactory.createCommand(commands);
        assertNull(command);
    }


    private List<String> constructCommands() {
        String[] commandsArray = new String[]{BUCKET, targetColumn, targetRow, color};
        return new ArrayList<>(Arrays.asList(commandsArray));
    }
}
