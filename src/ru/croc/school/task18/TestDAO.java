package ru.croc.school.task18;

import ru.croc.school.task17.FillDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestDAO {

    public static void main(String[] args) throws SQLException, DatabaseException {

        Connection connection = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "");
        ProductDAO productDAO = new ProductDAO(connection);

        //проверяем, что в базе не существует объекта с артикулом Т7
        System.out.println(productDAO.findProduct("T7"));

        //проверяем, что в базе существует объект с артикулом Т3
        System.out.println(productDAO.findProduct("Т3"));

        //создаем объект с артикулом Т7 в базе
        System.out.println(productDAO.createProduct(new Product("Т7", "Колонка", 3000)));

        //обновим объект с артикулом Т7
        System.out.println(productDAO.updateProduct(new Product("Т7", "IPhone", 70000)));

        //теперь удалим объект с артикулом Т3 и проверим, что его нет
        productDAO.deleteProduct(new Product("T3", "Клавиатура", 150));
        System.out.println(productDAO.findProduct("Т3"));

        //тестируем создание заказов
        OrderDAO orderDAO = new OrderDAO(connection);
        Product p1 = new Product("T5", "Видеокарта", 15000);
        Product p2 = new Product("T1", "Монитор", 500);
        System.out.println(orderDAO.createOrder("alenka", new ArrayList<Product>(Arrays.asList(p1, p2))));

    }


}
