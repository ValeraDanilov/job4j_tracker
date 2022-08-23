package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.action.FindByNameAction;
import ru.job4j.tracker.model.Input;
import ru.job4j.tracker.model.Output;
import ru.job4j.tracker.output.StubOutput;
import ru.job4j.tracker.store.MemTracker;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MoxFindByNameActionTest {

    @Before
    public void setUp() {
        System.out.println("Before method");
    }

    @After
    public void tearDown() {
        System.out.println("After method");
    }

    @Test
    public void execute() throws IOException {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("FindByName item"));
        FindByNameAction find = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("FindByName item");

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Name: FindByName item. Id: 1" + ln + ln));
        assertNotNull(tracker.findById(1));
    }
}
