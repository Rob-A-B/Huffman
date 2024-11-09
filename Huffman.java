import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Huffman {
    private static List<HuffmanNode> nodes = new ArrayList<>();
    private static HuffmanNode root; // Raiz da árvore

    public static void calculateFrequencies(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int order = 0; // Índice de ordem de inserção

        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue(), order++));
        }
    }

    public static HuffmanNode buildHuffmanTree() {
        while (nodes.size() > 1) {
            // Ordena por frequência e, em caso de empate, pela ordem de inserção
            nodes.sort((a, b) -> {
                if (a.frequency != b.frequency) {
                    return a.frequency - b.frequency;
                } else {
                    return Character.compare(a.charValue, b.charValue);
                }
            });

            HuffmanNode left = nodes.remove(0);
            HuffmanNode right = nodes.remove(0);

            HuffmanNode merged = new HuffmanNode(left.frequency + right.frequency, left, right);
            nodes.add(merged);
        }
        root = nodes.get(0);
        return root;
    }

    public static Map<Character, String> huffmanEncoding(String text) {
        nodes.clear();
        calculateFrequencies(text);
        HuffmanNode root = buildHuffmanTree();
        Map<Character, String> codes = new HashMap<>();
        generateHuffmanCodes(root, "", codes);
        return codes;
    }

    // Método para gerar os códigos de Huffman
    public static void generateHuffmanCodes(HuffmanNode node, String currentCode, Map<Character, String> codes) {
        if (node == null) return;

        // Se for um nó folha, adicione o código ao mapa
        if (node.left == null && node.right == null) {
            codes.put(node.charValue, currentCode);
        }

        // Recursão para os filhos esquerdo e direito
        generateHuffmanCodes(node.left, currentCode + '0', codes);
        generateHuffmanCodes(node.right, currentCode + '1', codes);
    }

    public static HuffmanNode getRoot() {
        return root;
    }

    public static void printTree(HuffmanNode node, String prefix) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            System.out.println(prefix + "Char: '" + node.charValue + "', Freq: " + node.frequency);
        } else {
            System.out.println(prefix + "Freq: " + node.frequency);
        }

        printTree(node.left, prefix + "0 ");
        printTree(node.right, prefix + "1 ");
    }
}
