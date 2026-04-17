package com.karapada.school.util;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.karapada.school.entity.Student;
import com.karapada.school.entity.academic.Attendance;
import com.karapada.school.entity.academic.Marks;

public class PdfGenerator {

    private static final String REPORT_PATH = "reports/";

    public static String generate(
            Student student,
            List<Attendance> attendance,
            List<Marks> marks) {

        try {
            File dir = new File(REPORT_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = REPORT_PATH + "Report_" + student.getId() + ".pdf";

         // Dummy PDF creation (replace with iText / OpenPDF later)
         FileOutputStream fos = new FileOutputStream(fileName);

         fos.write("Student Report\n".getBytes());
         fos.write(("Name: " + student.getFullName() + "\n").getBytes());
         fos.write(("Username: " + student.getUsername() + "\n").getBytes());
         fos.write(("Attendance Count: " + attendance.size() + "\n").getBytes());
         fos.write(("Marks Count: " + marks.size() + "\n").getBytes());

         fos.close();
         return fileName;


        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}
