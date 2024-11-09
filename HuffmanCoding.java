import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner; // Importa Scanner para ler a entrada do usuário

public class HuffmanCoding {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria um Scanner para ler a entrada do usuário
        System.out.print("Digite uma palavra para codificar com Huffman: ");
        String text = scanner.nextLine(); // Lê a palavra de entrada do usuário

        // Exibe os caracteres em UTF-8 e o tamanho original em bits
        System.out.println("\nOriginal Characteres (representação em UTF8):");
        int originalBits = 0;
        for (char c : text.toCharArray()) {
            byte[] utf8Bytes = String.valueOf(c).getBytes(StandardCharsets.UTF_8);
            originalBits += utf8Bytes.length * 8; // Cada byte tem 8 bits
            System.out.print("Character: '" + c + "', UTF-8 Binario: ");
            for (byte b : utf8Bytes) {
                System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0') + " ");
            }
            System.out.println();
        }
        System.out.println("Tamanho original (bits): " + originalBits);

        // Gere o mapa de códigos de Huffman
        Map<Character, String> huffmanCodeMap = Huffman.huffmanEncoding(text);
        HuffmanNode root = Huffman.getRoot();

        System.out.println("\nHuffman Codes: " + huffmanCodeMap);

        // Codifique o texto
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodeMap.get(c));
        }
        System.out.println("Encoded Text: " + encodedText.toString());

        // Calcule o tamanho do texto codificado em bits
        int compressedBits = encodedText.length();
        System.out.println("Tamanho comprimido (bits): " + compressedBits);

        // Calcule a taxa de compressão
        double compressionRate = (1 - ((double) compressedBits / originalBits)) * 100;
        System.out.printf("Taxa de compressão de: %.2f%%\n", compressionRate);

        // Decodifique o texto (usando a árvore)
        String decodedText = decode(encodedText.toString(), root);
        System.out.println("\nDecoded Text: " + decodedText);
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
