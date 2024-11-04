import java.util.*;

class Student {
    String name;
    String course;
    int grade;

    public Student(String name, String course, int grade) {
        this.name = name;
        this.course = course;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getGrade() {
        return grade;
    }
}

public class StudentRating {

    public String getStudentRating(String studentData, String courseInfo) {
        String[] studentEntries = studentData.split(";");
        String[] courseDetails = courseInfo.split(",");
        String targetCourse = courseDetails[0];
        int passingGrade = Integer.parseInt(courseDetails[1]);

        List<Student> students = new ArrayList<>();

        for (String entry : studentEntries) {
            String[] parts = entry.split(",");
            String name = parts[0];
            String course = parts[1];
            int grade = Integer.parseInt(parts[2]);

            students.add(new Student(name, course, grade));
        }

        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getCourse().equals(targetCourse) && student.getGrade() > passingGrade) {
                filteredStudents.add(student);
            }
        }

        filteredStudents.sort((s1, s2) -> Integer.compare(s2.getGrade(), s1.getGrade()));

        StringBuilder result = new StringBuilder();
        if (filteredStudents.isEmpty()) {
            result.append("Никто");
        } else {
            for (Student student : filteredStudents) {
                if (!result.isEmpty()) {
                    result.append("\n");
                }
                result.append(student.getName()).append(",").append(student.getGrade());
            }
        }

        return result.toString();
    }
}