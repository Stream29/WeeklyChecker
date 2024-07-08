package com.stream.weeklychecker;

import lombok.val;

import static com.stream.weeklychecker.Constraint.findFile;

public class Main
{
    public static void main(String[] args)
    {
        val path = "E:\\ACodeSpace\\IDEA\\Stream-weekly-report-24";
        val weekName = "week5";
        System.out.println("周报检查");
        val checker = new WeeklyChecker(path,
                                        weekName,
                                        new Constraint("周报未完成", findFile("周报")),
                                        new Constraint("任务未完成",
                                                       findFile("pom.xml")
                                                               .or(findFile("build.gradle"))
                                                               .or(findFile(".js"))));
        System.out.println(checker.getCheckReport());
//        expandWithSelf(new File(path)).forEach(System.out::println);
    }
}