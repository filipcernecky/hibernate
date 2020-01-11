package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] argz) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query students, using .list()
            List<Student> theStudents = session.createQuery("from Student").list();

            // display students
            displayStudents(theStudents);

            // query students: LastName='Tam', using .getResultList()
            theStudents = session.createQuery("from Student s where s.lastName='Tam'").getResultList();

            // display students
            System.out.println("\n\nStudents who have last name of Tam");
            displayStudents(theStudents);

            // query students: lastName='Tam' OR firstName='Ken'
            theStudents = session.createQuery("from Student s where"
                            + " s.lastName='Tam' OR s.firstName='Ken'").getResultList();

            // display students
            System.out.println("\n\nStudents who have last name of Tam or first name of Ken");
            displayStudents(theStudents);

            // query students where email like '%luv2code.com'
            theStudents = session.createQuery("from Student s where"
                    + " s.email LIKE '%luv2code.com'").getResultList();

            // display students
            System.out.println("\n\nStudents who have an email ending with love2code.com");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    // display students method
    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}

