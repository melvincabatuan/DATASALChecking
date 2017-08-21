package ph.edu.dlsu.datasal.santos.myarraylist;

public class SimpleListTest {

    public static void main(String[] args) {
        SimpleListTest app = new SimpleListTest();
        app.run();

    } 

    private void run() {
        MyArrayList<String> list1 = new MyArrayList<>();
        list1.add(1, "Aa");
        list1.add(2, "Ba");
        MyArrayList<String> list2 = new MyArrayList<>();
        list2.add(1, "Aa");
        list2.add(2, "Ba");
        list2.add(3, "Ka");
        list2.removeAll(list1);
        if(list2.containsAll(list1)){
            System.out.println("list2 does not contain all of list1 elements");
        }
        else{
            System.out.println("list2 might contain some list1 elements");
        }      
    }
    
}
