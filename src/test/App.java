package test;

import model.Controller;
import model.Stack;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

public class App {

    @Test
    public void testInsertTask() {

        Controller controller = new Controller<>();

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
    public void testDeleteTask() {

        Controller controller = new Controller<>();

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
    public void testPush() {
        Stack<String> stack = new Stack<>();

        String testData = "Dato de prueba";
        stack.push(testData);

        assertNotNull(stack.top);
        assertEquals(testData, stack.top.getValue());
        assertEquals(1, stack.size());
    }
}
