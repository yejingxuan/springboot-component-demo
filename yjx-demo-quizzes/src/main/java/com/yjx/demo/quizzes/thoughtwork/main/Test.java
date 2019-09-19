package com.yjx.demo.quizzes.thoughtwork.main;

public class Test {

    public static void main(String[] args) {
        String downDocUrl = "ftp:文档.docx";

        String name = downDocUrl.substring(downDocUrl.lastIndexOf("/")+1, downDocUrl.length());

        System.out.println(name);
        System.out.println(downDocUrl.substring(0, downDocUrl.lastIndexOf("/")).concat("/").concat(name));
    }

}
