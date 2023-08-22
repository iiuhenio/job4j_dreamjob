package ru.job4j.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.controller.CandidateController;
import ru.job4j.dto.FileDto;
import ru.job4j.model.City;
import ru.job4j.model.User;
import ru.job4j.model.Candidate;
import ru.job4j.service.CityService;
import ru.job4j.service.CandidateService;
import ru.job4j.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


public class UserControllerTest {

    private UserService userService;

    private CityService cityService;

    private UserController userController;

    private MultipartFile testFile;

    @BeforeEach
    public void initServices() {
        userService = mock(UserService.class);
        cityService = mock(CityService.class);
        userController = new UserController(userService);
        testFile = new MockMultipartFile("testFile.img", new byte[] {1, 2, 3});
    }

    @Test
    public void register() {
        var user1 = new User(1, "testEmail", "testName", "testPassword");
        when(userService.save(user1)).thenReturn(Optional.of(user1));

        var model = new ConcurrentModel();
        var view = userController.getLoginPage(model, new User());
        var actualUsers = model.getAttribute("users");

        assertThat(view).isEqualTo("users/login");
    }
}