import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String URL =
            "jdbc:mysql://localhost:3306/sql_practise";
    private static final String USER = "jdbc_student";

    // Set MYSQL_PASSWORD in your IntelliJ run configuration.
    private static final String PASSWORD = System.getenv("MYSQL_PASSWORD");

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public int addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students(name,email,age,course,course_id) " +
                "VALUES(?,?,?,?,?)";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getCourse());
            ps.setInt(5, student.getCourseId());

            return ps.executeUpdate();
        }
    }

    public int updateName(String name, int id) throws SQLException {
        String sql = "UPDATE students SET name=? WHERE id=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, id);

            return ps.executeUpdate();
        }
    }

    public int updateCourse(String course, int courseId, int id)
            throws SQLException {
        String sql = "UPDATE students SET course=?,course_id=? WHERE id=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, course);
            ps.setInt(2, courseId);
            ps.setInt(3, id);

            return ps.executeUpdate();
        }
    }

    public int updateAge(int age, int id) throws SQLException {
        String sql = "UPDATE students SET age=? WHERE id=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, age);
            ps.setInt(2, id);

            return ps.executeUpdate();
        }
    }

    public int deleteByName(String name) throws SQLException {
        String sql = "DELETE FROM students WHERE name=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            return ps.executeUpdate();
        }
    }

    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public int deleteByCourse(String course) throws SQLException {
        String sql = "DELETE FROM students WHERE course=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, course);
            return ps.executeUpdate();
        }
    }

    public int deleteByCourseId(int courseId) throws SQLException {
        String sql = "DELETE FROM students WHERE course_id=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            return ps.executeUpdate();
        }
    }

    public int deleteByAge(int age) throws SQLException {
        String sql = "DELETE FROM students WHERE age=?";

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, age);
            return ps.executeUpdate();
        }
    }

    public List<Student> showStudentList() throws SQLException {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Student student = new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getInt("age"),
                        result.getString("course"),
                        result.getInt("course_id")
                );

                students.add(student);
            }
        }

        return students;
    }
}