import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Represents a GUI for a Pokemon battle game.
 * Allows players to select their Pokemon and conduct a battle.
 */
public class PokeBattleGUI extends JFrame {
    private boolean isPlayer1Turn;
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

    // JLabels for the GIFs
    private JLabel backgroundLabel;
    private JLabel topRightLabel;
    private JLabel bottomLeftLabel;

    private Map<JButton, Pokemon> buttonPokemonMap = new HashMap<>();
    private Battle battle;

    private List<Pokemon> allPokemons = Arrays.asList(
            new Fire("Charmander", 100, 10, Arrays.asList(new Attack("Flame", 10, 100), new Attack("Flamethrower", 20, 80))),
            new Fire("Cyndaquil", 100, 10, Arrays.asList(new Attack("Flame", 10, 100), new Attack("Flamethrower", 20, 80))),
            new Fire("Moltres", 100, 10, Arrays.asList(new Attack("Flame", 10, 100), new Attack("Flamethrower", 20, 80))),
            new Water("Squirtle", 100, 10, Arrays.asList(new Attack("Water Jet", 10, 100), new Attack("Hydro Pump", 20, 80))),
            new Water("Mudkip", 100, 10, Arrays.asList(new Attack("Water Jet", 10, 100), new Attack("Hydro Pump", 20, 80))),
            new Water("Vaporeon", 100, 10, Arrays.asList(new Attack("Water Jet", 10, 100), new Attack("Hydro Pump", 20, 80))),
            new Grass("Bulbasaur", 100, 10, Arrays.asList(new Attack("Razor Leaf", 10, 100), new Attack("Vine Whip", 20, 80))),
            new Grass("Chikorita", 100, 10, Arrays.asList(new Attack("Razor Leaf", 10, 100), new Attack("Vine Whip", 20, 80))),
            new Grass("Treecko", 100, 10, Arrays.asList(new Attack("Razor Leaf", 10, 100), new Attack("Vine Whip", 20, 80)))
    );

