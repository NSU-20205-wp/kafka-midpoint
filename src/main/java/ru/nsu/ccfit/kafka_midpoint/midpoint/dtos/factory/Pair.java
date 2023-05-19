package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.factory;

public class Pair<T1, T2> {
    public T1 first;
    public T2 second;

    public Pair(T1 a, T2 b) {
        first = a;
        second = b;
    }
}
