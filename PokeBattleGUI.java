import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;
import java.util.List;

public class PokeBattleGUI extends JFrame {
    private boolean isJogador1Turno;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JPanel selectionPanel;
    private JButton[] player1Buttons = new JButton[9];
    private JButton[] player2Buttons = new JButton[9];
    private JButton readyButton1;
    private JButton readyButton2;
    private boolean player1Ready = false;
    private boolean player2Ready = false;
    private boolean player1Selected = false;
    private boolean player2Selected = false;

    private JPanel battlePanel;
    private JPanel centralPanel;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton attackButton;
    private JButton itemsButton;
    private JButton pokemonButton;
    private JButton quitButton;
    private JButton selectedButtonPlayer1;
    private JButton selectedButtonPlayer2;

    // JLabels para os GIFs
    private JLabel backgroundLabel;
    private JLabel topRightLabel;
    private JLabel bottomLeftLabel;

    private Map<JButton, Pokemon> buttonPokemonMap = new HashMap<>();
    private Battle battle;

    private List<Pokemon> todosPokemons = Arrays.asList(
            new Fire("Charmander", 100, 10, Arrays.asList(new Ataque("Chama", 10, 100), new Ataque("Lança-Chamas", 90, 85))),
            new Fire("Cyndaquil", 90, 10, Arrays.asList(new Ataque("Chama", 10, 100), new Ataque("Lança-Chamas", 90, 85))),
            new Fire("Moltres", 110, 10, Arrays.asList(new Ataque("Chama", 10, 100), new Ataque("Lança-Chamas", 90, 85))),
            new Water("Squirtle", 100, 10, Arrays.asList(new Ataque("Jato de Água", 10, 100), new Ataque("Hidro Bomba", 90, 85))),
            new Water("Mudkip", 90, 10, Arrays.asList(new Ataque("Jato de Água", 10, 100), new Ataque("Hidro Bomba", 90, 85))),
            new Water("Vaporeon", 120, 10, Arrays.asList(new Ataque("Jato de Água", 10, 100), new Ataque("Hidro Bomba", 90, 85))),
            new Grass("Bulbasaur", 100, 10, Arrays.asList(new Ataque("Folha Navalha", 10, 100), new Ataque("Chicote de Vinha", 45, 100))),
            new Grass("Chikorita", 90, 10, Arrays.asList(new Ataque("Folha Navalha", 10, 100), new Ataque("Chicote de Vinha", 45, 100))),
            new Grass("Treecko", 110, 10, Arrays.asList(new Ataque("Folha Navalha", 10, 100), new Ataque("Chicote de Vinha", 45, 100)))
    );

    public PokeBattleGUI() {
        this.isJogador1Turno = true; // Jogador 1 começa

        setTitle("PokeBattle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createSelectionPanel();
        createBattlePanel();

        mainPanel.add(selectionPanel, "Selection");
        mainPanel.add(battlePanel, "Battle");

        add(mainPanel);

        cardLayout.show(mainPanel, "Selection");
    }

    private void createSelectionPanel() {
        selectionPanel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Select Your Character"));
        selectionPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel charactersPanel = new JPanel(new GridLayout(1, 2));

        JPanel player1Panel = new JPanel(new BorderLayout());
        player1Panel.add(new JLabel("Player 1"), BorderLayout.NORTH);
        JPanel player1ButtonsPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            player1Buttons[i] = new JButton(this.todosPokemons.get(i).getNome());
            buttonPokemonMap.put(player1Buttons[i], this.todosPokemons.get(i));
            int finalI = i;
            player1Buttons[i].addActionListener(e -> {
                player1Selected = true;
                selectedButtonPlayer1 = player1Buttons[finalI];
                highlightSelectedButton(player1Buttons, finalI);
            });
            player1ButtonsPanel.add(player1Buttons[i]);
        }
        player1Panel.add(player1ButtonsPanel, BorderLayout.CENTER);
        readyButton1 = new JButton("Ready");
        player1Panel.add(readyButton1, BorderLayout.SOUTH);

        JPanel player2Panel = new JPanel(new BorderLayout());
        player2Panel.add(new JLabel("Player 2"), BorderLayout.NORTH);
        JPanel player2ButtonsPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            player2Buttons[i] = new JButton(this.todosPokemons.get(i).getNome());
            buttonPokemonMap.put(player2Buttons[i], this.todosPokemons.get(i));
            int finalI = i;
            player2Buttons[i].addActionListener(e -> {
                player2Selected = true;
                selectedButtonPlayer2 = player2Buttons[finalI];
                highlightSelectedButton(player2Buttons, finalI);
            });
            player2ButtonsPanel.add(player2Buttons[i]);
        }
        player2Panel.add(player2ButtonsPanel, BorderLayout.CENTER);
        readyButton2 = new JButton("Ready");
        player2Panel.add(readyButton2, BorderLayout.SOUTH);

