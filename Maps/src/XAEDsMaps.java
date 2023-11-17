public class XAEDsMaps {

    private Grafo grafo;
    private int v1, v2;

    public XAEDsMaps(Grafo grafo, int v1, int v2) {
        this.grafo = grafo;
        this.v1 = v1;
        this.v2 = v2;
    }

    public void obterArvoreCMCDistancia() throws Exception {

        Dijkstra dijkstra = new Dijkstra(this.grafo);
        dijkstra.obterArvoreCMCDistancia(this.v1);
        dijkstra.imprimeCaminhoDistancia(this.v1, this.v2);
    }

    public void obterArvoreCMCTempo() throws Exception {

        Dijkstra dijkstra = new Dijkstra(this.grafo);
        dijkstra.obterArvoreCMCTempo(this.v1);
        dijkstra.imprimeCaminhoTempo(this.v1, this.v2);
    }
}
