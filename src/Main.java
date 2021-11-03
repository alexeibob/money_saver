package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
public class Main {
    static Scanner scan = new Scanner(System.in);

    static int ChoiceInput() {
        System.out.println("Выберите способ работы с программой.");
        System.out.println("1 - ввод из консоли. 2 - ввод из файла.");
        int choice;
        choice = 0;
        boolean IsCorrect;
        do {
            IsCorrect = true;
            try {
                choice = Integer.parseInt(scan.nextLine());
            }catch (Exception e) {
                System.out.println("Данные введены некорректно. Введите Цифру 1 или 2.");
                IsCorrect = false;
            }
            if ((choice != 1 && choice != 2)) {
                System.out.println("Данные введены некорректно. Введите Цифру 1 или 2.");
                IsCorrect = false;
            }
        } while (IsCorrect == false);
        return choice;
    }

    static int ReturnMatrixSizeConsole() {
        boolean IsCorrect;
        int size;
        size = 0;
        System.out.println("Введите размер будущей матрицы это целое число в интервале [2;10].");
        do {
            IsCorrect = true;
            try {
                size = Integer.parseInt(scan.nextLine());
            }catch (Exception e) {
                System.out.println("Данные введены некорректно, размер будущей матрицы это целое число в интервале [2;10]. Повторите ввод.");
                IsCorrect = false;
                size = 2;
            }
            if(size < 2 || size > 10) {
                IsCorrect = false;
                System.out.println("Число должно быть целым в интервале [2;10]. Повторите ввод.");
            }
        } while (IsCorrect == false);
        return size;
    }

    static int ReturnCorrectNumberConsole() {
        boolean IsCorrect;
        int number;
        number = 0;
        do {
            IsCorrect = true;
            try {
                number = Integer.parseInt(scan.next());
            }catch (Exception e) {
                System.out.println("Данные введены некорректно, Элементы будущей матрицы это целые числа в интервале [-30,30]. Повторите ввод.");
                IsCorrect = false;
                number = 2;
            }
            if (number < -30 || number > 30) {
                IsCorrect = false;
                System.out.println("Элементы будущей матрицы это целые числа в интервале [-30,30]. Повторите ввод.");
            }
        } while (IsCorrect == false);
        return number;
    }

