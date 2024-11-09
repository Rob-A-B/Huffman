import java.util.Map;
import java.util.HashMap;

public class HuffmanCoding {

    public static void main(String[] args) {
        String text = "Julio";

        // Gere o mapa de códigos de Huffman e a raiz da árvore
        Map<Character, String> huffmanCodeMap = Huffman.huffmanEncoding(text);
        HuffmanNode root = Huffman.getRoot(); // Método para obter a raiz da árvore a partir da classe Huffman

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
                decodedText.append(current.charValue);
                current = root;
            }
        }
        return decodedText.toString();
    }
}
