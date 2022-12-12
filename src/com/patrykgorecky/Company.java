package com.patrykgorecky;

import java.util.ArrayList;
import java.util.List;

public class Company {
    final Double initialBudget = 50000.0;
    Double actualBudget = initialBudget;
    Double taxOnMonthlyEarnings = 0.0;
    String name;
    Integer numberOfActiveProjects = 0;
    Integer numberOfHiredEmployees = 0;
    List<Project> activeProjects = new ArrayList<>();
    List<Employee> hiredEmployees = new ArrayList<>();
    List<Project> completedProjects = new ArrayList<>();
    List<Payment> payments = new ArrayList<>();
    Integer numberOfTesters = 0;
    Integer payoutDays = 0;
    static class Payment{
        Double moneyInQue;
        final Integer paymentDeadLine;
        Integer daysOfWaiting;
        public Payment(Double moneyInQue, Integer daysOfWaitingForMoney){
            this.paymentDeadLine = daysOfWaitingForMoney;
            this.moneyInQue = moneyInQue;
        }
    }


    public Company(String name){
        this.name = name;
    }

    public void setActiveProjects(Project project){
        numberOfActiveProjects += 1;
        activeProjects.add(project);
        project.isActive = true;
    }
    public void getActiveProjects(){
        int i = 0;
        for (Project project : activeProjects) {
            System.out.println(i+1  +". " +project);
            i++;
        }
    }
    public void getActiveWorkers(){
        for (Employee employee : hiredEmployees) {
            int i = 0;
            System.out.println(i+1 +". "+ employee);
            i++;
        }
    }
    public void getProjectToSubmit(int userPick){
        System.out.println("Is completed?");
           for (int i = 0; i < activeProjects.get(userPick - 1).isCompleted.length; i++){
               System.out.print(i+1 +". "  + Project.skillsNames[userPick-1]+"-"  + activeProjects.get(userPick - 1).isCompleted[i]+ ", ");
           }
        System.out.println();
    }
    public void setHiredEmployees(Employee employee){
        numberOfHiredEmployees += 1;
        hiredEmployees.add(employee);
    }

    @Override
    public String toString() {
        return "Player name: " + name + ", Actual Budget: $" + actualBudget;
    }
}