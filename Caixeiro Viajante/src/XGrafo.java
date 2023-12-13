package src;

public class XGrafo {

    private int mat[][]; // Matriz que contém os pesos
    private int numVertices; // Numero de vertices no grafo
    private int pos[]; // Posição atual ao se percorrer os adjs de um vértice v
    private boolean directed; // O grafo eh direcionado ou nao

    public static class Aresta {
        private int v1, v2, peso;

        public Aresta(int v1, int v2, int peso) {
            this.v1 = v1;
            this.v2 = v2;
            this.peso = peso;
        }

        public Aresta(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
            this.peso = 1;
        }

        public int getPeso() { return this.peso; }
        public int getV1() { return this.v1; }
        public int getV2() { return this.v2; }
    }

    public XGrafo(int numVertices, boolean directed) {
        this.mat = new int[numVertices][numVertices]; // Indice começa em 0
        this.numVertices = numVertices;
        this.pos = new int[numVertices];
        this.directed = directed;

        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                this.mat[i][j] = 0;
            }
            this.pos[i] = -1;
        }
    }

    public void inserirAresta(int v1, int v2, int peso) {
        this.insertEdge(v1, v2, peso);
    }

    private void insertEdge(int v1, int v2, int peso) {
        if (!this.directed) { // Se nao for direcionado, colocamos o 'peso' em dois lugares na matriz, isto
                              // eh, em (v1, v2) e em (v2, v1)
            this.mat[v1][v2] = peso;
            this.mat[v2][v1] = peso;
        } else // Se for direcionado, colocamos o 'peso' em apenas um lugar na matriz, isto eh,
               // em (v1, v2)
            this.mat[v1][v2] = peso;
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++)
                s += this.mat[i][j] + "  ";
            s += "\n";
        }
        return s;
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public int[][] getMat() {
        return this.mat;
    }
}
