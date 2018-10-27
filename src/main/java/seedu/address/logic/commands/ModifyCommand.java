package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERRED_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Modify the user profile in the Profile window.
 */
public class ModifyCommand extends Command {

    public static final String COMMAND_WORD = "modify";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Modify the user profile in the Profile window \n"
            + "Parameters: "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_USERNAME + "USERNAME] "
            + "[" + PREFIX_HEIGHT + "HEIGHT] "
            + "[" + PREFIX_WEIGHT + "WEIGHT] "
            + "[" + PREFIX_PREFERRED_DIFFICULTY + "PREFERRED_DIFFICULTY] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GENDER + "Female "
            + PREFIX_USERNAME + "Sarah "
            + PREFIX_HEIGHT + "1.69 "
            + PREFIX_WEIGHT + "87.2 "
            + PREFIX_PREFERRED_DIFFICULTY + "beginner";

    public static final String MESSAGE_SUCCESS = "User profile has been modified! Please rerun the"
            + " MainApp to see the changes.";

    private final String gender;
    private final String username;
    private final String height;
    private final String weight;
    private final String preferredDifficulty;


    // element.owntext() return element w/o <b>
    public ModifyCommand(String gender, String username, String height, String weight, String preferredDifficulty) {

        this.gender = gender;
        this.username = username;
        this.height = height;
        this.weight = weight;
        this.preferredDifficulty = preferredDifficulty;

    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ModifyCommand)) {
            return false;
        }
        // state check
        ModifyCommand e = (ModifyCommand) other;
        return gender.equals(e.gender)
                && username.equals(e.username)
                && height.equals(e.height)
                && weight.equals(e.weight)
                && preferredDifficulty.equals(e.preferredDifficulty);

    }
}