    static int[][] InputMatrixConsole(int size){
        int[][] Matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int g = 0; g < size; g++) {
                Matrix[i][g] = 								ReturnCorrectNumberConsole();
            }
        }
        return Matrix;
    }

    static Boolean TryInputAmountFromFile(String way) {
        File my_file = new File(way);
        String text;
        Boolean IsCorrect;
        IsCorrect = true;
        try (FileReader fr = new FileReader(way)) {
            Scanner scan = new Scanner(fr);
            if (scan.hasNextLine() == true) {
                text = scan.nextLine();
            }else {
                IsCorrect = false;
                System.out.println("Этот файл пустой. Введите новый путь к файлу.");
            }
        }catch (IOException ex) {
            IsCorrect = false;
            System.out.println("Ошибка считавания из файла введите путь к новому.");
        }
        return IsCorrect;
    }

    static String InputWayFile() throws Exception {
        String InputWay;
        boolean IsCorrect;
        do {
            IsCorrect = true;

            InputWay = scan.nextLine();
            if (Files.exists(Path.of(InputWay)) == false) {
                IsCorrect = false;
                System.out.println("Не удалось найти файл по этому пути. Повторите ввод.");
            }
            else {
                IsCorrect = TryInputAmountFromFile(InputWay);
            }

        }while(IsCorrect == false);
        return InputWay;
    }

    static String FindInputFile () throws Exception {
        String InputWay;
        boolean IsCorrect;
        int amount;
        do {
            IsCorrect = true;
            InputWay = InputWayFile();
            IsCorrect = CheckAmount(InputWay);
            if (IsCorrect == true) {
                amount = InputAmount(InputWay);
                IsCorrect = TryReadMatrixFromFile(InputWay, amount);
            }

        }while(IsCorrect == false);
        return InputWay;
    }

    static Boolean CheckAmount(String InputWay) throws 	 Exception {
        int amount;
        amount = 0;
        boolean IsCorrect;
        String text;
        FileReader fr = new FileReader(InputWay);
        Scanner scan = new Scanner(fr);
        text = scan.nextLine();
        IsCorrect = true;
        try {
            amount = Integer.parseInt(text);
        } catch (Exception e) {
            System.out.println("Из файла считается число, это 					     должно быть целое число.");
            System.out.println("Введите путь к новому файлу.");
            IsCorrect = false;
        }
        if (IsCorrect == true) {
            if (amount < 2 || amount > 20) {
                System.out.println("Из файла считается размер 						   матрицы, это должно быть 						   целое число в 								   интервале[2,20].");
                System.out.println("Введите путь к новому 							   файлу.");
                IsCorrect = false;

            }
        }
        return IsCorrect;
    }
    static int InputAmount(String InputWay) throws Exception {
        String text;
        int amount;
        FileReader fr = new FileReader(InputWay);
        Scanner scan = new Scanner(fr);
        text = scan.nextLine();
        amount = Integer.parseInt(text);
        return amount;

    }

    static boolean TryReadMatrixFromFile(String InPutWay, int 	   amount) throws Exception {
        int stet;
        stet = 0;
        int number;
        number = -1;
        boolean IsCorrect, FactorsInInterval;
        FactorsInInterval = true;
        Scanner fileReader = new Scanner (new File(InPutWay));
        amount = amount * amount;
        String text;
        text = fileReader.nextLine();
        IsCorrect = true;
        while(fileReader.hasNext() == true && IsCorrect == 	 true) {
            try {
                number = fileReader.nextInt();
            } catch(NumberFormatException e) {
                IsCorrect = false;
                System.out.println("Ошибка ввода элементов 							   матрицы.");
            }
            stet++;
            if (number < -30 || number > 30) {
                FactorsInInterval = false;
            }
        }
        if (IsCorrect == true) {
            if(stet  == amount && FactorsInInterval == true) {
                IsCorrect = true;
                System.out.println("Эти элементы по количеству 						   подходят в качестве 							   элементов будущей матрицы и 						   их значения также 								   подходят.");
            }
            else
            if (stet  != amount && FactorsInInterval == 		     true){
                IsCorrect = false;
                System.out.println("В файле должно быть 							   значения для элементов  							   будущей матрицы, не больше 						   не меньше.");
            }
            else
            if (stet  == amount && FactorsInInterval 		      == false) {
                IsCorrect = false;
                System.out.println("Элементы будущей 								    матрицы,есть целые 							    числа в интервале 							    [-30;30].");
            }
            else {
                IsCorrect = false;
                System.out.println("Эти элементы ни по 							   количеству, ни по значению 						   для будущей матрицы не 							   подходят.");
            }
        }
        return IsCorrect;
    }

    static int[][] MatrixFile(int size, String way) throws 	 Exception {
        int[][] Matrix = new int[size][size];
        int number;
        FileReader fr= new FileReader(way);
        Scanner scan = new Scanner(fr);
        number = scan.nextInt();
        for (int i = 0; i < size; i++) {
            for (int g = 0; g < size; g++) {
                Matrix[i][g] = scan.nextInt();
            }
        }
        fr.close();
        return Matrix;
    }

    static int[] ReturnCoordinates(int [][]array, int size) {
        int max;
        boolean IsCorrect;
        int i;
        int[] Coordinates = new int[2];
        i = 0;
        max = 0;
        IsCorrect = false;
        while (i < size && IsCorrect == false) {
            IsCorrect = true;
            max = 0;
            for (int g = 1; g < size; g++) {
                if (array[i][g] < array[i][max]) {
                    max = g;
                }
            }
            for (int g = 0; g < size; g++) {
                if (array[i][max] == array[i][g]) {
                    for (int k = 0; k < size; k++) {
                        if (array[k][max] > array[i][max]) {
                            IsCorrect = false;
                        }
                    }
                }
            }
            Coordinates[0] = i;
            Coordinates[1] = max;
            i++;
        }
        if (IsCorrect == false) {
            Coordinates[0] = -1;
        }
        return Coordinates;
    }

    static void WriteInformation(int[][] array, int size,int[] 	   Coordinates)throws Exception {
        if (Coordinates[0] == -1) {
            System.out.println("В данной матрице нету седловой 					     точки.");
        }
        else {
            System.out.println("В матрице есть седловая 						     точка.");
            System.out.println("Её координаты i = " + 							     Coordinates[0]);
            System.out.println("g = " + Coordinates[1]);
            System.out.println("И значение в этой точке : " + 				          array[Coordinates[0]]
                    [Coordinates[1]]);
        }
        System.out.println("Вот наша матрица: ");
        for (int i = 0; i < size; i++) {
            for (int g = 0; g < size; g++) {

                System.out.print(" " + array[i][g]);

            }
            System.out.println("");
        }
    }

    static void WriteInformationIntoFile(int[][] array, int 		   size, String OutputWay, int[] Coordinates) throws 	        	   Exception {
        FileWriter writer = new FileWriter(OutputWay);
        if (Coordinates[0] == -1) {
            writer.write("В данной матрице нету седловой 					    точки.\n");
        }
        else {
            writer.write("В матрице есть седловая точка.\n");
            writer.write("Её координаты i = " + Coordinates[0] 				    + "\n");
            writer.write("g = " + Coordinates[1] + "\n");
            writer.write("И значение в этой точке : " + 					    array[Coordinates[0]]
                    [Coordinates[1]] + "\n");
        }
        writer.write("Вот наша матрица: \n");
        for (int i = 0; i < size; i++) {
            for (int g = 0; g < size; g++) {

                writer.write(" " + array[i][g]);

            }
            writer.write("\n");
        }
        writer.close();
        System.out.println("Данные записаны в файл.");
    }

    static String OutputFile(){
        String OutputWay;
        boolean IsCorrect;
        do {
            IsCorrect = true;
            OutputWay = scan.nextLine();
            if (Files.exists(Path.of(OutputWay)) == false) {
                IsCorrect = false;
                System.out.println("Не удалось найти файл по 						   этому пути. Повторите 							   ввод.");
            }
            else {
                try(FileReader fr = new 									FileReader(OutputWay)){
                    Scanner scan = new Scanner(fr);
                    if (scan.hasNextLine() == true) {
                        IsCorrect = false;
                        System.out.println("Этот файл не 									пустой. Введите 									новый путь к 									файлу.");
                    }else {
                        System.out.println("Этот файл 										подойдёт.");
                    }
                }catch (IOException ex) {
                    IsCorrect = false;
                    System.out.println("Ошибка считывания из 							  файла введите путь к 							  новому.");
                }
            }
        }while(IsCorrect == false);
        return OutputWay;
    }

    static void PrintTask() {
        System.out.println("Дана квадратная матрица А порядка 					 n. Найти седловую точку матрицы.");
        System.out.println("Т.е. элемент, который является 						 наименьшим в своей строке и 						 наибольшим в своем столбце.");
    }

    static void WorkWithMatrix(int[][] Matrix, int size) 	 throws Exception {
        String OutputWay;
        int[] Coordinates = new int[2];
        Coordinates = ReturnCoordinates(Matrix, size);
        WriteInformation(Matrix, size, Coordinates);
        System.out.println("Введите путь к файлу для записи 					      данных, это должен быть пустой 						 файл.");
        OutputWay = OutputFile();
        WriteInformationIntoFile(Matrix, size, OutputWay, 	 Coordinates);
    }

    static void Main() throws Exception {
        PrintTask();
        int size, IOChoice;
        String InputWay;
        int[][] Matrix;
        IOChoice = ChoiceInput();
        switch (IOChoice)
        {
            case 1:
                size = ReturnMatrixSizeConsole();
                System.out.println("Введите элементы будущей матрицы.");
                Matrix = InputMatrixConsole(size);
                WorkWithMatrix(Matrix, size);
                break;
            case 2:
                System.out.println("Введите путь к файлу для 						   считывания размера и 							   элементов матрицы.");
                InputWay = FindInputFile();
                size = InputAmount(InputWay);
                Matrix = MatrixFile(size, InputWay);
                WorkWithMatrix(Matrix, size);
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        Main();
    }
