import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;

public class Huffman {
    public static HuffmanResult buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        // Adicione todos os caracteres e suas frequências à fila de prioridade
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Construa a árvore de Huffman
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            int sum = left.frequency + right.frequency;
            pq.add(new HuffmanNode(sum, left, right));
        }

        // O último nó na fila é a raiz da árvore de Huffman
        HuffmanNode root = pq.poll();

        // Gera os códigos de Huffman a partir da árvore
        Map<Character, String> huffmanCodeMap = new HashMap<>();
        generateCodes(root, "", huffmanCodeMap);

        // Retorna o resultado com o mapa de códigos e a raiz da árvore
        return new HuffmanResult(huffmanCodeMap, root);
    }

    private static void generateCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodeMap) {
        if (node == null) return;

        // Se for uma folha, adicione o código ao mapa
        if (node.left == null && node.right == null) {
            huffmanCodeMap.put(node.c, code);
        }

        // Percorra os filhos esquerdo (0) e direito (1)
        generateCodes(node.left, code + "0", huffmanCodeMap);
        generateCodes(node.right, code + "1", huffmanCodeMap);
    }
}
