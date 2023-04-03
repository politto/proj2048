import java.io.File;
import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.*;

public class SoundPlayer{


    public void playSound (String name, double volume) {

      System.out.println(volume);
        if (volume == 0) volume -= 80;
        else volume = volume * (0.46) - 40;
        System.out.println(volume);
        File file = new File(name);
        try {
          AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
          Clip clip = AudioSystem.getClip();
          clip.open(audioStream);
          FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
          fc.setValue((float)volume);
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

    public void RandomPlayBGmusic(int volume) {

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
  
        customTimerTask task = new customTimerTask(song, volume);
  
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

  private String song;
  private int volume;

  customTimerTask(String song, int volume){
    super();
    this.setSong(song);
    this.setVolume(volume);
    playSong(this.getSong());
  }
  public void run() {

  }
  
  public int getVolume(){
    return volume;
  }

  public void setVolume(int vol){
    this.volume = vol;
  }

  public void playSong(String name) {
    SoundPlayer sp =new SoundPlayer();
    sp.playSound(name, volume);
  }
  
  public String getSong(){
    return this.song;
  }

  public void setSong(String song){
    this.song = song;
  }

}