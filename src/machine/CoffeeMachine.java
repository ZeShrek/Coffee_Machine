package machine;

import java.util.Scanner;

enum State {
    WAITING,
    BUY,
    EXPRESSO(250, 0, 16, 1, 4),
    LATTE(350, 75, 20, 1, 7),
    CAPUCCINO(200, 100, 12, 1, 6),
    FILL,
    TAKE(0, 0, 0, 0, 0),
    REMAINING(0, 0, 0, 0, 0),
    EXIT;

    int water;
    int milk;
    int coffee;
    int cups;
    int money;

    State(int water, int milk, int beans, int cups, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffee = beans;
        this.cups = cups;
        this.money = cost;
    }

    State() {
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getCups() {
        return cups;
    }

    public int getMoney() {
        return money;
    }


}

class Machine {


    public Machine() {
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    int water = 400;
    int milk = 540;
    int coffee = 120;
    int cups = 9;
    int money = 550;

}

public class CoffeeMachine {
    public static boolean isOutOfResources(Machine c, State state) {
        if (c.getCups() - 1 < 0) {
            System.out.println("Sorry, not enough disposable cups!");
            return true;
        } else {
            switch (state) {
                case EXPRESSO:
                    if (c.getWater() - 250 < 0) {
                        System.out.println("Sorry, not enough water!");
                        return true;
                    }
                    if (c.getCoffee() - 16 < 0) {
                        System.out.println("Sorry, not enough coffee beans!");
                        return true;
                    }
                    break;
                case LATTE:
                    if (c.getWater() - 350 < 0) {
                        System.out.println("Sorry, not enough water!");
                        return true;
                    }
                    if (c.getMilk() - 75 < 0) {
                        System.out.println("Sorry, not enough milk!");
                        return true;
                    }
                    if (c.getCoffee() - 20 < 0) {
                        System.out.println("Sorry, not enough coffee beans!");
                        return true;
                    }
                    break;
                case CAPUCCINO:
                    if (c.getWater() - 200 < 0) {
                        System.out.println("Sorry, not enough water!");
                        return true;
                    }
                    if (c.getMilk() - 100 < 0) {
                        System.out.println("Sorry, not enough milk!");
                        return true;
                    }
                    if (c.getCoffee() - 12 < 0) {
                        System.out.println("Sorry, not enough coffee beans!");
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        State state = State.WAITING;
        Machine cofeM = new Machine();
        String line;


        boolean on = true;

        while (on) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            line = scanner.nextLine().toUpperCase();

            switch (line) {
                /* ===== BUY OPTION ==== */
                case "BUY":
                    state = State.BUY;
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    String buyOption = scanner.nextLine();

                    if (buyOption.equals("1")) {
                        state = State.EXPRESSO;
                        if (isOutOfResources(cofeM, state)) {
                            break;
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                            cofeM.setWater(cofeM.getWater() - State.EXPRESSO.getWater());
                            cofeM.setCoffee(cofeM.getCoffee() - State.EXPRESSO.getCoffee());
                            cofeM.setCups(cofeM.getCups() - State.EXPRESSO.getCups());
                            cofeM.setMoney(cofeM.getMoney() + State.EXPRESSO.getMoney());
                            state = State.WAITING;
                            break;
                        }
                    } else if (buyOption.equals("2")) {
                        state = State.LATTE;
                        if (isOutOfResources(cofeM, state)) {
                            break;
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                            cofeM.setWater(cofeM.getWater() - State.LATTE.getWater());
                            cofeM.setMilk(cofeM.getMilk() - State.LATTE.getMilk());
                            cofeM.setCoffee(cofeM.getCoffee() - State.LATTE.getCoffee());
                            cofeM.setCups(cofeM.getCups() - State.LATTE.getCups());
                            cofeM.setMoney(cofeM.getMoney() + State.LATTE.getMoney());
                            state = State.WAITING;
                            break;
                        }
                    } else if (buyOption.equals("3")) {
                        state = State.CAPUCCINO;
                        if (isOutOfResources(cofeM, state)) {
                            break;
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                            cofeM.setWater(cofeM.getWater() - State.CAPUCCINO.getWater());
                            cofeM.setMilk(cofeM.getMilk() - State.CAPUCCINO.getMilk());
                            cofeM.setCoffee(cofeM.getCoffee() - State.CAPUCCINO.getCoffee());
                            cofeM.setCups(cofeM.getCups() - State.CAPUCCINO.getCups());
                            cofeM.setMoney(cofeM.getMoney() + State.CAPUCCINO.getMoney());
                            state = State.WAITING;
                            break;
                        }
                    } else if(buyOption.equals("back")){
                        state = State.WAITING;
                        break;
                    }

                    /* ===== FILL OPTION ==== */
                case "FILL":
                    System.out.println("Write how many ml of water do you want to add: ");
                    cofeM.setWater(cofeM.getWater() + scanner.nextInt());
                    System.out.println("Write how many ml of milk do you want to add: ");
                    cofeM.setMilk(cofeM.getMilk() + scanner.nextInt());
                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    cofeM.setCoffee(cofeM.getCoffee() + scanner.nextInt());
                    System.out.println("Write how many disposable cups of coffee do you want to add: ");
                    cofeM.setCups(cofeM.getCups() + scanner.nextInt());
                    scanner.nextLine();
                    state = State.WAITING;
                    //erro aqui
                    break;

                /* ===== TAKE OPTION ==== */
                case "TAKE":
                    System.out.println("I gave you $" + cofeM.getMoney());
                    cofeM.setMoney(0);
                    state = State.WAITING;
                    break;

                /* ===== REMAINING OPTION ==== */
                case "REMAINING":
                    System.out.println("The coffee machine has:");
                    System.out.println(cofeM.getWater() + " of water");
                    System.out.println(cofeM.getMilk() + " of milk");
                    System.out.println(cofeM.getCoffee() + " of coffee beans");
                    System.out.println(cofeM.getCups() + " of disposable cups");
                    System.out.println("$" + cofeM.getMoney() + " of money");
                    state = State.WAITING;
                    break;

                /* ===== REMAINING OPTION ==== */
                case "EXIT":
                    on = false;
                    break;
                default:
                    break;
            }
        }
    }
}
