package com.patrykgorecky;

import java.util.Random;

public class Employee {
    static String[] workersName = new String[]{"Rob", "Martin", "Liam", "Jackson", "Noah", "Aiden", "Grayson", "Caden", "Lucas", "Elijah", "Jack", "Rayon"};
    String[] skillsNames = new String[]{"Front-end", "Backend", "Databases", "Mobile-apps", "WordPress", "PrestaShop"};
    String name;
    Boolean[] skills;
    Double salary;
    Integer chanceForDelay;
    Integer chanceForMistake;
    static Employee[] employees = new Employee[15];
    static Boolean[] randomSkills = new Boolean[6];

    public Employee(String name, Boolean[] skills, Double salary, Integer chanceForDelay, Integer chanceForMistake) {
        this.name = name;
        this.skills = skills;
        this.salary = salary;
        this.chanceForDelay = chanceForDelay;
        this.chanceForMistake = chanceForMistake;
    }
    static public Boolean[] randomSkillSet(){
        Random random = new Random();
        for (Boolean skill:randomSkills) {
            skill = random.nextBoolean();
        }
        return randomSkills;
    }
    static public Employee[] generateEmployees(){
        Random random = new Random();
        employees[0] = new Employee("Best Student",randomSkillSet(),2000.0, 0, 0);
        employees[1] = new Employee("Average Student",randomSkillSet(),1500.0, 0, 10);
        employees[2] = new Employee("Smug Student",randomSkillSet(),1000.0, 20, 20);
        for (int i = 3; i < employees.length; i++){
            employees[i] = new Employee(workersName[i-3], randomSkillSet(),random.nextDouble(3000,7000), random.nextInt(10), random.nextInt(6));
        }
        return employees;
    }
    @Override
    public String toString() {
        return "Name: " + name + " Skills: " + skills + " Salary: $" + Math.round(salary) + " " + chanceForDelay + " " + chanceForMistake;
    }
}