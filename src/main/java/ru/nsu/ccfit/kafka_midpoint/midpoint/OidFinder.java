package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.AbstractFactory;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.List;

public class OidFinder {
    private static final Logger logger = Logger.getLogger(OidFinder.class.getCanonicalName());

    private OidFinder() {}

    public static String findOid(String typeObject, String field, String value) throws IOException {
        MidpointSearcher searcher;
        try {
            searcher = (MidpointSearcher) AbstractFactory.instance()
                    .getFactory("search").createProduct(typeObject, null);
        }
        catch(ProductCreatorException e) {
            logger.severe(e.getMessage());
            return null;
        }
        if(searcher == null) {
            return null;
        }
        List<? extends MidpointDTO> list = searcher.getListObjects(field, value);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0).getOid();
    }

}

