package order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<String> ingredients;

    public Order() {
        ingredients = new ArrayList<>();
    }

    public List<String> getIngredients() {
        return ingredients;
    }

}



