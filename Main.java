import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Lab4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n МЕНЮ ЛАБОРАТОРНОЇ РОБОТИ №4");
            System.out.println("1 — Завдання 1 (Варіант 1: Фільтрація маленьких латинських літер та сортування рядків)");
            System.out.println("2 — Завдання 2 (Варіант 4: Підрахунок кількості значущих символів у файлі)");
            System.out.println("0 — Вихід");
            System.out.print("Оберіть пункт меню: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    runTask1(scanner);
                    break;
                case 2:
                    runTask2(scanner);
                    break;
                case 0:
                    System.out.println("Вихід з програми.");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void runTask1(Scanner scanner) {
        BufferedReader reader = null;

        while (reader == null) {
            System.out.print("Введіть назву вхідного файлу для Завдання 1: ");
            String inputFileName = scanner.nextLine();
            try {
                reader = new BufferedReader(new FileReader(inputFileName));
            } catch (FileNotFoundException e) {
                System.out.println("Файл не знайдено або шлях введено невірно. Спробуйте ще раз.");
            }
        }

        ArrayList<String> processedLines = new ArrayList<>();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c >= 'a' && c <= 'z') {
                        sb.append(c);
                    }
                }

                String filteredStr = sb.toString();
                if (!filteredStr.isEmpty()) {
                    processedLines.add(filteredStr);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
            return;
        }

        Collections.sort(processedLines);

        System.out.print("Введіть назву вихідного файлу для збереження результату: ");
        String outputFileName = scanner.nextLine();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            for (String s : processedLines) {
                writer.write(s);
                writer.newLine();
            }
            writer.close();
            System.out.println("Результат успішно записано у файл '" + outputFileName + "'!");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    public static void runTask2(Scanner scanner) {
        BufferedReader reader = null;

        while (reader == null) {
            System.out.print("Введіть назву файлу для підрахунку символів: ");
            String fileName = scanner.nextLine();
            try {
                reader = new BufferedReader(new FileReader(fileName));

                long charCount = 0;
                int c;
                while ((c = reader.read()) != -1) {
                    char ch = (char) c;
                    if (ch != ' ' && ch != '\n' && ch != '\r' && ch != '\t') {
                        charCount++;
                    }
                }
                reader.close();

                System.out.println("Файл: " + fileName);
                System.out.println("Кількість значущих символів (без пробілів і переносів рядків): " + charCount);

            } catch (FileNotFoundException e) {
                System.out.println("Файл не знайдено. Будь ласка, перевірте назву та спробуйте знову.");
            } catch (IOException e) {
                System.out.println("Помилка при читанні файлу: " + e.getMessage());
                break;
            }
        }
    }
}