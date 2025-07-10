package com.excell.resume.view;

import com.excell.resume.model.Career;
import com.excell.resume.model.Education;
import com.excell.resume.model.PersonInfo;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class ResumeView {

    Scanner scanner = new Scanner(System.in);

    public List<Career> inputCareerList(){
        List<Career> careerList = new ArrayList<>();

        while(true) {
            System.out.println("경력 정보를 입력해 주세요.");
            System.out.println("------------------------------");

            System.out.print("근무기간: ");
            String workPeriod = scanner.nextLine();

            System.out.print("근무처: ");
            String companyName = scanner.nextLine();

            System.out.print("담당업무: ");
            String jobTile = scanner.nextLine();

            System.out.print("근속연수: ");
            String employmentYear = scanner.nextLine();

            Career career = new Career(workPeriod, companyName, jobTile, employmentYear);
            careerList.add(career);

            System.out.println("다른 경력 정보를 더 입력하시겠습니까? (Y/N): ");
            String more = scanner.nextLine().trim();
            if(!more.equalsIgnoreCase("Y")){
                break;
            }
        }

        return careerList;
    }

    public List<Education> inputEducationList(){
        List<Education> educationsList = new ArrayList<>();
        while (true) {
            System.out.println("학력 정보를 입려해 주세요.");
            System.out.println("--------------------------------");

            System.out.print("졸업년도: ");
            String graduationYear = scanner.nextLine();

            System.out.print("출신학교: ");
            String schoolName = scanner.nextLine();

            System.out.print("전공: ");
            String major = scanner.nextLine();

            System.out.print("졸업학점: ");
            String graduationStatus = scanner.nextLine();

            Education education =new Education(graduationYear, schoolName, major, graduationStatus);
            educationsList.add(education);

            System.out.println("다른 학력 정보를 더 입력하시겠습니까? (Y/N): ");
            String more = scanner.nextLine().trim();
            if(!more.equalsIgnoreCase("Y")){
                break;
            }
        }
        return educationsList;
    }

    public PersonInfo inputPersonInfo() throws IOException {

        System.out.println("개인정보를 입력해주세요.");
        System.out.println("---------------------------------");

        System.out.println("사진의 경로를 입력해주세요: ");
        byte[] photo;
        String email;
        while (true) {
            String url = scanner.nextLine();
            Path path = Paths.get(url);
            if (Files.exists(path)) {
                photo = Files.readAllBytes(path);
                break;
            } else {
                System.out.println("이미지파일이 존재하지 않습니다.");
                continue;
            }
        }

        System.out.println("이름을 입력해 주세요: ");
        String name = scanner.nextLine();

        System.out.println("E-mail주소를 입력해 주세요: ");
        while(true) {
            email = scanner.nextLine().trim();
            if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
                break;
            }else{
                System.out.println("이메일 형식이 맞지않습니다. 다시 입력해 주세요.");
                continue;
            }
        }
        System.out.println("현재 거주하고있는 주소를 입력해 주세요: ");
        String address = scanner.nextLine();

        System.out.println("핸드폰 번호를 입력해 주세요: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("생년월일을 입력해주세요: ");
        String birthDate = scanner.nextLine();

        return new PersonInfo(photo, name, email, address, phoneNumber, birthDate);
    }

    public String inputSelfIntroduction() throws IOException {
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder introduction = new StringBuilder();
        String line;
        int confirm;

        System.out.println("자기소개서를 입력하세요.(입력이 끝나면 \"END\"를 입력하고 엔터를 눌러주세요.)");

        while((line = reader.readLine()) != null){
            if(line.equals("END")){
                System.out.println("입력한 자기소개서:\n"+introduction.toString());
                System.out.print("입력한 자기소개서 수정사항이 없으면 \"1\"을 수정사항이 있으면 \"2\"를 입력해 주세요: ");
                confirm = scanner.nextInt();
                scanner.nextLine();
                if(confirm == 1){break;
                }else{
                    introduction.setLength(0);
                    System.out.println("다시 입력해주세요(일력이 끝나면 \"END\"를 입력하고 엔터를 눌러주세요.)):");
                    continue;
                }
            }

            introduction.append(line).append("\n");
        }

        return introduction.toString();
    }

}
