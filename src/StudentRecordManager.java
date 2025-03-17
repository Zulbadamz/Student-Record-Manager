import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StudentRecordManager {
    private TreeMap<Integer, Student> studentRecords;
    private Scanner scanner;

    // Constructor
    public StudentRecordManager() {
        studentRecords = new TreeMap<>();
        scanner = new Scanner(System.in);
    }

    // Method to add a student record
    public void addStudent(int id, String name, double gpa) {
        if (studentRecords.containsKey(id)) {
            System.out.println("Error: Student ID already exists.");
            return;
        }
        studentRecords.put(id, new Student(id, name, gpa));
        System.out.println("Student added successfully!");
    }

    // Method to delete a student record
    public void deleteStudent(int id) {
        if (studentRecords.remove(id) != null) {
            System.out.println("Student record deleted.");
        } else {
            System.out.println("Error: Student ID not found.");
        }
    }

    // Method to update a student's GPA
    public void updateGpa(int id, double newGpa) {
        Student student = studentRecords.get(id);
        if (student != null) {
            student.setGpa(newGpa);
            System.out.println("GPA updated successfully.");
        } else {
            System.out.println("Error: Student ID not found.");
        }
    }

    // Method to display all student records sorted by ID
    public void displayAllStudents() {
        if (studentRecords.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }
        System.out.println("\nStudent Records (Sorted by ID):");
        for (Map.Entry<Integer, Student> entry : studentRecords.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    // Method to find students with GPA above a certain value
    public void findStudentsAboveGpa(double minGpa) {
        boolean found = false;
        System.out.println("\nStudents with GPA above " + minGpa + ":");
        for (Student student : studentRecords.values()) {
            if (student.getGpa() > minGpa) {
                System.out.println(student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found with GPA above " + minGpa);
        }
    }

    // Main method to test functionality
    public static void main(String[] args) {
        StudentRecordManager manager = new StudentRecordManager();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update GPA");
            System.out.println("4. Display All Students");
            System.out.println("5. Find Students with GPA Above");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter GPA: ");
                    double gpa = scanner.nextDouble();
                    manager.addStudent(id, name, gpa);
                    break;

                case 2:
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteStudent(deleteId);
                    break;

                case 3:
                    System.out.print("Enter Student ID to update GPA: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new GPA: ");
                    double newGpa = scanner.nextDouble();
                    manager.updateGpa(updateId, newGpa);
                    break;

                case 4:
                    manager.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter GPA threshold: ");
                    double threshold = scanner.nextDouble();
                    manager.findStudentsAboveGpa(threshold);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
