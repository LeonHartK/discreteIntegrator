package test;

import model.Controller;
import model.Stack;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Date;
import java.util.EmptyStackException;

public class App {

    Controller controller = new Controller<>();
    Stack<String> stack = new Stack<>();

    @Test
    public void testInsertTask() {

        Integer key = 12;
        String title = "Tarea de prueba";
        String description = "Descripción de prueba";
        Date limitDate = new Date();
        int priority = 1;

        String result = controller.insertTask(key, title, description, limitDate, priority);

        assertNotNull(result);
        assertTrue(result.contains("----> Se ha registrado existosamente la tarea <----"));
    }

    @Test
    public void testInsertTaskexp2() {

        Number key = 12.2;
        String title = "Tarea de prueba numero 2";
        String description = "Descripción de prueba numero 2";
        Date limitDate = new Date();
        int priority = 2;

        String result = controller.insertTask(key, title, description, limitDate, priority);

        assertNotNull(result);
        assertTrue(result.contains("----> Se ha registrado existosamente la tarea <----"));
    }

    @Test
    public void testDeleteTask() {

        Integer key = 12;
        String title = "Tarea de prueba";
        String description = "Descripción de prueba";
        Date limitDate = new Date();
        int priority = 1;

        controller.insertTask(key, title, description, limitDate, priority);

        String result = controller.deleteTask(key);

        assertNotNull(result);
        assertTrue(result.contains("----> La tarea se elimino correctamente <----"));
    }

    @Test
    public void testDeleteTaskexp2() {

        Number key = 25.2;
        String title = "Tarea de prueba numero 2";
        String description = "Descripción de prueba numero 2";
        Date limitDate = new Date();
        int priority = 2;

        controller.insertTask(key, title, description, limitDate, priority);

        String result = controller.deleteTask(key);

        assertNotNull(result);
        assertTrue(result.contains("----> La tarea se elimino correctamente <----"));
    }

    @Test
    public void testPush() {

        String testData = "Dato de prueba";
        stack.push(testData);

        assertNotNull(stack.top);
        assertEquals(testData, stack.top.getValue());
        assertEquals(1, stack.size());
    }

    @Test
    public void testPop() {

        String testData = "Dato de prueba";
        stack.push(testData);

        String result = stack.pop();

        assertNotNull(result);
        assertEquals(testData, result);

        assertEquals(0, stack.size());
        assertNull(stack.top);
    }

    @Test
    public void testPopEmptyStackException() {
        assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        });
    }
}
