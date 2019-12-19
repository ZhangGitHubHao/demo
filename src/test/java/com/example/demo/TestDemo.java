package com.example.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDemo {
    public static void main(String args[]) {
        int[] arr = new int[40000];
        for (int i = 0; i < 40000; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }
        int[] A = Arrays.copyOf(arr, arr.length);
        int[] B = Arrays.copyOf(arr, arr.length);
        int[] C = Arrays.copyOf(arr, arr.length);
        long start1 = System.currentTimeMillis();
        TestDemo roots = new TestDemo();
        for (int number : A) {
            roots.add(number);
        }

        System.out.println(roots.values());
        long end1 = System.currentTimeMillis();
        long time1 = end1 - start1;
        System.out.println("时间:" + time1);


        long start2 = System.currentTimeMillis();
        quickSort(C, 0, C.length-1);
        long end2 = System.currentTimeMillis();
        long time2 = end2 - start2;
        System.out.println("时间:" + time2);


        long start = System.currentTimeMillis();
        bubbleSort(B);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("时间:" + time);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 找出最大的和最小(非0)的那个文件，打印出他们的文件名
     */
    private static void findFile() {

        File maxFile = null;
        File minFile = null;
        Boolean flag = true;
        File file = new File("c:/Windows");
        if (!file.exists()) {
            System.out.println("文件或文件夹不存在");
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                System.out.println("目录为空");
                return;
            }
            for (File f : files) {
                if (flag && f.isFile() && f.length() != 0) {
                    maxFile = file;
                    minFile = file;
                    flag = false;
                    continue;
                }
                if (f.isFile() && f.length() > maxFile.length()) {
                    maxFile = f;
                } else if (f.isFile() && f.length() < minFile.length() && f.length() != 0) {
                    minFile = f;
                }
            }
        }

        System.out.println("最大文件:" + maxFile.getName() + " 文件长度:" + maxFile.length());
        System.out.println("最小文件:" + minFile.getName() + " 文件长度:" + minFile.length());
    }

    private static void method1() {
        ArrayList heros = new ArrayList();

        // 初始化5个对象
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero " + i));
        }
        Hero specialHero = new Hero("special hero");
        heros.add(specialHero);
        System.out.println(heros);
        Hero hs[] = (Hero[]) heros.toArray(new Hero[]{});
        System.out.println("数组:" + hs);

    }

    private static void bubbleSort(int[] arr) {
        //如果只有一个元素就不用排序了
        if (arr.length <= 1) return;
        // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
        boolean flag = false;
        for (int i = 0; i < arr.length; ++i) {
            // 此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面，数组下标又是从0开始的，
            // 所以i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
            for (int j = 0; j < arr.length - i - 1; ++j) {
                // 即这两个相邻的数是逆序的，交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;//没有数据交换，数组已经有序，退出排序
        }
    }


    private static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }


    // 左子节点
    private TestDemo leftNode;
    // 右子节点
    private TestDemo rightNode;

    // 值
    private Object value;

    // 插入 数据
    private void add(Object v) {
        // 如果当前节点没有值，就把数据放在当前节点上
        if (null == value)
            value = v;
            // 如果当前节点有值，就进行判断，新增的值与当前值的大小关系
        else {
            // 新增的值，比当前值小或者相同
            if ((Integer) v - ((Integer) value) <= 0) {
                if (null == leftNode)
                    leftNode = new TestDemo();
                leftNode.add(v);
            }
            // 新增的值，比当前值大
            else {
                if (null == rightNode)
                    rightNode = new TestDemo();
                rightNode.add(v);
            }
        }
    }

    // 中序遍历所有的节点
    public List<Object> values() {
        List<Object> values = new ArrayList<>();
        // 左节点的遍历结果
        if (null != leftNode)
            values.addAll(leftNode.values());
        // 当前节点
        values.add(value);
        // 右节点的遍历结果
        if (null != rightNode)
            values.addAll(rightNode.values());
        return values;
    }
}

class Hero {
    String name;

    public Hero(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(name, hero.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}