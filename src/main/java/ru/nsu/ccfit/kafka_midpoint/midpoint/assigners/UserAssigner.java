package ru.nsu.ccfit.kafka_midpoint.midpoint.assigners;

import ru.nsu.ccfit.kafka_midpoint.midpoint.ModificationType;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.CategoryNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.MidpointException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.AbstractFactory;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.modifiers.UserModifier;

import java.io.IOException;

public class UserAssigner {

    private UserModifier modifier;

    public UserAssigner(String nameUser) throws ObjectNotFoundException, IOException {
        modifier = new UserModifier(nameUser);

    }

    public int modifyAssignment(String what, String name, ModificationType type)
            throws MidpointException, IOException {
        ValueBuilder builder;
        try {
            builder = (ValueBuilder) AbstractFactory.instance().getFactory("build")
                    .createProduct(what, null);
        }
        catch(ProductCreatorException e) {
            throw new CategoryNotFoundException(e);
        }
        return modifier.updateField("assignment", builder.buildValue(name), type);
    }
}
