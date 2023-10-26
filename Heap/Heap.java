public class Heap {

    private Item v [ ] ;
    private int n;
    public static int comparacoes = 0;
    public Heap (Item v [] ) {
        this .v = v;
        this.n = this.v.length - 1;
    }

    public Item max ( ) {
        return this .v[1];
    }

    public void refaz ( int esq, int dir ) {
        int j = esq * 2;
        Item x = this .v[esq] ;
        while ( j <= dir ) {
            comparacoes ++;
            if (( j < dir ) && (this .v[ j ] .compara (this .v[ j + 1]) < 0))
                j ++;
            if (x.compara (this .v[ j ]) >= 0) break;
            this .v[esq] = this .v[ j ] ;
            esq = j ; j = esq * 2;
        }
        this .v[esq] = x;
    }

    public void constroi () {
        int esq = n / 2 + 1;
        while (esq > 1) {
            esq-=1;
            this . refaz(esq, this .n);
        }
    }

    public static void heapsort ( Item v [ ] , int n) {
        comparacoes = 0;

        Heap fpHeap = new Heap ( v ) ;
        int dir = n;
        fpHeap. constroi ( ) ;
        while ( dir > 1 ) {
            Item x = v [ 1 ] ;
            v[1] = v [ dir ] ;
            v [ dir ] = x ;
            dir--;
            fpHeap. refaz ( 1 , dir ) ;
        }
    }
}

