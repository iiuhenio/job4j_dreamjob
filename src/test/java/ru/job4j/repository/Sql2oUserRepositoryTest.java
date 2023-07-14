package ru.job4j.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.configuration.DatasourceConfiguration;
import ru.job4j.model.User;
import ru.job4j.model.User;

import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oUserRepositoryTest {
    private static Sql2oUserRepository sql2oUserRepository;
    private static Sql2o sql2o;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oUserRepository.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");
        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        sql2o = configuration.databaseClient(datasource);
        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void clearTable() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("TRUNCATE TABLE users RESTART IDENTITY");
            query.executeUpdate();
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        String email = "pavelwalker@mail.ru";
        String pass = "qwerty";
        var user = sql2oUserRepository.save(new User(0, email, "Pavel", pass)).get();
        var findUser = sql2oUserRepository.findByEmailAndPassword(email, pass).get();
        assertThat(findUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenSaveAndGetInvalidPasswordThenIsEmpty() {
        String email = "pavelwalker@mail.ru";
        String pass = "qwerty";
        sql2oUserRepository.save(new User(0, email, "Pavel", pass)).get();
        var findUser = sql2oUserRepository.findByEmailAndPassword(email, "12345");
        assertThat(findUser.isEmpty()).isTrue();
    }

    @Test
    public void whenSaveAndGetInvalidMailThenIsEmpty() {
        String email = "pavelwalker@mail.ru";
        String pass = "qwerty";
        sql2oUserRepository.save(new User(0, email, "Pavel", pass)).get();
        var findUser = sql2oUserRepository.findByEmailAndPassword("pavel@mail.ru", pass);
        assertThat(findUser.isEmpty()).isTrue();
    }

    @Test
    public void whenSaveUserIsEmpty() {
        sql2oUserRepository.save(new User(2, "mail", "name1", "password1"));
        var savedUser = sql2oUserRepository.save(new User(2, "mail", "name2",
                "password1"));
        assertThat(savedUser).isEmpty();
    }
}