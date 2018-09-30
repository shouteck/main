package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.workout.Workout;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;

import java.util.List;
import java.util.Random;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECOMMEND;

/**
 * Recommends an existing workout from the workout book.
 */
public class RecommendCommand extends Command {

    public static final String COMMAND_WORD = "recommend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Recommends a workout from the workout book "
            + "identified by the difficulty.\n"
            + "Parameters: "
            + PREFIX_RECOMMEND + "[DIFFICULTY]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_RECOMMEND + "beginner";

    public static final String MESSAGE_SUCCESS = "Workout recommended!";

    private final String difficulty;

    public RecommendCommand(String difficulty) {
        requireAllNonNull(difficulty);

        this.difficulty = difficulty;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        List<Workout> filteredWorkoutList = model.getFilteredWorkoutList();

        Random rand = new Random();
        int targetIndex =  rand.nextInt(filteredWorkoutList.size());

        EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex));
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof RecommendCommand)) {
            return false;
        }
        // state check
        RecommendCommand e = (RecommendCommand) other;
        return difficulty.equals(e.difficulty);
    }

}
