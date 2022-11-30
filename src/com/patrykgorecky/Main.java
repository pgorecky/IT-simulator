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
        while (Game.shouldContinue){
        for (Company player : players) {
            Game.options(player);
            int playerChoice = scanner.nextInt();
            switch (playerChoice) {
                case 1 -> projects = Game.signContract(projects, player);
                case 2 -> Game.customerSearchDay();
                case 3 -> Game.programmingDay(player);
                case 4 -> Game.testingDay(player);
                case 5 -> Game.submitProject(player);
                case 6 -> employees = Game.hireEmployee(employees, player);
                case 7 -> Game.hireSellerOrTester(player);
                case 8 -> player.hiredEmployees = Game.fireEmployee(player);
                case 9 -> Game.jobAds(player);
                case 10 -> Game.payouts(player);
//                case 11 -> Game.displayInformation();
                case 0 -> Game.shouldContinue = false;
                default -> System.out.println("Incorrect value given");
                }
                Game.dailyCompany(player, projects);
            }
        Game.dailyRoutine();
        }
    }
}