package dao;

import model.Course;
import org.hibernate.Session;
import util.HibernateUtil;
import util.LoggerUtil;

import java.util.Scanner;
import java.util.logging.Logger;

public class CourseDAO extends BaseDAO{
    private static final Logger logger = LoggerUtil.getLogger();
    public void addCourse(Course course) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter course id: ");
//        course.setCourseId(scanner.nextInt());
        System.out.println("Enter course name: ");
        course.setCourseName(scanner.nextLine());
        System.out.println("Enter course credits: ");
        course.setCreditHours(scanner.nextInt());
        System.out.println("Enter course instructor id: ");
        course.setInstructorId(scanner.nextInt());

        insertData(course);
    }
    public Course getCoursesById(int courseId){
        Course course = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            course = session.get(Course.class, courseId);
        }catch (Exception e){
            logger.severe("Error fetching course with id: " + courseId);
            e.printStackTrace();

        }
        return course;
    }
    public void updateCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        Course course = getCoursesById(courseId);
        if(course == null){
            System.out.println("Course not found with id: " + courseId);
            return;
        }
        System.out.println("Enter course name(leave blank to keep current: ");
        String courseName = scanner.nextLine();
        if (!courseName.isEmpty()) {
            course.setCourseName(courseName);
        }
        System.out.println("Enter course credits(leave blank to keep current: ");
        String credits = scanner.nextLine();
        if (!credits.isEmpty()) {
            course.setCreditHours(Integer.parseInt(credits));
        }

        updateData(course);
    }

    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();
        Course course = new Course();
        System.out.println("1. Add course\n2. Update course\n3. Delete course\n4. Get course by id\n5. Get all courses\n6. Exit");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()){
            case 1:
                courseDAO.addCourse(course);
                break;
            case 2:
                courseDAO.updateCourse();
                break;
            case 3:
                courseDAO.deleteCourse();
                break;
            case 4:
                courseDAO.viewCourseById();
                break;
            case 5:
                courseDAO.viewAllCourses();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void deleteCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id: ");
        int courseId = scanner.nextInt();
        Course course = getCoursesById(courseId);
        if(course == null){
            System.out.println("Course not found with id: " + courseId);
            return;
        }
        deleteData(course);
    }

    private void viewAllCourses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("from Course", Course.class).list().forEach(System.out::println);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.severe("Error fetching courses");
            e.printStackTrace();

        }
    }

    private void viewCourseById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id: ");
        int courseId = scanner.nextInt();
        Course course = getCoursesById(courseId);
        if(course == null){
            System.out.println("Course not found with id: " + courseId);
            return;
        }
        System.out.println(course);
    }

}
