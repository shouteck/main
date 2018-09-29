package seedu.address.logic.parser;

import seedu.address.logic.commands.RecommendCommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECOMMEND;

/**
 * Parses input arguments and creates a new {@code RecommendCommand} object
 */
public class RecommendCommandParser implements Parser<RecommendCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RecommendCommand}
     * and returns a {@code RecommendCommand} object for execution.
     */
    public RecommendCommand parse(String args) {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RECOMMEND);

        String difficulty = argMultimap.getValue(PREFIX_RECOMMEND).orElse("");

        return new RecommendCommand(difficulty);
    }


}
