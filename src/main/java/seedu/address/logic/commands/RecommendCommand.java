package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;

import java.util.List;
import java.util.Random;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.RecommendArguments;
import seedu.address.model.workout.Workout;

/**
 * Recommends an existing workout from the workout book.
 */
public class RecommendCommand extends Command {

    public static final String COMMAND_WORD = "recommend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Recommends a workout from the workout book "
            + "identified by a combination of DURATION,DIFFICULTY,CALORIES\n"
            + "Parameters: "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_DIFFICULTY + "DIFFICULTY] "
            + "[" + PREFIX_CALORIES + "CALORIES]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DURATION + "20m";


    public static final String MESSAGE_SUCCESS = "Workout recommended!";
    public static final String MESSAGE_NO_SUCH_WORKOUT = "There is no such workout in the workout book.";

    private final RecommendArguments recommendArguments;

    public RecommendCommand(RecommendArguments recommendArguments) {
        requireAllNonNull(recommendArguments);
        this.recommendArguments = recommendArguments;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Workout> filteredWorkoutList = model.getFilteredWorkoutList();
        List<Workout> filteredInternalList;

        filteredInternalList = model.getFilteredInternalList(recommendArguments);
        if (filteredInternalList.isEmpty()) {
            throw new CommandException(MESSAGE_NO_SUCH_WORKOUT);
        }

        int targetIndex = getTargetIndex(filteredWorkoutList, filteredInternalList);

        EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex));
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private int getTargetIndex(List<Workout> filteredWorkoutList, List<Workout> filteredInternalList) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(filteredInternalList.size());
        Workout randomWorkout = filteredInternalList.get(randomIndex);
        return filteredWorkoutList.indexOf(randomWorkout);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecommendCommand // instanceof handles nulls
                && recommendArguments.equals(((RecommendCommand) other).recommendArguments));
    }

}
