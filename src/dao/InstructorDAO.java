package dao;

import model.Instructor;
import org.hibernate.Session;
import util.LoggerUtil;

import java.util.Scanner;
import java.util.logging.Logger;

import static util.HibernateUtil.getSessionFactory;

public class InstructorDAO extends BaseDAO{
    private static final Logger logger = LoggerUtil.getLogger();
    public void addInstructor(Instructor instructor) {

        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter instructor id: ");
//        instructor.setInstructorId(scanner.nextInt());
        System.out.println("Enter instructor name: ");
        instructor.setInstructorName(scanner.nextLine());
        System.out.println("Enter instructor email: ");
        instructor.setInstructorEmail(scanner.nextLine());
        System.out.println("Enter instructor department: ");
        instructor.setInstructorDepartment(scanner.nextLine());
        System.out.println("Instructor phone number: ");
        instructor.setInstructorPhone(scanner.nextLine());
        insertData(instructor);
    }
    public Instructor getInstrutorById(int instructorId){
        Instructor instructor = null;
        try(Session session = getSessionFactory().openSession()) {
            instructor = session.get(Instructor.class, instructorId);
        }catch (Exception e){
            logger.severe("Error fetching instructor by id: " + e.getMessage());
            e.printStackTrace();
        }
        return instructor;

    }
    public void updateCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter instructor id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Instructor instructor = getInstrutorById(id);
        if (instructor == null){
            System.out.println("Instructor not found with id: " + id);
            return;
        }

        System.out.println("Enter instructor name (Leave blank to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()){
            instructor.setInstructorName(newName);
        }

        System.out.println("Enter instructor email (Leave blank to keep current): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()){
            instructor.setInstructorEmail(newEmail);
        }

        System.out.println("Enter instructor department (Leave blank to keep current): ");
        String newDepartment = scanner.nextLine();
        if (!newDepartment.isEmpty()){
            instructor.setInstructorDepartment(newDepartment);
        }

        System.out.println("Enter instructor phone number (Leave blank to keep current): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()){
            instructor.setInstructorPhone(newPhone);
        }

        updateData(instructor);

    }

    public void deleteInstructor(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter instructor id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Instructor instructor = getInstrutorById(id);
        if (instructor == null){
            System.out.println("Instructor not found with id: " + id);
            return;
        }
        deleteData(instructor);
    }

    public void viewAllInstructors(){
        try(Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("from Instructor", Instructor.class).list().forEach(System.out::println);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.severe("Error fetching all instructors: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public void viewInstructorById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter instructor id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Instructor instructor = getInstrutorById(id);
        if (instructor == null){
            System.out.println("Instructor not found with id: " + id);
            return;
        }
        System.out.println(instructor);
    }

    public static void main(String[] args) {
        InstructorDAO instructorDAO = new InstructorDAO();
        Instructor instructor = new Instructor();

        while (true){
            System.out.println("1. Add Instructor\n2. Update Instructor\n3. Delete Instructor\n4. View All Instructors\n5. View Instructor by ID\n6. Exit");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            switch (scanner.nextInt()){
                case 1:
                    instructorDAO.addInstructor(instructor);
                    break;
                case 2:
                    instructorDAO.updateCourse();
                    break;
                case 3:
                    instructorDAO.deleteInstructor();
                    break;
                case 4:
                    instructorDAO.viewAllInstructors();
                    break;
                case 5:
                    instructorDAO.viewInstructorById();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
