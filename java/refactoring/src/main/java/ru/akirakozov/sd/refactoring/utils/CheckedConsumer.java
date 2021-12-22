package ru.akirakozov.sd.refactoring.utils;

import java.io.IOException;
import java.sql.SQLException;

@FunctionalInterface
public interface CheckedConsumer<T> {
    void accept(T t) throws IOException, SQLException;
}
