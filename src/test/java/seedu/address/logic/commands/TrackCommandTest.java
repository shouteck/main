package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.TrackCommand.MESSAGE_ARGUMENTS_ACCEPTED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.testutil.TypicalWorkouts.getTypicalWorkoutBook;


import org.junit.Test;

import javafx.util.Pair;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains unit tests for TrackCommand
 */
public class TrackCommandTest {

    private Model model = new ModelManager(getTypicalWorkoutBook(), new UserPrefs());

    @Test
    public void execute(){
        final String subcommand = "start";
        final Pair<Prefix, String> parameter = new Pair<>(PREFIX_MUSCLE, "bicep");
        assertCommandFailure(new TrackCommand(subcommand, parameter), model, new CommandHistory(),
                String.format(MESSAGE_ARGUMENTS_ACCEPTED, subcommand, parameter));
    }

    @Test
    public void equals(){
        final TrackCommand testCommand = new TrackCommand("start", new Pair<>(PREFIX_MUSCLE, "bicep"));

        //check 1: same object -> returns true
        assertEquals(testCommand, testCommand);

        //check 2: same values -> return true
        TrackCommand sameValuesInCommand = new TrackCommand("start", new Pair<>(PREFIX_MUSCLE, "bicep"));
        assertEquals(sameValuesInCommand, testCommand);

        //check 3: null -> return false
        assertNotEquals(null, testCommand);

        //check 4: different command -> return false
        assertNotEquals(testCommand, new ClearCommand());

        //check 5: different subcommand -> return false
        TrackCommand differentSubcommand = new TrackCommand("stop", new Pair<>(PREFIX_MUSCLE, "bicep"));
        assertNotEquals(testCommand, differentSubcommand);

        //check 6: different parameter -> return false
        TrackCommand differentParameter = new TrackCommand("start", new Pair<>(PREFIX_MUSCLE, "bicep"));
        assertNotEquals(testCommand, differentParameter);
    }
}
