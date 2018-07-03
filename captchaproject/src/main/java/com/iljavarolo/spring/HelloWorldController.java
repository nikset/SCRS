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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HelloWorldController {

//@RequestMapping(value = "/hello")
//public String hello(@RequestParam(value="msg", required=false, defaultValue="Hello World") String msg, Model model) {
//	model.addAttribute("msg", msg);
//	return "helloworld";
//}

//@RequestMapping(value = "/autenticazione")
//public String autenticazione(@RequestParam(value="msg", required=false, defaultValue="Hello World") String msg, Model model) {

//model.addAttribute("msg", msg);

//return "authentication";

//}


//@RequestMapping(value = "/welcome", method = RequestMethod.GET) 
//	public String welcome( ) {		
//		        System.out.println("Application Startup Welcome Page");		
//		        return "welcome";		
//		    }

 //@RequestMapping(value = "/redirect_page", method = RequestMethod.GET)
 //   public String redirect(@RequestParam("userName")String name, @RequestParam("password")String password) {
 //       System.out.println("Redirecting Result To The Final Page");
        
        
 //       System.out.println(name);
 //       System.out.println(password);
        
 //       return "redirect:final_page";
 //   }






@RequestMapping(value = "/authentication", method = RequestMethod.GET) 
public String authentication( ) {		
	 System.out.println("Sto indirizzando all autenticazione");		
	        return "authentication_page";		
	    }



@RequestMapping(value = "/controllo_credenziali", method = RequestMethod.GET)
public String controllo_credenziali(@RequestParam("userName")String name, @RequestParam("password")String password,@RequestParam("captchaPin")String captchaPin, String msg, Model model ) throws IOException, URISyntaxException, NoSuchAlgorithmException  {
    System.out.println("Redirecting Result To The Final Page");

    System.out.println("Username che arriva alla classe:"+ name);
    System.out.println("Password che arriva alla classe:" + password);
    System.out.println("Pin che arriva alla classe:" + captchaPin);
    
    //Codifica del captchaPin 
    String captchaPinCodificato = codificaStringa(captchaPin);
    
    
    
    //funziona la lettura potrebbe andare in errore se il file non esistesse ancora
    InputStream is = getClass().getResourceAsStream("/credenziali.txt");
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
   
    String autenticazione = name +password+captchaPinCodificato; 
    System.out.println(autenticazione);
    String str;
    while((str = reader.readLine())!=null){
	   
    	if(str.equals(autenticazione) ){
    		//return "autenticazione_effettuata";
    		//reindirizzamento allapagina di google
    		return "redirect:https://www.google.com/";
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
public String registrazione(@RequestParam("userName")String name, @RequestParam("password")String password,@RequestParam("captchaPin")String captchaPin, String msg, Model model) throws IOException, URISyntaxException, NoSuchAlgorithmException  {
	
	//Controlla che la registrazione non sia stata già effettuata e che quindi le credenziali non siano già
	//presenti nel file delle credenziali
	
	
	//codifico il captchaPin
	String captchaPinCodificato = codificaStringa(captchaPin);
	
    //funzione di lettura
	
	
	
    InputStream is = getClass().getResourceAsStream("/credenziali.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
   
   String autenticazione = name + password + captchaPinCodificato; 
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
		out.append(captchaPinCodificato);
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
//@RequestMapping(value = "/final_page", method = RequestMethod.GET)
//public String finalPage() {
 //     System.out.println("Showing The Redirected Page");
      

 //     return "final";
 // }



/*
//1)codifichiamo il pin in base 256 = in una stringa di 64 bit
//2) mettiamo questa stringa in un array
//3) scorriamo l'array e prendiamo il primo (a)e il secondo elemento (b)
//4) troviamo la "a" posizione ascii
//5) troviamo la "b" posizione in codice ascii
//6) sommiamo le posizioni e facciamo mod256(asciiPosition(a)+ascciPosition(b))
//7) prendiamo i primi 256 caratteri dello stringone e troviamo l'elemento nella posizione mod256(asciiPosition(a)+ascciPosition(b))
//8) prendiamo il terzo elemento e quarto elemento della mia stringa chiamati ("c" e "d") e facciamo la stessa cosa
//9) contando che c e d devono lavorare sui successivi 256 caratteri dello stringone
//10) genero la mia stringa codificata
*
*/
public String  codificaStringa(String captchaPin) throws IOException, NoSuchAlgorithmException{
   
 //ricevo il pin dall'esterno
    
    char stringagenerata[] = new char[8192] ;
    String pinFinale = "";
    
    
    //funziona la lettura
    InputStream is = getClass().getResourceAsStream("/stringaCodificata.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
   
    //leggo il mio stringone
    String str;
    while((str = reader.readLine())!=null){
	   //devo mettere str dentro un array di 8192 caratteri così mi posso prendere la posizione che voglio
	   stringagenerata =str.toCharArray();
	   //for(int i=0;i<stringagenerata.length;i++){
		 //   System.out.println("Data at ["+i+"]="+stringagenerata[i]);}
    }
    	reader.close();
    
    	//1) codifichiamo il pin in base 256 = in una stringa di 64 bit
    	MessageDigest md = null;
    	md = MessageDigest.getInstance("SHA-256");
    	md.update(captchaPin.getBytes());
    	StringBuffer captchaCodificato = new StringBuffer();
    	for (byte b : md.digest()) captchaCodificato.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
    
    
    	String captchaCodificatoString =   captchaCodificato.toString();
    	   
    	//2) mettiamo questa stringa in un array
    	char captchaCodificatoArr[] =captchaCodificatoString.toCharArray();
	   
    	
    	int contatore =0;
   
	   		char primoElemento;
	   		char secondoElemento;
	   		//3) scorriamo l'array e prendiamo il primo (a)e il secondo elemento (b)
	   			for(int i=0, j=0; i<captchaCodificatoArr.length && j<stringagenerata.length  ; i=i+2,j=j+255) {
	   
	   				
	   				contatore= contatore+1;
	   				
	   				primoElemento  = captchaCodificatoArr[i];
	   				secondoElemento  = captchaCodificatoArr[i+1];
		    
	   				//troviamo la posizione del "primo elemento" in codice ascii
	   				int posizioneASCIIprimoElemento=(int)primoElemento;
		 
	   				//troviamo la posizione del "secondo elemento" in codice ascii  
	   				int posizioneASCIIsecondoElemento=(int)secondoElemento;

	   				int sommaPosizioniASCII = posizioneASCIIprimoElemento + posizioneASCIIsecondoElemento;
		    
	   				int moduloSommaPosizioniASCII = sommaPosizioniASCII %256;
		    
		    	    //tale elemento va cocatenato con i valori generati man mano per creare il pin generale
		    	    char elementoPin =stringagenerata[j+moduloSommaPosizioniASCII];
		    			    	    
		    	    pinFinale =pinFinale.concat(String.valueOf(elementoPin) );
		    	
	   		}
    	
    	
   
   return pinFinale;
   
}













}