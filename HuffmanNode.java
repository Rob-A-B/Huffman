// Arquivo: HuffmanNode.java
public class HuffmanNode {
    char charValue;
    int frequency;
    int order; // Adiciona a ordem de aparição
    HuffmanNode left;
    HuffmanNode right;

    // Construtor para nós de folha (com caractere, frequência e ordem)
    public HuffmanNode(char charValue, int frequency, int order) {
        this.charValue = charValue;
        this.frequency = frequency;
        this.order = order;
        this.left = null;
        this.right = null;
    }

    // Construtor para nós internos (apenas com frequência)
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.charValue = '\0'; // Indica que é um nó interno
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.order = Math.min(left.order, right.order); // Define o menor índice dos filhos
    }
}
