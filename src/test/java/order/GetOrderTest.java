package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import user.BaseTestLogIn;

import java.util.List;

import static org.apache.http.HttpStatus.*;

public class GetOrderTest extends BaseTestLogIn {
    private Order order;

    @Test
    @DisplayName("Получить инфо о заказе без авторизации")
    public void getOrderWithNoAuthTest() {
        order = new Order();
        Response response = CreateOrder.getIngredients();
        List<String> list = response.then().extract().path("data._id");
        List<String> ingredients = order.getIngredients();
        ingredients.add(list.get(0));
        ingredients.add(list.get(3));
        ingredients.add(list.get(3));
        ingredients.add(list.get(0));
        CreateOrder.getOrdersNoLoggedInUser().then().assertThat().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Получить инфо о заказе с авторизацией")
    public void getOrderAuthTest() {
        order = new Order();
        Response response = CreateOrder.getIngredients();
        List<String> list = response.then().extract().path("data._id");
        List<String> ingredients = order.getIngredients();
        ingredients.add(list.get(0));
        ingredients.add(list.get(3));
        ingredients.add(list.get(3));
        ingredients.add(list.get(0));
        CreateOrder.getOrdersLoggedInUser(token).then().assertThat().statusCode(SC_OK);
    }
}
