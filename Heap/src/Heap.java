public class Heap {

    private Item v [ ] ;
    private int n;
    public Heap (Item v [] ) {
        this .v = v;
        this.n = this.v.length - 1;
    }

    public Item max ( ) {
        return this .v[1];
    }

    public void refaz ( int esq, int dir ) {
        int j = esq ∗ 2;
        Item x = this .v[esq] ;
        while ( j <= dir ) {
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

    public Item retiraMax () throws Exception {
        Item maximo;
        if (this .n < 1) throw new Exception ( "Erro : heap vazio" );
        else {
            maximo = this .v[1];
            this .v[1] = this .v[this .n--];
            refaz (1 , this .n);
        }
        return maximo;
    }

    public void aumentaChave ( int i , Object chaveNova)
            throws Exception {
        Item x = this .v[ i ] ;
        if (chaveNova == null)
            throw new Exception ( "Erro : chaveNova com valor null " );
        x.alteraChave (chaveNova);
        while (( i > 1) && (x.compara (this .v[ i / 2]) >= 0)) {
            this .v[ i ] = this .v[ i / 2 ] ; i /= 2;
        }
        this .v[ i ] = x;
    }
}
