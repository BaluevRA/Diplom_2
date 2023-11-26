package order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import user.BaseTestLogIn;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.response.Response;

import static org.apache.http.HttpStatus.*;

public class CreateOrderTest extends BaseTestLogIn {

    private Order order;

    @Test
    @DisplayName("Создать заказ с авторизацией")
    public void createOrderWithAuthTest() {
        order = new Order();
        Response response = CreateOrder.getIngredients();
        List<String> list = response.then().extract().path("data._id");
        List<String> ingredients = order.getIngredients();
        ingredients.add(list.get(0));
        ingredients.add(list.get(4));
        ingredients.add(list.get(2));
        ingredients.add(list.get(0));
        Response responseCreateOrder = CreateOrder.createOrderWhileLoggedIn(order, token);
        responseCreateOrder.then().assertThat().statusCode(SC_OK).body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создать заказ без авторизации")
    public void createOrderWithNoAuthTest() {
        order = new Order();
        Response response = CreateOrder.getIngredients();
        List<String> list = response.then().extract().path("data._id");
        List<String> ingredients = order.getIngredients();
        ingredients.add(list.get(0));
        ingredients.add(list.get(4));
        ingredients.add(list.get(2));
        ingredients.add(list.get(0));
        CreateOrder.createOrdersNoAuth(order).then().assertThat().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Создать заказ с неверным хэшем ингредиентов с авторизацией")
    public void createOrderWithAuthInvalidHashTest() {
        order = new Order();
        CreateOrder.getIngredients();
        List<String> ingredients = order.getIngredients();
        ingredients.add("TotallyWrondIngredient");
        Response responseCreateOrder = CreateOrder.createOrderWhileLoggedIn(order, token);
        responseCreateOrder.then().assertThat().statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Создать заказ с авторизацией без ингридиентов")
    public void createOrderWithAuthNoIngredientsTest() {
        order = new Order();
        Response responseCreateOrder = CreateOrder.createOrderWhileLoggedIn(order, token);
        responseCreateOrder.then().assertThat().statusCode(SC_BAD_REQUEST).body("success", equalTo(false));
    }

}