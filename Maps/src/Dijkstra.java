public class Dijkstra {
    private int antecessorDistancia[];
    private int antecessorTempo[];
    private double p[];
    private Grafo grafo;

    public Dijkstra(Grafo grafo) {
        this.grafo = grafo;
    }

    public void obterArvoreCMCDistancia(int raiz) throws Exception {
        int n = this.grafo.numVertices();
        this.p = new double[n]; // peso dos vértices
        int vs[] = new int[n + 1]; // vértices
        this.antecessorDistancia = new int[n];
        for (int u = 0; u < n; u++) {
            this.antecessorDistancia[u] = -1;
            p[u] = Double.MAX_VALUE; // ∞
            vs[u + 1] = u; // Heap indireto a ser construído
        }
        p[raiz] = 0;

        FPHeapMinIndireto heap = new FPHeapMinIndireto(p, vs);
        heap.constroi();
        while (!heap.vazio()) {
            int u = heap.retiraMin();
            if (!this.grafo.listaAdjDistVazia(u)) {
                Grafo.Aresta adj = grafo.primeiroListaAdjDistancia(u);
                while (adj != null) {
                    int v = adj.v2();
                    if (this.p[v] > (this.p[u] + adj.distancia())) {
                        antecessorDistancia[v] = u;
                        heap.diminuiChave(v, this.p[u] + adj.distancia());
                    }
                    adj = grafo.proxAdjDistancia(u);
                }
            }
        }
    }

    public void imprimeCaminhoDistancia(int origem, int v) {
        if (origem == v)
            System.out.print(origem + " ");
        else if (this.antecessorDistancia[v] == -1)
            System.out.println("Nao existe caminho de " + origem + " ate " + v);
        else {
            imprimeCaminhoDistancia(origem, this.antecessorDistancia[v]);
            System.out.print(v + " ");
        }
    }

    public void obterArvoreCMCTempo(int raiz) throws Exception {
        int n = this.grafo.numVertices();
        this.p = new double[n]; // peso dos vértices
        int vs[] = new int[n + 1]; // vértices
        this.antecessorTempo = new int[n];
        for (int u = 0; u < n; u++) {
            this.antecessorTempo[u] = -1;
            p[u] = Double.MAX_VALUE; // ∞
            vs[u + 1] = u; // Heap indireto a ser construído
        }
        p[raiz] = 0;

        FPHeapMinIndireto heap = new FPHeapMinIndireto(p, vs);
        heap.constroi();
        while (!heap.vazio()) {
            int u = heap.retiraMin();
            if (!this.grafo.listaAdjTempoVazia(u)) {
                Grafo.Aresta adj = grafo.primeiroListaAdjTempo(u);
                while (adj != null) {
                    int v = adj.v2();
                    if (this.p[v] > (this.p[u] + adj.tempo())) {
                        antecessorTempo[v] = u;
                        heap.diminuiChave(v, this.p[u] + adj.tempo());
                    }
                    adj = grafo.proxAdjTempo(u);
                }
            }
        }
    }

    public void imprimeCaminhoTempo(int origem, int v) {
        if (origem == v)
            System.out.print(origem + " ");
        else if (this.antecessorTempo[v] == -1)
            System.out.println("Nao existe caminho de " + origem + " ate " + v);
        else {
            imprimeCaminhoTempo(origem, this.antecessorTempo[v]);
            System.out.print(v + " ");
        }
    }
}