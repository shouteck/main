package seedu.address.logic.commands;

import org.junit.Test;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.RecommendCommand.MESSAGE_NOT_IMPLEMENTED_YET;
import static seedu.address.testutil.TypicalWorkouts.getTypicalWorkoutBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RecommendCommand.
 */
public class RecommendCommandTest {

    private Model model = new ModelManager(getTypicalWorkoutBook(), new UserPrefs());

    @Test
    public void execute() {
        assertCommandFailure(new RecommendCommand(), model, new CommandHistory(), MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
