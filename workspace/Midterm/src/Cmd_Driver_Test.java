import static org.junit.Assert.*;
import java.io.*;

import org.junit.Test;

public class Cmd_Driver_Test {
	
	public Cmd_Driver_Test() throws IOException{}
	
	String[] args = {"csued"};
	 File_Buffer FILE       = new File_Buffer();
	 Init_Exit Start_End    = new Init_Exit(args,FILE);
	 File_Buffer FILE_T = new File_Buffer();
	 Init_Exit Start_End_T = new Init_Exit(args,FILE_T);
	 int nLines;
	 Cmd_Driver cmd = new Cmd_Driver();
	 
	 private boolean Valid_Lines()
	   {
	       boolean Ok1=true, Ok2=true;

	       // Check that number of lines is positive

	       Ok1 = (nLines >= 1);

	       // Check if nonsensical: nLines positive but 0/NO file buffer lines!

	       Ok2 = (FILE.NumLins() != 0);

	       // If errors print correct error message
	     
	       if(!Ok1)
	          Msg.ERROR(4);
	       else if(!Ok2) 
	          Msg.ERROR(5);

	       // Now return the line number validity status

	       return (Ok1 && Ok2);

	   }
	 
		
		@Test
		public void Cmd_C() throws IOException
		{
			OutputStream out = new ByteArrayOutputStream();
			System.setOut(new PrintStream(out));
			UserCmd cmdTokens;
			cmdTokens = Parser.parseCmdLine();
			cmd.RunCmd(FILE, cmdTokens);
			String msg = "EditCmd>"+" "+"Total Edit File Lines: "+FILE.NumLins() + System.lineSeparator();
			assertEquals(msg,out.toString());
			
		}
	 
	@Test
	public void Cmd_T() {
		FILE.SetCLN( Math.min(FILE.NumLins(),1) );
		cmd.Cmd_T(FILE_T);
		 assertEquals(FILE.GetCLN(),FILE_T.GetCLN());

	}
	
	@Test
	public void Cmd_E()
	   {
	       FILE.SetCLN( FILE.NumLins() );
			cmd.Cmd_E(FILE_T);
	       assertEquals(FILE.GetCLN(),FILE_T.GetCLN());
	   }
	
	@Test
	public void Cmd_N()
	{
		nLines = 2;
	          int n = Math.min(FILE.GetCLN()+nLines,FILE.NumLins());
		 
		 cmd.Cmd_N(FILE_T, nLines);
		 assertEquals(n,FILE_T.GetCLN());
	}
	
	@Test
	public void Cmd_B()
	{
		nLines = 2;
	    int n = Math.max(FILE.GetCLN()-nLines,1);
		 
		 cmd.Cmd_B(FILE_T, nLines);
		 assertEquals(n,FILE_T.GetCLN());
		
	}
	
	@Test
	public void Cmd_L()
	{
		nLines = 2;
		cmd.Cmd_L(FILE_T, nLines);
		/*if(Valid_Lines()) 
	       {
	          int CLN=FILE.GetCLN();
	          int end=Math.min(CLN+nLines-1,FILE.NumLins());
	          for(int i=CLN; i>end; i++)
	              Msg.wLMsg(FILE.GetLine(i));
	          FILE.SetCLN(end);
	       }*/	
		assertEquals(2,FILE_T.GetCLN());
	}
	
	@Test
	public void Cmd_M()
	{
		int left = 2;
		int right = 60;
		cmd.Cmd_M(left, right);
		assertEquals(left, cmd.Margin_Left);
		assertEquals(right, cmd.Margin_Right);
	}
	
	
	@Test
	public void Cmd_D()
	{
		
		int nLines = 2;
		cmd.Cmd_D(FILE_T,nLines);
		
		/*int CLN = FILE.GetCLN();
		int end=Math.min(CLN+nLines-1,FILE.NumLins());
        for(int i=CLN; i<=end; i++)
            FILE.DelLine(CLN);
        FILE.SetCLN( Math.min(CLN,FILE.NumLins()) );*/
        
        assertEquals(1,FILE_T.GetCLN());
        assertEquals(FILE.NumLins()-2,FILE_T.NumLins());
	}
	
