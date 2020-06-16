package com.yjx.demo.quizzes;

import lombok.Data;

public class ReferTest {

    public static void main(String[] args) {
        ReferTest referTest = new ReferTest();
        User user = new User();
        Job job = new Job();
        job.setJobName("程序员");
        user.setName("张三");
        user.setJob(job);

        System.out.println(user.toString());
        referTest.changeUser(user);
        System.out.println(user.toString());
    }

    public void changeUser(User user) {
        user.setName("李四");
        user.getJob().setJobName("公务员");
    }


}

@Data
class User implements Cloneable{
    private String name;
    private Job job;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        User userTemp = new User();
        userTemp.setName(this.name);
        return userTemp;
    }
}

@Data
class Job{
    private String jobName;
}
