package ui;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Controller;

public class Main {
    Scanner sc;
    Controller<Number> controller;

    public Main() {
        sc = new Scanner(System.in);
        controller = new Controller<>();
    }

    public static void main(String[] args) {
        Main green = new Main();

        int opc;

        do {
            opc = green.menu();

            green.ejecutar(opc);
        } while (opc != 6);
    }

    public int menu() {
        System.out.println("\nBienvenido a Notion \n");
        System.out.println("Escoge un opcion segun lo que desees hacer\n");
        System.out.println("1) Registrar tarea/recordatorio\n");
        System.out.println("2) Eliminar una tarea\n");
        System.out.println("3) Modificar una tarea\n");
        System.out.println("4) Ver tareas \n");
        System.out.println("5) Ver acciones\n");

        int n = sc.nextInt();
        sc.nextLine();

        return n;
    }

    public void registerTask() {
        System.out.println("\nIngrese el identificador para esta tarea\n");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("\nEscriba el tema principal de la tarea\n ");
        String title = sc.nextLine();

        System.out.println("\nEscriba una breve descripcion para esta tarea\n");
        String description = sc.nextLine();

        System.out.println("\nDigite la fecha limite para la tarea en el siguiente formato\n\nDD/MM/YYYY\n");
        String date = sc.nextLine();

        Date fechaactualizada = null;

        try {
            fechaactualizada = controller.parseo(date);
        } catch (InputMismatchException e) {
            System.out.println(e);
        }

        System.out.println(
                "\nDigite el numero segun corresponda al nivel de prioridad de la tarea\n\n1)Priority\n\n2)No priority\n");
        int priority = sc.nextInt();
        sc.nextLine();

        System.out.println(controller.insertTask(id, title, description, fechaactualizada, priority));
    }

    public void deleteTask() {
        System.out.println("\nDigite el identificador de la tarea que desea eliminar\n");
        int delete = sc.nextInt();

        System.out.println(controller.deleteTask(delete));

    }

    public void modifyTask() {
        System.out.print("\nIntroduzca el ID de la tarea a modificar:\n\n");
        int taskID = sc.nextInt();
        sc.nextLine();

        System.out.println(
                "\n¿Que deseas cambiar de la tarea?\n\n1) Title\n2) Description\n3) Limit Date\n4) Priority\n");
        int opc = sc.nextInt();
        sc.nextLine();

        if (opc >= 1 && opc <= 4) {
            System.out.println("\nEscriba el cambio que desea realizar\n");
            String cambio = sc.nextLine();
            System.out.println(controller.modifyTask(taskID, opc, cambio));
        } else {
            System.out.println("\n¡Opcion invalida!\n");
        }
    }

    public int seeTasks() {
        System.out.println("\n¿Que tareas deseas ver?\n\n1) Priority\n\n2) Not priority\n");
        int opc = sc.nextInt();
        sc.nextLine();

        return opc;
    }

    public void seeNotPriorityTask() {
        controller.notPriorityTask();
    }

    public void seeActions() {
        controller.printStack();
        ;
    }

    public void ejecutar(int n) {
        switch (n) {
            case 1:
                registerTask();
                break;
            case 2:
                deleteTask();
                break;
            case 3:
                modifyTask();
                break;
            case 4:
                int opc = seeTasks();
                if (opc == 1) {

                } else if (opc == 2) {
                    seeNotPriorityTask();
                } else {
                    System.out.println("\n¡Opcion no valida!\n");
                }
                break;
            case 5:
                seeActions();
                break;
            default:
                break;
        }
    }
}
