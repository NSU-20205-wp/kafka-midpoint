package ru.nsu.ccfit.kafka_midpoint.utilities;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class StringUtilities {
    static public String generateString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
