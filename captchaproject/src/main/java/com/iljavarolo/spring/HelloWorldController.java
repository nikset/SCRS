package com.iljavarolo.spring;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HelloWorldController {

@RequestMapping(value = "/hello")
public String hello(@RequestParam(value="msg", required=false, defaultValue="Hello World") String msg, Model model) {

model.addAttribute("msg", msg);

return "helloworld";

}

//@RequestMapping(value = "/autenticazione")
//public String autenticazione(@RequestParam(value="msg", required=false, defaultValue="Hello World") String msg, Model model) {

//model.addAttribute("msg", msg);

//return "authentication";

//}


@RequestMapping(value = "/welcome", method = RequestMethod.GET) 
	public String welcome( ) {		
		        System.out.println("Application Startup Welcome Page");		
		        return "welcome";		
		    }

@RequestMapping(value = "/redirect_page", method = RequestMethod.GET)
    public String redirect(@RequestParam("userName")String name, @RequestParam("password")String password) {
        System.out.println("Redirecting Result To The Final Page");
        
        
        System.out.println(name);
        System.out.println(password);
        
        return "redirect:final_page";
    }






@RequestMapping(value = "/authentication", method = RequestMethod.GET) 
public String authentication( ) {		
	 System.out.println("Sto indirizzando all autenticazione");		
	        return "authentication_page";		
	    }



@RequestMapping(value = "/controllo_credenziali", method = RequestMethod.GET)
public String controllo_credenziali(@RequestParam("userName")String name, @RequestParam("password")String password, String msg, Model model ) throws IOException, URISyntaxException  {
    System.out.println("Redirecting Result To The Final Page");

    System.out.println("Username che arriva alla classe:"+ name);
    System.out.println("Password che arriva alla classe:" + password);

    
    //funziona la lettura potrebbe andare in errore se il file non esistesse ancora
    InputStream is = getClass().getResourceAsStream("/credenziali.txt");
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
   
   String autenticazione = name +password; 
   System.out.println(autenticazione);
   String str;
   while((str = reader.readLine())!=null){
	   
	   if(str.equals(autenticazione) ){
		   return "autenticazione_effettuata";
	   }
	   
	   
   }
   
   msg = "Autenticazione fallita";
   model.addAttribute("msg", msg);
   System.out.println("Autenticazione fallita");
   reader.close();
   return "pagina_errori_credenziali";  
   
}




@RequestMapping(value = "/registrazione", method = RequestMethod.GET)
public String indirizzaPaginaRegistrazione( ) {		
    System.out.println("Sto indirizzando alla registrazione");		
    return "registration_page";		
}





@RequestMapping(value = "/esegui_registrazione", method = RequestMethod.GET)
public String registrazione(@RequestParam("userName")String name, @RequestParam("password")String password, String msg, Model model) throws IOException, URISyntaxException  {
	
	//Controlla che la registrazione non sia stata già effettuata e che quindi le credenziali non siano già
	//presenti nel file delle credenziali
	
    //funziona la lettura
	
	
	
    InputStream is = getClass().getResourceAsStream("/credenziali.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
   
   String autenticazione = name + password; 
   System.out.println(autenticazione);
   String str;
   while((str = reader.readLine())!=null){
	   
	   if(str.equals(autenticazione)){
		   msg = "credenziali precedentemente registrate";
		   model.addAttribute("msg", msg);
		   System.out.println("Credenziali già presenti");
		   reader.close();
		   return "pagina_errori_credenziali";   
	   }
	    
   }
   
   //salva le credenziali inserite nel file di testo
	
   
   
   
   
   
   //File file= new File (this.getClass().getResource("/credenziali.txt").toURI());
   File file= new File (this.getClass().getResource("/credenziali.txt").toURI()   );
   FileWriter fw;
   
 
   
   if (file.exists())
   {
   	//salva all'interno del file di testo le credenziali su un'unica riga concatenate
      fw = new FileWriter(file,true);
      PrintWriter out = new PrintWriter(fw);
      out.println("\n");
		out.append(name);
		out.append(password);
		out.close();
      fw.close();
   }
   else
   {
   	//TODO
      file.createNewFile();
      fw = new FileWriter(file);
   }
	
	
	return "registrazione_effettuata";
}




//passiamo i parametri alla final Page per poterli visualizzare
@RequestMapping(value = "/final_page", method = RequestMethod.GET)
public String finalPage() {
      System.out.println("Showing The Redirected Page");
      

      return "final";
  }

}