package ru.job4j.tracker.input;

import ru.job4j.tracker.model.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements Input {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String askStr(String question) throws IOException {
        System.out.print(question);
        return reader.readLine();
    }

    @Override
    public int askInt(String question) throws IOException {
        return Integer.parseInt(askStr(question));
    }

    @Override
    public int askInt(String question, int max) throws IOException {
        int select = askInt(question);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }
}