import javax.swing.*;
import java.awt.*;

public class GifExample extends JFrame {

    public GifExample() {
        // Configurações da janela principal
        setTitle("Exemplo de GIF em Java");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Caminho do arquivo GIF (certifique-se de que o arquivo está no local correto)
        String gifPath = "C:\\Users\\Bruno Neves\\OneDrive\\Área de Trabalho\\XOsX.gif"; // Substitua pelo caminho do seu GIF

        // Carregar o GIF
        ImageIcon gifIcon = new ImageIcon(gifPath);

        // Criar um JLabel e definir o ícone
        JLabel gifLabel = new JLabel(gifIcon);

        // Adicionar o JLabel ao JFrame
        add(gifLabel);

        // Tornar a janela visível
        setVisible(true);
    }

    public static void main(String[] args) {
        // Executar o exemplo na Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GifExample();
            }
        });
    }
}
