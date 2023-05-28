package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.builders.ValueBuilder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.CategoryNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.MidpointException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.AbstractFactory;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.modifiers.UserModifier;

import java.io.IOException;

public class User {

    private UserModifier modifier;

    public User(String nameUser) throws ObjectNotFoundException, IOException {
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
