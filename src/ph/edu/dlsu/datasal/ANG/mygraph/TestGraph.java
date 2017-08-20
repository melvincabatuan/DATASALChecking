package ph.edu.dlsu.datasal.ang.mygraph;
class TestGraph{

    public static void main(String [] args){

          myGraph G = new myGraph(7);
           
     

          System.out.printf("The graph now has %d nodes and %d edge/s.\n", G.n(), G.e());

          G.setMark(0,3);
          G.setMark(1,10);
          G.setMark(2,5);
          G.setMark(3,8);
          G.setMark(4,6);
          G.setMark(5,17);

          System.out.printf("The mark at 0 is %d \n", G.getMark(0));


         
          System.out.println("G.isEdge(0,0) "   + G.isEdge(0,0));

          G.setEdge(0,0,1);

          System.out.printf("The graph now has %d nodes and %d edge/s.\n", G.n(), G.e());

          System.out.println("G.isEdge(0,0) "   + G.isEdge(0,0));

          G.delEdge(0,0);

          System.out.printf("The graph now has %d nodes and %d edge/s.\n", G.n(), G.e());

          G.setEdge(0,1,1);

          System.out.printf("The graph now has %d nodes and %d edge/s.\n", G.n(), G.e());

          System.out.println("G.isEdge(0,1) "   + G.isEdge(0,1));

           System.out.println(" Integer.MAX_VALUE = " +  Integer.MAX_VALUE);
       
    }

    
}
