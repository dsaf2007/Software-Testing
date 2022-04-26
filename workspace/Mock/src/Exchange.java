import java.io.IOException;

public interface Exchange {
	
	double getRate(String input, String output) throws IOException;

}
