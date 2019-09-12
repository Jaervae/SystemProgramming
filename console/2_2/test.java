public class myClass {

    private List<String> mylist = Collections.synchronizedList(new ArrayList<String>());
    public myClass(){
        mylist.add("abc");
        mylist.add("def");
        mylist.add("ghi");
        mylist.add("jkl");
    }
    public void addString(String str) {
        mylist.add(str);
    }

    public void displayValues() {
        for (int i = 0; i < mylist.size(); i++) {
            System.out.println("value is " + mylist.get(i) + "at " + i);
        }
    }

    public void deleteString(int i) {
        mylist.remove(i);
    }
}

class addThread {

    public static void main(String a[]) {
        final myClass mine = new myClass();
        Thread t1 = new Thread() {

            @Override
            public void run() {
                mine.displayValues();
                mine.addString("aaa");
                mine.displayValues();
            }
        };
        Thread t2 = new Thread() {

            public void run() {
                mine.displayValues();
                mine.deleteString(1);
                mine.displayValues();
            }
        };
        t1.start();
        t2.start();
    }
}