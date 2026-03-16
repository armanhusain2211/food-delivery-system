package com.foodapp.menuservice.repository;

import com.foodapp.menuservice.entity.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void saveMenuTest() {
        Menu menu = Menu.builder()
                .name("Lunch Menu")
                .restaurantId("1")
                .build();

        Menu savedMenu = menuRepository.save(menu);

        assertNotNull(savedMenu.getId());
        assertEquals("Lunch Menu", savedMenu.getName());
    }

    @Test
    void findMenuByIdTest() {
        Menu menu = Menu.builder()
                .name("Dinner Menu")
                .restaurantId("2")
                .build();

        Menu savedMenu = menuRepository.save(menu);

        Optional<Menu> foundMenu = menuRepository.findById(savedMenu.getId());

        assertTrue(foundMenu.isPresent());
        assertEquals("Dinner Menu", foundMenu.get().getName());
    }

    @Test
    void findAllMenusTest() {
        Menu menu1 = Menu.builder()
                .name("Breakfast Menu")
                .restaurantId("1")
                .build();

        Menu menu2 = Menu.builder()
                .name("Dinner Menu")
                .restaurantId("2")
                .build();

        menuRepository.save(menu1);
        menuRepository.save(menu2);

        var menus = menuRepository.findAll();

        assertEquals(2, menus.size());
    }

    @Test
    void deleteMenuTest() {
        Menu menu = Menu.builder()
                .name("Test Menu")
                .restaurantId("3")
                .build();

        Menu savedMenu = menuRepository.save(menu);

        menuRepository.deleteById(savedMenu.getId());

        Optional<Menu> result = menuRepository.findById(savedMenu.getId());

        assertTrue(result.isEmpty());
    }
}