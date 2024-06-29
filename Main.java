import java.util.List;
import java.util.Arrays;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PokeBattleGUI gui = new PokeBattleGUI();
        PokeBattleGUI.playBackgroundMusic("C:\\Users\\muril\\IdeaProjects\\PokeBattle\\src\\sounds\\fundo.wav");
        gui.setVisible(true);

    }

    void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Play in loop
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
