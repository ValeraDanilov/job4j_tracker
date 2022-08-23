package ru.job4j.tracker.action;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.model.*;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAction implements UserAction {

    private final Output output;

    public CreateAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Add new Item";
    }

    @Override
    public boolean execute(Input input, Store store) throws IOException, SQLException {
        String name = input.askStr("=== Create a new Item ====" + System.lineSeparator() + "Enter name: ");
        Item item = new Item(name);
        store.add(item);
        return true;
    }
}
