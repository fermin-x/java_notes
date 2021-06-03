package com.fermin.dataStructuresAndAlgorithms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * n个人要晚上过桥，在任何时候最多两个人一组过桥，每组要有一只手电筒。在这n个人中只有一个手电筒能用，求这些人过桥所用的最短时间。
 * 输入的第一行给出n,接下来的n行给出每个人的过桥时间
 * 例如： 5 1 2 3 4 5
 * 最短时间： 16
 * <p>
 * 总共有多少中过桥方法
 */
public class CrossBridge {
    public static void main(String[] args) throws Exception {
        List<Guy> guysList = new ArrayList();
        guysList.add(new Guy("张三", 5));
        guysList.add(new Guy("李四", 1));
        guysList.add(new Guy("王五", 2));
        guysList.add(new Guy("赵六", 3));
        guysList.add(new Guy("二嘎", 4));
        guysList.add(new Guy("大毛", 5));

        cross(guysList);

    }


    /**
     * 剩下没过桥的人2组合
     *
     * @param guys
     * @return
     */
    public static List<List<Guy>> getTwo(List<Guy> guys) {
        List<List<Guy>> guysGroup = new ArrayList();
        for (int i = 0; i < guys.size(); i++) {
            for (int j = 0; j < guys.size(); j++) {
                if (i != j) {
                    List sets = new ArrayList(2);
                    // 选择过桥的两个人
                    Guy guy1 = guys.get(i);
                    Guy guy2 = guys.get(j);
                    sets.add(guy1);
                    sets.add(guy2);
                    guysGroup.add(sets);
                }
            }
        }
        return guysGroup;
    }

    /**
     * 剩下没有过桥的人过桥的
     */

    public static List<Guy> cross(List<Guy> guys) throws Exception {


        if (guys.size() < 2) {
            System.out.println(String.format("***************************************"));
            return null;
        }
        if (guys.size() == 2) {
            int cost = Math.max(guys.get(0).getCrossTime(), guys.get(1).getCrossTime());
            System.out.println(String.format("最后过桥的两人：%s , %s, 时间：%s", guys.get(0), guys.get(1), cost));
            System.out.println(String.format("***************************************"));
            return null;
        }

        System.out.println(String.format("======================"));
        System.out.println(String.format("还有%s个人没有过桥", guys.size()));

        List<List<Guy>> groups = getTwo(guys);
        for (int i = 0; i < groups.size(); i++) {
            // 选择过桥的两个人
            Guy guy1 = groups.get(i).get(0);
            Guy guy2 = groups.get(i).get(1);

            System.out.println(String.format("过桥的两人：%s , %s", guy1, guy2));
            for (int j = 0; j < groups.get(i).size(); j++) {
                List<Guy> newGuys = deepCopy(guys);
                //选择guy2回去
                System.out.println(String.format("%s过桥", groups.get(i).get(j)));
                System.out.println(String.format("%s回去接人", groups.get(i).get(1 - j)));
                newGuys.remove(groups.get(i).get(j));
                //过桥的时间
                int costTime = Math.max(guy1.getCrossTime(), guy2.getCrossTime()) + groups.get(i).get(1 - j).getCrossTime();
                System.out.println(String.format("用掉时间： %s", costTime));
                System.out.println(String.format("======================"));
                cross(newGuys);
            }
        }

        return null;
    }

    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    static class Guy implements Serializable {
        private String name;
        private int crossTime;

        public Guy(String name, int crossTime) {
            this.name = name;
            this.crossTime = crossTime;
        }

        public int getCrossTime() {
            return crossTime;
        }

        @Override
        public String toString() {
            return "Guy{" +
                    "name='" + name + '\'' +
                    ", crossTime=" + crossTime +
                    '}';
        }
    }

}
