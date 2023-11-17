public class Grafo {
    public static class Aresta {
        private int v1, v2, distancia, tempo;

        public Aresta(int v1, int v2, int distancia, int tempo) {
            this.v1 = v1;
            this.v2 = v2;
            this.distancia = distancia;
            this.tempo = tempo;
        }

        public int distancia() {
            return this.distancia;
        }

        public int tempo() {
            return this.tempo;
        }

        public int v1() {
            return this.v1;
        }

        public int v2() {
            return this.v2;
        }
    }

    private int mat_dist[][]; // distancias do tipo inteiro
    private int mat_temp[][]; // tempos do tipo inteiro
    private int numVertices;
    private int pos_dist[]; // posição atual ao se percorrer os adjs de um vértice v
    private int pos_tempo[]; // posição atual ao se percorrer os adjs de um vértice v

    public Grafo(int numVertices) {
        this.mat_dist = new int[numVertices][numVertices];
        this.mat_temp = new int[numVertices][numVertices];
        this.pos_dist = new int[numVertices];
        this.pos_tempo = new int[numVertices];
        this.numVertices = numVertices;
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++)
                this.mat_dist[i][j] = 0;
            this.pos_dist[i] = -1;
            this.pos_tempo[i] = -1;
        }
    }

    public void insereAresta(int v1, int v2, int distancia, int tempo) {
        this.mat_dist[v1][v2] = distancia;
        this.mat_temp[v1][v2] = tempo;
    }

    public boolean listaAdjDistVazia(int v) {
        for (int i = 0; i < this.numVertices; i++)
            if (this.mat_dist[v][i] > 0)
                return false;
        return true;
    }

    public boolean listaAdjTempoVazia(int v) {
        for (int i = 0; i < this.numVertices; i++)
            if (this.mat_temp[v][i] > 0)
                return false;
        return true;
    }

    public Aresta primeiroListaAdjDistancia(int v) {
        // Retorna a primeira aresta que o vértice v participa ou
        // null se a lista de adjacência de v for vazia
        this.pos_dist[v] = -1;
        return this.proxAdjDistancia(v);
    }

    public Aresta primeiroListaAdjTempo(int v) {
        // Retorna a primeira aresta que o vértice v participa ou
        // null se a lista de adjacência de v for vazia
        this.pos_tempo[v] = -1;
        return this.proxAdjTempo(v);
    }

    public Aresta proxAdjDistancia(int v) {
        // Retorna a próxima aresta que o vértice v participa ou
        // null se a lista de adjacência de v estiver no fim
        this.pos_dist[v]++;
        while ((this.pos_dist[v] < this.numVertices) &&
                (this.mat_dist[v][this.pos_dist[v]] == 0))
            this.pos_dist[v]++;
        if (this.pos_dist[v] == this.numVertices)
            return null;
        else
            return new Aresta(v, this.pos_dist[v], this.mat_dist[v][this.pos_dist[v]], this.mat_temp[v][this.pos_dist[v]]);
    }

    public Aresta proxAdjTempo(int v) {
        // Retorna a próxima aresta que o vértice v participa ou
        // null se a lista de adjacência de v estiver no fim
        this.pos_tempo[v]++;
        while ((this.pos_tempo[v] < this.numVertices) &&
                (this.mat_dist[v][this.pos_tempo[v]] == 0))
            this.pos_tempo[v]++;
        if (this.pos_tempo[v] == this.numVertices)
            return null;
        else
            return new Aresta(v, this.pos_tempo[v], this.mat_temp[v][this.pos_tempo[v]], this.mat_temp[v][this.pos_tempo[v]]);
    }

    public int numVertices() {
        return this.numVertices;
    }
}