package ru.academits.pronin.csv;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Требуется 2 аргумента - входной CSV и выходной HTML файлы.");
            return;
        }

        try {
            convertCsvToHtml(args[0], args[1]);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден.");
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла.");
        }
    }

    public static void convertCsvToHtml(String inputFilePath, String outputFilePath) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter writer = new PrintWriter(outputFilePath)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang=\"ru\">");
            writer.println("\t<head>");
            writer.println("\t\t<meta charset=\"utf-8\" />");
            writer.println("\t\t<title>Таблица</title>");
            writer.println("\t</head>");
            writer.println("\t<body>");
            writer.println("\t\t<table>");

            boolean areQuotesOpened = false;

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (!areQuotesOpened) {
                    writer.println("\t\t\t<tr>");
                    writer.print("\t\t\t\t<td>");
                }

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);

                    if (c == '"') {
                        if (areQuotesOpened && (i < line.length() - 1) && (line.charAt(i + 1) == '"')) {
                            writer.print('"');
                            i++;
                        } else {
                            areQuotesOpened = !areQuotesOpened;
                        }
                    } else if (c == ',' && !areQuotesOpened) {
                        writer.println("</td>");
                        writer.print("\t\t\t\t<td>");
                    } else if (c == '<') {
                        writer.print("&lt;");
                    } else if (c == '>') {
                        writer.print("&gt;");
                    } else if (c == '&') {
                        writer.print("&amp;");
                    } else {
                        writer.print(c);
                    }
                }

                if (areQuotesOpened) {
                    writer.print("<br/>");
                } else {
                    writer.println("</td>");
                    writer.println("\t\t\t</tr>");
                }
            }

            writer.println("\t\t</table>");
            writer.println("\t</body>");
            writer.print("</html>");
        }
    }
}