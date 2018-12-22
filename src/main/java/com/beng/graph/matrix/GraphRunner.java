package com.beng.graph.matrix;

import java.util.ArrayList;
import java.util.List;

import com.beng.app.Runner;

public class GraphRunner implements Runner {

    public static void main(String[] args) {
        new GraphRunner().run();
    }

    @Override
    public void run() {
        Graph graph = createGraph(true);
        System.out.println("图的矩阵如下：");
        graph.print();

        System.out.println("深度优先遍历顺序如下：");
        int[] visit = new int[graph.getSize()];
        graph.dfs(0, visit);
    }

    /**
     * @param direction
     *            是否是有向图
     * @return
     */
    public static Graph createGraph(boolean direction) {
        String[] citys = new String[] { "北京", "上海", "广州", "重庆", "武汉", "南昌" };

        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge("北京", "广州", 10));
        edgeList.add(new Edge("北京", "上海", 11));
        edgeList.add(new Edge("上海", "南昌", 6));
        edgeList.add(new Edge("广州", "重庆", 14));
        edgeList.add(new Edge("广州", "武汉", 9));
        edgeList.add(new Edge("重庆", "武汉", 20));
        edgeList.add(new Edge("武汉", "北京", 13));
        edgeList.add(new Edge("武汉", "南昌", 12));
        edgeList.add(new Edge("南昌", "广州", 18));

        Edge[] edgeArray = new Edge[edgeList.size()];

        return new Graph(citys, edgeList.toArray(edgeArray), direction);
    }

}
