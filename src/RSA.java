import java.lang.Math; 
import java.util.Random;
import java.lang.String;

public class RSA
{
  public static int Puissance_modulaire(int a,int n,int m)
  {
      int  p = 1;
    while (n>0) 
        {  while (n%2 == 0) 
       		 {  a = (a*a)%m; if (a<0) a*=(-1); // pour certain nombres %m nous donne le resultat juste mais negatif
           		n = n/2;
             }
            p = (p*a)%m;
         if (p<0) p*=(-1); // pour certain nombres %m nous donne le resultat juste mais negatif
            n = n-1;
        }
    return p;

  }
  public static int Inverse_modulaire(int a0,int m0)
  {
    int[] tab= {a0, m0, 1, 0};
    int[] temp= new int[4];
    while (tab[1] != 0) 
    {
     	temp[0]=tab[1];
        temp[1]=tab[0]%tab[1];
        temp[2]=tab[3];
        temp[3]=tab[2]-(tab[0]/tab[1])*tab[3];
      for (int i=0;i<4;i++)
      tab[i] =temp[i];
    }
    if (tab[0] == 1) 
    {
        while ( tab[2] < 0 )
            tab[2] = tab[2]+m0;
        return tab[2];
    }
    else 
        return 0;

  }
  public static int[] Crible(int n)
  {
    int[] L=new int[n-1];
    int m=n-1; // a la fin m devient le nombre de premiers dans le tableau
    
    for (int i=0;i<(n-1);i++)
      L[i] =i+2; 				// tableau contenant les entiers de 2 jusqu'a n
   
     for (int i=0;((i+2)*(i+2))<=n;i++)
    {
     	 for (int j=((i+2)*(i+2))-2;j<n-1;j+=(i+2))
   		 {
           if(L[j]!=0)
           { L[j]=0;
           	 m--;// L[j] n'est pas premier alors on l'enleve du tableau et m diminue
           }
         }
     }
    int[] premiers=new int[m];
    int j=0;//compteur
    for (int i=0;i<(n-1);i++)
    {
      if (L[i]!=0)
    	{
        	premiers[j]=L[i];
        	j++;
        }
    }
    
    return premiers;
  	}
  
   public static int[] cles()
  {
     int[] premiers=Crible(100); // tableau des premiers inf a 100 ( si on choisis un nombre >100 faut travailler avec long et non int
     Random rand = new Random();
     
    int p ;
    int q;
	int n;
    int m;
    int e;
    int d;
     do {
       	p= premiers[rand.nextInt(premiers.length)] ;
    	q = premiers[rand.nextInt(premiers.length)] ;
     	
        n = p*q;
        m = (p-1)*(q-1);
        e = rand.nextInt(premiers.length);
        while ( Inverse_modulaire(e, m) == 0)
        {
            e = rand.nextInt(premiers.length);
        }
        d = Inverse_modulaire(e, m);
     }while(e==d);
    System.out.println("cle publique :("+e+","+n+") , cle privee :("+d+","+n+")");
     int[] cles={e, d, n};// {e, d, n};
     return cles;

   }
  
  // fcts de transformation du texte /////////////////////////////////////
  
  public static int[] Texte_ascii(String s)
  {	
    char[] c=s.toCharArray();
    int[] ascii=new int[c.length];
     for (int i=0;i<c.length;i++)
    {
       ascii[i]=c[i];
     }
    return ascii;
  }
  public static String Ascii_texte(int[] a)
  {	
    char[] c=new char[a.length];
 
    String s="";
     for (int i=0;i<a.length;i++)
    {	c[i]=(char)(a[i]);
       s+=c[i];
     }
    return s;
  }
    public static String[] Ascii_bloc(int[] a, int taille)
  {	
    String[] b = new String[a.length];
     for (int i=0;i<a.length;i++)
    {
       b[i]=String.format("%0"+taille+"d",  a[i]);
       
   
     }
    return b;
  }
    public static int[] Bloc_ascii(String[] b)
  {	
    int[] a = new int[b.length];
     for (int i=0;i<a.length;i++)
    {
       a[i]=Integer.parseInt(b[i]);
       
    
     }
    return a;
  }
  
   public static String Bloc_code(String[] b, int[] cle,int taille)// la var taille est pour conserver la taille des blocs apres le cryptage
  {	int temp; // var temporaires pour le calcul
   String[] t= new String[1] ;
     String code="";
     for (int i=0;i<b.length;i++)
    {
      temp=Puissance_modulaire(Integer.parseInt(b[i]),cle[0],cle[1]);
       int[] a={temp};
        t= Ascii_bloc(a,taille);
       code+=t[0];
     }
    return code;
  }
   public static String[]  Code_bloc(String c, int[] cle,int taille) 
  {	String[] b= new String[c.length()/taille];
   int temp; // var temporaires pour le calcul
   	int index=0;
   
   
      for (int i=0;i<(c.length()/taille);i++)
    {	
        b[i]=c.substring(index,index+taille);
        index+=taille;
        temp=Puissance_modulaire(Integer.parseInt(b[i]),cle[0],cle[1]); 
       	b[i]=Integer.toString(temp);
    }
   	return b;
  }
  
  public static String cryptage (String texte , int[] cle , int taille)
  {
    return Bloc_code(Ascii_bloc(Texte_ascii(texte), 3), cle,taille);// 3 est le nombre de caracteres dans un code ascii 
  }
  public static String decryptage (String code , int[] cle , int taille)
  {
    return Ascii_texte(Bloc_ascii(Code_bloc(code, cle,taille) )) ;
  }
  ///////////////////////////////////////////////////////////////////////
  
  
  
  public static void main(String[] args)
  {int[] a =RSA.cles();
   String n=Integer.toString(a[2]); 
  int[] b = {a[0],a[2]};
   int[] c = {a[1],a[2]};
   int taille;
   
   if(n.length()>3)
    taille =n.length();
   else	
     taille =3; // si n a la taille inf a 3 on doit respecter dans le calcul la taille min du code ascii qui est 3 
   
   String texte="hellooooos ! ki jitek ha ?";
   System.out.println("texte ="+texte);
   texte=RSA.cryptage (texte ,b ,taille);
   System.out.println("cryptage ="+texte);
   System.out.println(RSA.decryptage(texte, c,taille) ); // b la cle de cryptage , n.length() la taille a respecter
  }
}

