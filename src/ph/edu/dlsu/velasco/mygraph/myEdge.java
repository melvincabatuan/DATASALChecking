package ph.edu.dlsu.velasco.mygraph;

import ph.edu.dlsu.velasco.facepamphlet.*;


public class myEdge {

    private String vert1, vert2; 
    private int weight; // The vertex indices

    public myEdge(String vt1, String vt2, int wt) {
        vert1 = vt1;
        vert2 = vt2;
        weight = wt;
    }

    public String v1() {
        return vert1;
    }

    public String v2() {
        return vert2;
    }

    public int weight() {
        return weight;
    }
}
