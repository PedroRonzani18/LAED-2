public class ArvoreBinaria {

    private static class No {
        Item reg;
        No esq, dir;
    }

    private No raiz;
    private int quantidade_comparacoes;

    public int getQuantidadeComparacoes() { return this.quantidade_comparacoes; }

    public ArvoreBinaria() {
        this.raiz = null;
        this.quantidade_comparacoes = 0;
    }

    private No insere(Item reg, No p) {
        if(p == null) {
            p = new No(); p.reg = reg;
            p.esq = null; p.dir = null;
        }
        else if(reg.compara(p.reg) < 0) p.esq = insere(reg, p.esq);

        else if(reg.compara(p.reg) > 0) p.dir = insere(reg, p.dir);

        else System.out.println("Elemento ja existente.");
        return  p;
    }

    public void insere(Item reg) {
        this.raiz = this.insere(reg, this.raiz);
    }

    private Item pesquisa(Item reg, No p) {

        if(p == null) return  null; // Registro não encontrado

        if(reg.compara(p.reg) < 0)  {
            this.quantidade_comparacoes+=1;
            return pesquisa(reg, p.esq); // Se for menor, pesquisa para a esquerda
        }

        if(reg.compara(p.reg) > 0) {
            this.quantidade_comparacoes+=2;
            return pesquisa(reg, p.dir); // se for maior, pesqusia para a direita
        }

        this.quantidade_comparacoes+=3;
        return p.reg; // Encontrou o registro
    }

    public Item pesquisa(Item reg) {
        return this.pesquisa(reg, this.raiz);
    }
}