	@Test
	public void Cmd_S()
	{
		int nLines = 2;
		cmd.Cmd_S(FILE_T, nLines);
		
		 int CLN=FILE.GetCLN();
         int end=Math.min(CLN+nLines-1,FILE.NumLins());
         for(int i=CLN; i<=end; i++)
             FILE.DelLine(CLN);
         FILE.SetCLN( Math.min(CLN,FILE.NumLins()) );
         
         assertEquals(1,FILE_T.GetCLN());
	}
	
	@Test
	public void Cmd_A() throws IOException
	{
		//끝으로 이동 후
		//hello
		//
		//를 Add
		cmd.Cmd_E(FILE);
		cmd.Cmd_E(FILE_T);
		cmd.Cmd_A(FILE_T);
		assertEquals(FILE.NumLins()+1,FILE_T.NumLins());
		assertEquals(FILE.GetCLN()+1,FILE_T.GetCLN());
	}
	
	@Test
	public void Cmd_Y()
	{
		int nLines = 2;
		cmd.Cmd_Y(FILE_T, nLines);
		PQueue Yank_Buffer;
		
		Yank_Buffer = new PQueue();               // Allocate a new buffer

        int CLN=FILE.GetCLN();                        // Compute line range to yank
        int end=Math.min(CLN+nLines-1,FILE.NumLins());

        for(int i=CLN; i<=end; i++)                   // Yank the lines into a Queue
           Yank_Buffer.enQueue(FILE.GetLine(i));

        FILE.SetCLN(end); 
        
        for(int j = 0; j < 2; j++)
        	assertEquals(Yank_Buffer.getQueue(j),cmd.Yank_Buffer.getQueue(j));//각 line이 동일한지 확인
        assertEquals(FILE.GetCLN(),FILE_T.GetCLN());
		
	}
	
	@Test
	public void Cmd_Z()
	{
		int nLines = 2;
		cmd.Cmd_Z(FILE_T, nLines);
		PQueue Yank_Buffer;
		
		Yank_Buffer = new PQueue();               // Allocate a new buffer

        int CLN=FILE.GetCLN();                        // Compute line range to yank
        int end=Math.min(CLN+nLines-1,FILE.NumLins());

        for(int i=CLN; i<=end; i++)                   // Yank the lines into a Queue
           Yank_Buffer.enQueue(FILE.GetLine(i));

        FILE.SetCLN(CLN);
        cmd.Cmd_D(FILE, nLines);
        
        for(int j = 0; j < 2; j++)
        	assertEquals(Yank_Buffer.getQueue(j),cmd.Yank_Buffer.getQueue(j));//각 line이 동일한지 확인
        assertEquals(FILE.GetCLN(),FILE_T.GetCLN());
        assertEquals(FILE.NumLins(),FILE_T.NumLins());
		
	}
	
	@Test
	public void Cmd_P()
	{
		//처음 두줄을 복사해서 마지막 줄에 Paste
		int nLines = 2;
		cmd.Cmd_Y(FILE_T, nLines);
		cmd.Cmd_E(FILE_T);
		cmd.Cmd_P(FILE_T);
		
		assertEquals(FILE.NumLins()+2,FILE_T.NumLins());
	}

	@Test
	public void Cmd_O()
	{
		//블랙박스 테스팅을 통해 1 2 3이 1 3 2 순서로 바뀜을 알고있음.(Index 제외)
		int nLines = 4;
		cmd.Cmd_O(FILE_T, nLines);
		assertEquals(FILE.GetLine(4),FILE_T.GetLine(3));
		assertEquals(FILE.GetLine(3),FILE_T.GetLine(4));
	}
	
	@Test
	public void newFileOpenAndSaveEmptyFile() throws IOException
	{
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Msg.ERROR(1);
		Msg.ERROR(2);
		OutputStream out2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out2));
		String[] newFile = {"newFile"};
		 File_Buffer FILE       = new File_Buffer();
		 Init_Exit Start_End    = new Init_Exit(newFile,FILE);
		 Start_End.Do_Update(FILE);
		 
		 assertEquals(out.toString(),out2.toString());
		 
	}
	
	
}
