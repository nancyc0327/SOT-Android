package com.ccfl.mytestapplication;

import java.util.ArrayList;

/**
 * Created by ncai2 on 5/4/17.
 */

class PageInfo {

    String mainDirName = "";
    String secondDirName = "";
    String pageFileName = "";

    PageInfo(String firstDir, String secondDir, String fileName)
    {
        mainDirName = firstDir;
        secondDirName = secondDir;
        pageFileName = fileName;
    }
}

class DirectoryModel{
    private PageInfo[] pages = new PageInfo[50];
    private int size = 0;

    private void addPage(String firstDir, String secondDir, String fileName)
    {
        pages[size] = new PageInfo(firstDir,secondDir,fileName);
        size++;
    }

    DirectoryModel()
    {
        addPage("Developmental Milestones by Age","Read First","disclaimer.html");
        addPage("Developmental Milestones by Age","0-3 Months","0-3.html");
        addPage("Developmental Milestones by Age","3-6 Months","3-6.html");
        addPage("Developmental Milestones by Age","6-9 Months","6-9.html");
        addPage("Developmental Milestones by Age","9-12 Months","9-12.html");
        addPage("Developmental Milestones by Age","12-18 Months","12-18.html");
        addPage("Developmental Milestones by Age","19-24 Months","2-yr.html");
        addPage("Developmental Milestones by Age","3 Years","3-yr.html");
        addPage("Developmental Milestones by Age","4 Years","4-yr.html");
        addPage("Developmental Milestones by Age","5 Years","5-yr.html");

        addPage("Support Growth and Learning","0-18 Months","Growth-Learning-0-18.html");
        addPage("Support Growth and Learning","19-24 Months","Growth-Learning-18-3.html");
        addPage("Support Growth and Learning","3-5 Years","Growth-Learning-3-5.html");

        addPage("Keep Your Child Healthy","0-18 Months","Child-Health-0-18.html");
        addPage("Keep Your Child Healthy","18 Months - 3 Years","Child-Health-18-3.html");
        addPage("Keep Your Child Healthy","3-5 Years","Child-Health-3-5.html");

        addPage("Keep Your Child Safe","0-18 Months","Child-Safe-0-18.html");
        addPage("Keep Your Child Safe","18 Months - 3 Years","Child-Safe-18-3.html");
        addPage("Keep Your Child Safe","3-5 Years","Child-Safe-3-5.html");

        addPage("Kindergarten","What does school readiness mean?","readiness.html");
        addPage("Kindergarten","When can my child start kindergarten?","starting_K.html");
        addPage("Kindergarten","How do I know if my child is ready?","know_ready.html");
        addPage("Kindergarten","What will kindergarten be like?","be_like.html");
        addPage("Kindergarten","Should my child start now or next year?","now_next_yr.html");
        addPage("Kindergarten","How can I help my child be ready?","help_child_ready.html");
        addPage("Kindergarten","How can I prepare for the first day?","first_day.html");
        addPage("Kindergarten","How can I support my child's education?","support_education.html");
        addPage("Kindergarten","About","kinder_about.html");

    }

    ArrayList<String> getSubDirName(String mainDirName)
    {
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            if (mainDirName.equals(pages[i].mainDirName))
                list.add(pages[i].secondDirName);
        }
        return list;
    }

    ArrayList<String> getFileNameList(String mainDirName)
    {
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            if (mainDirName.equals(pages[i].mainDirName))
                list.add(pages[i].pageFileName);
        }
        return list;
    }

}
