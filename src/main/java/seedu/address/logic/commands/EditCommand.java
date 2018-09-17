package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EQUIPMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Name;
//import seedu.address.model.person.Address;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Phone;
import seedu.address.model.person.Type;
import seedu.address.model.person.Duration;
import seedu.address.model.person.Difficulty;
import seedu.address.model.person.Equipment;
import seedu.address.model.person.Muscle;
import seedu.address.model.person.Calories;
import seedu.address.model.person.Instruction;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing workout in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the workout identified "
            + "by the index number used in the displayed workout list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
//            + "[" + PREFIX_PHONE + "PHONE] "
//            + "[" + PREFIX_EMAIL + "EMAIL] "
//            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TYPE + "TYPE] "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_DIFFICULTY + "DIFFICULTY] "
            + "[" + PREFIX_EQUIPMENT + "EQUIPMENT] "
            + "[" + PREFIX_MUSCLE + "MUSCLE] "
            + "[" + PREFIX_CALORIES + "CALORIES] "
            + "[" + PREFIX_INSTRUCTION + "INSTRUCTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TYPE + "Anaerobic "
            + PREFIX_DIFFICULTY + "Beginner";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Workout: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This workout already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.updatePerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
//        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
//        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
//        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Type updatedType = editPersonDescriptor.getType().orElse(personToEdit.getType());
        Duration updatedDuration = editPersonDescriptor.getDuration().orElse(personToEdit.getDuration());
        Difficulty updatedDifficulty = editPersonDescriptor.getDifficulty().orElse(personToEdit.getDifficulty());
        Equipment updatedEquipment = editPersonDescriptor.getEquipment().orElse(personToEdit.getEquipment());
        Muscle updatedMuscle = editPersonDescriptor.getMuscle().orElse(personToEdit.getMuscle());
        Calories updatedCalories = editPersonDescriptor.getCalories().orElse(personToEdit.getCalories());
        Instruction updatedInstruction = editPersonDescriptor.getInstruction().orElse(personToEdit.getInstruction());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedType, updatedDuration, updatedDifficulty, updatedEquipment,
        updatedMuscle, updatedCalories, updatedInstruction, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
//        private Phone phone;
//        private Email email;
//        private Address address;
        private Type type;
        private Duration duration;
        private Difficulty difficulty;
        private Equipment equipment;
        private Muscle muscle;
        private Calories calories;
        private Instruction instruction;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
//            setPhone(toCopy.phone);
//            setEmail(toCopy.email);
//            setAddress(toCopy.address);
            setType(toCopy.type);
            setDuration(toCopy.duration);
            setDifficulty(toCopy.difficulty);
            setEquipment(toCopy.equipment);
            setMuscle(toCopy.muscle);
            setCalories(toCopy.calories);
            setInstruction(toCopy.instruction);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, type, duration, difficulty, equipment, muscle, calories,
            instruction, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Optional<Type> getType() {
            return Optional.ofNullable(type);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setDifficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
        }

        public Optional<Difficulty> getDifficulty() {
            return Optional.ofNullable(difficulty);
        }

        public void setEquipment(Equipment equipment) {
            this.equipment = equipment;
        }

        public Optional<Equipment> getEquipment() {
            return Optional.ofNullable(equipment);
        }

        public void setMuscle(Muscle muscle) {
            this.muscle = muscle;
        }

        public Optional<Muscle> getMuscle() {
            return Optional.ofNullable(muscle);
        }

        public void setCalories(Calories calories) {
            this.calories = calories;
        }

        public Optional<Calories> getCalories() {
            return Optional.ofNullable(calories);
        }

        public void setInstruction(Instruction instruction) {
            this.instruction = instruction;
        }

        public Optional<Instruction> getInstruction() {
            return Optional.ofNullable(instruction);
        }

//        public void setPhone(Phone phone) {
//            this.phone = phone;
//        }
//
//        public Optional<Phone> getPhone() {
//            return Optional.ofNullable(phone);
//        }
//
//        public void setEmail(Email email) {
//            this.email = email;
//        }
//
//        public Optional<Email> getEmail() {
//            return Optional.ofNullable(email);
//        }
//
//        public void setAddress(Address address) {
//            this.address = address;
//        }
//
//        public Optional<Address> getAddress() {
//            return Optional.ofNullable(address);
//        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
//                    && getPhone().equals(e.getPhone())
//                    && getEmail().equals(e.getEmail())
//                    && getAddress().equals(e.getAddress())
                    && getType().equals(e.getType())
                    && getDuration().equals(e.getDuration())
                    && getDifficulty().equals(e.getDifficulty())
                    && getEquipment().equals(e.getEquipment())
                    && getMuscle().equals(e.getMuscle())
                    && getCalories().equals(e.getCalories())
                    && getInstruction().equals(e.getInstruction())
                    && getTags().equals(e.getTags());
        }
    }
}
