package dev.fachpersonal;

import java.util.ArrayList;
import java.util.Scanner;

public class RentACar {
    static void main() {
        ArrayList<Car> cars = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        new Command("list", "Displays all Rented Cars", () -> cars.forEach((car) -> System.out.println(car.toString())));
        new Command("new", "Creates a new Car", () -> rentNewVehicle(sc, cars));
        new Command("debug", "Creates a new Cars", () -> debug(cars));
        new Command("help", "Displays this view", RentACar::help);
        new Command("return", "Allows user to return a car", () -> returnCar(cars,sc));

        help();
        System.out.print(" > ");
        String line;

        while(true) {
            line = getInput(sc);
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            for(Command cmd : Command.commands) {
                if(cmd.getTriggerWord().equalsIgnoreCase(line)) {
                    cmd.getAction().run();
                }
            }
            System.out.print(" > ");
        }
        System.out.println("Good Bye!");
    }

    private static void returnCar(ArrayList<Car> cars, Scanner sc) {
        for (int i = 0; i < cars.size(); i++) {
            System.out.printf("[%d] %s %s%n", i+1, cars.get(i).getBrand(), cars.get(i).getModel());
        }
        System.out.print("Which car do you want to return?\n > ");
        int index = sc.nextInt();
        if(index < cars.size() && index > 0) {
            cars.remove(index-1);
            System.out.println("Returned car " +  cars.get(index-1).getBrand() + " " + cars.get(index-1).getModel());
        }
    }

    private static void debug(ArrayList<Car> cars) {
        cars.add(new Car("Audi","A5", 300, 15));
        cars.add(new Car("Audi","A3", 200, 15));
        cars.add(new Car("Audi","A4", 250, 15));
        cars.add(new Car("BMW","320i", 250, 15));
        cars.add(new Car("BMW","320d", 275, 15));
    }

    private static void rentNewVehicle(Scanner sc, ArrayList<Car> cars) {
        String brand, model;
        double rate;
        int duration;
        System.out.print("Brand : ");
        brand = getInput(sc);
        System.out.print("Model : ");
        model = getInput(sc);
        System.out.print("Rate : ");
        rate = sc.nextDouble();
        System.out.print("Duration : ");
        duration = sc.nextInt();

        cars.add(new Car(brand,model,rate,duration));
    }

    private static void help() {
        Command.commands.forEach((cmd) -> System.out.printf("%s - %s%n", cmd.getTriggerWord(), cmd.getDescription()));
    }

    public static String getInput(Scanner sc) {
        String input = sc.nextLine();
        while(input.isBlank()) {
            input = sc.nextLine();
        }
        return input;
    }
}
class Car {
    private String brand;
    private String model;
    private double rate;
    private int duration;

    public Car(String brand, String model, double rate, int duration) {
        this.brand = brand;
        this.model = model;
        this.rate = rate;
        this.duration = duration;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getRate() {
        return rate;
    }

    public String toString() {
        return String.format("%s %s - %.2f$%nFinal Cost: %.2f$", brand, model, rate, duration*rate);
    }
}

class Command {
    private String triggerWord;
    private String description;
    private Action action;

    public static ArrayList<Command> commands;

    public Command(String triggerWord, String description, Action action) {
        if(commands == null)
            commands = new ArrayList<>();

        this.triggerWord = triggerWord;
        this.description = description;
        this.action = action;

        commands.add(this);
    }


    public String getTriggerWord() {
        return triggerWord;
    }

    public String getDescription() {
        return description;
    }

    public Action getAction() {
        return action;
    }
}

interface Action {
    void run();
}
