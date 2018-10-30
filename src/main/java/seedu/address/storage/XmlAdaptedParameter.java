package seedu.address.storage;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.workout.Parameter;

/**
 * JAXB-friendly version of the Workout.
 */
public class XmlAdaptedParameter {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Parameter's %s field is missing!";

    @XmlElement(required = true)
    private String prefix;
    @XmlElement(required = true)
    private String value;

    /**
     * Constructs an XmlAdaptedParameter.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedParameter() {}

    /**
     * Constructs an {@code XmlAdaptedParameter} with the given parameter details.
     */
    public XmlAdaptedParameter(String prefix, String value) {
        this.prefix = prefix;
        this.value = value;
    }

    /**
     * Converts a given Parameter into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedParameter
     */
    public XmlAdaptedParameter(Parameter source) {
        prefix = source.getPrefix().getPrefix();
        value = source.getValue();
    }

    /**
     * Converts this jaxb-friendly adapted parameter object into the model's Parameter object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Workout
     */
    public Parameter toModelType() throws IllegalValueException {
        if (prefix == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Prefix.class.getSimpleName()));
        }
        final Prefix modelPrefix = new Prefix(prefix);

        if (value == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "value"));
        }
        final String modelValue = value;

        return new Parameter(modelPrefix, modelValue);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedParameter)) {
            return false;
        }

        XmlAdaptedParameter otherParameter = (XmlAdaptedParameter) other;
        return Objects.equals(prefix, otherParameter.prefix)
                && Objects.equals(value, otherParameter.value);
    }
}
