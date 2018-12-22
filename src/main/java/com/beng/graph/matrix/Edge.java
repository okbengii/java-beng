package com.beng.graph.matrix;

/**
 * 这个类表名一条边，起始点和终点，和长度
 * 
 * @author apple
 */
public class Edge {

    /**
     * 始发城市
     */
    private String beginCity;
    /**
     * 终点城市
     */
    private String endCity;
    /**
     * 距离
     */
    private int cost;

    public Edge(String beginCity, String endCity, int cost) {
        super();
        this.beginCity = beginCity;
        this.endCity = endCity;
        this.cost = cost;
    }

    public String getBeginCity() {
        return beginCity;
    }

    public void setBeginCity(String beginCity) {
        this.beginCity = beginCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
