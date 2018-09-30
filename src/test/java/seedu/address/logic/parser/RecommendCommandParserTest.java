package seedu.address.logic.parser;

import org.junit.Test;
import seedu.address.logic.commands.RecommendCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIFFICULTY_AMY_WORKOUT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECOMMEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class RecommendCommandParserTest {
    private RecommendCommandParser parser = new RecommendCommandParser();
    private final String nonEmptyDifficulty = "Some difficulty.";

    @Test
    public void parse_allFieldsPresent_success() {
        // have difficulty
        String userInput = " " + PREFIX_RECOMMEND + nonEmptyDifficulty;
        RecommendCommand expectedCommand = new RecommendCommand(nonEmptyDifficulty);
        assertParseSuccess(parser, userInput, expectedCommand);

        // no difficulty
        userInput = " " + PREFIX_RECOMMEND;
        expectedCommand = new RecommendCommand("");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecommendCommand.MESSAGE_USAGE);

        // missing recommend prefix
        assertParseFailure(parser, " " + VALID_DIFFICULTY_AMY_WORKOUT, expectedMessage);
    }
}
