package com.patrykgorecky;

import java.util.Random;

public class Project {
    static String[] names = new String[]{"Accenture", "SAP", "Capgemini", "ATOS", "Google", "Amazon", "Apple", "CD Projekt RED", "Asseco Poland", "Amadeus IT Group", "Spotify", "Sopra Steria", "YouTube", "Twitter", "Facebook"};
    static String[] skillsNames = new String[]{"Front-end", "Backend", "Databases", "Mobile-apps", "WordPress", "PrestaShop"};
    final String projectName;
    Client client;
    Integer difficulty;
    Boolean[] skillsRequired;
    Integer[] daysNeeded;
    Boolean[] isCompleted = new Boolean[]{false, false, false, false, false, false};
    Integer deadLine;
    Double fine;
    Double cost;
    Integer paymentsDeadLine;
    Integer chanceForMistake;
    boolean isReceivedFromSeller = false;
    boolean isTestedByMyself = false;
    boolean isProgrammedByMyself = false;
    Integer howManyTrue;
    boolean isActive;
    static Project[] projects = new Project[15];

    public Project(String projectName, Client client, Integer difficulty, Boolean[] skillsRequired, Integer[] daysNeeded, Integer deadLine, Double fine, Double cost, Integer paymentsDeadLine, Integer technologiesNeeded) {
        this.projectName = projectName;
        this.client = client;
        this.difficulty = difficulty;
        this.skillsRequired = skillsRequired;
        this.daysNeeded = daysNeeded;
        this.deadLine = deadLine;
        this.fine = fine;
        this.cost = cost;
        this.paymentsDeadLine = paymentsDeadLine;
        this.isActive = false;
        this.chanceForMistake = 10;
        this.howManyTrue = technologiesNeeded;
    }

    static public Project[] generateProjects() {
        Random random = new Random();
        int howManyTrue = 0;
        Client client;
        for (int i = 0; i < projects.length; i++) {
            int x = random.nextInt(1, 4);
            if (x == 1) {
                client = Client.chilledClient;
            } else if (x == 2) {
                client = Client.demandingClient;
            } else client = Client.mthrfkrClient;
            int level = random.nextInt(1, 4);
            Boolean[] skills = new Boolean[6];
            Integer[] days = new Integer[6];
            if (level == 1) {
                howManyTrue = 1;
                int z = random.nextInt(6);
                skills[z] = true;
                days[z] = random.nextInt(1,4);
                for (int a = 0; a < skills.length; a++) {
                    if (a != z) {
                        skills[a] = false;
                        days[a] = 0;
                    }
                }
            } else if (level == 2) {
                howManyTrue = 2;
                int b,c;
                do {
                    b = random.nextInt(6);
                    c = random.nextInt(6);
                } while (b == c);
                skills[b] = true;
                days[b] = random.nextInt(2,5);
                skills[c] = true;
                days[c] = random.nextInt(2,5);
                for (int a = 0; a < skills.length; a++) {
                    if (a != b && a!=c) {
                        skills[a] = false;
                        days[a] = 0;
                    }
                }
            } else {
                do{
                    for (int d = 0; d < skills.length; d++){
                        skills[d] = random.nextBoolean();
                        if (skills[d] = true) {
                            howManyTrue++;
                            days[d] = random.nextInt(3,6);
                        } else days[d] = 0;
                    }
                } while (howManyTrue < 3);
            }
            Integer deadLine = random.nextInt(8,16);
            Double fine = random.nextDouble(5000,10000);
            Double price = random.nextDouble(2000,15000);
            Integer deadLinePay = random.nextInt(7,16);
            projects[i] = new Project(names[i], client, level, skills, days, deadLine, fine, price, deadLine, howManyTrue);
        }
        return projects;
    }
    public String toString() {
        return "Company: " + projectName + ", Deadline(in days): " + deadLine + ", You can get: $" + Math.round(cost) + ", Difficulty: " + difficulty;
    }
}