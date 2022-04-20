import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

public class WhiteBoxTesting {
	
	
	public WhiteBoxTesting() throws IOException{}
	
	String[] args = {"csued"};
	 File_Buffer FILE       = new File_Buffer();
	 Init_Exit Start_End    = new Init_Exit(args,FILE);
	 File_Buffer FILE_T = new File_Buffer();
	 Init_Exit Start_End_T = new Init_Exit(args,FILE_T);
	 int nLines;
	 Cmd_Driver cmd = new Cmd_Driver();

	@Test
	public void case1() throws IOException {

		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="SYNTAX ERROR IN COMMAND: J" +System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case2() throws IOException {

		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="At Edit File Line "+ FILE.GetCLN() +System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case3() throws IOException {
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="EditCmd> ";
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case4() throws IOException {

		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="NUMBER LINES VALUE MUST BE POSITIVE & NONZERO.  No action taken."+System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case5() throws IOException {
		
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="COMMAND NOT IMPLEMENTED (for F, R, O) YET."+System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case6() throws IOException {
		
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="REVERSED OR BACKWARDS COLUMN RANGES ARE ILLEGAL:  No action taken."+System.lineSeparator()
		+"COMMAND NOT IMPLEMENTED (for F, R, O) YET."+System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case7() throws IOException {
		
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine(); // keyword는 I가 먼저 실행되어야 하기 때문에 한번 더 입력
		cmd.RunCmd(FILE, cmdTokens);
		
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="1  2  "+System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case8() throws IOException {
		
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine(); // keyword는 I가 먼저 실행되어야 하기 때문에 한번 더 입력
		cmd.RunCmd(FILE, cmdTokens);
		
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="THIS KEYWORD DOES NOT EXIST:  No action taken."+System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case9() throws IOException {
		
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="1: @computing"+System.lineSeparator()
		+"2: In computing, a line editor is a text editor in which each editing command applies to one or more complete lines of text designated by the user. Line editors predate screen-based text editors and originated in an era when a computer operator typically interacted with a teleprinter (essentially a printer with a keyboard),"+System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case10() throws IOException {
		
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="A NULL (0 LENGTH) STRING HAS NO MEANING HERE:  No action taken."+System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case11() throws IOException {
		
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="@Computing"+System.lineSeparator()
		+"In Computing, a line editor is a text editor in which each editing command applies to one or more complete lines of text designated by the user. Line editors predate screen-based text editors and originated in an era when a computer operator typically interacted with a teleprinter (essentially a printer with a keyboard)," +System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case12() throws IOException {
		
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="A NULL (0 LENGTH) STRING HAS NO MEANING HERE:  No action taken."+System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	
	
	@Test
	public void case13() throws IOException {
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="... ";
		assertEquals(msg,out.toString());
	}
	
	@Test
	public void case14() throws IOException {

		UserCmd cmdTokens;
		cmdTokens = Parser.parseCmdLine();
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		if(cmdTokens.getOkSyntax())
			cmd.RunCmd(FILE, cmdTokens);
		 else
         {
             Msg.ERROR(-3);
             Msg.wLMsg(cmdTokens.getCmdLine());
         }
		String msg ="SYNTAX ERROR IN COMMAND: H J" +System.lineSeparator();
		assertEquals(msg,out.toString());
	}
	

}
