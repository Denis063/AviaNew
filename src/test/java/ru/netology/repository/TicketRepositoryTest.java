package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;

import static org.junit.jupiter.api.Assertions.*;


public class TicketRepositoryTest {
    private TicketRepository repository = new TicketRepository();
    private Ticket first = new Ticket(1, 1_000, 60, "KUF", "LED");
    private Ticket second = new Ticket(2, 2_000, 70, "KUF", " GOJ");
    private Ticket third = new Ticket(3, 3_000, 80, "KUF", "FRU");
    private Ticket fourth = new Ticket(5, 1_500, 90, "KUF", "OGZ");
    private Ticket fifth = new Ticket(10, 4_000, 100, "KUF", "ARH");
    private Ticket sixth = new Ticket(7, 2_500, 120, "KUF", "ASF");
    private Ticket seventh = new Ticket(7, 7_900, 120, "KUF", "FRU");


    @Test
    void Save() {
        repository.add(first);
        Ticket[] expected = new Ticket[]{first};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void ShowEmpty() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void ShowNotEmpty() {
        repository.add(first);
        repository.add(second);
        Ticket[] expected = new Ticket[]{first, second};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }
}