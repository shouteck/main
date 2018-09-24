package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalWorkouts.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.workout.Workout;
import seedu.address.testutil.WorkoutBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newWorkout_success() {
        Workout validWorkout = new WorkoutBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addWorkout(validWorkout);
        expectedModel.commitAddressBook();

        assertCommandSuccess(new AddCommand(validWorkout), model, commandHistory,
                String.format(AddCommand.MESSAGE_SUCCESS, validWorkout), expectedModel);
    }

    @Test
    public void execute_duplicateWorkout_throwsCommandException() {
        Workout workoutInList = model.getAddressBook().getWorkoutList().get(0);
        assertCommandFailure(new AddCommand(workoutInList), model, commandHistory,
                AddCommand.MESSAGE_DUPLICATE_WORKOUT);
    }

}
