package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.action.DeleteAction;
import ru.job4j.tracker.model.Input;
import ru.job4j.tracker.model.Output;
import ru.job4j.tracker.output.StubOutput;
import ru.job4j.tracker.store.MemTracker;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {

    @Before
    public void setUp() {
        System.out.println("Before method");
    }

    @After
    public void tearDown() {
        System.out.println("After method");
    }

    @Ignore
    public void execute() throws SQLException, IOException {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Delete item"));
        DeleteAction del = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("1");
        int id = tracker.findById(1).getId();
        del.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Application deleted successfully" + ln));
        assertNull(tracker.findById(id));
    }
}