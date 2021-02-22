public class Driver {
    public static void main(String[] args) {
        RestaurantListTwo res = new RestaurantListTwo();

        res.addFood("Pizza");
        res.addFood("Shawarma");
        res.addFood("Steak");
        res.addFood("Salad");
        res.addFood("Falafel");
        res.addFood("Falafel");
        res.addFood("Shawarma");
        res.addFood("Pasta");
        res.addFood("Hamburger");

        System.out.println(res.findMinDiff("Hamburger", "Schnitzel"));

        System.out.println(res);

    }
}
