public class Student {
    private final int id;
    private final String name;
    private final String email;
    private final int age;
    private final String course;
    private final int courseId;

    // Used when creating a new student; the database assigns the ID.
    public Student(String name, String email, int age, String course, int courseId) {
        this(0, name, email, age, course, courseId);
    }

    // Used when creating a Student from a database row.
    public Student(int id, String name, String email, int age, String course, int courseId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public int getCourseId() {
        return courseId;
    }
}