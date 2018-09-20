package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EQUIPMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book. 
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a workout to the workout book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TYPE + "TYPE "
            + PREFIX_DURATION + "DURATION "
            + PREFIX_DIFFICULTY + "DIFFICULTY "
            + PREFIX_EQUIPMENT + "EQUIPMENT "
            + PREFIX_MUSCLE + "MUSCLE "
            + PREFIX_CALORIES + "CALORIES "
            + PREFIX_INSTRUCTION + "INSTRUCTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "john doe's workout "
            + PREFIX_TYPE + "strength "
            + PREFIX_DURATION + "20m "
            + PREFIX_DIFFICULTY + "beginner "
            + PREFIX_EQUIPMENT + "dumbbell "
            + PREFIX_MUSCLE + "bicep "
            + PREFIX_CALORIES + "150 "
            + PREFIX_INSTRUCTION + "set1: bicep curl reps: 4-6 "
            + PREFIX_TAG + "heavy "
            + PREFIX_TAG + "current favourite ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
