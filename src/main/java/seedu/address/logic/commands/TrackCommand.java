package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.workout.Parameter;


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
    public static final String SUBCOMMAND_VALIDATION_REGEX = "(start)|(stop)";

    private final String subcommand;
    private final Parameter parameter;

    /**
     * @param parameter to be tracked
     */
    public TrackCommand(String subcommand, Parameter parameter) {
        requireAllNonNull(subcommand, parameter);

        this.subcommand = subcommand;
        this.parameter = parameter;
    }

    /**
     *
     * WIP
     */
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        //step 1: check whether list containing data being tracked exists
        if(subcommand.equals("start")) {
            model.addDataToTrack(parameter);
        } else if(subcommand.equals("stop")){
            model.removeDataFromTrack(parameter);
        }
        //step 2: check whether file specific to desired data exists
        //step 2: write to file
        return new CommandResult(String.format(MESSAGE_SUCCESS, parameter.getPrefix(), parameter.getValue()));
    }

    /**
     *
     * WIP
     */
    public boolean equals(Object other) {
        //check 1: same object
        if (other == this) {
            return true;
        }

        //check 2: null
        if (!(other instanceof TrackCommand)) {
            return false;
        }

        //check 3: check all attributes
        TrackCommand t = (TrackCommand) other;
        return subcommand.equals(t.subcommand) && parameter.equals(t.parameter);
    }

    public static boolean isValidSubcommand(String test) {
        return test.matches(SUBCOMMAND_VALIDATION_REGEX);
    }
}
