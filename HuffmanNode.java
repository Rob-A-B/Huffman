class HuffmanNode {
    char c;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char c, int frequency) {
        this.c = c;
        this.frequency = frequency;
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.c = '-'; // Use um valor de caractere especial para nós intermediários
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}
