package com.stream.weeklychecker;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Files
{
    public static @NotNull File[] subfiles(File file)
    {
        return Objects.requireNonNullElse(file.listFiles(), new File[0]);
    }

    public static Stream<File> subfileStream(File file)
    {
        return Arrays.stream(subfiles(file));
    }
}
