import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() { //добавляет в менеджер задачи
        SimpleTasks simpleTask = new SimpleTasks(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchWhereHaveMatch() { //выводит задачи, где есть совпадения, если они есть
        SimpleTasks simpleTask = new SimpleTasks(87, "Сходить в магазин");

        String[] subtasks = {"Выгулять собаку", "Домашняя работа"};
        Epic epic = new Epic(68, subtasks);

        Meeting meeting = new Meeting(73, "Подготовка ДЗ ", "работа 13", "15 октября");

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {epic, meeting};
        Task[] actual = todos.search("работа");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchWhereDoNotHaveMatch() { //не выводит ничего, если нет совпадений
        SimpleTasks simpleTask = new SimpleTasks(87, "Сходить в магазин");

        String[] subtasks = {"Выгулять собаку", "Купить курицу"};
        Epic epic = new Epic(68, subtasks);

        Meeting meeting = new Meeting(73, "Подготовка ДЗ", "работа 13", "15 октября");

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("гитара");
        Assertions.assertArrayEquals(expected, actual);
    }
}