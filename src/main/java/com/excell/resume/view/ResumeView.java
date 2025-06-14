package com.excell.resume.view;

import com.excell.resume.model.Career;
import com.excell.resume.model.Education;
import com.excell.resume.model.PersonInfo;
import java.util.Scanner;

public class ResumeView {

    Scanner scanner = new Scanner(System.in);

    public Career inputCareerList(){
        System.out.println("경력 정보를 입력해 주세요.");
        System.out.print("근무기간: ");
        String workPeriod = scanner.nextLine();

        System.out.print("근무처: ");
        String companyName = scanner.nextLine();

        System.out.print("담당업무: ");
        String jobTile = scanner.nextLine();

        System.out.print("근속연수: ");
        String employmentYear = scanner.nextLine();

        return new Career(workPeriod, companyName, jobTile, employmentYear);
    }

    public Education inputEducationList(){

        System.out.println("학력 정보를 입려해 주세요.");

        System.out.print("졸업년도: ");
        String graduationYear = scanner.nextLine();

        System.out.print("출신학교: ");
        String schoolName = scanner.nextLine();

        System.out.print("전공: ");
        String major = scanner.nextLine();

        System.out.print("졸업학점: ");
        String graduationStatus = scanner.nextLine();

        return new Education(graduationYear, schoolName, major, graduationStatus);
    }

    public PersonInfo inputPersonInfo(){

        System.out.println("개인정보를 입력해주세요.");

        System.out.println("사진의 경로를 입력해주세요.");
        String photo = scanner.nextLine();


        return new PersonInfo();
    }


}
