import java.io.File;
import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.*;

public class SoundPlayer{


    public void playSound (String name) {

        File file = new File(name);
        try {
          AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
          Clip clip = AudioSystem.getClip();
          clip.open(audioStream);
          clip.start();
        }
        catch(UnsupportedAudioFileException e){
          System.out.println("fuck");
        }
        catch(LineUnavailableException e) {
          System.out.println("line unavalible.");
        }
        catch(IOException e){
          System.out.println("IOexcepton !! : " + e);
        }
        

    }

    public void RandomPlayBGmusic() {

      while (true){
        int random = (int)(Math.random() * 7);
        String song = "";
       int duration = 0;
       System.out.println(random + " < random");
       switch (random) {
        case 0:
          song = "cafe1.wav";
          duration = 285;
          break;
        case 1:
          song = "cafe2.wav";
          duration = 185;
          break;   
        case 2:
          song = "cafe3.wav";
          duration = 285;
          break;             
        case 3:
          song = "cafe4.wav";
          duration = 230;
          break;            
        case 4:
          song = "First Dream - Brian Bolger.wav";
          duration = 150;
          break;
      
        case 5:
          song = "Nineties Pad - Brian Bolger.wav";
          duration = 100;
          break;
        
        case 6:
          song = "Yoga Style - Chris Haugen.wav"; 
          duration = 180;
          break;
        
        default:
          break;
       }
       Timer timer = new Timer();
 
       customTimerTask task = new customTimerTask(song);
 
       timer.schedule(task ,0 , duration * 1000);
       try {
        Thread.sleep(1000 * duration);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      }
    }
}

class customTimerTask extends TimerTask{

  String song;

  customTimerTask(String song){
    super();
    this.song = song;
    playSong(song);
  }
  public void run() {

  }

  public void playSong(String name) {
    SoundPlayer sp =new SoundPlayer();
    sp.playSound(name);
  }
}