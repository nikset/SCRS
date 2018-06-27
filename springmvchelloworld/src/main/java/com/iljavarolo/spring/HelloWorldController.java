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

@RequestMapping(value = "/autenticazione")
public String autenticazione(@RequestParam(value="msg", required=false, defaultValue="Hello World") String msg, Model model) {

model.addAttribute("msg", msg);

return "authentication";

}


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


//passiamo i parametri alla final Page per poterli visualizzare
@RequestMapping(value = "/final_page", method = RequestMethod.GET)
 public String finalPage() {
        System.out.println("Showing The Redirected Page");
        

        return "final";
    }



@RequestMapping(value = "/authentication", method = RequestMethod.GET) 
public String authentication( ) {		
	        System.out.println("Application Startup Welcome Page");		
	        return "authentication_page";		
	    }





@RequestMapping(value = "/controllo_credenziali", method = RequestMethod.GET)
public String controllo_credenziali(@RequestParam("userName")String name, @RequestParam("password")String password) throws IOException, URISyntaxException  {
    System.out.println("Redirecting Result To The Final Page");

    System.out.println(name);
    System.out.println(password);
    

    
    
    PrintWriter writer1 =new PrintWriter(new File(this.getClass().getResource("/credenziali.txt").toURI()));
    writer1.println("ahahahahah");
  
    writer1.append("ciao");
   writer1.close();
    
    //funziona la lettura
    InputStream is = getClass().getResourceAsStream("/credenziali.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
   
    
   String str;
   while((str = reader.readLine())!=null){
	   System.out.println("ciao:" + str + "\n");
   }
   
   reader.close();
   
   
   
   
   
    
       

     
   
   
   
   
   
   
   
   
   
    
    return "redirect:final_page";
}








}