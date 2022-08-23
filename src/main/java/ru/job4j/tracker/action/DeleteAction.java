package ru.job4j.tracker.action;

import ru.job4j.tracker.model.Input;
import ru.job4j.tracker.model.Output;
import ru.job4j.tracker.model.Store;
import ru.job4j.tracker.model.UserAction;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteAction implements UserAction {

    private final Output output;

    public DeleteAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store store) throws IOException, SQLException {
        output.println("=== Delete item ===");
        int id = input.askInt("Enter id to delete:");
        if (store.delete(id)) {
            output.println("Application deleted successfully");
        } else {
            output.println("Error. Id not found");
        }
        return true;
    }
}
