package com.stream.weeklychecker;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UnqualifiedStudent
{
    String studentName;
    List<String> messages = new ArrayList<>();

    public UnqualifiedStudent(String studentName)
    {
        this.studentName = studentName;
    }

    @Override
    public String toString()
    {
        return studentName + ":" + String.join("\n", messages);
    }
}
