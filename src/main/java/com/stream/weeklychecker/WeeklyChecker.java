package com.stream.weeklychecker;

import lombok.val;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WeeklyChecker
{
    private final String path;
    private final String weekName;
    private final Constraint[] constraints;

    public WeeklyChecker(String path, String weekName, Constraint... constraints)
    {
        this.path = path;
        this.weekName = weekName;
        this.constraints = constraints;
    }

    public String getCheckReport()
    {
        val qualifiedStudentNameList = new ArrayList<String>();
        val unqualifiedStudentList = new ArrayList<UnqualifiedStudent>();

        val studentFiles = Arrays.stream(Files.subfiles(new File(path)))
                                 .filter(File::isDirectory)
                                 .filter(f -> !f.getName().contains("."))
                                 .filter(f -> !f.getName().equals("刁一轩"))
                                 .filter(f -> !f.getName().equals("每周任务"))
                                 .toList();

        for(val studentFile : studentFiles)
        {
            val studentName = studentFile.getName();
            try
            {
                val weekFile = new File(studentFile, weekName);
                if(!weekFile.exists())
                {
                    throw new UnqualifiedException(MessageFormat.format("{0}文件夹未创建", weekName));
                }

                val messages = new ArrayList<String>();
                for(val constraint : constraints)
                {
                    val message = constraint.check(weekFile);
                    message.ifPresent(messages::add);
                }
                if(!messages.isEmpty())
                {
                    throw new UnqualifiedException(messages);
                }
                qualifiedStudentNameList.add(studentName);
            }
            catch(UnqualifiedException e)
            {
                unqualifiedStudentList.add(new UnqualifiedStudent(studentName, e.getMessages()));
            }
        }

        return MessageFormat.format("{0}检查结果如下：\n合格学生：{1}\n不合格学生：\n{2}",
                                    weekName,
                                    String.join(", ", qualifiedStudentNameList),
                                    unqualifiedStudentList.stream()
                                                          .map(UnqualifiedStudent::toString)
                                                          .collect(Collectors.joining("\n")));
    }
}
