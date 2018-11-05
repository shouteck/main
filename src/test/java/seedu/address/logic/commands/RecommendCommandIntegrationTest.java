package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalParameters.getTypicalTrackedDataList;
import static seedu.address.testutil.TypicalWorkouts.getTypicalWorkoutBook;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecommendArguments;
import seedu.address.model.TrackedData;
import seedu.address.model.UserPrefs;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Duration;

/**
 * Contains integration tests (interaction with the Model) for {@code RecommendCommand}.
 */
public class RecommendCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalWorkoutBook(), getTypicalTrackedDataList(),
                new TrackedData(), new UserPrefs());
    }

    @Test
    public void execute_newRecommendArguments_success() {
        Optional<Calories> calories = Optional.of(new Calories("20"));
        RecommendArguments validRecommendArguments = new RecommendArguments.Builder().withCalories(calories,
                Optional.of(false)).build();

        Model expectedModel = new ModelManager(model.getWorkoutBook(), model.getTrackedDataList(),
                model.getTrackedData(), new UserPrefs());

        assertCommandSuccess(new RecommendCommand(validRecommendArguments), model, commandHistory,
                RecommendCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_workoutNotFound_throwsCommandException() {
        Optional<Duration> duration = Optional.of(new Duration("1m"));
        RecommendArguments noMatchRecommendArguments = new RecommendArguments.Builder().withDuration(duration,
                Optional.of(false)).build();
        assertCommandFailure(new RecommendCommand(noMatchRecommendArguments), model, commandHistory,
                RecommendCommand.MESSAGE_NO_SUCH_WORKOUT);
    }

}
