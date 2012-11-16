import java.io.*;
import java.util.*;

public class HelloServer extends NanoHTTPD
{
	public HelloServer() throws IOException
	{
		super(8080, null);
	}

	public Response serve( String uri, String method, Properties header, Properties parms, Properties files )
	{
		System.out.println( method + " '" + uri + "' " );
        if(uri.equals("/go")){
            try{
               Runtime.getRuntime().exec("/usr/bin/say go");
            }catch(java.io.IOException e){
                System.err.println(e);
            }
            System.out.println("go!!!!");
            return new NanoHTTPD.Response( HTTP_OK, MIME_HTML, "go");
        }
        if(uri.equals("/stop")){
            try{
               Runtime.getRuntime().exec("/usr/bin/say stop");
            }catch(java.io.IOException e){
                System.err.println(e);
            }
            System.out.println("stop!!!!");
            return new NanoHTTPD.Response( HTTP_OK, MIME_HTML, "stop");
        }
        return serveFile(uri, header, new File("./data"), true);
	}


	public static void main( String[] args )
	{
		try
		{
			new HelloServer();
		}
		catch( IOException ioe )
		{
			System.err.println( "Couldn't start server:\n" + ioe );
			System.exit( -1 );
		}
		System.out.println( "Listening on port 8080. Hit Enter to stop.\n" );
		try { System.in.read(); } catch( Throwable t ) {};
	}
}
