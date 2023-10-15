package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class Controller<K> {
    HashTable<K, Task> tableHash;
    Cola<K> queue = new Cola<>();
    Stack<K> stack = new Stack<>();

    public Controller() {
        tableHash = new HashTable<>(100);
    }

    public String insertTask(K key, String title, String description, Date limitDate, int priority) {
        Task task = new Task(title, description, limitDate, getPriority(priority));
        String msg = "";
        if (tableHash.size() != tableHash.getNumOfBuckets()) {
            tableHash.put(key, task);
            if (task.getPriority() == Priority.NOTPRIORITY) {
                queue.enqueue(task);
            }
            String var = task.getTitle();
            String var2 = task.getDescription();
            Date var3 = task.getLimitDate();
            String action = "La tarea registrada es: " + var + "\n" +
                    "Descripcion: " + var2 + "\n" +
                    "Fecha limite: " + var3;
            stack.pushAction(action);
            ;
            msg = "\n----> Se ha registrado existosamente la tarea <----\n\n" +
                    "La tarea registrada es: " + task.getTitle() + "\n" +
                    "Descripcion: " + task.getDescription() + "\n" +
                    "Fecha limite: " + task.getLimitDate();
        } else {
            msg = "\n----> No se pudo registrar la tarea ya que alcanzo el limite posible <----";
        }
        return msg;
    }

    public Priority getPriority(int num) {
        switch (num) {
            case 1:
                return Priority.PRIORITY;
            case 2:
                return Priority.NOTPRIORITY;
        }
        return null;
    }

    public String deleteTask(K delete) {
        String msg = "";
        if (!tableHash.isEmpty()) {
            if (tableHash.getBoolean(delete) == true) {
                String var = tableHash.get(delete).getTitle();
                String var2 = tableHash.get(delete).getDescription();
                Date var3 = tableHash.get(delete).getLimitDate();
                Priority var4 = tableHash.get(delete).getPriority();
                String action = "";
                tableHash.remove(delete);
                action = "La tarea eliminada es: " + var + "\n" +
                        "Descripcion: " + var2 + "\n" +
                        "Fecha limite: " + var3 + "\n" +
                        "Prioridad: " + var4;
                stack.pushAction(action);
                msg = "\n----> La tarea se elimino correctamente <----";
            } else {
                msg = "\n----> No se encontro la tarea indicaste <----";
            }
        } else {
            msg = "\n----> Tu lista de tarea se encuentra vacia, por ende no se puede borrar ninguna tarea en especifico <----";
        }
        return msg;
    }

    public String modifyTask(K key, int opc, String value) {
        String msg = "";
        if (!tableHash.isEmpty()) {
            if (tableHash.getBoolean(key)) {
                Task task = tableHash.get(key);
                String var = task.getTitle();
                String var2 = task.getDescription();
                Date var3 = task.getLimitDate();
                Priority var4 = task.getPriority();
                String action = "";
                switch (opc) {
                    case 1:
                        task.setTitle(value);
                        tableHash.modify(key, task);
                        action = "La tarea modificada es: " + var + "\n" +
                                "Descripcion: " + var2 + "\n" +
                                "Fecha limite: " + var3 + "\n" +
                                "Prioridad: " + var4;
                        stack.pushAction(action);
                        msg = "\n----> Se ha modificado la tarea <----";
                        break;
                    case 2:
                        task.setDescription(value);
                        tableHash.modify(key, task);
                        action = "La tarea modificada es: " + var + "\n" +
                                "Descripcion: " + var2 + "\n" +
                                "Fecha limite: " + var3 + "\n" +
                                "Prioridad: " + var4;
                        stack.pushAction(action);
                        msg = "\n----> Se ha modificado la tarea <----";
                        break;
                    case 3:
                        task.setLimitDate(parseo(value));
                        tableHash.modify(key, task);
                        action = "La tarea modificada es: " + var + "\n" +
                                "Descripcion: " + var2 + "\n" +
                                "Fecha limite: " + var3 + "\n" +
                                "Prioridad: " + var4;
                        stack.pushAction(action);
                        msg = "\n----> Se ha modificado la tarea <----";
                        break;
                    case 4:
                        task.setPriority(getPriority(Integer.parseInt(value)));
                        tableHash.modify(key, task);
                        action = "La tarea modificada es: " + var + "\n" +
                                "Descripcion: " + var2 + "\n" +
                                "Fecha limite: " + var3 + "\n" +
                                "Prioridad: " + var4;
                        stack.pushAction(action);
                        msg = "\n----> Se ha modificado la tarea <----";
                        break;
                }
            } else {
                msg = "\n----> No se encontro la tarea que indicaste <----";
            }
        } else {
            msg = "\n----> Tu lista de tarea se encuentra vacia, por ende no se puede modificar ninguna tarea en especifico <----";
        }
        return msg;
    }

    public Date parseo(String fecha) throws InputMismatchException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = null;

        String date = fecha;

        try {
            testDate = df.parse(date);
        } catch (Exception e) {
            throw new InputMismatchException("Format invalid");
        }

        if (!df.format(testDate).equals(date)) {
            throw new InputMismatchException("Format invalid");
        }
        return testDate;
    }

    public void notPriorityTask() {
        if (!queue.isEmpty()) {
            System.out.println("\n----> Tareas no prioritarias: <----\n");
            queue.print();
        } else {
            System.out.println("\n----> La lista no tiene tareas no prioritarias <----");
        }
    }

    public void printStack() {

        if (stack.isEmpty()) {
            return;
        }

        String x = stack.peek();

        stack.pop();

        System.out.print(x + "\n\n");

        printStack();

        stack.push(x);
    }

}
