package com.stream.weeklychecker;

import lombok.val;

import static com.stream.weeklychecker.Constraint.findFile;

public class Main
{
    public static void main(String[] args)
    {
        val path = "E:\\ACodeSpace\\Stream-NEUQ-ACM-weekly-report-24";
        val weekName = "week2";
        val checker = new WeeklyChecker(path,
                                        weekName,
                                        new Constraint("周报未完成", findFile("周报")),
                                        new Constraint("任务未完成", findFile(".java")));
        System.out.println(checker.getCheckReport());
    }
}