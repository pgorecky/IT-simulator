package com.patrykgorecky;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game.setTime();
        Project[] projects = Project.generateProjects();
        Employee[] employees = Employee.generateEmployees();
        Integer numberOfPlayers = Game.numberOfPlayers();
        Company[] players = new Company[numberOfPlayers];
        Game.startGame(players);
        Game.welcoming();
        while (Game.shouldContinue) {
            for (int i = 0; i < players.length; i++){
                Game.options(players[i]);
                int playerChoice = scanner.nextInt();
                switch (playerChoice) {
                    case 1 -> projects = Game.signContract(projects, players[i]);
                    case 2 -> Game.customerSearchDay();
                    case 3 -> Game.programmingDay(players[i]);
                    case 4 -> Game.testingDay(players[i]);
                    case 5 -> Game.submitProject(players[i]);
                    case 6 -> employees = Game.hireEmployee(employees, players[i]);
                    case 7 -> Game.hireSellerOrTester(players[i]);
                    case 8 -> Game.fireEmployee(players[i]);
                    case 9 -> Game.jobAds(players[i]);
                    case 10 -> Game.payouts(players[i]);
                    case 11 -> {
                        Game.displayInformation(players[i], projects, employees);
                        i--;
                    }
                    case 0 -> Game.shouldContinue = false;
                    default -> System.out.println("Incorrect value given");
                }
                if (playerChoice != 11) Game.dailyCompany(players[i]);
            }
            Game.dailyRoutine(projects);
        }
    }
}