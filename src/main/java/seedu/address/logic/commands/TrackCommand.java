package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.util.Pair;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;


/**
 * Allows users to track parameters in subsequently added workouts
 */
public class TrackCommand extends Command {

    public static final String COMMAND_WORD = "track";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": allows the user to track specific parameters listed in the command, for any new workouts that are"
            + "added after this command is entered.\n"
            + "Parameters: subcommand (parameter prefix)/parameter\n"
            + "Example: " + COMMAND_WORD + " start muscle/bicep";

    public static final String MESSAGE_ARGUMENTS_ACCEPTED = "Subcommand: %1$s, Parameter: %2$s";
    public static final String MESSAGE_SUCCESS = "Now tracking %1$s%2$s";

    public static final String MESSAGE_SUBCOMMAND_CONSTRAINTS = "subcommand must be \"start\"";
    public static final String SUBCOMMAND_VALIDATION_REGEX = "(start)";

    private final String subcommand;
    private final Pair<Prefix, String> parameter;

    /**
     * @param parameter to be tracked
     */
    public TrackCommand(String subcommand, Pair<Prefix, String> parameter){
        requireAllNonNull(subcommand, parameter);

        this.subcommand = subcommand;
        this.parameter = parameter;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult(String.format(MESSAGE_SUCCESS, parameter.getKey(), parameter.getValue()));
    }

    public boolean equals(Object other) {
        //check 1: same object
        if (other == this) {
            return true;
        }

        //check 2: null
        if(!(other instanceof TrackCommand)){
            return false;
        }

        //check 3: check all attributes
        TrackCommand t = (TrackCommand) other;
        return subcommand.equals(t.subcommand) && parameter.equals(t.parameter);
    }

    public static boolean isValidSubcommand(String test){
        return test.matches(SUBCOMMAND_VALIDATION_REGEX);
    }
}
