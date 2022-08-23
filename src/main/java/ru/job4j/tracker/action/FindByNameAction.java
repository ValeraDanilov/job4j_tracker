package ru.job4j.tracker.action;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.model.*;

import java.io.IOException;
import java.util.List;

public class FindByNameAction implements UserAction {

    private final Output output;

    public FindByNameAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Store store) throws IOException {
        String name = input.askStr("Enter name: ");
        List<Item> items = store.findByName(name);
        if (!(items.isEmpty())) {
            for (Item item : items) {
                String res = String.format("Name: %s%s%s%s", item.getName(), ". Id: ", item.getId(), System.lineSeparator());
                output.println(res);
            }
        } else {
            System.out.println("Name not found");
        }
        return true;
    }
}
