package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.action.FindAllAction;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.jdbc.ConnectionRollback;
import ru.job4j.tracker.jdbc.SqlTracker;
import ru.job4j.tracker.model.Output;
import ru.job4j.tracker.output.StubOutput;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindAllActionTest {

    @Before
    public void before() {
        System.out.println("Before method");
    }

    @After
    public void after() {
        System.out.println("After method");
    }

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Ignore
    public void whenCheckOutput() throws Exception {
        Output outs = new StubOutput();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))){
            Item item = new Item("fix bug");
            sqlTracker.add(item);
            FindAllAction act = new FindAllAction(outs);
            act.execute(new StubInput(new String[]{}), sqlTracker);
            String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                    .add("Name: " + item.getName() + " | Id: " + item.getId())
                    .toString();
            assertThat(out.toString(), is(expect));
            sqlTracker.delete(item.getId());
            System.setOut(def);
        }
    }
}
