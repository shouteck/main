package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Allows users to track parameters in subsequently added workouts
 */
public class TrackCommand extends Command {

    public static final String COMMAND_WORD = "track";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": allows the user to track specific parameters listed in the command, for any new workouts that are"
            + "added after this command is entered.\n"
            + "Parameters: (parameter prefix)/parameter"
            + "Example: " + COMMAND_WORD + "bicep";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Command has not been implemented yet";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
