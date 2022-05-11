package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {

    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);
    Ticket first = new Ticket(1, 3_390, 60, "KUF", "DME");
    Ticket second = new Ticket(2, 4_080, 60, "KUF", "LED");
    Ticket third = new Ticket(3, 4_000, 60, "KUF", "RTW");
    Ticket fourth = new Ticket(4, 13_340, 60, "KUF", "KHV");
    Ticket fifth = new Ticket(5, 2_585, 60, "KUF", "SVO");
    Ticket sixth = new Ticket(6, 2_900, 60, "KUF", "LED");
    Ticket seventh = new Ticket(7, 7_900, 120, "KUF", "FRU");

    @BeforeEach
    public void setup() {
        repository.add(first);
        repository.add(second);
        repository.add(third);
        repository.add(fourth);
        repository.add(fifth);
        repository.add(sixth);
    }

    @Test // тест добавления еще одного элемента
    public void shouldAddOneMore() {
        repository.add(seventh);
        Ticket[] actual = repository.findAll();
        Ticket[] expected = new Ticket[]{first, second, third, fourth, fifth, sixth, seventh};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // Тест удаление одного элемента
    public void shouldRemoveExist() {
        repository.removeById(1);
        Ticket[] expected = new Ticket[]{second, third, fourth, fifth, sixth};
        Ticket[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test // Тест сохранить элементы
    public void shouldSaveAndFindAll() {
        Ticket[] expected = new Ticket[]{first, second, third, fourth, fifth, sixth};
        Ticket[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test //Тест сортировка по стоимости билета по возрастанию
    public void shouldSortByPrice() {
        Ticket[] expected = new Ticket[]{fifth, sixth, first, third, second, fourth};
        assertArrayEquals(expected, manager.showOffers());
    }


    @Test // Тест сортировка по времени
    public void shouldSortByTime() {
        Ticket[] actual = manager.findAll("KUF", "DME");
        Ticket[] expected = new Ticket[]{first};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // Тест неверный аэропорт вылета
    public void shouldWrongFrom() {
        Ticket[] actual = manager.findAll("", "RTW");
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }

    @Test // Тест неверный аэропорт прилета
    public void shouldWrongTo() {
        Ticket[] actual = manager.findAll("KUF", "");
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }


    @Test // поиск имеющегося в списке
    void SearchIfExists() {
        Ticket[] expected = new Ticket[]{first};
        assertArrayEquals(expected, manager.findAll("KUF", "DME"));
    }

    @Test // поиск несуществующего
    void mustSearchIfNotExists() {
        Ticket[] expected = new Ticket[0];
        assertArrayEquals(expected, manager.findAll("KUF", "MOW"));
    }
}
