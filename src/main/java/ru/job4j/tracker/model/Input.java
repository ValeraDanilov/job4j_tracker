package ru.job4j.tracker.model;

import java.io.IOException;

public interface Input {
    String askStr(String question) throws IOException;

    int askInt(String question) throws IOException;

    int askInt(String question, int max) throws IOException;
}