package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.workout.Name;
import seedu.address.model.workout.Type;
import seedu.address.model.workout.Duration;
import seedu.address.model.workout.Difficulty;
import seedu.address.model.workout.Equipment;
import seedu.address.model.workout.Muscle;
import seedu.address.model.workout.Calories;
import seedu.address.model.workout.Instruction;
import seedu.address.model.workout.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }


    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */

    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setType(person.getType());
        descriptor.setDuration(person.getDuration());
        descriptor.setDifficulty(person.getDifficulty());
        descriptor.setEquipment(person.getEquipment());
        descriptor.setMuscle(person.getMuscle());
        descriptor.setCalories(person.getCalories());
        descriptor.setInstruction(person.getInstruction());
        /*
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        */
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */

    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }


    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */

    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */

    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
