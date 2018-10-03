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

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.TrackCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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

        String subcommand;
        try{
            subcommand = ParserUtil.parseSubcommand(argMultimap.getPreamble());
        } catch(IllegalValueException ive){
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrackCommand.MESSAGE_USAGE), ive);
        }

        String parameter;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            parameter = argMultimap.getValue(PREFIX_NAME).get();
        }
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            parameter = argMultimap.getValue(PREFIX_TYPE).get();
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            parameter = argMultimap.getValue(PREFIX_DURATION).get();
        }
        if (argMultimap.getValue(PREFIX_DIFFICULTY).isPresent()) {
            parameter = argMultimap.getValue(PREFIX_DIFFICULTY).get();
        }
        if (argMultimap.getValue(PREFIX_EQUIPMENT).isPresent()) {
            parameter = argMultimap.getValue(PREFIX_EQUIPMENT).get();
        }
        if (argMultimap.getValue(PREFIX_MUSCLE).isPresent()) {
            parameter = argMultimap.getValue(PREFIX_MUSCLE).get();
        }
        if (argMultimap.getValue(PREFIX_CALORIES).isPresent()) {
            parameter = argMultimap.getValue(PREFIX_CALORIES).get();
        }
        if (argMultimap.getValue(PREFIX_INSTRUCTION).isPresent()) {
            parameter = argMultimap.getValue(PREFIX_INSTRUCTION).get();
        }
        //parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editWorkoutDescriptor::setTags);

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrackCommand.MESSAGE_USAGE));
    }
}
