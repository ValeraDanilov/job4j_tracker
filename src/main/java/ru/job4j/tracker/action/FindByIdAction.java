package ru.job4j.tracker.action;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.model.*;

import java.io.IOException;

public class FindByIdAction implements UserAction {

    private final Output output;

    public FindByIdAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Find item by Id";
    }

    @Override
    public boolean execute(Input input, Store store) throws IOException {
        int id = input.askInt("Enter id to search for goods: ");
        Item item = store.findById(id);
        if (item != null) {
            String res = String.format("Name: %s%s%s", item.getName(), ". Id: ", item.getId());
            output.println(res);
        } else {
            output.println("Error. Name not found");
        }
        return true;
    }
}
