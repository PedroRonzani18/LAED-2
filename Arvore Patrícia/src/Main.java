/*
 * Pedro Augusto de Portilho Ronzani
 * Ulisses Andrade Carvalho
 * Lucas Andrade Brandão
 * 
 * Laboratorio de Algoritmos e Estruturas de Dados II
 */
public class Main {

    private static void testing(String[] palavras, String fileDir) {
        try {
            ExtraiPalavra extractor = new ExtraiPalavra(fileDir);
            ArvorePatricia arvorePatricia = new ArvorePatricia(128);

            String extracted;
            while ((extracted = extractor.proximaPalavra()) != null) {
                if (!extracted.isEmpty()) {
                    arvorePatricia.insere(extracted, extractor.getLinha(), extractor.getLinha());
                }
            }

            extractor.fecharArquivos();
            for (String palavra : palavras) {
                arvorePatricia.pesquisa(palavra);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println("------------EXEMPLO 1------------");
        String[] palavras1 = {
                "trabalho", "computacao", "governo", "educacao", "tecnologia",
                "formacao", "desenvolvimento", "que", "informatica", "em", "crise",
        };
        testing(palavras1, "../examples/exemplo1.txt");

        System.out.println("------------EXEMPLO 2------------");
        String[] palavras2 = {
                "sociedade", "software", "ideia", "pessoa", "Informatica",
                "etica", "muito", "ciencia", "computacao", "que", "area", "moral"
        };
        testing(palavras2, "../examples/exemplo2.txt");
    }
}
