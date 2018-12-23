package com.beng.graph.matrix;

import java.util.Stack;

/**
 * @author apple
 */
public class Graph {

    private int size;
    private int[][] matrix;
    private String[] cityArray;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public String[] getCityArray() {
        return cityArray;
    }

    public void setCityArray(String[] cityArray) {
        this.cityArray = cityArray;
    }

    /**
     * @param cityArray
     *            所有城市的信息
     * @param edge
     *            所有的边
     * @param direction
     *            true 代表有向图；false 代表无向图
     */
    public Graph(String[] cityArray, Edge[] edge, boolean direction) {
        this.cityArray = cityArray;
        this.size = cityArray.length;
        matrix = new int[size][size]; // 构建矩阵

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (Edge e : edge) {
            int begin = findIndex(e.getBeginCity(), cityArray);
            int end = findIndex(e.getEndCity(), cityArray);
            matrix[begin][end] = e.getCost();
            if (!direction) {
                matrix[end][begin] = e.getCost();
            }
        }
    }

    /**
     * 找到城市在城市数组中的位置
     * 
     * @param city
     * @param cityArray
     * @return
     */
    public int findIndex(String city, String[] cityArray) {
        for (int i = 0; i < cityArray.length; i++) {
            if (city.equals(cityArray[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     * 
     * @param start
     *            始发地点
     * @param visit
     */
    public void dfs(int start, int[] visit) {
        System.out.print(cityArray[start] + "  ");
        // 记录访问的城市
        visit[start] = 1;
        for (int i = 0; i < visit.length; i++) {
            if (matrix[start][i] > 0 && visit[i] == 0 && matrix[start][i] != Integer.MAX_VALUE) {
                dfs(i, visit);
            }
        }
    }

    // 宽度优先遍历
    public void bfs(int start) {
        int visit[] = new int[size];
        Stack stack = new Stack<>();
        stack.push(start);
        // 根节点入队
        visit[start] = 1;
        while (!stack.isEmpty()) {
            // 根节点出队
            int index = (int) stack.pop();
            // 访问出队节点
            System.out.print(cityArray[index] + "  ");
            for (int i = 0; i < cityArray.length; i++) {
                if (matrix[index][i] != 0 && visit[i] == 0 && matrix[index][i] != Integer.MAX_VALUE) {
                    // 下一个节点入队
                    stack.push(i);
                    visit[i] = 1;
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            int[] ii = matrix[i];
            System.out.print(cityArray[i] + "  ");
            for (int j = 0; j < ii.length; j++) {
                System.out.printf("%-16d", ii[j]);
            }
            System.out.println();
        }
    }
}
