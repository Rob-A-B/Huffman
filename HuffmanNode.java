public class HuffmanNode {
    char charValue; // Campo que armazena o caractere
    int frequency;
    int order; // Para desempate, se necessário
    HuffmanNode left;
    HuffmanNode right;

    // Construtor para nós de folha (com caractere e frequência)
    public HuffmanNode(char charValue, int frequency, int order) {
        this.charValue = charValue;
        this.frequency = frequency;
        this.order = order;
        this.left = null;
        this.right = null;
    }

    // Construtor para nós internos (apenas com frequência)
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.charValue = '\0'; // Indica que é um nó interno, sem caractere associado
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.order = Math.min(left.order, right.order); // Define o menor índice dos filhos
    }
}
