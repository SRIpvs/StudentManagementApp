import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("WELCOME TO THE STUDENT MANAGEMENT SYSTEM\n");

        Scanner s = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();

        while (true) {
            System.out.println("Press the number for the operation you want to perform:\n");
            System.out.println("1. Add a student");
            System.out.println("2. Update a student");
            System.out.println("3. Delete a student");
            System.out.println("4. Show the student list");
            System.out.println("5. Exit");

            int input = s.nextInt();

            if (input == 1) {
                System.out.println("Please enter the details one by one.");

                System.out.print("Name: ");
                String name = s.next();

                System.out.print("Email: ");
                String email = s.next();

                System.out.print("Age: ");
                int age = s.nextInt();

                s.nextLine();
                System.out.print("Course: ");
                String course = s.nextLine();

                System.out.print("Course ID: ");
                int courseId = s.nextInt();

                Student student = new Student(name, email, age, course, courseId);

                int rowInserted = studentDAO.addStudent(student);

                if (rowInserted == 1) {
                    System.out.println("Student added successfully!\n");
                }
            } else if (input == 2) {
                System.out.println("Please enter the number for what you want to update.");
                System.out.println("1. Name");
                System.out.println("2. Course");
                System.out.println("3. Age");

                int option = s.nextInt();
                int rowsUpdated;

                if (option == 1) {
                    System.out.print("Enter a name: ");
                    String name = s.next();

                    System.out.print("Enter an ID: ");
                    int id = s.nextInt();

                    rowsUpdated = studentDAO.updateName(name, id);

                    if (rowsUpdated == 1) {
                        System.out.println("Name updated successfully.");
                    }
                } else if (option == 2) {
                    s.nextLine();
                    System.out.print("Enter a course name: ");
                    String course = s.nextLine();

                    System.out.print("Enter a course ID: ");
                    int courseId = s.nextInt();

                    System.out.print("Enter a student ID: ");
                    int id = s.nextInt();

                    rowsUpdated = studentDAO.updateCourse(course, courseId, id);

                    if (rowsUpdated == 1) {
                        System.out.println("Course updated successfully.");
                    }
                } else if (option == 3) {
                    System.out.print("Enter an age: ");
                    int age = s.nextInt();

                    System.out.print("Enter an ID: ");
                    int id = s.nextInt();

                    rowsUpdated = studentDAO.updateAge(age, id);

                    if (rowsUpdated == 1) {
                        System.out.println("Age updated successfully.");
                    }
                } else {
                    System.out.println("Please select a valid option!");
                }
            } else if (input == 3) {
                System.out.println("How do you want to delete? Please select an option:");

                System.out.println("1. By name");
                System.out.println("2. By ID");
                System.out.println("3. By course");
                System.out.println("4. By course ID");
                System.out.println("5. By age");

                int option = s.nextInt();
                int rowsDeleted;

                if (option == 1) {
                    System.out.print("Enter a name: ");
                    String name = s.next();

                    rowsDeleted = studentDAO.deleteByName(name);
                } else if (option == 2) {
                    System.out.print("Enter an ID: ");
                    int id = s.nextInt();

                    rowsDeleted = studentDAO.deleteById(id);
                } else if (option == 3) {
                    s.nextLine();
                    System.out.print("Enter a course name: ");
                    String course = s.nextLine();

                    rowsDeleted = studentDAO.deleteByCourse(course);
                } else if (option == 4) {
                    System.out.print("Enter a course ID: ");
                    int courseId = s.nextInt();

                    rowsDeleted = studentDAO.deleteByCourseId(courseId);
                } else if (option == 5) {
                    System.out.print("Enter an age: ");
                    int age = s.nextInt();

                    rowsDeleted = studentDAO.deleteByAge(age);
                } else {
                    System.out.println("Please enter a valid option!\n");
                    continue;
                }

                if (rowsDeleted > 0) {
                    System.out.println("Deleted successfully!");
                    System.out.println(rowsDeleted + " row(s) deleted.\n");
                } else {
                    System.out.println("No matching student was found.\n");
                }
            } else if (input == 4) {
                List<Student> students = studentDAO.showStudentList();

                for (Student student : students) {
                    System.out.println("ID: " + student.getId());
                    System.out.println("Name: " + student.getName());
                    System.out.println("Email: " + student.getEmail());
                    System.out.println("Course: " + student.getCourse());
                    System.out.println("Age: " + student.getAge());
                    System.out.println("Course ID: " + student.getCourseId());
                }
            } else if (input == 5) {
                break;
            } else {
                System.out.println("Please press a valid operation number!");
            }
        }

        s.close();
    }
}