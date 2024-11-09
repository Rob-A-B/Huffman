import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Huffman {
    static List<HuffmanNode> nodes = new ArrayList<>();
    private static HuffmanNode root; // Adiciona um campo estático para armazenar a raiz da árvore
    static int orderCounter = 0; // Contador para a ordem de aparição

    public static void calculateFrequencies(String text) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char charValue : text.toCharArray()) {
            if (!frequencies.containsKey(charValue)) {
                frequencies.put(charValue, 0);
                nodes.add(new HuffmanNode(charValue, 0, orderCounter++)); // Adiciona a ordem de aparição
            }
            frequencies.put(charValue, frequencies.get(charValue) + 1);
        }

        for (HuffmanNode node : nodes) {
            node.frequency = frequencies.get(node.charValue);
        }
    }

    // Método para ordenar a lista de nós
    public static void sortNodes() {
        nodes.sort((a, b) -> {
            if (a.frequency != b.frequency) {
                return a.frequency - b.frequency; // Ordena por frequência
            } else {
                return a.order - b.order; // Desempata pela ordem de aparição
            }
        });
    }

    public static HuffmanNode buildHuffmanTree() {
        while (nodes.size() > 1) {
            sortNodes(); // Chama o método de ordenação separado
            HuffmanNode left = nodes.remove(0);
            HuffmanNode right = nodes.remove(0);

            HuffmanNode merged = new HuffmanNode(left.frequency + right.frequency, left, right);
            nodes.add(merged);
        }
        root = nodes.get(0); // Atribui a raiz da árvore à variável estática
        return root;
    }

    public static void generateHuffmanCodes(HuffmanNode node, String currentCode, Map<Character, String> codes) {
        if (node == null) {
            return;
        }

        if (node.charValue != '\0') {
            codes.put(node.charValue, currentCode);
        }

        generateHuffmanCodes(node.left, currentCode + '0', codes);
        generateHuffmanCodes(node.right, currentCode + '1', codes);
    }

    public static Map<Character, String> huffmanEncoding(String text) {
        nodes.clear();
        orderCounter = 0; // Reseta o contador de ordem para cada nova palavra
        calculateFrequencies(text);
        buildHuffmanTree(); // Chama o método para construir a árvore
        Map<Character, String> codes = new HashMap<>();
        generateHuffmanCodes(root, "", codes);
        return codes;
    }

    // Método para retornar a raiz da árvore
    public static HuffmanNode getRoot() {
        return root;
    }
}
