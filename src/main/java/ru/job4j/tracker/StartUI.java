package ru.job4j.tracker;

import ru.job4j.tracker.action.*;
import ru.job4j.tracker.hbn.HbmTracker;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.model.Input;
import ru.job4j.tracker.model.Output;
import ru.job4j.tracker.model.Store;
import ru.job4j.tracker.model.UserAction;
import ru.job4j.tracker.output.ConsoleOutput;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class StartUI {

    private final Output output;

    public StartUI(Output output) {
        this.output = output;
    }

    public void init(Input input, Store store, List<UserAction> actions) throws IOException, SQLException {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, store);
        }
    }

    private void showMenu(List<UserAction> actions) {
        System.out.println(System.lineSeparator() + "Menu.");
        for (int index = 0; index < actions.size(); index++) {
            this.output.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(new ConsoleInput(), output);
        Store store = new HbmTracker();
        List<UserAction> actions = Arrays.asList(new CreateAction(output),
                new ReplaceAction(output), new DeleteAction(output),
                new FindAllAction(output), new FindByNameAction(output),
                new FindByIdAction(output), new ExitAction());
        new StartUI(output).init(input, store, actions);

/*
        Input validate = new ValidateInput(
                new ConsoleInput()
        );
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            UserAction[] actions = {
                    new CreateAction(), new ReplaceAction(), new DeleteAction(), new FindAllAction(), new FindByNameAction(), new FindByIdAction()
            };
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }

 */
    }
}
