package io.kohnkong.title08;

import io.kimmking.aop.ISchool;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 19:20
 */
@Service
public class School implements ISchool {

    private Klass class1;

    private Student student100;

    public Klass getClass1() {
        return class1;
    }

    public void setClass1(Klass class1) {
        this.class1 = class1;
    }

    public Student getStudent100() {
        return student100;
    }

    public void setStudent100(Student student100) {
        this.student100 = student100;
    }

    @Override
    public void ding() {

        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);

    }
}
