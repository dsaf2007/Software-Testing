import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;


public class CurrencyTest {
	
	@Test
	public void ToEuros() throws IOException
	{
		Currency in = new Currency(1.0,"USD");
		Currency ex = new Currency(0.93, "EUR");
		Exchange mock = EasyMock.createMock(Exchange.class);
		EasyMock.expect(mock.getRate("USD","EUR")).andReturn(0.93);
		EasyMock.replay(mock);
		Currency test = in.toEuros(mock);
		assertEquals(ex,test);
		EasyMock.verify(mock);
		
	}

}
