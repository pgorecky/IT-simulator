package com.patrykgorecky;

public class Company {
    final private Double initialBudget = 50000.0;
    public Double actualBudget = initialBudget;
    public String name;
    Integer numberOfActiveProjects = 0;
    public Integer numberOfHiredEmployees = 0;
    public Project[] activeProjects = new Project[numberOfActiveProjects];
    public Employee[] hiredEmployees = new Employee[numberOfHiredEmployees];
    public Integer numberOfTesters = 0;
    public Integer payoutDays = 0;


    public Company(String name){
        this.name = name;
    }

    public void setActiveProjects(Project project){
        numberOfActiveProjects += 1;
        Project[] intermediateProjects = new Project[numberOfActiveProjects];
        intermediateProjects[numberOfActiveProjects-1] = project;
        for (int i = 0; i < intermediateProjects.length - 1; i++){
            intermediateProjects[i] = activeProjects[i];
        }
        this.activeProjects = intermediateProjects;
    }
    public void getActiveProjects(){
        for (int i = 0; i<activeProjects.length; i++) {
            System.out.println(i+1 +". "+ activeProjects[i]);
        }
    }
    public void getActiveWorkers(){
        for (int i = 0; i<hiredEmployees.length; i++) {
            System.out.println(i+1 +". "+ hiredEmployees[i]);
        }
    }
    public void getProjectToSubmit(int userPick){
        System.out.println("Is completed?");
           for (int i = 0; i < activeProjects[userPick-1].isCompleted.length; i++){
               System.out.print(i+1 +". "  + Project.skillsNames[userPick-1]+"-"  + activeProjects[userPick-1].isCompleted[i]+ ", ");
           }
        System.out.println();
    }
    public void setHiredEmployees(Employee employee){
        numberOfHiredEmployees += 1;
        Employee[] intermediateProjects = new Employee[numberOfHiredEmployees];
        intermediateProjects[numberOfHiredEmployees-1] = employee;
        this.hiredEmployees = intermediateProjects;
    }

    @Override
    public String toString() {
        return "Player name: " + name + ", Actual Budget: $" + actualBudget;
    }
}