package ru.job4j.tracker.model;

import java.io.IOException;
import java.sql.SQLException;

public interface UserAction {
    String name();

    boolean execute(Input input, Store store) throws IOException, SQLException;
}
