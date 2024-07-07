package com.stream.weeklychecker;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
public class UnqualifiedException extends Exception
{
    final private List<String> messages = new ArrayList<>();

    public UnqualifiedException(String... messages)
    {
        this.messages.addAll(Arrays.asList(messages));
    }

    public UnqualifiedException(Collection<String> messages)
    {
        this.messages.addAll(messages);
    }
}
