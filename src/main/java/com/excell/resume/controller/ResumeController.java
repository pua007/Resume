package com.excell.resume.controller;

import com.excell.resume.model.Career;
import com.excell.resume.model.Education;
import com.excell.resume.model.PersonInfo;
import com.excell.resume.view.ResumeView;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
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


        Row dataRow = sheet1.createRow(7 );
        String photoFileName = "kidong.jpg";

        //try를 걸어준 이유는 만일 파일이 없거나 잘못된 이름일 경우 예외처리를 하기위해
        try(InputStream photoStream = new FileInputStream(photoFileName)) {//파일에서 읽어오는 빨때를 꼽는 동작
            BufferedImage originalImage = ImageIO.read(photoStream);//그 빨대로 이지를 읽어오는 부분, ImageIO.read()의 반환타입 BufferedImage이다

            //증명사진 크기로 이미지를 조절한다(가로: 35mm, 세로: 45mm)
            int newwidth = (int) (35 * 2.83465);
            int newHeight = (int) (45 * 2.83465);
            //원래 사진의 크기를 조절해서 만드는거
            Image resizedImage = originalImage.getScaledInstance(newwidth, newHeight, Image.SCALE_SMOOTH);//getScaledInstance()는 이미지를 리사이즈하는 메서드인데, 결과를 Image 타입으로 돌려줘서 다시 BufferedImage로 바꿔야 해.
            //비어 있는 새 그림판을 만드는 거 크기는 방금 계산한 크기로 투명 배경도 만들수있음
            BufferedImage resizedBufferedImage = new BufferedImage(newwidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR);
            //새 그림판에 그림을 글릴 수 있는 연필을 준비하는 것
            Graphics2D g2d = resizedBufferedImage.createGraphics();
            //새그림을 그림판에 그리는 해위 (그림판이름, 왼쪽 위 모서리부터 그림을 그리하는 뜻,?)
            g2d.drawImage(resizedImage, 0, 0, null);
            //다 그렸으면 연필을 정리하고 치우라는 것
            g2d.dispose();

            //조절된 이미지를 바이트배열로 변환합니다
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedBufferedImage, "jpg", baos);
            byte[] imageBytes = baos.toByteArray();
            int imageIndex = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_JPEG);
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

}
