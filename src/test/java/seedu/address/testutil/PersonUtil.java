package seedu.address.testutil;


import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EQUIPMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_TYPE + person.getType().fullType + " ");
        sb.append(PREFIX_DURATION + person.getDuration().fullDuration + " ");
        sb.append(PREFIX_DIFFICULTY + person.getDifficulty().fullDifficulty + " ");
        sb.append(PREFIX_EQUIPMENT + person.getEquipment().fullEquipment + " ");
        sb.append(PREFIX_MUSCLE + person.getMuscle().fullMuscle + " ");
        sb.append(PREFIX_CALORIES + person.getCalories().fullCalories + " ");
        sb.append(PREFIX_INSTRUCTION + person.getInstruction().fullInstruction + " ");
        /*
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        */
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getType().ifPresent(type -> sb.append(PREFIX_TYPE).append(type.fullType).append(" "));
        descriptor.getDuration().ifPresent(duration -> sb.append(PREFIX_DURATION).append(duration.fullDuration).append(" "));
        descriptor.getDifficulty().ifPresent(difficulty -> sb.append(PREFIX_DIFFICULTY).append(difficulty.fullDifficulty).append(" "));
        descriptor.getEquipment().ifPresent(equipment -> sb.append(PREFIX_EQUIPMENT).append(equipment.fullEquipment).append(" "));
        descriptor.getMuscle().ifPresent(muscle -> sb.append(PREFIX_MUSCLE).append(muscle.fullMuscle).append(" "));
        descriptor.getCalories().ifPresent(calories -> sb.append(PREFIX_CALORIES).append(calories.fullCalories).append(" "));
        descriptor.getInstruction().ifPresent(instruction -> sb.append(PREFIX_INSTRUCTION).append(instruction.fullInstruction).append(" "));
        /*
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        */
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
