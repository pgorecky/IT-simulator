package com.patrykgorecky;

public class Client {
    Integer latePayment;
    Integer dodgePenalty;
    Integer lossContract;

    static Client chilledClient = new Client(30, 20, 0);
    static Client demandingClient = new Client(0, 0, 50);
    static Client mthrfkrClient = new Client(30, 0, 100);
    public Client(Integer latePayment, Integer dodgePenalty, Integer lossContract) {
        this.latePayment = latePayment;
        this.dodgePenalty = dodgePenalty;
        this.lossContract = lossContract;
    }
}