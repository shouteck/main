package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

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
            + "Example: " + COMMAND_WORD
            + PREFIX_RECOMMEND + "beginner";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Recommend command not implemented yet";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }

}
