package ru.nsu.ccfit.kafka_midpoint.utilities;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class StringUtilities {
    static public String generateString(int length) {
        String chars = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random rnd = new Random();
        while (stringBuilder.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }
}