        charactersPanel.add(player1Panel);
        charactersPanel.add(player2Panel);

        selectionPanel.add(charactersPanel, BorderLayout.CENTER);

        readyButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1Selected) {
                    player1Ready = true;
                    readyButton1.setEnabled(false);
                    checkReady();
                } else {
                    JOptionPane.showMessageDialog(PokeBattleGUI.this, "Player 1 must select a character.");
                }
            }
        });

        readyButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player2Selected) {
                    player2Ready = true;
                    readyButton2.setEnabled(false);
                    checkReady();
                } else {
                    JOptionPane.showMessageDialog(PokeBattleGUI.this, "Player 2 must select a character.");
                }
            }
        });
    }

    private void highlightSelectedButton(JButton[] buttons, int index) {
        for (int i = 0; i < buttons.length; i++) {
            if (i == index) {
                buttons[i].setBackground(Color.GREEN);
            } else {
                buttons[i].setBackground(null);
            }
        }
    }

    private void createBattlePanel() {
        battlePanel = new JPanel(new BorderLayout()); // Layout absoluto para posicionar os JLabels

        // Escolhe aleatoriamente um número entre 1 e 9 para o nome do arquivo
        Random random = new Random();
        int randomNumber = random.nextInt(9) + 1;
        String backgroundPath = "/gifs/background/" + randomNumber + ".png";

        // Adicionando o JLabel para o GIF na parte superior direita
        topRightLabel = createGifLabel("/gifs/moltres_frente.gif", 500, 200, 150, 150);
        battlePanel.add(topRightLabel);



        // Adicionando o JLabel para o GIF na parte inferior esquerda
        bottomLeftLabel = createGifLabel("/gifs/bulbasaur_costa.gif", 125, 450, 150, 150);
        battlePanel.add(bottomLeftLabel);

        // Adicionando o JLabel para o GIF de fundo
        backgroundLabel = createGifLabel(backgroundPath, 0, 0, 800, 600);
        battlePanel.add(backgroundLabel);

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("PokeBattle"));
        battlePanel.add(titlePanel, BorderLayout.NORTH);

        centralPanel = new JPanel();
        battlePanel.add(centralPanel, BorderLayout.CENTER);

        bottomPanel = new JPanel(new BorderLayout());
        leftPanel = new JPanel(new GridLayout(2, 2));
        rightPanel = new JPanel(new GridLayout(2, 2));
        attackButton = new JButton("Attack");
        itemsButton = new JButton("Items");
        pokemonButton = new JButton("Pokemon");
        quitButton = new JButton("Quit");
        rightPanel.add(attackButton);
        rightPanel.add(itemsButton);
        rightPanel.add(pokemonButton);
        rightPanel.add(quitButton);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(rightPanel, BorderLayout.EAST);
        battlePanel.add(bottomPanel, BorderLayout.SOUTH);

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLeftPanel("Attack");
            }
        });

        itemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLeftPanel("Items");
            }
        });

        pokemonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLeftPanel("Pokemon");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(PokeBattleGUI.this, "The Battle was aborted! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    player1Ready = false;
                    player2Ready = false;
                    player1Selected = false;
                    player2Selected = false;
                    readyButton1.setEnabled(true);
                    readyButton2.setEnabled(true);
                    highlightSelectedButton(player1Buttons, -1);
                    highlightSelectedButton(player2Buttons, -1);
                    cardLayout.show(mainPanel, "Selection");
                } else {
                    System.exit(0);
                }
            }
        });
    }

    private void checkReady() {
        if (player1Ready && player2Ready) {
            iniciarBatalha();
            updateGifLabels();
            cardLayout.show(mainPanel, "Battle");
        }
    }

    private void updateGifLabels() {
        Pokemon pokemonJogador1 = buttonPokemonMap.get(selectedButtonPlayer1);
        Pokemon pokemonJogador2 = buttonPokemonMap.get(selectedButtonPlayer2);

        topRightLabel.setIcon(resizeIcon(createImageIcon("/gifs/" + pokemonJogador2.getNome() + "_frente.gif"), 100, 100));
        bottomLeftLabel.setIcon(resizeIcon(createImageIcon("/gifs/" + pokemonJogador1.getNome() + "_costa.gif"), 100, 100));
    }

    // Método auxiliar para redimensionar um ImageIcon
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        if (icon != null) {
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            return new ImageIcon(newImg);
        } else {
            return null;
        }
    }

    private void updateLeftPanel(String type) {
        Pokemon pokemonJogador1 = buttonPokemonMap.get(selectedButtonPlayer1);
        Pokemon pokemonJogador2 = buttonPokemonMap.get(selectedButtonPlayer2);

        leftPanel.removeAll();
        switch (type) {
            case "Attack" -> {
                Pokemon currentPokemon = isJogador1Turno ? pokemonJogador1 : pokemonJogador2;
                Pokemon opponentPokemon = isJogador1Turno ? pokemonJogador2 : pokemonJogador1;
                for (int i = 0; i < currentPokemon.getAtaques().size(); i++) {
                    JButton attackButton = getAttackButton(currentPokemon, i, opponentPokemon);
                    leftPanel.add(attackButton);
                }
            }
            case "Items" -> {
                for (int i = 0; i < 3; i++) {
                    leftPanel.add(new JButton("Item " + (i + 1)));
                }
            }
            case "Pokemon" -> {
                for (int i = 0; i < 3; i++) {
                    leftPanel.add(new JButton("Pokemon " + (i + 1)));
                }
            }
        }
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    private JButton getAttackButton(Pokemon currentPokemon, int i, Pokemon opponentPokemon) {
        JButton attackButton = new JButton(currentPokemon.getAtaques().get(i).getNome());
        Ataque ataque = currentPokemon.getAtaques().get(i);
        attackButton.addActionListener(e -> {
            battle.realizarTurno(ataque);
            if (opponentPokemon.getHp() <= 0) {
                int result = JOptionPane.showConfirmDialog(PokeBattleGUI.this, "The battle has ended! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            } else {
                isJogador1Turno = !isJogador1Turno;
            }
        });
        return attackButton;
    }

    private void resetGame() {
        player1Ready = false;
        player2Ready = false;
        player1Selected = false;
        player2Selected = false;
        readyButton1.setEnabled(true);
        readyButton2.setEnabled(true);
        highlightSelectedButton(player1Buttons, -1);
        highlightSelectedButton(player2Buttons, -1);
        cardLayout.show(mainPanel, "Selection");
    }

    // Método para criar um JLabel com um GIF específico em uma posição e tamanho
    private JLabel createGifLabel(String path, int x, int y, int width, int height) {
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);

        // Carrega o ícone do GIF
        ImageIcon gifIcon = createImageIcon(path);
        if (gifIcon != null) {
            // Obtém o ícone como imagem e ajusta ao tamanho da label
            Image img = gifIcon.getImage();
            Image newImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon resizedIcon = new ImageIcon(newImg);

            label.setIcon(resizedIcon);
            // Centraliza a imagem dentro da JLabel
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);

            // Inicia a animação do GIF
            ((ImageIcon) label.getIcon()).setImageObserver(label);
        } else {
            label.setText("GIF não encontrado!");
        }

        return label;
    }

    // Método auxiliar para carregar o GIF a partir do arquivo
    private ImageIcon createImageIcon(String path) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            System.err.println("Não foi possível carregar o arquivo de imagem: " + path);
            return null;
        }
    }

    private void iniciarBatalha() {
        if (selectedButtonPlayer1 == null || selectedButtonPlayer2 == null) {
            // Handle case where a player has not selected a Pokémon
            return;
        }

        Pokemon pokemonJogador1 = buttonPokemonMap.get(selectedButtonPlayer1);
        Pokemon pokemonJogador2 = buttonPokemonMap.get(selectedButtonPlayer2);

        battle = new Battle(pokemonJogador1, pokemonJogador2);
        battle.iniciar();
    }
}