    /**
     * Constructs the PokeBattleGUI, sets up the initial state and GUI components.
     */
    public PokeBattleGUI() {
        this.isPlayer1Turn = true; // Player 1 starts

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

    /**
     * Creates the selection panel where players select their Pokemon.
     */
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
            player1Buttons[i] = new JButton(this.allPokemons.get(i).getName());
            buttonPokemonMap.put(player1Buttons[i], this.allPokemons.get(i));
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
            player2Buttons[i] = new JButton(this.allPokemons.get(i).getName());
            buttonPokemonMap.put(player2Buttons[i], this.allPokemons.get(i));
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

    /**
     * Highlights the selected button by changing its background color.
     *
     * @param buttons The array of buttons to search through.
     * @param index   The index of the button to highlight.
     */
    private void highlightSelectedButton(JButton[] buttons, int index) {
        for (int i = 0; i < buttons.length; i++) {
            if (i == index) {
                buttons[i].setBackground(Color.GREEN);
            } else {
                buttons[i].setBackground(null);
            }
        }
    }

    /**
     * Creates the battle panel where the battle takes place.
     */
    private void createBattlePanel() {
        battlePanel = new JPanel(new BorderLayout()); // Absolute layout to position the JLabels

        // Randomly choose a number between 1 and 9 for the file name
        Random random = new Random();
        int randomNumber = random.nextInt(9) + 1;
        String backgroundPath = "/gifs/background/" + randomNumber + ".png";

        // Adding the JLabel for the GIF in the top right
        topRightLabel = createGifLabel("/gifs/moltres_frente.gif", 500, 200, 150, 150);
        battlePanel.add(topRightLabel);

        // Adding the JLabel for the GIF in the bottom left
        bottomLeftLabel = createGifLabel("/gifs/bulbasaur_costa.gif", 125, 450, 150, 150);
        battlePanel.add(bottomLeftLabel);

        // Adding the JLabel for the background GIF
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

    /**
     * Checks if both players are ready, and if so, starts the battle.
     */
    private void checkReady() {
        if (player1Ready && player2Ready) {
            startBattle();
            updateGifLabels();
            cardLayout.show(mainPanel, "Battle");
        }
    }

    /**
     * Updates the GIF labels based on the selected Pokemon of each player.
     */
    private void updateGifLabels() {
        Pokemon player1Pokemon = buttonPokemonMap.get(selectedButtonPlayer1);
        Pokemon player2Pokemon = buttonPokemonMap.get(selectedButtonPlayer2);

        topRightLabel.setIcon(resizeIcon(createImageIcon("/gifs/" + player2Pokemon.getName() + "_frente.gif"), 100, 100));
        bottomLeftLabel.setIcon(resizeIcon(createImageIcon("/gifs/" + player1Pokemon.getName() + "_costa.gif"), 100, 100));
    }

    /**
     * Resizes an ImageIcon to the specified width and height.
     *
     * @param icon  The ImageIcon to resize.
     * @param width  The desired width.
     * @param height The desired height.
     * @return The resized ImageIcon.
     */
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        if (icon != null) {
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            return new ImageIcon(newImg);
        } else {
            return null;
        }
    }

    /**
     * Updates the left panel with buttons corresponding to the selected type.
     *
     * @param type The type of update ("Attack", "Items", or "Pokemon").
     */
    private void updateLeftPanel(String type) {
        Pokemon player1Pokemon = buttonPokemonMap.get(selectedButtonPlayer1);
        Pokemon player2Pokemon = buttonPokemonMap.get(selectedButtonPlayer2);

        leftPanel.removeAll();
        switch (type) {
            case "Attack" -> {
                Pokemon currentPokemon = isPlayer1Turn ? player1Pokemon : player2Pokemon;
                Pokemon opponentPokemon = isPlayer1Turn ? player2Pokemon : player1Pokemon;
                for (int i = 0; i < currentPokemon.getAttacks().size(); i++) {
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

    /**
     * Creates an attack button for the given Pokemon and attack index.
     *
     * @param currentPokemon   The Pokemon using the attack.
     * @param index            The index of the attack in the Pokemon's attack list.
     * @param opponentPokemon  The opposing Pokemon.
     * @return The created attack button.
     */
    private JButton getAttackButton(Pokemon currentPokemon, int index, Pokemon opponentPokemon) {
        JButton attackButton = new JButton(currentPokemon.getAttacks().get(index).getName());
        Attack attack = currentPokemon.getAttacks().get(index);
        attackButton.addActionListener(e -> {
            battle.performTurn(attack);
            if (opponentPokemon.getHp() <= 0) {
                int result = JOptionPane.showConfirmDialog(PokeBattleGUI.this, "The battle has ended! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            } else {
                isPlayer1Turn = !isPlayer1Turn;
            }
        });
        return attackButton;
    }

    /**
     * Resets the game state to allow players to start a new battle.
     */
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

    /**
     * Creates a JLabel with a specific GIF, position, and size.
     *
     * @param path   The path to the GIF file.
     * @param x      The x-coordinate of the JLabel.
     * @param y      The y-coordinate of the JLabel.
     * @param width  The width of the JLabel.
     * @param height The height of the JLabel.
     * @return The created JLabel.
     */
    private JLabel createGifLabel(String path, int x, int y, int width, int height) {
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);

        // Load the GIF icon
        ImageIcon gifIcon = createImageIcon(path);
        if (gifIcon != null) {
            // Get the icon as an image and resize it to the label size
            Image img = gifIcon.getImage();
            Image newImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon resizedIcon = new ImageIcon(newImg);

            label.setIcon(resizedIcon);
            // Center the image within the JLabel
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);

            // Start the GIF animation
            ((ImageIcon) label.getIcon()).setImageObserver(label);
        } else {
            label.setText("GIF not found!");
        }

        return label;
    }

    /**
     * Loads an ImageIcon from the specified file path.
     *
     * @param path The path to the GIF file.
     * @return The loaded ImageIcon.
     */
    private ImageIcon createImageIcon(String path) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            System.err.println("Unable to load image file: " + path);
            return null;
        }
    }

    /**
     * Starts the battle by initializing the Battle object with the selected Pokemon.
     */
    private void startBattle() {
        if (selectedButtonPlayer1 == null || selectedButtonPlayer2 == null) {
            // Handle case where a player has not selected a Pokémon
            return;
        }

        Pokemon player1Pokemon = buttonPokemonMap.get(selectedButtonPlayer1);
        Pokemon player2Pokemon = buttonPokemonMap.get(selectedButtonPlayer2);

        battle = new Battle(player1Pokemon, player2Pokemon);
        battle.start();
    }
}
