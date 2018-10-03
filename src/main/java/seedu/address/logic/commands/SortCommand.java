package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.workout.Workout;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = "sorted all workouts";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        model.sortFilteredWorkoutList();

        model.commitWorkoutBook();

        return new CommandResult(MESSAGE_USAGE);
    }


}
