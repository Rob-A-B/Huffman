import java.util.Map;
import java.util.HashMap;

public class HuffmanCoding {

    public static void main(String[] args) {
        String text = "Roberto";

        // Conte a frequência de cada caractere no texto
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Construa a árvore de Huffman e obtenha o resultado com os códigos e a raiz
        HuffmanResult result = Huffman.buildHuffmanTree(freqMap);
        Map<Character, String> huffmanCodeMap = result.huffmanCodeMap;
        HuffmanNode root = result.root;

        System.out.println("Huffman Codes: " + huffmanCodeMap);

        // Codifique o texto
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodeMap.get(c));
        }
        System.out.println("Encoded Text: " + encodedText.toString());

        // Decodifique o texto (usando a árvore)
        String decodedText = decode(encodedText.toString(), root);
        System.out.println("Decoded Text: " + decodedText);
    }

    public static String decode(String encodedText, HuffmanNode root) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedText.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.left == null && current.right == null) { // Nó folha
                decodedText.append(current.c);
                current = root;
            }
        }
        return decodedText.toString();
    }
}
