package wg.app.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.model.converters.AdminJsonConverter;
import wg.app.model.converters.StudentJsonConverter;
import wg.app.model.converters.TeacherJsonConverter;
import wg.app.model.dto.StudentDto;
import wg.app.model.dto.TeacherDto;
import wg.app.model.user.Admin;
import wg.app.model.user.Teacher.Dashboard;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class DataManagement
{
    private static final String URL = "jdbc:mysql://localhost:3306/eLearningPlatform?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";


    private final AdminService adminService;
    private final TeacherService teacherService;
    private final StudentService studentService;



    public void deleteAllData()
    {

        try (Connection connection =
                     DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String[] sql = {"DROP TABLE advertisement","DROP TABLE certificates","DROP TABLE courses","DROP TABLE courses_certificates","DROP TABLE invitation","DROP TABLE lessons",
            "DROP TABLE meetings","DROP TABLE message_logged", "DROP TABLE message_un_logged", "DROP TABLE opinions","DROP TABLE students","DROP TABLE subjects", "DROP TABLE teachers",
            "DROP TABLE user","DROP TABLE hibernate_sequence"};

            Statement statement = connection.createStatement();

            for (String s : sql)
            {
                statement.execute(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void init()
    {
        String[] teachers = {"teacher1.json","teacher2.json"};
        String[] students = {"student1.json","student2.json","student3.json","student4.json"};


        for (String t : teachers)
        {
            TeacherJsonConverter teacherJsonConverter = new TeacherJsonConverter(t);
            TeacherDto teacherDto = teacherJsonConverter.fromJson().get();
            teacherDto.setDashboard(Dashboard
                    .builder()
                    .visitorsAge(new ArrayList<>())
                    .visitorMale(0)
                    .visitorFemale(0)
                    .build()
            );

            teacherService.update(teacherDto);
        }

        for (String s : students)
        {
            StudentJsonConverter studentJsonConverter = new StudentJsonConverter(s);
            StudentDto studentDto = studentJsonConverter.fromJson().get();
            studentService.update(studentDto);
        }

        AdminJsonConverter adminJsonConverter = new AdminJsonConverter("admin.json");

        Admin admin = adminJsonConverter.fromJson().get();

        adminService.addAdmin(admin);

    }
}
