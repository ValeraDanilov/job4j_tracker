package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.action.FindByNameAction;
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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindByNameActionTest {

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
        PrintStream stream = System.out;
        System.setOut(new PrintStream(out));
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("fix bug");
            tracker.add(item);
            FindByNameAction name = new FindByNameAction(outs);
            name.execute(new StubInput(new String[]{item.getName()}), tracker);
            String result = String.format("Name: %s%s%s%s", item.getName(), ". Id: ", item.getId(), System.lineSeparator());
            assertThat(out.toString(), is(result));
            tracker.delete(item.getId());
            System.setOut(stream);
        }
    }

}
