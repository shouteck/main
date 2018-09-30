package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DIFFICULTY_AMY_WORKOUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIFFICULTY_BOB_WORKOUT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.RecommendCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalWorkouts.getTypicalWorkoutBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RecommendCommand.
 */
public class RecommendCommandTest {

    private Model model = new ModelManager(getTypicalWorkoutBook(), new UserPrefs());

    @Test
    public void execute() {
        final String difficulty = "Some difficulty.";

        assertCommandFailure(new RecommendCommand(difficulty), model, new CommandHistory(),
                String.format(MESSAGE_SUCCESS, difficulty));
    }

    @Test
    public void equals() {
        final RecommendCommand standardCommand = new RecommendCommand(VALID_DIFFICULTY_AMY_WORKOUT);

        // same values -> returns true
        final RecommendCommand commandWithSameValues = new RecommendCommand(VALID_DIFFICULTY_AMY_WORKOUT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different difficulty -> returns false
        assertFalse(standardCommand.equals(new RecommendCommand(VALID_DIFFICULTY_BOB_WORKOUT)));
    }
}
