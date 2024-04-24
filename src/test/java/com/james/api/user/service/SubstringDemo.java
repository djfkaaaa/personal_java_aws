package com.james.api.user.service;

import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
public class SubstringDemo {
    @Test
    public void personalId(){
        String human1 = "970301-1";
        String human2 = "950101-2";
        String human3 = "020101-3";
        String human4 = "050101-4";
        String human5 = "730101-5";
        String human6 = "820101-6";
        String human7 = "120101-7";
        String human8 = "050101-8";

        String [] arr = {human1,human2,human3,human4,human5,human6,human7,human8};

        for (int i = 0; i < arr.length; i++) {
            System.out.println("\n" +"조회 대상 주민번호 " + arr[i]);
            gender(arr[i]);
            age(arr[i]);
        }
    }

    private static void gender(String arr) {
        StringTokenizer st = new StringTokenizer(arr,"-");
        while (st.hasMoreTokens()){
            String token = st.nextToken();
            if (token.length() == 1){
                int gender = Integer.parseInt(token);
                switch (gender){
                    case 1,3,7 -> System.out.println("성별은 남자");
                    case 2,4,6,8 -> System.out.println("성별은 여자");
                    case 5 -> System.out.println("외국인");
                }
            }
        }
    }

    private static void age(String arr){
        int year =LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        StringTokenizer st = new StringTokenizer(arr,"-");
        while (st.hasMoreTokens()){
            String token = st.nextToken();
            if (token.length() == 6) {
                int age = Integer.parseInt(token.substring(0,2));
                switch (age/10){
                    case 7,8,9 -> System.out.println("나이는 = " + (year-(age+1900)));
                    case 0,1 -> System.out.println("나이는 = " + (year-(age+2000)));
                }
            }
        }
    }


    private String genderTest(String ssn){
        return switch (ssn.charAt(7)){
            case '1','3'->"M";
            case '2','4'->"F";
            case '5'->"외국인";
            default -> "error";
        };
    }


    @Test
    public void getAge(){
        String ssn = "960422-1";
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        int birthYear = Integer.parseInt(ssn.substring(0,2));
        birthYear = switch (ssn.charAt(7)){
            case '1','2','5','6' -> birthYear + 1900;
            case '3','4','7','8' -> birthYear + 2000;
            default -> birthYear + 1800;
        };
        int age = year - birthYear;
        int ageCompareMon = Integer.parseInt(ssn.substring(2,4));
        int ageCompareDay = Integer.parseInt(ssn.substring(4,6));
        if (ageCompareMon < month) {
            age--;
        } else if(ageCompareMon == month){
            if (ageCompareDay<day || ageCompareDay==day) {
                age--;
            }
        }
        assertThat(age).isEqualTo(27);
    }

    @Test
    public void getAgeUsingLambda(){
        String ssn = "970301-1";
        int fullYear = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int ageResult = Stream.of(ssn)
                .map(i -> Integer.parseInt(ssn.substring(0,2)))
                .map(birthyear->switch (ssn.charAt(7)){
                    case '1','2','5','6' -> birthyear + 1900;
                    case '3','4','7','8' -> birthyear + 2000;
                    default -> birthyear + 1800;
                })
                .map(i -> i * 10000)
                .map(i -> i + Integer.parseInt(ssn.substring(2,6)))
                .map(i -> (fullYear - i)/10000)
                .findFirst()
                .get();
        assertThat(ageResult).isEqualTo(27);
    }

}

