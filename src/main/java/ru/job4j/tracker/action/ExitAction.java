package ru.job4j.tracker.action;

import ru.job4j.tracker.model.Input;
import ru.job4j.tracker.model.Store;
import ru.job4j.tracker.model.UserAction;

import java.io.IOException;

public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public boolean execute(Input input, Store store) throws IOException {
        return false;
    }
}
