package io.kohnkong.title08;

import lombok.Data;

import java.util.List;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 19:53
 */
@Data
public class Klass {

    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }

}