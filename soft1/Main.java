
public class Main {

    public static void main(String[] args) {
        Pizza pizza = new PlainPizza();

        pizza = new Cheese(pizza);
        pizza = new Olives(pizza);
        pizza = new Pineapple(pizza);

        System.out.println("Pizza Order Details:");
        System.out.println("Description : " + pizza.getDescription());
        System.out.printf("Total Cost  : $%.2f%n", pizza.getCost());

        System.out.println("\n--- Alternative: All wrapped in one chain ---");
        Pizza deluxePizza = new Pineapple(
                new Olives(
                        new Cheese(
                                new PlainPizza())));

        System.out.println("Description : " + deluxePizza.getDescription());
        System.out.printf("Total Cost  : $%.2f%n", deluxePizza.getCost());
    }
}
