import java.util.Observer;
import java.util.Observable;
public class TextObserver implements Observer
{
   private Tamagotchies ov = null;
   public TextObserver(Tamagotchies ov)
   {
      this.ov = ov;
   }
   public void update(Observable obs, Object obj)
   {
      if (obs == ov)
      {
         System.out.println(ov.getValue());
      }
   }
}