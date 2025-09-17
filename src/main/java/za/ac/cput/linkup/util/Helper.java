package za.ac.cput.linkup.util;


import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.enums.Course;
import za.ac.cput.linkup.domain.enums.Gender;
import za.ac.cput.linkup.domain.enums.Interest;
import za.ac.cput.linkup.domain.enums.RelationshipType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Helper {
    public static boolean isStringNullOrEmpty(String x){
        if(x == null || x.isEmpty())
            return true;
        return false;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean isIntNull(int y){
        if(y == 0)
            return true;
        return false;
    }

    public static boolean isIntNegative(int y){
        if(y < 0)
            return true;
        return false;
    }

    public static boolean isIntZero(int y){
        if(y == 0)
            return true;
        return false;
    }

    public static boolean isDoubleNull(double z){
        if(z == 0.0)
            return true;
        return false;
    }

    public static boolean isObjectNull(Object object){
        if (object == null){
            return true;
        }
        return false;
    }


    public static boolean isDateNull(LocalDateTime dateTime) {
        return dateTime == null;
    }

    public static boolean isDateInFuture(LocalDateTime dateTime) {
        return dateTime != null && dateTime.isAfter(LocalDateTime.now());
    }

    public static boolean isTrue(boolean b){
        if(b == true)
            return true;
        return false;
    }

    public static LocalDate getDate(String id){
        int year = Integer.parseInt(id.substring(0,2));
        int month = Integer.parseInt(id.substring(2,4));
        int day = Integer.parseInt(id.substring(4,6));
        LocalDate date = LocalDate.of(year,month,day);
        return date;
    }

    public static boolean isValidLong(Long value) {
        return value != null && value > 0;
    }

    public static User validateUser(User user) {
        return isObjectNull(user) ? null : user;
    }

    public static List<Interest> validateInterests(List<Interest> interests) {
        return (interests == null || interests.isEmpty()) ? null : interests;
    }

    public static RelationshipType validateRelationshipType(RelationshipType type) {
        return type == null ? null : type;
    }

    public static Integer validateMinAge(int minAge) {
        return (minAge < 18) ? null : minAge;
    }

    public static Integer validateMaxAge(int maxAge, int minAge) {
        return (maxAge < minAge || maxAge > 120) ? null : maxAge;
    }

    public static Gender validatePreferredGender(Gender gender) {
        return gender == null ? null : gender;
    }

    public static List<Course> validateCourses(List<Course> courses) {
        return (courses == null || courses.isEmpty()) ? null : courses;
    }

    public static Integer validateMaxDistance(int distance) {
        return distance < 0 ? null : distance;
    }

    public static Boolean validateSmokingPreference(boolean smoking) {
        return smoking;
    }

    public static Boolean validateDrinkingPreference(boolean drinking) {
        return drinking;
    }
}
