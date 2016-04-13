package com.ia.rubicks.cube;

import com.ia.rubicks.cube.Tree.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public abstract class RCube {

    private static List<String> movement_historic;

    private static Tree<HashMap<Integer, String>> treeOfCubes;

    public static void doJob(String initialMovements) {

        initializeCube();

        String movements = "";

        //initialize cube
        for (int i = 0; i < initialMovements.length(); i++) {
            if (movements.length() == 0 || movements.length() == 1) {
                movements += Character.toString(initialMovements.charAt(i));
            } else {

                switch (movements) {
                    case "Wh":
                        moveW(treeOfCubes.getRoot().getData(), true);
                        break;
                    case "Wa":
                        moveW(treeOfCubes.getRoot().getData(), false);
                        break;
                    case "Rh":
                        moveR(treeOfCubes.getRoot().getData(), true);
                        break;
                    case "Ra":
                        moveR(treeOfCubes.getRoot().getData(), false);
                        break;
                    case "Gh":
                        moveG(treeOfCubes.getRoot().getData(), true);
                        break;
                    case "Ga":
                        moveG(treeOfCubes.getRoot().getData(), false);
                        break;
                    case "Bh":
                        moveB(treeOfCubes.getRoot().getData(), true);
                        break;
                    case "Ba":
                        moveB(treeOfCubes.getRoot().getData(), false);
                        break;
                    case "Yh":
                        moveY(treeOfCubes.getRoot().getData(), true);
                        break;
                    case "Ya":
                        moveY(treeOfCubes.getRoot().getData(), false);
                        break;
                    case "Oh":
                        moveO(treeOfCubes.getRoot().getData(), true);
                        break;
                    case "Oa":
                        moveO(treeOfCubes.getRoot().getData(), false);
                        break;
                    default:
                        break;
                }
                movements = "";

            }

        }

        movement_historic = new ArrayList<>();

        //start searching for result
        doSearch();
    }

    private static void initializeCube() {
        //initialize list that holds the historic of the cube's movements
        HashMap<Integer, String> cube = new HashMap<>();

        int i = 0;

        cube.put(i, "R");
        i++;
        cube.put(i, "R");
        i++;
        cube.put(i, "R");
        i++;
        cube.put(i, "R");
        i++;
        cube.put(i, "R");
        i++;
        cube.put(i, "R");
        i++;
        cube.put(i, "R");
        i++;
        cube.put(i, "R");
        i++;
        cube.put(i, "B");
        i++;
        cube.put(i, "B");
        i++;
        cube.put(i, "B");
        i++;
        cube.put(i, "W");
        i++;
        cube.put(i, "W");
        i++;
        cube.put(i, "W");
        i++;
        cube.put(i, "G");
        i++;
        cube.put(i, "G");
        i++;
        cube.put(i, "G");
        i++;
        cube.put(i, "Y");
        i++;
        cube.put(i, "Y");
        i++;
        cube.put(i, "Y");
        i++;
        cube.put(i, "B");
        i++;
        cube.put(i, "B");
        i++;
        cube.put(i, "W");
        i++;
        cube.put(i, "W");
        i++;
        cube.put(i, "G");
        i++;
        cube.put(i, "G");
        i++;
        cube.put(i, "Y");
        i++;
        cube.put(i, "Y");
        i++;
        cube.put(i, "B");
        i++;
        cube.put(i, "B");
        i++;
        cube.put(i, "B");
        i++;
        cube.put(i, "W");
        i++;
        cube.put(i, "W");
        i++;
        cube.put(i, "W");
        i++;
        cube.put(i, "G");
        i++;
        cube.put(i, "G");
        i++;
        cube.put(i, "G");
        i++;
        cube.put(i, "Y");
        i++;
        cube.put(i, "Y");
        i++;
        cube.put(i, "Y");
        i++;
        cube.put(i, "O");
        i++;
        cube.put(i, "O");
        i++;
        cube.put(i, "O");
        i++;
        cube.put(i, "O");
        i++;
        cube.put(i, "O");
        i++;
        cube.put(i, "O");
        i++;
        cube.put(i, "O");
        i++;
        cube.put(i, "O");
        i++;

        treeOfCubes = new Tree<>(cube);
    }

    private static void doSearch() {

        Node<HashMap<Integer, String>> rootCube = treeOfCubes.getRoot();

        Queue q = new ConcurrentLinkedQueue<>();
        q.add(rootCube);

        while (!q.isEmpty()) {
            Node<HashMap<Integer, String>> n = (Node<HashMap<Integer, String>>) q.poll();
            n.isVisited = true;

            if (isDone(n.getData())) {
                System.out.println("Done! Printing movements needed to result the initial setup: ");
                Collections.reverse(movement_historic);

                String finalMovs = "";
                for (int i = 0; i < movement_historic.size(); i++) {
                    finalMovs = movement_historic.get(i) + " ";
                }

                System.out.println(finalMovs);
                return;
            }

            generateChildrens(n);

            List<Node<HashMap<Integer, String>>> childrens = n.getChildren();

            for (Node<HashMap<Integer, String>> children : childrens) {

                System.out.println(printData(children.getData()));
                
                if (children.isVisited == false) {
                    q.add(children);
                }
            }

        }

    }

    private static String printData(HashMap<Integer, String> data) {
        String s = "";
        for (int i = 0; i < data.size(); i++) {
            s += data.get(i);
        }
        return s;
    }

    private static void generateChildrens(Tree.Node<HashMap<Integer, String>> cube) {
        cube.addChild(cube, moveW(cube.getData(), true));
        cube.addChild(cube, moveW(cube.getData(), false));
        cube.addChild(cube, moveR(cube.getData(), true));
        cube.addChild(cube, moveR(cube.getData(), false));
        cube.addChild(cube, moveG(cube.getData(), true));
        cube.addChild(cube, moveG(cube.getData(), false));
        cube.addChild(cube, moveY(cube.getData(), true));
        cube.addChild(cube, moveY(cube.getData(), false));
        cube.addChild(cube, moveO(cube.getData(), true));
        cube.addChild(cube, moveO(cube.getData(), false));
        cube.addChild(cube, moveB(cube.getData(), true));
        cube.addChild(cube, moveB(cube.getData(), false));
    }

    private static boolean isDone(HashMap<Integer, String> cube) {
        int number_of_white_faces = 0;

        if ("W".equals(cube.get(12))) {
            number_of_white_faces++;
        }
        if ("W".equals(cube.get(13))) {
            number_of_white_faces++;
        }
        if ("W".equals(cube.get(14))) {
            number_of_white_faces++;
        }
        if ("W".equals(cube.get(23))) {
            number_of_white_faces++;
        }
        if ("W".equals(cube.get(24))) {
            number_of_white_faces++;
        }
        if ("W".equals(cube.get(32))) {
            number_of_white_faces++;
        }
        if ("W".equals(cube.get(33))) {
            number_of_white_faces++;
        }
        if ("W".equals(cube.get(34))) {
            number_of_white_faces++;
        }

        return (number_of_white_faces == 8);
    }

    private static HashMap<Integer, String> moveW(HashMap<Integer, String> curentCube, boolean isClockwise) {
        HashMap<Integer, String> cube = (HashMap<Integer, String>) curentCube.clone();

        String p12 = cube.get(11);
        String p13 = cube.get(12);
        String p14 = cube.get(15);
        String p23 = cube.get(24);
        String p24 = cube.get(25);
        String p32 = cube.get(33);
        String p33 = cube.get(35);
        String p34 = cube.get(36);

        String p11 = cube.get(10);
        String p22 = cube.get(21);
        String p31 = cube.get(30); // BLUE RIGHT
        String p6 = cube.get(5);
        String p7 = cube.get(6);
        String p8 = cube.get(7); //RED BOTTOM
        String p15 = cube.get(14);
        String p25 = cube.get(24);
        String p35 = cube.get(34); //GREEN LEFT
        String p41 = cube.get(40);
        String p42 = cube.get(41);
        String p43 = cube.get(42); //ORANGE TOP

        if (isClockwise) {
            cube.put(11, p32);
            cube.put(13, p12);
            cube.put(33, p14);
            cube.put(31, p34);
            cube.put(12, p23);
            cube.put(23, p13);
            cube.put(32, p24);
            cube.put(22, p33);

            //W : R -> G -> O -> B
            cube.put(14, p6);
            cube.put(24, p7);
            cube.put(34, p8);
            cube.put(40, p15);
            cube.put(41, p25);
            cube.put(42, p35);
            cube.put(10, p41);
            cube.put(21, p42);
            cube.put(30, p43);
            cube.put(5, p11);
            cube.put(6, p22);
            cube.put(7, p31);
        } else {
            cube.put(11, p14);
            cube.put(13, p34);
            cube.put(33, p32);
            cube.put(31, p12);
            cube.put(12, p24);
            cube.put(23, p33);
            cube.put(32, p23);
            cube.put(22, p13);

            //W : B -> O -> G -> R
            cube.put(14, p41);
            cube.put(24, p42);
            cube.put(34, p43);
            cube.put(40, p11);
            cube.put(41, p22);
            cube.put(42, p31);
            cube.put(10, p6);
            cube.put(21, p7);
            cube.put(30, p8);
            cube.put(5, p15);
            cube.put(6, p25);
            cube.put(7, p35);
        }

        return cube;
    }

    private static HashMap<Integer, String> moveR(HashMap<Integer, String> curentCube, boolean isClockwise) {
        HashMap<Integer, String> cube = (HashMap<Integer, String>) curentCube;

        String p1 = cube.get(0);
        String p2 = cube.get(1);
        String p3 = cube.get(2);
        String p4 = cube.get(3);
        String p5 = cube.get(4);
        String p6 = cube.get(5);
        String p7 = cube.get(6);
        String p8 = cube.get(7);

        String p9 = cube.get(8);
        String p10 = cube.get(9);
        String p11 = cube.get(10); //BLUE TOP
        String p12 = cube.get(11);
        String p13 = cube.get(12);
        String p14 = cube.get(13); //WHITE TOP
        String p15 = cube.get(14);
        String p16 = cube.get(15);
        String p17 = cube.get(16); //GREEN TOP
        String p18 = cube.get(17);
        String p19 = cube.get(18);
        String p20 = cube.get(19); //YELLOW TOP

        if (isClockwise) {
            cube.put(0, p6);
            cube.put(2, p1);
            cube.put(7, p3);
            cube.put(5, p8);
            cube.put(1, p4);
            cube.put(4, p2);
            cube.put(6, p5);
            cube.put(3, p7);

            //R : B -> Y -> G -> W
            cube.put(17, p9);
            cube.put(18, p10);
            cube.put(19, p11);
            cube.put(14, p18);
            cube.put(15, p19);
            cube.put(16, p20);
            cube.put(11, p15);
            cube.put(12, p16);
            cube.put(13, p17);
            cube.put(8, p12);
            cube.put(9, p13);
            cube.put(10, p14);

        } else {
            cube.put(0, p3);
            cube.put(2, p8);
            cube.put(7, p6);
            cube.put(5, p1);
            cube.put(1, p5);
            cube.put(4, p7);
            cube.put(6, p4);
            cube.put(3, p2);

            //R: W -> G -> Y -> B
            cube.put(17, p15);
            cube.put(18, p16);
            cube.put(19, p17);
            cube.put(14, p12);
            cube.put(15, p13);
            cube.put(16, p14);
            cube.put(11, p9);
            cube.put(12, p10);
            cube.put(13, p11);
            cube.put(8, p18);
            cube.put(9, p19);
            cube.put(10, p20);
        }

        return cube;
    }

    private static HashMap<Integer, String> moveY(HashMap<Integer, String> curentCube, boolean isClockwise) {
        HashMap<Integer, String> cube = (HashMap<Integer, String>) curentCube;

        String p18 = cube.get(17);
        String p19 = cube.get(18);
        String p20 = cube.get(19);
        String p27 = cube.get(26);
        String p28 = cube.get(27);
        String p38 = cube.get(37);
        String p39 = cube.get(38);
        String p40 = cube.get(39);

        String p1 = cube.get(0);
        String p2 = cube.get(1);
        String p3 = cube.get(2);//RED TOP
        String p17 = cube.get(16);
        String p26 = cube.get(25);
        String p37 = cube.get(36);//GREEN RIGHT
        String p46 = cube.get(45);
        String p47 = cube.get(46);
        String p48 = cube.get(47);//ORANGE BOTTOM
        String p9 = cube.get(8);
        String p21 = cube.get(20);
        String p29 = cube.get(28); //BLUE LEFT

        if (isClockwise) {
            cube.put(17, p38);
            cube.put(19, p18);
            cube.put(39, p20);
            cube.put(37, p40);
            cube.put(18, p27);
            cube.put(27, p19);
            cube.put(38, p28);
            cube.put(26, p39);

            //Y : R -> B -> O -> G
            cube.put(8, p1);
            cube.put(20, p2);
            cube.put(28, p3);
            cube.put(45, p9);
            cube.put(46, p21);
            cube.put(47, p29);
            cube.put(16, p46);
            cube.put(25, p47);
            cube.put(36, p48);
            cube.put(0, p17);
            cube.put(1, p26);
            cube.put(2, p37);
        } else {
            cube.put(17, p20);
            cube.put(19, p40);
            cube.put(39, p38);
            cube.put(37, p18);
            cube.put(18, p28);
            cube.put(27, p39);
            cube.put(38, p27);
            cube.put(26, p19);

            //Y: G -> O -> B -> R
            cube.put(8, p46);
            cube.put(20, p47);
            cube.put(28, p48);
            cube.put(45, p17);
            cube.put(46, p26);
            cube.put(47, p37);
            cube.put(16, p1);
            cube.put(25, p2);
            cube.put(36, p3);
            cube.put(0, p9);
            cube.put(1, p21);
            cube.put(2, p29);
        }

        return cube;
    }

    private static HashMap<Integer, String> moveO(HashMap<Integer, String> curentCube, boolean isClockwise) {
        HashMap<Integer, String> cube = (HashMap<Integer, String>) curentCube;

        String p41 = cube.get(40);
        String p42 = cube.get(41);
        String p43 = cube.get(42);
        String p44 = cube.get(43);
        String p45 = cube.get(44);
        String p46 = cube.get(45);
        String p47 = cube.get(46);
        String p48 = cube.get(47);

        String p35 = cube.get(34);
        String p36 = cube.get(35);
        String p37 = cube.get(36);//GREEN BOTTOM
        String p32 = cube.get(31);
        String p33 = cube.get(32);
        String p34 = cube.get(33);//WHITE BOTTOM
        String p38 = cube.get(37);
        String p39 = cube.get(38);
        String p40 = cube.get(39);//YELLOW BOTTOM
        String p29 = cube.get(28);
        String p30 = cube.get(29);
        String p31 = cube.get(30);//BLUE BOTTOM

        if (isClockwise) {
            cube.put(40, p46);
            cube.put(42, p41);
            cube.put(47, p43);
            cube.put(45, p48);
            cube.put(41, p47);
            cube.put(44, p42);
            cube.put(46, p45);
            cube.put(43, p47);

            //O : G -> Y -> B -> W
            cube.put(37, p35);
            cube.put(38, p36);
            cube.put(39, p37);
            cube.put(28, p38);
            cube.put(29, p39);
            cube.put(30, p40);
            cube.put(31, p29);
            cube.put(32, p30);
            cube.put(33, p31);
            cube.put(34, p32);
            cube.put(35, p33);
            cube.put(36, p34);
        } else {
            cube.put(40, p43);
            cube.put(42, p48);
            cube.put(47, p46);
            cube.put(45, p41);
            cube.put(41, p45);
            cube.put(44, p47);
            cube.put(46, p44);
            cube.put(43, p42);

            //O: W -> B -> Y -> G
            cube.put(37, p29);
            cube.put(38, p30);
            cube.put(39, p31);
            cube.put(28, p32);
            cube.put(29, p33);
            cube.put(30, p34);
            cube.put(31, p35);
            cube.put(32, p36);
            cube.put(33, p37);
            cube.put(34, p38);
            cube.put(35, p39);
            cube.put(36, p40);
        }

        return cube;
    }

    private static HashMap<Integer, String> moveB(HashMap<Integer, String> curentCube, boolean isClockwise) {
        HashMap<Integer, String> cube = (HashMap<Integer, String>) curentCube;

        String p9 = cube.get(8);
        String p10 = cube.get(9);
        String p11 = cube.get(10);
        String p21 = cube.get(20);
        String p22 = cube.get(21);
        String p29 = cube.get(28);
        String p30 = cube.get(29);
        String p31 = cube.get(30);

        String p20 = cube.get(19);
        String p28 = cube.get(27);
        String p40 = cube.get(39); //YELLOW RIGHT
        String p1 = cube.get(0);
        String p4 = cube.get(3);
        String p6 = cube.get(5); //RED LEFT
        String p12 = cube.get(11);
        String p23 = cube.get(22);
        String p32 = cube.get(31); //WHITE LEFT
        String p41 = cube.get(40);
        String p44 = cube.get(43);
        String p46 = cube.get(45); //ORANGE LEFT

        if (isClockwise) {
            cube.put(8, p29);
            cube.put(10, p9);
            cube.put(30, p11);
            cube.put(28, p31);
            cube.put(9, p21);
            cube.put(21, p10);
            cube.put(29, p22);
            cube.put(20, p30);

            //B : Y -> R -> W -> O
            cube.put(0, p20);
            cube.put(3, p28);
            cube.put(5, p40);
            cube.put(11, p1);
            cube.put(22, p4);
            cube.put(31, p6);
            cube.put(40, p12);
            cube.put(43, p23);
            cube.put(45, p32);
            cube.put(19, p41);
            cube.put(27, p44);
            cube.put(39, p46);
        } else {
            cube.put(8, p11);
            cube.put(10, p31);
            cube.put(30, p29);
            cube.put(28, p9);
            cube.put(9, p22);
            cube.put(21, p30);
            cube.put(29, p21);
            cube.put(20, p10);

            //B : O -> W -> R -> Y
            cube.put(0, p12);
            cube.put(3, p23);
            cube.put(5, p32);
            cube.put(11, p41);
            cube.put(22, p44);
            cube.put(31, p46);
            cube.put(40, p20);
            cube.put(43, p28);
            cube.put(45, p40);
            cube.put(19, p1);
            cube.put(27, p4);
            cube.put(39, p6);
        }

        return cube;
    }

    private static HashMap<Integer, String> moveG(HashMap<Integer, String> curentCube, boolean isClockwise) {
        HashMap<Integer, String> cube = (HashMap<Integer, String>) curentCube;

        String p15 = cube.get(14);
        String p16 = cube.get(15);
        String p17 = cube.get(16);
        String p25 = cube.get(24);
        String p26 = cube.get(25);
        String p35 = cube.get(34);
        String p36 = cube.get(35);
        String p37 = cube.get(36);

        String p14 = cube.get(13);
        String p24 = cube.get(23);
        String p34 = cube.get(33); //WHITE RIGHT
        String p3 = cube.get(2);
        String p5 = cube.get(4);
        String p8 = cube.get(7); //RED RIGHT
        String p18 = cube.get(17);
        String p27 = cube.get(26);
        String p38 = cube.get(37);  //YELLOW LEFT
        String p41 = cube.get(40);
        String p44 = cube.get(43);
        String p46 = cube.get(45); //ORANGE LEFT

        if (isClockwise) {
            cube.put(14, p35);
            cube.put(16, p17);
            cube.put(36, p17);
            cube.put(34, p37);
            cube.put(15, p25);
            cube.put(25, p16);
            cube.put(35, p26);
            cube.put(24, p36);

            //G : Y -> O -> W -> R
            cube.put(40, p18);
            cube.put(43, p27);
            cube.put(45, p38);
            cube.put(13, p41);
            cube.put(23, p44);
            cube.put(33, p46);
            cube.put(2, p14);
            cube.put(4, p24);
            cube.put(7, p34);
            cube.put(17, p3);
            cube.put(26, p5);
            cube.put(37, p8);
        } else {
            cube.put(14, p17);
            cube.put(16, p37);
            cube.put(36, p35);
            cube.put(34, p15);
            cube.put(15, p26);
            cube.put(25, p36);
            cube.put(35, p25);
            cube.put(25, p16);

            //G : R -> W -> O -> Y 
            cube.put(40, p14);
            cube.put(43, p24);
            cube.put(45, p34);
            cube.put(13, p3);
            cube.put(23, p5);
            cube.put(33, p8);
            cube.put(2, p18);
            cube.put(4, p27);
            cube.put(7, p38);
            cube.put(17, p41);
            cube.put(26, p44);
            cube.put(37, p46);
        }

        return cube;
    }

}
