package com.stream.weeklychecker;

import java.io.File;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Constraint
{
    private final Predicate<File> condition;

    private final String message;

    public Optional<String> check(File file)
    {
        return condition.test(file) ? Optional.empty() : Optional.of(message);
    }

    public Constraint(String message, Predicate<File> condition)
    {
        this.message = message;
        this.condition = condition;
    }

    public static Predicate<File> findFile(String name)
    {
        return file -> Stream.of(file)
                             .flatMap(Constraint::expand)
                             .anyMatch(f -> f.getName().contains(name));
    }

    public static Predicate<File> findFileOrDirectory(String name)
    {
        return file -> Stream.of(file)
                             .flatMap(Constraint::expandWithSelf)
                             .anyMatch(f -> f.getName().contains(name));
    }

    public static Stream<File> expand(File file)
    {
        return Files.subfileStream(file).flatMap(f -> f.isFile() ? Stream.of(f) : expand(f));
    }

    public static Stream<File> expandWithSelf(File file)
    {
        return Stream.concat(Stream.of(file), Files.subfileStream(file).flatMap(Constraint::expandWithSelf));
    }
}
