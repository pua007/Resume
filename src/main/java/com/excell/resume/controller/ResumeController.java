package com.excell.resume.controller;

import com.excell.resume.model.Career;
import com.excell.resume.model.Education;
import com.excell.resume.model.PersonInfo;
import com.excell.resume.view.ResumeView;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.sql.rowset.RowSetFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ResumeController {

    public static void main(String[] args) throws IOException {
        ResumeView resumeView = new ResumeView();

        List<Career> career = resumeView.inputCareerList();
        List<Education> education = resumeView.inputEducationList();
        PersonInfo personInfo = resumeView.inputPersonInfo();
        String Introduction = resumeView.inputSelfIntroduction();
        int rowIndex =3;
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet1 = workbook.createSheet("이력서");
        Sheet sheet2 = workbook.createSheet("자기소개서");

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        CellStyle wrapStyle = workbook.createCellStyle();
        wrapStyle.setWrapText(true);

        Row titleRow = sheet1.createRow(0);
        Cell image = titleRow.createCell(0);
        image.setCellValue("사진");
        Cell name = titleRow.createCell(1);
        name.setCellValue("이름");
        name.setCellStyle(style);
        Cell email = titleRow.createCell(2);
        email.setCellValue("이메일");
        email.setCellStyle(style);
        Cell address = titleRow.createCell(3);
        address.setCellValue("주소");
        address.setCellStyle(style);
        Cell phone = titleRow.createCell(4);
        phone.setCellValue("전화번호");
        phone.setCellStyle(style);
        Cell birthDate = titleRow.createCell(5);
        birthDate.setCellValue("생년월일");
        birthDate.setCellStyle(style);


        Row row2 = sheet1.createRow(1);
        sheet1.setColumnWidth(1, 2800);
        row2.setHeightInPoints(113);
        int pictureIdx = workbook.addPicture(personInfo.getPhoto(), Workbook.PICTURE_TYPE_JPEG);
        XSSFDrawing drawing = (XSSFDrawing) sheet1.createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor(0,0,0,0,0,1,1,2);
        XSSFPicture picture = drawing.createPicture(anchor, pictureIdx);
        picture.resize(1);
        Cell nameText = row2.createCell(1);
        nameText.setCellValue(personInfo.getName());
        Cell emailText = row2.createCell(2);
        emailText.setCellValue(personInfo.getEmail());
        Cell adrressText = row2.createCell(3);
        adrressText.setCellValue(personInfo.getAddress());
        Cell phoneNumber = row2.createCell(4);
        phoneNumber.setCellValue(personInfo.getPhoneNumber());
        Cell birthDateText = row2.createCell(5);
        birthDateText.setCellValue(personInfo.getBirthDate());


        Row row3 = sheet1.createRow(2);
        Cell graduationYear = row3.createCell(0);
        graduationYear.setCellValue("졸업년도");
        graduationYear.setCellStyle(style);
        Cell schoolName = row3.createCell(1);
        schoolName.setCellValue("학교명");
        schoolName.setCellStyle(style);
        Cell major = row3.createCell(2);
        major.setCellValue("전공");
        major.setCellStyle(style);
        Cell graduationStatus = row3.createCell(3);
        graduationStatus.setCellValue("졸업여부");
        graduationStatus.setCellStyle(style);

        for(Education e : education){
            Row row4 = sheet1.createRow(rowIndex++);
            Cell graduationYearText = row4.createCell(0);
            graduationYearText.setCellValue(e.getGraduationYear());
            Cell schoolNameText = row4.createCell(1);
            schoolNameText.setCellValue(e.getSchoolName());
            Cell majorText = row4.createCell(2);
            majorText.setCellValue(e.getMajor());
            Cell graduationStatusText = row4.createCell(3);
            graduationStatusText.setCellValue(e.getGraduationStatus());
        }

        Row row5 = sheet1.createRow(rowIndex++);
        Cell workPeriod = row5.createCell(0);
        workPeriod.setCellValue("근무기간");
        workPeriod.setCellStyle(style);
        Cell companyName = row5.createCell(1);
        companyName.setCellValue("근무처");
        companyName.setCellStyle(style);
        Cell jobTile = row5.createCell(2);
        jobTile.setCellValue("담당업무");
        jobTile.setCellStyle(style);
        Cell employmentYears = row5.createCell(3);
        employmentYears.setCellValue("근속연수");
        employmentYears.setCellStyle(style);

        for(Career c : career){
            Row row6 = sheet1.createRow(rowIndex++);
            Cell workPeriodText = row6.createCell(0);
            workPeriodText.setCellValue(c.getWorkPeriod());
            Cell companyNameText = row6.createCell(1);
            companyNameText.setCellValue(c.getCompanyName());
            Cell jobTileText = row6.createCell(2);
            jobTileText.setCellValue(c.getJobTile());
            Cell employmentYearsText = row6.createCell(3);
            employmentYearsText.setCellValue(c.getEmploymentYears());
        }

        for(int i = 0 ; i <= 5 ; i++){
            sheet1.autoSizeColumn(i);
        }

        Row introductionRow = sheet2.createRow(0);
        Cell introductionText = introductionRow.createCell(0);
        introductionText.setCellValue(Introduction);
        introductionText.setCellStyle(wrapStyle);

        FileOutputStream fileOut = new FileOutputStream("resume.xlsx");
        workbook.write(fileOut);
        workbook.close();
    }
}
