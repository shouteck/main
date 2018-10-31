package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_AMY_WORKOUT;
import static seedu.address.logic.commands.CommandTestUtil.EQUIPMENT_DESC_AMY_WORKOUT;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_AMY_WORKOUT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.workout.DurationPredicate;
import seedu.address.model.workout.EquipmentPredicate;
import seedu.address.model.workout.TypePredicate;



public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new DurationPredicate(Arrays.asList("20m")),
                        new TypePredicate(Arrays.asList("cardio")),
                        new EquipmentPredicate(Arrays.asList("yoga mat")));
        String userInput = TYPE_DESC_AMY_WORKOUT + DURATION_DESC_AMY_WORKOUT + EQUIPMENT_DESC_AMY_WORKOUT;

        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }
}
