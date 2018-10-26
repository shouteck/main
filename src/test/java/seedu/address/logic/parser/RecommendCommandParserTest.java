package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Optional;

import org.junit.Test;

import seedu.address.logic.commands.RecommendCommand;
import seedu.address.model.RecommendArguments;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Duration;

public class RecommendCommandParserTest {
    private RecommendCommandParser parser = new RecommendCommandParser();

    @Test
    public void parse_optionalFieldsMissing_success() {
        // Missing Difficulty and Duration fields
        Optional<Calories> calories = Optional.of(new Calories("150"));
        RecommendArguments expectedRecommendArguments = new RecommendArguments.Builder().withCalories(calories).build();
        assertParseSuccess(parser, " " + PREFIX_CALORIES + "150",
                new RecommendCommand(expectedRecommendArguments));

        // Missing Calories and Duration fields
        Optional<Difficulty> difficulty = Optional.of(new Difficulty("beginner"));
        expectedRecommendArguments = new RecommendArguments.Builder().withDifficulty(difficulty).build();
        assertParseSuccess(parser, " " + PREFIX_DIFFICULTY + "beginner",
                new RecommendCommand(expectedRecommendArguments));

        // Missing Calories and Difficulty fields
        Optional<Duration> duration = Optional.of(new Duration("10m"));
        expectedRecommendArguments = new RecommendArguments.Builder().withDuration(duration).build();
        assertParseSuccess(parser, " " + PREFIX_DURATION + "10m",
                new RecommendCommand(expectedRecommendArguments));

        // Missing Duration field
        calories = Optional.of(new Calories("150"));
        difficulty = Optional.of(new Difficulty("beginner"));
        expectedRecommendArguments = new RecommendArguments.Builder().withCalories(calories)
                .withDifficulty(difficulty).build();
        assertParseSuccess(parser, " " + PREFIX_CALORIES + "150" + " " + PREFIX_DIFFICULTY + "beginner",
                new RecommendCommand(expectedRecommendArguments));

        // Missing Difficulty field
        calories = Optional.of(new Calories("150"));
        duration = Optional.of(new Duration("10m"));
        expectedRecommendArguments = new RecommendArguments.Builder().withCalories(calories)
                .withDuration(duration).build();
        assertParseSuccess(parser, " " + PREFIX_CALORIES + "150" + " " + PREFIX_DURATION + "10m",
                new RecommendCommand(expectedRecommendArguments));

        // Missing Calories Field
        difficulty = Optional.of(new Difficulty("beginner"));
        duration = Optional.of(new Duration("10m"));
        expectedRecommendArguments = new RecommendArguments.Builder().withDifficulty(difficulty)
                .withDuration(duration).build();
        assertParseSuccess(parser, " " + PREFIX_DIFFICULTY + "beginner" + " " + PREFIX_DURATION + "10m",
                new RecommendCommand(expectedRecommendArguments));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RecommendCommand.MESSAGE_USAGE));

        // Missing Calories, Difficulty and Duration fields
        assertParseFailure(parser, " ", expectedMessage);
    }

}
