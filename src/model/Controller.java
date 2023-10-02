package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class Controller<K> {
    HashTable<K, Task> tableHash;

    public Controller() {
        tableHash = new HashTable<>(100);
    }

    public String insertTask(K key, String title, String description, Date limitDate, int priority) {
        Task task = new Task(title, description, limitDate, getPriority(priority));
        String msg = "";
        if (tableHash.size() != tableHash.getNumOfBuckets()) {
            tableHash.put(key, task);
            msg = "\n----> Se ha registrado existosamente la tarea <----";
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
                tableHash.remove(delete);
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
                switch (opc) {
                    case 1:
                        task.setTitle(value);
                        tableHash.modify(key, task);
                        msg = "\n----> Se ha modificado la tarea <----";
                        break;
                    case 2:
                        task.setDescription(value);
                        tableHash.modify(key, task);
                        msg = "\n----> Se ha modificado la tarea <----";
                        break;
                    case 3:
                        task.setLimitDate(parseo(value));
                        tableHash.modify(key, task);
                        msg = "\n----> Se ha modificado la tarea <----";
                        break;
                    case 4:
                        task.setPriority(getPriority(Integer.parseInt(value)));
                        tableHash.modify(key, task);
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
}
