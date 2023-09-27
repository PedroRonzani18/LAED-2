public class Main {
    public static void main(String[] args) {


        Item v[] = new Item[10];

        for (int i = 0; i < 10; i++) {
            v[i] = new Item(i);
        }

        Heap heap = new Heap(v);
    }
}