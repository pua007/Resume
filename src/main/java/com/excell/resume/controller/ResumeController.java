package com.excell.resume.controller;

import com.excell.resume.model.Career;
import com.excell.resume.model.Education;
import com.excell.resume.model.PersonInfo;
import com.excell.resume.view.ResumeView;
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

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet1 = workbook.createSheet("이력서");
        Sheet sheet2 = workbook.createSheet("자기소개서");

        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font = workbook.createFont();

        Row titleRow = sheet1.createRow(0);
        Cell image = titleRow.createCell(0);
        image.setCellValue("사진");
        Cell name = titleRow.createCell(1);
        name.setCellValue("이름");
        Cell email = titleRow.createCell(2);
        name.setCellValue("이메일");
        Cell address = titleRow.createCell(3);
        name.setCellValue("주소");
        Cell phone = titleRow.createCell(4);
        name.setCellValue("전화번호");
        Cell birthDate = titleRow.createCell(5);
        name.setCellValue("생년월일");


        Row row2 = sheet1.createRow(1);
        sheet1.setColumnWidth(1, 2800);
        row2.setHeightInPoints(113);
        int pictureIdx = workbook.addPicture(personInfo.getPhoto(), Workbook.PICTURE_TYPE_JPEG);
        XSSFDrawing drawing = (XSSFDrawing) sheet1.createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor(0,0,0,0,0,1,1,2);
        XSSFPicture picture = drawing.createPicture(anchor, pictureIdx);
        picture.resize();
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
        Cell graduationDate = row3.createCell(0);
        image.setCellValue("졸업년도");
        Cell schoolName = row3.createCell(1);
        name.setCellValue("학교명");
        Cell major = row3.createCell(2);
        name.setCellValue("전공");
        Cell graduationStatus = row3.createCell(3);
        name.setCellValue("졸업여부");











    }
}
