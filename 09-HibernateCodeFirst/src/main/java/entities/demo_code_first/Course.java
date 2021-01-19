package entities.demo_code_first;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    private String name;
    private Set<Student> students;

    public Course() {
    }

    public String getName() {
        return name;
    }

    @Column(name = "name", nullable = false, unique = true)
    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
    )
            public Set<Student>getStudents(){
            return students;
            }

            public void setStudents(Set < Student > students){
            this.students=students;
    }
}
