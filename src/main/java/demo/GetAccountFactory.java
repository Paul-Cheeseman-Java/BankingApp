package demo;

public class GetAccountFactory {
	
	
    public static Account getAccount(String accountType){  
        if(accountType == null){  
         return null;  
        }  
      if(accountType.equalsIgnoreCase("BASIC")) {  
             return new Basic();  
           }   
       else if(accountType.equalsIgnoreCase("CURRENT")){  
            return new Current();  
        }   
      else if(accountType.equalsIgnoreCase("CREDIT")) {  
            return new Credit();  
      }  
      else if(accountType.equalsIgnoreCase("SAVING")) {  
          return new Saving();  
    }  
  return null;  
}  

}
