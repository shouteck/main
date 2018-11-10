package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showWorkoutAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_WORKOUT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_WORKOUT;
import static seedu.address.testutil.TypicalParameters.getTypicalTrackedDataList;
import static seedu.address.testutil.TypicalWorkouts.getTypicalWorkoutBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TrackedData;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code CompleteCommand}.
 */
public class CompleteCommandTest {

    private Model model = new ModelManager(getTypicalWorkoutBook(), getTypicalTrackedDataList(), new TrackedData(),
            new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredWorkoutList().size() + 1);
        CompleteCommand completeCommand = new CompleteCommand(outOfBoundIndex);

        assertCommandFailure(completeCommand, model, commandHistory, Messages.MESSAGE_INVALID_WORKOUT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showWorkoutAtIndex(model, INDEX_FIRST_WORKOUT);

        Index outOfBoundIndex = INDEX_SECOND_WORKOUT;
        // ensures that outOfBoundIndex is still in bounds of workout book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWorkoutBook().getWorkoutList().size());

        CompleteCommand completeCommand = new CompleteCommand(outOfBoundIndex);

        assertCommandFailure(completeCommand, model, commandHistory, Messages.MESSAGE_INVALID_WORKOUT_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredWorkoutList().size() + 1);
        CompleteCommand completeCommand = new CompleteCommand(outOfBoundIndex);

        // execution failed -> workout book state not added into model
        assertCommandFailure(completeCommand, model, commandHistory, Messages.MESSAGE_INVALID_WORKOUT_DISPLAYED_INDEX);

        // single workout book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void equals() {
        CompleteCommand completeFirstCommand = new CompleteCommand(INDEX_FIRST_WORKOUT);
        CompleteCommand completeSecondCommand = new CompleteCommand(INDEX_SECOND_WORKOUT);

        // same object -> returns true
        assertTrue(completeFirstCommand.equals(completeFirstCommand));

        // same values -> returns true
        CompleteCommand deleteFirstCommandCopy = new CompleteCommand(INDEX_FIRST_WORKOUT);
        assertTrue(completeFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(completeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(completeFirstCommand.equals(null));

        // different workout -> returns false
        assertFalse(completeFirstCommand.equals(completeSecondCommand));
    }
}
