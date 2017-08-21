/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.namesurfer;
import ph.edu.dlsu.kitane.mygraph.myQueueList;
import acm.program.*;
/**
 *
 * @author ChristophJohnEric
 */
public class TestEntry extends ConsoleProgram implements NameSurferConstants {
    private NameSurferEntry entry;
    private NameSurferDataBase entrySearch;
    private myQueueList<Integer> test = new myQueueList<>();
    public void run(){
        int[] num = new int[9];
        System.out.println(num.length);
        entrySearch = new NameSurferDataBase(NAMES_DATA_FILE);
        entry = entrySearch.findEntry("Zulma");
        println("Get name: "+entry.getName());
        println("Get rank: "+entry.getRank(2));
        println("toString: "+entry.toString());
        
        test.createQueue();
        for(int i=0;i<30;i++){
            if(test.isFullQueue()){
                test.deleteQueue();
                test.addQueue(i);
                System.out.println("test.front() = "+test.front());
                System.out.println("test.back() = "+test.back());
                pause(2000);
            }else{
                test.addQueue(i);
                pause(2000);
            }
        }
    }
    public static void main(String[] args) {
            new TestEntry().start(args);
     }
    //String test = "Sam 58 69 99 131 168 236 278 380 467 408 466 997";
}
