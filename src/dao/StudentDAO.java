package dao;

import model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;
import util.LoggerUtil;

import java.util.Scanner;
import java.util.logging.Logger;

public class StudentDAO extends BaseDAO {
    private static final Logger logger = LoggerUtil.getLogger();
    public void addStudent(Student student) {
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter student ID: ");
//        student.setStudentId(scanner.nextInt());
//        scanner.nextLine(); // Consume newline

        System.out.print("Enter student name: ");
        student.setStudentName(scanner.nextLine());

        System.out.print("Enter student email: ");
        student.setStudentEmail(scanner.nextLine());

        System.out.print("Enter student phone: ");
        student.setStudentPhone(scanner.nextLine());

        System.out.print("Enter student enrollment date (YYYY-MM-DD): ");
        student.setEnrollmentDate(scanner.nextLine());

        insertData(student);
    }
    public Student getStudentById(int studentId) {
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Fetch the student by ID
            student = session.get(Student.class, studentId);
        } catch (HibernateException e) {
            logger.severe("Error fetching student with ID: " + studentId + " - " + e.getMessage());
            e.printStackTrace();
        }

        return student;
    }

    public void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        System.out.print("Enter new student name (leave blank to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            student.setStudentName(newName);
        }

        System.out.print("Enter new student email (leave blank to keep current): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            student.setStudentEmail(newEmail);
        }

        // You can similarly update other fields if needed
        // For example:
        System.out.print("Enter new student phone (leave blank to keep current): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            student.setStudentPhone(newPhone);
        }

        System.out.print("Enter new student enrollment date (leave blank to keep current): ");
        String newEnrollmentDate = scanner.nextLine();
        if (!newEnrollmentDate.isEmpty()) {
            student.setEnrollmentDate(newEnrollmentDate);
        }

        // Update the student in the database
        updateData(student);

    }

    public void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline


        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }

        // Delete the student from the database
        deleteData(student);
    }

    public void viewAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Fetch all students
            session.beginTransaction();
            session.createQuery("from Student", Student.class).list().forEach(System.out::println);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.severe("Error fetching all students - " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void viewStudentById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
        } else {
            System.out.println(student);
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        System.out.println("1. Add student");
        System.out.println("2. Update student");
        System.out.println("3. Delete student");
        System.out.println("4. View all students");
        System.out.println("5. View student by ID");
        System.out.println("6. Exit");
        StudentDAO studentDAO = new StudentDAO();
        Student student = new Student();
        switch (scanner.nextInt()) {
            case 1:

                studentDAO.addStudent(student);
                break;
            case 2:

                studentDAO.updateStudent();
                break;
            case 3:
                studentDAO.deleteStudent();
                break;
            case 4:
                studentDAO.viewAllStudents();
                break;
            case 5:
                viewStudentById();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
