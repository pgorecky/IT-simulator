package com.patrykgorecky;

import java.util.Arrays;
import java.util.Random;

public class Employee {
    static String[] workersName = new String[]{"Rob", "Martin", "Liam", "Jackson", "Noah", "Aiden", "Grayson", "Caden", "Lucas", "Elijah", "Jack", "Rayon"};
    String name;
    Boolean[] skills;
    Double salary;
    Integer chanceForDelay;
    Integer chanceForMistake;
    Boolean receivedSalary = false;
    static Employee[] employees = new Employee[15];
    static Boolean[] randomSkills = new Boolean[6];

    public Employee(String name, Double salary, Boolean[] skills, Integer chanceForDelay, Integer chanceForMistake) {
        this.name = name;
        this.skills = skills;
        this.salary = salary;
        this.chanceForDelay = chanceForDelay;
        this.chanceForMistake = chanceForMistake;
    }
    static public void randomSkillSet(Boolean [] skills){
        Random random = new Random();
        for (int i = 0; i < skills.length; i++) {
            skills[i] = random.nextBoolean();
        }
    }
    static public Employee[] generateEmployees(){
        Random random = new Random();
        randomSkillSet(randomSkills);
        employees[0] = new Employee("Best Student",2000.0,randomSkills, 0, 0);
        randomSkillSet(randomSkills);
        employees[1] = new Employee("Average Student",1500.0,randomSkills, 0, 10);
        randomSkillSet(randomSkills);
        employees[2] = new Employee("Smug Student",1000.0,randomSkills, 20, 20);
        for (int i = 3; i < employees.length; i++){
            randomSkillSet(randomSkills);
            employees[i] = new Employee(workersName[i-3],random.nextDouble(3000,7000),randomSkills, random.nextInt(10), random.nextInt(6));
        }
        return employees;
    }
    @Override
    public String toString() {
        return "Name: " + name + " Skills: " + Arrays.toString(skills) + " Salary: $" + Math.round(salary) + " " + chanceForDelay + " " + chanceForMistake;
    }
}