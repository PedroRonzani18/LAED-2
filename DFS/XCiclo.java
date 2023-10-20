public class XCiclo {

    public static final byte branco = 0;
    public static final byte cinza = 1;
    public static final byte preto = 2;
    public int arestasCiclo = 0;
    private int d[], t[], antecessor[];
    private XGrafo grafo;

    public XCiclo(XGrafo grafo) {
        this.grafo = grafo;
        int n = this.grafo.getNumVertices();
        d = new int[n]; // tempo de descoberta
        t = new int[n]; // tempo de termino do exame
        antecessor = new int[n];
    }
    
    public int d(int v) { return this.d[v]; }
    public int t(int v) { return this.t[v]; }
    public int antecessor(int v) { return this.antecessor[v]; }

    private int visitaDfs(int u, int tempo, int cor[]) {
        cor[u] = cinza;
        this.d[u] = ++tempo; 

        if (!this.grafo.listaAdjVazia(u)) {
            XGrafo.Aresta a = this.grafo.primeiroListaAdj(u);
            while (a != null) { 
                int v = a.getV2();
                if (cor[v] == branco) { 
                    this.antecessor[v] = u;
                    tempo = this.visitaDfs(v, tempo, cor);
                }

                else if (cor[v] == cinza) 
                    this.arestasCiclo++;
                
                a = this.grafo.proxAdj(u);
            }
        }
        cor[u] = preto;
        this.t[u] = ++tempo;

        return tempo;
    }

    public void buscaEmProfundidade() {
        int tempo = 0;
        int cor[] = new int[this.grafo.getNumVertices()];

        for (int u = 0; u < grafo.getNumVertices(); u++) {
            cor[u] = branco;
            this.antecessor[u] = -1;
        }
        
        for (int u = 0; u < grafo.getNumVertices(); u++)
            if (cor[u] == branco)
                tempo = this.visitaDfs(u, tempo, cor);
    }

    public boolean possuiCiclos() {
        buscaEmProfundidade(); 

        return (this.arestasCiclo > 0);
    }
}