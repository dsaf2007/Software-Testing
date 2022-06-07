import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class notepad {
	public static void main(String[] args) throws FindFailed, InterruptedException { 
		String icon = "C:\\test\\note_icon.png";
		String note = "C:\\test\\notepad.png";
		
		Screen s=new Screen();
		s.find(icon); 
		s.doubleClick();
		s.click(note);
		s.type(note,"This is Nice Sikuli Tutorial!!!!");
	}
}
