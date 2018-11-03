package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MUSCLE_DESC_AMY_WORKOUT;
import static seedu.address.logic.commands.CommandTestUtil.MUSCLE_DESC_BOB_WORKOUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_BOB_WORKOUT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.TrackCommand;
import seedu.address.model.workout.Parameter;

public class TrackCommandParserTest {
    private TrackCommandParser parser = new TrackCommandParser();

    @Test
    public void parse_compulsoryFieldsPresent_returnsTrackCommand() {
        // have subcommand and parameter
        String subcommand = "start";
        String userInput = subcommand + " " + MUSCLE_DESC_BOB_WORKOUT;
        TrackCommand expectedCommand = new TrackCommand(subcommand,
                new Parameter(PREFIX_MUSCLE, VALID_MUSCLE_BOB_WORKOUT));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrackCommand.MESSAGE_USAGE);

        // no subcommand
        assertParseFailure(parser, MUSCLE_DESC_BOB_WORKOUT, expectedMessage);

        // no parameter
        assertParseFailure(parser, "start", expectedMessage);

        // only command word
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_multipleParameters_throwsParseException() {
        String userInput = MUSCLE_DESC_AMY_WORKOUT + " " + MUSCLE_DESC_BOB_WORKOUT;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrackCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }
}
