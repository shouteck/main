package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EQUIPMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.TrackCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.workout.Parameter;

/**
 * Parses input arguments and creates a new TrackCommand object
 */
public class TrackCommandParser implements Parser<TrackCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code TrackCommand}
     * and returns a {@code TrackCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    @Override
    public TrackCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TYPE, PREFIX_DURATION, PREFIX_DIFFICULTY,
                        PREFIX_EQUIPMENT, PREFIX_MUSCLE, PREFIX_CALORIES, PREFIX_INSTRUCTION, PREFIX_TAG);

        // check 1: no subcommand
        // check 2: no prefixes present
        // check 3: more than one prefix present
        if (argMultimap.getPreamble().isEmpty()
                || !areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TYPE, PREFIX_DURATION, PREFIX_DIFFICULTY,
                PREFIX_EQUIPMENT, PREFIX_MUSCLE, PREFIX_CALORIES, PREFIX_INSTRUCTION)
                || areMultipleParametersPresent(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrackCommand.MESSAGE_USAGE));
        }

        // check 4: one prefix present but value is empty
        if (argMultimap.getTheOnlyArg().getValue().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrackCommand.MESSAGE_USAGE));
        }

        String subcommand;
        try {
            subcommand = ParserUtil.parseSubcommand(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrackCommand.MESSAGE_USAGE), ive);
        }

        Parameter parameter = argMultimap.getTheOnlyArg();

        return new TrackCommand(subcommand, parameter);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if there is only one parameter in the given {@code ArgumentMultimap}.
     */
    private static boolean areMultipleParametersPresent(ArgumentMultimap argumentMultimap) {
        return (argumentMultimap.getNumberOfArgs() > 2);
    }
}
