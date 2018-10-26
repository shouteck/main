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
        // Valid Calories without Difficulty and Duration fields
        Optional<Calories> calories = Optional.of(new Calories("1"));
        RecommendArguments expectedRecommendArguments = new RecommendArguments.Builder().withCalories(calories).build();
        assertParseSuccess(parser, " " + PREFIX_CALORIES + "1",
                new RecommendCommand(expectedRecommendArguments));

        // Valid Difficulty without Calories and Duration fields
        Optional<Difficulty> difficulty = Optional.of(new Difficulty("beginner"));
        expectedRecommendArguments = new RecommendArguments.Builder().withDifficulty(difficulty).build();
        assertParseSuccess(parser, " " + PREFIX_DIFFICULTY + "beginner",
                new RecommendCommand(expectedRecommendArguments));

        // Valid Duration without Calories and Difficulty fields
        Optional<Duration> duration = Optional.of(new Duration("1m"));
        expectedRecommendArguments = new RecommendArguments.Builder().withDuration(duration).build();
        assertParseSuccess(parser, " " + PREFIX_DURATION + "1m",
                new RecommendCommand(expectedRecommendArguments));

        // Valid Calories and Difficulty without Duration field
        calories = Optional.of(new Calories("1000"));
        difficulty = Optional.of(new Difficulty("intermediate"));
        expectedRecommendArguments = new RecommendArguments.Builder().withCalories(calories)
                .withDifficulty(difficulty).build();
        assertParseSuccess(parser, " " + PREFIX_CALORIES + "1000" + " " + PREFIX_DIFFICULTY + "intermediate",
                new RecommendCommand(expectedRecommendArguments));

        // Valid Calories and Duration without Difficulty field
        calories = Optional.of(new Calories("500"));
        duration = Optional.of(new Duration("1000m"));
        expectedRecommendArguments = new RecommendArguments.Builder().withCalories(calories)
                .withDuration(duration).build();
        assertParseSuccess(parser, " " + PREFIX_CALORIES + "500" + " " + PREFIX_DURATION + "1000m",
                new RecommendCommand(expectedRecommendArguments));

        // Valid Difficulty and Duration without Calories Field
        difficulty = Optional.of(new Difficulty("advanced"));
        duration = Optional.of(new Duration("500m"));
        expectedRecommendArguments = new RecommendArguments.Builder().withDifficulty(difficulty)
                .withDuration(duration).build();
        assertParseSuccess(parser, " " + PREFIX_DIFFICULTY + "advanced" + " " + PREFIX_DURATION + "500m",
                new RecommendCommand(expectedRecommendArguments));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        // Valid Calories,Difficulty and Duration fields
        Optional<Calories> calories = Optional.of(new Calories("750"));
        Optional<Difficulty> difficulty = Optional.of(new Difficulty("intermediate"));
        Optional<Duration> duration = Optional.of(new Duration("750m"));
        RecommendArguments expectedRecommendArguments = new RecommendArguments.Builder().withCalories(calories)
                .withDifficulty(difficulty).withDuration(duration).build();
        assertParseSuccess(parser, " " + PREFIX_CALORIES + "750" + " " + PREFIX_DIFFICULTY + "intermediate"
                        + " " + PREFIX_DURATION + "750m",
                new RecommendCommand(expectedRecommendArguments));
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid Calories < 1
        assertParseFailure(parser, " " + PREFIX_CALORIES + "0", Calories.MESSAGE_CALORIES_CONSTRAINTS);

        // Invalid Calories > 1000
        assertParseFailure(parser, " " + PREFIX_CALORIES + "1001", Calories.MESSAGE_CALORIES_CONSTRAINTS);

        // Invalid Calories for non-integer
        assertParseFailure(parser, " " + PREFIX_CALORIES + "hundred", Calories.MESSAGE_CALORIES_CONSTRAINTS);

        // Invalid Difficulty for non-case sensitive inputs
        assertParseFailure(parser, " " + PREFIX_DIFFICULTY + "beginNer",
                Difficulty.MESSAGE_DIFFICULTY_CONSTRAINTS);

        // Invalid Difficulty for integer
        assertParseFailure(parser, " " + PREFIX_DIFFICULTY + "1", Difficulty.MESSAGE_DIFFICULTY_CONSTRAINTS);

        // Invalid Duration for < 1m
        assertParseFailure(parser, " " + PREFIX_DURATION + "0m", Duration.MESSAGE_DURATION_CONSTRAINTS);

        // Invalid Duration for > 1000m
        assertParseFailure(parser, " " + PREFIX_DURATION + "1001m", Duration.MESSAGE_DURATION_CONSTRAINTS);

        // Invalid Duration for non-integer
        assertParseFailure(parser, " " + PREFIX_DURATION + "fivem", Duration.MESSAGE_DURATION_CONSTRAINTS);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecommendCommand.MESSAGE_USAGE);

        // Missing Calories, Difficulty and Duration fields
        assertParseFailure(parser, " ", expectedMessage);
    }

}
