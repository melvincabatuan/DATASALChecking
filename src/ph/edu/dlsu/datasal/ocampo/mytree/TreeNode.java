/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ocampo.mytree;

/**
 * Integer tree node
 */
public class TreeNode {
    private int data;
    
    public TreeNode(int newData) {
        data = newData;
    }
    
    public int getData() {
        return data;
    }
    
    public void setData(int newData) {
        data = newData;
    }
    
}
