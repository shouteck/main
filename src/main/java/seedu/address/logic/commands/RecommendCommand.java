package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;

import java.util.List;
import java.util.Random;

import seedu.address.logic.CommandHistory;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.model.Model;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;
import seedu.address.model.workout.Workout;

/**
 * Recommends an existing workout from the workout book.
 */
public class RecommendCommand extends Command {

    public static final String COMMAND_WORD = "recommend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Recommends a workout from the workout book "
            + "identified by one of DURATION,DIFFICULTY,CALORIES\n"
            + "Parameters: "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_DIFFICULTY + "DIFFICULTY] "
            + "[" + PREFIX_CALORIES + "CALORIES]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DURATION + "20m";


    public static final String MESSAGE_SUCCESS = "Workout recommended!";

    private Duration duration = null;
    private Difficulty difficulty = null;
    private Calories calories = null;

    public RecommendCommand(Duration duration) {
        requireAllNonNull(duration);
        this.duration = duration;
    }

    public RecommendCommand(Difficulty difficulty) {
        requireAllNonNull(difficulty);
        this.difficulty = difficulty;
    }

    public RecommendCommand(Calories calories) {
        requireAllNonNull(calories);
        this.calories = calories;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        List<Workout> filteredWorkoutList = model.getFilteredWorkoutList();
        List<Workout> filteredInternalList;
        if (duration != null) {
            filteredInternalList = model.getFilteredInternalList(duration);
        } else if (difficulty != null) {
            filteredInternalList = model.getFilteredInternalList(difficulty);
        } else {
            filteredInternalList = model.getFilteredInternalList(calories);
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(filteredInternalList.size());
        Workout randomWorkout = filteredInternalList.get(randomIndex);
        int targetIndex = filteredWorkoutList.indexOf(randomWorkout);

        EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex));

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecommendCommand // instanceof handles nulls
                && duration.equals(((RecommendCommand) other).duration)
                && difficulty.equals(((RecommendCommand) other).difficulty)
                && calories.equals(((RecommendCommand) other).calories)); // state check
    }

}
