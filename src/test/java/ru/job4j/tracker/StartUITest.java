package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.StubAction;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.jdbc.SqlTracker;
import ru.job4j.tracker.model.Output;
import ru.job4j.tracker.model.UserAction;
import ru.job4j.tracker.output.ConsoleOutput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StartUITest {

    @Test
    public void whenExit() throws IOException, SQLException {
        Output out = new ConsoleOutput();
        StubInput input = new StubInput(new String[]{"0"});
        StubAction action = new StubAction();
        List<UserAction>  array = List.of(action);
        new StartUI(out).init(input, new SqlTracker(), array);
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenPrtMenu() throws IOException, SQLException {
        Output outs = new ConsoleOutput();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(new String[]{"0"});
        StubAction action = new StubAction();
        List<UserAction>  array = List.of(action);
        new StartUI(outs).init(input, new SqlTracker(), array);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(System.lineSeparator() + "Menu.")
                .add("0. Stub action")
                .toString();
        assertThat(out.toString(), is(expect));
        System.setOut(def);
    }
}
