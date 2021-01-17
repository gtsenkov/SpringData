package course.springdata.hibernateintro;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import course.springdata.hibernateintro.entity.Student;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class HibernateIntoMain {
    public static void main(String[] args) {
        //Create Hibernate config
        Configuration cfg = new Configuration();
        cfg.configure();

        //Create SessionFactory
        SessionFactory sf = cfg.buildSessionFactory();


        //Create Hibernate Session
        Session session = sf.openSession();

        //Persist an entity
        Student student = new Student("Gosho Georgiev");
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();

        //Read entity by Id
        session.beginTransaction();
        session.setHibernateFlushMode(FlushMode.MANUAL);
       // Student result = session.get(Student.class, 1L, LockMode.READ);
        Optional<Student> result = session.byId(Student.class).loadOptional(11L);
        session.getTransaction().commit();
        System.out.printf("Student: %s", result.orElse( null));

        //List all students using HQL

        session.beginTransaction();
        session.createQuery("FROM Student ", Student.class)
                .setFirstResult(5)
                .setMaxResults(15)
                .stream().limit(5).forEach(System.out::println);

        System.out.println("\n----------------------");

        session.createQuery("FROM Student WHERE name =:name", Student.class)
                .setParameter("name", "Gosho Georgiev")
                .stream().forEach(System.out::println);

        //Type-safe criteria queries
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> Student_ = query.from(Student.class);
        query.select(Student_).where(cb.like(Student_.get("name"), "G%"));
        session.createQuery(query).getResultStream()
                .forEach(System.out::println);


        //Close session
        session.close();

    }
}
