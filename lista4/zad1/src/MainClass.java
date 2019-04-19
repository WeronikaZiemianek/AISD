import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class MainClass {

    public static void main(String args[]) throws IOException {

        int insert = 0;
        int load = 0;
        int delete = 0;
        int find = 0;
        int min = 0;
        int max = 0;
        int successor = 0;
        int inorder = 0;

        int maxElem = 0;
        int currentElem = 0;

        double startTime;
        double estimatedTime;

        Path file = Paths.get("Log.txt");
        Files.deleteIfExists(file);
        Files.newOutputStream(file, CREATE, APPEND);

        int type = 0;

        Scanner scanner = new Scanner(System.in);
        String typeString = scanner.nextLine();
        if (typeString.compareTo("--type bst") == 0) type = 1;
        else if (typeString.compareTo("--type rbt") == 0) type = 2;
        else if (typeString.compareTo("--type hmap") == 0) type = 3;
        else if (typeString.compareTo("--type test") == 0) type = 4;

        startTime = System.currentTimeMillis();

        if (type == 1) {
            BinaryTree<String> binTree = new BinaryTree<>();

            String whatToDo;
            String value;
            while (true) {
                whatToDo = scanner.nextLine();

                if (whatToDo.contains("insert")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    value = insertStringCheck(value);
                    if (value != "") {
                        binTree.insert(value);
                        insert++;
                        currentElem++;
                        if (currentElem > maxElem) maxElem = currentElem;

                        estimatedTime = System.currentTimeMillis() - startTime;
                        List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                        Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                    }
                } else if (whatToDo.contains("load")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    currentElem += insertFromFile(value, binTree);
                    load++;
                    if (currentElem > maxElem) maxElem = currentElem;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("delete")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    binTree.delete(value);
                    delete++;
                    currentElem--;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("find")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    System.out.println(binTree.find(value));
                    find++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("min")) {
                    binTree.min();
                    min++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("max")) {
                    binTree.max();
                    max++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("successor")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    TreeElem<String> temp = binTree.find(value);
                    binTree.PrintSuccessor(temp);
                    successor++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("inorder")) {
                    binTree.inorder();
                    inorder++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                }
            }
        } else if (type == 2) {
            RBTree<String> rbTree = new RBTree<>();

            String whatToDo;
            String value;
            while (true) {
                whatToDo = scanner.nextLine();

                if (whatToDo.contains("insert")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    value = insertStringCheck(value);
                    if (value != "") {
                        rbTree.insert(value);
                        insert++;
                        currentElem++;
                        if (currentElem > maxElem) maxElem = currentElem;

                        estimatedTime = System.currentTimeMillis() - startTime;
                        List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                        Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                    }
                } else if (whatToDo.contains("load")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    currentElem += insertFromFile(value, rbTree);
                    load++;
                    if (currentElem > maxElem) maxElem = currentElem;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("delete")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    rbTree.deleteFind(value);
                    delete++;
                    currentElem--;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("find")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    rbTree.printFind(value);
                    find++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("min")) {
                    rbTree.min();
                    min++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("max")) {
                    rbTree.max();
                    max++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("successor")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    rbTree.printSuccessor(value);
                    successor++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("inorder")) {
                    rbTree.inorder();
                    inorder++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                }
            }

        } else if (type == 3) {
            HMap hMap = new HMap();

            String whatToDo;
            String value;
            while (true) {
                whatToDo = scanner.nextLine();

                if (whatToDo.contains("insert")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    value = insertStringCheck(value);
                    hMap.insert(value);
                    insert++;
                    currentElem++;
                    if (currentElem > maxElem) maxElem = currentElem;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("load")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    currentElem += insertFromFile(value, hMap);
                    load++;
                    if (currentElem > maxElem) maxElem = currentElem;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("delete")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    hMap.delete(value);
                    delete++;
                    currentElem--;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("find")) {
                    value = whatToDo.substring(whatToDo.indexOf(" "));
                    value = removeFirstChar(value);
                    hMap.printFind(value);
                    find++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("min")) {
                    hMap.min();
                    min++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("max")) {
                    hMap.max();
                    max++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("successor")) {
                    hMap.successor();
                    successor++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                } else if (whatToDo.contains("inorder")) {
                    hMap.inorder();
                    inorder++;

                    estimatedTime = System.currentTimeMillis() - startTime;
                    List<String> lines = PreperLog(insert, load, delete, find, min, max, successor, inorder, maxElem, currentElem, estimatedTime);
                    Files.write(file, lines, Charset.forName("UTF-8"), APPEND);
                }
            }
        } else if (type == 4) {
            BinaryTree<String> binTree = new BinaryTree<>();
            RBTree<String> rbTree = new RBTree<>();
            HMap hMap = new HMap();

            file = Paths.get("Result.txt");
            Files.deleteIfExists(file);
            Files.newOutputStream(file, CREATE, APPEND);
            List<String> res = new ArrayList<>();

            List<String> elems = makeListFromFile("sample.txt");
            Collections.shuffle(elems);
            List<TreeElem<String>> bTreeElems = new ArrayList<>();
            List<RBTreeElem<String>> rbTreeElems = new ArrayList<>();


            // BinaryTree
            res.add("BinaryTree");
            //Insert
            startTime = System.currentTimeMillis();
            for (String s : elems) {
                binTree.insert(s);
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Insert: " + estimatedTime / elems.size());
            //Find
            startTime = System.currentTimeMillis();
            for (int i = 0; i < elems.size(); i++) {
                bTreeElems.add(binTree.find(elems.get(i)));
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Find: " + estimatedTime / elems.size());
            //Successor
            startTime = System.currentTimeMillis();
            for (int i = 0; i < elems.size(); i++) {
                binTree.successor(bTreeElems.get(i));
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Successor: " + estimatedTime / elems.size());
            //Min
            startTime = System.currentTimeMillis();
            binTree.min();
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Time of Min: " + estimatedTime);
            //Max
            startTime = System.currentTimeMillis();
            binTree.max();
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Time of Max: " + estimatedTime);
            //Inorder
            startTime = System.currentTimeMillis();
            binTree.inorder();
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Inorder: " + estimatedTime);
            res.add("");
            // Delete
            startTime = System.currentTimeMillis();
            for (String s : elems) {
                binTree.delete(s);
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Delete: " + estimatedTime / elems.size());

            // RedBlackTree
            res.add("RedBlackTree");
            //Insert
            startTime = System.currentTimeMillis();
            for (String s : elems) {
                rbTree.insert(s);
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Insert: " + estimatedTime / elems.size());
            //Find
            startTime = System.currentTimeMillis();
            for (int i = 0; i < elems.size(); i++) {
                rbTreeElems.add(rbTree.find(elems.get(i)));
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Find: " + estimatedTime / elems.size());
            //Successor
            startTime = System.currentTimeMillis();
            for (int i = 0; i < elems.size(); i++) {
                rbTree.successor(rbTreeElems.get(i));
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Successor: " + estimatedTime / elems.size());
            //Min
            startTime = System.currentTimeMillis();
            rbTree.min();
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Time of Min: " + estimatedTime);
            //Max
            startTime = System.currentTimeMillis();
            rbTree.max();
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Time of Max: " + estimatedTime);
            //Inorder
            startTime = System.currentTimeMillis();
            rbTree.inorder();
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Inorder: " + estimatedTime);
            res.add("");
            // Delete
            startTime = System.currentTimeMillis();
            for (String s : elems) {
                rbTree.deleteFind(s);
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Delete: " + estimatedTime / elems.size());

            // HMap
            res.add("HMap");
            //Insert
            startTime = System.currentTimeMillis();
            for (String s : elems) {
                hMap.insert(s);
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Insert: " + estimatedTime / elems.size());
            //Find
            startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                hMap.find(elems.get(i));
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Find: " + estimatedTime / elems.size());
            // Delete
            startTime = System.currentTimeMillis();
            for (String s : elems) {
                hMap.delete(s);
            }
            estimatedTime = System.currentTimeMillis() - startTime;
            res.add("Avr Time of Delete: " + estimatedTime / elems.size());

            Files.write(file, res, Charset.forName("UTF-8"), APPEND);
        }
    }

    public static String insertStringCheck(String s) {

        int asc;

        while (true) {
            if (s.length() == 0) break;
            asc = (int) s.charAt(0);
            if (!((asc >= 65 && asc <= 90) || (asc >= 97 && asc <= 122))) {
                s = removeFirstChar(s);
            } else
                break;
        }

        while (true) {
            if (s.length() == 0) break;
            asc = (int) s.charAt(s.length() - 1);
            if (!((asc >= 65 && asc <= 90) || (asc >= 97 && asc <= 122))) {
                s = removeLastChar(s);
            } else
                break;
        }
        return s;
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    private static String removeFirstChar(String str) {
        return str.substring(1, str.length());
    }

    private static int insertFromFile(String nameOfFile, BinaryTree<String> binTree) {
        int add = 0;
        Scanner plikWe = null;
        String value;
        try {
            plikWe = new Scanner(new BufferedReader(new FileReader(nameOfFile)));
            while (plikWe.hasNext()) {
                value = plikWe.next();
                insertStringCheck(value);
                binTree.insert(value);
                add++;
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            if (plikWe != null) {
                plikWe.close();
            }
        }
        return add;
    }

    private static int insertFromFile(String nameOfFile, RBTree<String> rbTree) {
        int add = 0;
        Scanner plikWe = null;
        String value;
        try {
            plikWe = new Scanner(new BufferedReader(new FileReader(nameOfFile)));
            while (plikWe.hasNext()) {
                value = plikWe.next();
                insertStringCheck(value);
                rbTree.insert(value);
                add++;
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            if (plikWe != null) {
                plikWe.close();
            }
        }
        return add;
    }

    private static int insertFromFile(String nameOfFile, HMap hMap) {
        int add = 0;
        Scanner plikWe = null;
        String value;
        try {
            plikWe = new Scanner(new BufferedReader(new FileReader(nameOfFile)));
            while (plikWe.hasNext()) {
                value = plikWe.next();
                insertStringCheck(value);
                hMap.insert(value);
                add++;
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            if (plikWe != null) {
                plikWe.close();
            }
        }
        return add;
    }

    private static List<String> makeListFromFile(String nameOfFile) {
        int add = 0;
        Scanner plikWe = null;
        List<String> list = new ArrayList<>();
        String value;
        try {
            plikWe = new Scanner(new BufferedReader(new FileReader(nameOfFile)));
            while (plikWe.hasNext()) {
                value = plikWe.next();
                insertStringCheck(value);
                list.add(value);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            if (plikWe != null) {
                plikWe.close();
            }
        }
        return list;
    }

    private static List<String> PreperLog(int insert, int load, int delete, int find, int min, int max, int successor, int inorder, int maxElem, int currentElem, double time) {

        List<String> log = new ArrayList<>();

        log.add("Insert: " + insert);
        log.add("load: " + load);
        log.add("delete: " + delete);
        log.add("find: " + find);
        log.add("min: " + min);
        log.add("max: " + max);
        log.add("successor: " + successor);
        log.add("inorder: " + inorder);
        log.add("maxElem: " + maxElem);
        log.add("currentElem: " + currentElem);
        log.add("time: " + time);
        log.add("");

        return log;
    }
}