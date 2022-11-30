package com.patrykgorecky;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;

public class Game {
    static GregorianCalendar day = new GregorianCalendar();
    static Scanner scanner = new Scanner(System.in);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMMM yyyy");
    static boolean shouldContinue = true;
    static private Integer numberOfAvailableProject = 3;
    static private Integer numberOfAvailableWorkers = 6;
    static private Integer dayOfSearching = 0;
    static private Integer dayOfSellersSearching = 0;
    static private Integer numberOfSellers = 0;
    static private Integer dayCounter = 3;
    static private Boolean isWeekend;
    static private final Integer chanceForGettingSick = 5;

    static public void setTime() {
        day.set(2020, Calendar.JANUARY, 1);
    }

    static public void getTime() {
        System.out.println(simpleDateFormat.format(day.getTime()));
    }

    static public void startGame(Company[] company) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < company.length; i++) {
            System.out.print("Enter the name of the player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            company[i] = new Company(name);
        }
    }

    static Integer numberOfPlayers() {
        System.out.print("Enter the number of players: ");
        return scanner.nextInt();
    }

    static public Boolean draw(Integer percentageMistake) {
        Random rnd = new Random();
        int x = rnd.nextInt(100);
        boolean draw;
        draw = x <= percentageMistake;
        return draw;
    }

    public static void clearConsole() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    static public void welcoming() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game IT Company Simulator");
        System.out.println("Have fun!!!");
        System.out.println("Press any key to start game");
        scanner.nextLine();
        clearConsole();
    }

    public static void options(Company cmp) {
        getTime();
        System.out.println(cmp);
        System.out.println("What you want to do? ");
        System.out.println("1. Sign the contract for the project.");
        System.out.println("2. Spend a day looking for customers.");
        System.out.println("3. Spend a day on programming.");
        System.out.println("4. Spend a day testing.");
        System.out.println("5. Submit a completed project.");
        System.out.println("6. Hire a new employee.");
        System.out.println("7. Hire a salesperson or tester.");
        System.out.println("8. Fire the employee.");
        System.out.println("9. Spend money on a job ad.");
        System.out.println("10. Set aside a day for settlements with authorities.");
        System.out.println("11. View company information. ");
        System.out.println("0. Exit the game");
        System.out.print("My choice is: ");
    }

    public static void dailyRoutine() {
        day.add(Calendar.DAY_OF_MONTH, 1);
        dayCounter++;
        if (dayCounter > 7) dayCounter = 1;
        isWeekend = dayCounter == 6 || dayCounter == 7;
    }

    public static Project[] signContract(Project[] project, Company cmp) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your current available projects:");
        for (int i = 0; i < numberOfAvailableProject; i++) {
            System.out.println(i + 1 + ". " + project[i]);
        }
        System.out.print("Which project do you choose? ");
        int x = scanner.nextInt();
        if (project[x - 1].difficulty == 3 && cmp.numberOfHiredEmployees == 0) {
            System.out.println("You can't do difficult projects alone");
            cmp.actualBudget += project[x - 1].cost / 10;
            project[x - 1].cost -= project[x - 1].cost / 10;
            clearConsole();
            return project;
        } else {
            project[x - 1].isActive = true;
            cmp.setActiveProjects(project[x - 1]);
            project[x - 1] = null;
            Project[] intermediateProjects = new Project[project.length - 1];
            for (int i = 0; i < intermediateProjects.length; i++) {
                if (project[i] != null) {
                    intermediateProjects[i] = project[i];
                } else {
                    if (project[i + 1] != null) {
                        intermediateProjects[i] = project[i + 1];
                        project[i + 1] = null;
                    } else i++;
                }
            }
            clearConsole();
            return intermediateProjects;
        }
    }

    public static void customerSearchDay() {
        dayOfSearching++;
        if (dayOfSearching == 5) {
            numberOfAvailableProject++;
            dayOfSearching = 0;
        }
        System.out.println("It is the " + dayOfSearching + " day of searching");
        clearConsole();
    }

    public static void programmingDay(Company cmp) {
        for (int i = 0; i < cmp.activeProjects.length; i++) {
            System.out.println("Choose project: ");
            System.out.println(i + 1 + ". " + cmp.activeProjects[i]);
        }
        int x = scanner.nextInt();
        if (!cmp.activeProjects[x - 1].skillsRequired[5]) {
            System.out.println("Technologies available for programming:");
            for (int i = 0; i < cmp.activeProjects[x - 1].skillsRequired.length; i++) {
                System.out.println(i + 1 + ". " + Project.skillsNames[i] + ": " + cmp.activeProjects[x - 1].skillsRequired[i]);
            }
            System.out.println("Which one you choose? ");
            int y = scanner.nextInt();
            cmp.activeProjects[x - 1].daysNeeded[y - 1]--;
            if (cmp.activeProjects[x - 1].daysNeeded[y - 1] == 0) cmp.activeProjects[x - 1].isCompleted[y + 1] = true;
        } else System.out.println("You don't have the skills to do it yourself");
        clearConsole();
    }

    public static Integer testTheEmployee(Company cmp) {
        System.out.println("Select the employee you want to test");
        cmp.getActiveWorkers();
        int playerChoice = scanner.nextInt();
        return cmp.hiredEmployees[playerChoice - 1].chanceForMistake;
    }

    public static void testingDay(Company cmp) {
        System.out.println("Which project you want to test?");
        cmp.getActiveProjects();
        int projectPick = scanner.nextInt();
        System.out.println("Whose code you want to test?");
        System.out.println("1. My own code");
        System.out.println("2. One of my workers");
        System.out.println("Whose code you want to test?");
        int codeToTestPick = scanner.nextInt();
        switch (codeToTestPick) {
            case 1 -> cmp.activeProjects[projectPick - 1].chanceForMistake -= 10;
            case 2 -> cmp.activeProjects[projectPick - 1].chanceForMistake -= Game.testTheEmployee(cmp);
            default -> System.out.println("Incorrect value given");
        }
        Game.clearConsole();
    }

    public static void submitProject(Company cmp) {
        if (cmp.activeProjects != null) {
            cmp.getActiveProjects();
            System.out.println("Which project do you want to give away: ");
            int pickProjectToSubmit = scanner.nextInt();
            cmp.getProjectToSubmit(pickProjectToSubmit);
            int pickTechnologyToSubmit = scanner.nextInt();
            if (cmp.activeProjects[pickProjectToSubmit - 1].isCompleted[pickTechnologyToSubmit - 1]) {
                if (Game.draw(cmp.activeProjects[pickProjectToSubmit - 1].chanceForMistake)) {
                    System.out.println("Code was invalid!!!");
                    if (Game.draw(cmp.activeProjects[pickProjectToSubmit - 1].client.dodgePenalty)) {
                        System.out.println("Successfully avoided penalty for wrong code");
                        if (Game.draw(cmp.activeProjects[pickProjectToSubmit - 1].client.lossContract)) {
                            System.out.println("Your company lost that contract!");
                            cmp.activeProjects[pickProjectToSubmit - 1] = null;
                            Project[] intermediateProjects = new Project[cmp.activeProjects.length - 1];
                            for (int i = 0; i < intermediateProjects.length; i++) {
                                if (cmp.activeProjects[i] != null) {
                                    intermediateProjects[i] = cmp.activeProjects[i];
                                } else if (cmp.activeProjects[i + 1] != null) {
                                    intermediateProjects[i] = cmp.activeProjects[i + 1];
                                    cmp.activeProjects[i + 1] = null;
                                } else i++;
                            }
                            cmp.activeProjects = intermediateProjects;
                        }
                    } else cmp.actualBudget -= cmp.activeProjects[pickProjectToSubmit - 1].fine;
                } else
                    cmp.actualBudget += (cmp.activeProjects[pickProjectToSubmit - 1].cost / Project.technologiesNeeded);
            } else System.out.println("This technology is not completed yet!");
        }
        Game.clearConsole();
    }

    public static Employee[] hireEmployee(Employee[] employees, Company cmp) {
        System.out.println("Available workers for employment:");
        for (int i = 0; i < numberOfAvailableWorkers; i++) {
            System.out.println(i + 1 + ". " + employees[i]);
        }
        System.out.println("Which one you want to hire? ");
        int x = scanner.nextInt();
        cmp.setHiredEmployees(employees[x - 1]);
        employees[x - 1] = null;
        cmp.actualBudget -= 1000;
        Employee[] intermediateEmployees = new Employee[employees.length - 1];
        for (int i = 0; i < intermediateEmployees.length; i++) {
            if (employees[i] != null) {
                intermediateEmployees[i] = employees[i];
            } else {
                if (employees[i + 1] != null) {
                    intermediateEmployees[i] = employees[i + 1];
                    employees[i + 1] = null;
                } else i++;
            }
        }
        clearConsole();
        return intermediateEmployees;
    }

    public static void hireSellerOrTester(Company cmp) {
        System.out.println("Who do you want to hire?");
        System.out.println("1. Salesperson");
        System.out.println("2. Tester");
        int x = scanner.nextInt();
        switch (x) {
            case 1 -> numberOfSellers++;
            case 2 -> cmp.numberOfTesters++;
        }
        clearConsole();
    }

    public static Employee[] fireEmployee(Company cmp) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose employee: ");
        for (int i = 0; i < cmp.numberOfHiredEmployees; i++) {
            System.out.println(i + 1 + ". " + cmp.hiredEmployees[i]);
        }
        int x = scanner.nextInt();
        cmp.hiredEmployees[x - 1] = null;
        cmp.actualBudget -= 1000;
        Employee[] intermediateEmployees = new Employee[cmp.hiredEmployees.length - 1];
        for (int i = 0; i < intermediateEmployees.length; i++) {
            if (cmp.hiredEmployees[i] != null) {
                intermediateEmployees[i] = cmp.hiredEmployees[i];
            } else {
                if (cmp.hiredEmployees[i + 1] != null) {
                    intermediateEmployees[i] = cmp.hiredEmployees[i + 1];
                    cmp.hiredEmployees[i + 1] = null;
                } else i++;
            }
        }
        clearConsole();
        return intermediateEmployees;
    }

    public static void jobAds(Company cmp) {
        System.out.println("$500 was spent on job advertisements");
        cmp.actualBudget -= 500;
        numberOfAvailableWorkers += 3;
    }

    public static void payouts(Company cmp) {
        Double spentMoney = cmp.actualBudget;
        for (int i = 0; i < cmp.hiredEmployees.length; i++) {
            double paycheck = ((cmp.hiredEmployees[i].salary + cmp.hiredEmployees[i].salary * 0.1971) / 2);
            cmp.actualBudget -= paycheck;
        }
        cmp.actualBudget -= ((numberOfSellers * 2817 + cmp.numberOfTesters * 3158) / 2);
        if (cmp.actualBudget <= 0) shouldContinue = false;
        spentMoney -= cmp.actualBudget;
        cmp.payoutDays++;
        System.out.println("Expenses amounted to" + spentMoney);
    }

    public static void dailySellersWork(Project[] project) {
        if (!draw(chanceForGettingSick)) {
            if (numberOfSellers > 0) {
                dayOfSellersSearching++;
                if (dayOfSellersSearching == 5) {
                    numberOfAvailableProject++;
                    dayOfSellersSearching = 0;
                    project[numberOfAvailableProject - 1].isReceivedFromSeller = true;
                }
            }
        }
    }
    public static void dailyProgrammersWork(Company cmp){
        if(!draw(chanceForGettingSick)){
            if(cmp.numberOfHiredEmployees > 0){
                for(Employee employee : cmp.hiredEmployees){
                    for (int i = 0; i < cmp.activeProjects.length; i++){
                        for (int j = 0; j < Project.skillsNames.length; j++){
                            if(employee.skills[j] == true && cmp.activeProjects[i].skillsRequired[j] == true){
                                cmp.activeProjects[i].daysNeeded[j]--;
                                if (cmp.activeProjects[i].daysNeeded[j] == 0) cmp.activeProjects[i].isCompleted[j] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public static void dailyTestersWork(Company cmp){
        Random random = new Random();
        if(!draw(chanceForGettingSick)){
            if(cmp.numberOfTesters > (cmp.numberOfHiredEmployees/3)){
                for (Project project: cmp.activeProjects) {
                    project.chanceForMistake = 0;
                }
            } else if (cmp.numberOfTesters > 0 && cmp.activeProjects != null) {
                int i = 0;
                while (cmp.activeProjects[i].chanceForMistake == 0) i++;
                int rnd = random.nextInt(cmp.numberOfHiredEmployees);
                int rnd2 = random.nextInt(2);
                switch (rnd2){
                    case 0 -> cmp.activeProjects[i].chanceForMistake -= 10;
                    case 1 -> cmp.activeProjects[i].chanceForMistake -= cmp.hiredEmployees[rnd].chanceForMistake;
                }
            }
        }
    }

    public static void dailyCompany(Company cmp, Project[] availableProjects) {
        if (!isWeekend) {
            Game.dailySellersWork(availableProjects);
            Game.dailyProgrammersWork(cmp);
            Game.dailyTestersWork(cmp);
        }
        if (cmp.payoutDays < (2 * day.get(Calendar.MONTH))) {
            shouldContinue = false;
            System.out.println("ZUS punished your company for not paying taxes. You went bankrupt");
        }
        for (Project project: cmp.activeProjects) {
            project.deadLine--;
        }
    }
}
