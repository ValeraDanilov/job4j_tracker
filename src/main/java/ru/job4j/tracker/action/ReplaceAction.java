package ru.job4j.tracker.action;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.model.*;

import java.io.IOException;
import java.sql.SQLException;

public class ReplaceAction implements UserAction {

    private final Output output;

    public ReplaceAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Store store) throws IOException, SQLException {
        output.println("=== Edit item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter new name for Item: ");
        Item item = new Item(name);
        if (store.replace(id, item)) {
            output.println("Edit item is done.");
        } else {
            output.println(String.format("Item with id=%s not found.", id));
        }
        return true;
    }
}
