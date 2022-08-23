package ru.job4j.tracker.action;

import ru.job4j.tracker.model.Input;
import ru.job4j.tracker.model.Store;
import ru.job4j.tracker.model.UserAction;

public class StubAction implements UserAction {
    private boolean call = false;

    @Override
    public String name() {
        return "Stub action";
    }

    @Override
    public boolean execute(Input input, Store store) {
        call = true;
        return false;
    }

    public boolean isCall() {
        return call;
    }
}
