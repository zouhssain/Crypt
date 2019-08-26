import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.ietf.jgss.MessageProp;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class RSA_GRAPHE extends JFrame{
	

	int[] a =RSA.cles();

	JFrame fen=this;
	JPanel panel_1, panel_2;
	private JTextPane textacode;
	//private JTextField ;
	private JTextPane result_crypto;
	private JTextPane textadecode;
	private JTextPane result_decrypto;
	private JTextField cpr1;
	private JTextField cpr2;
	private JTextField cprive1;
	private JTextField cprive2;
	private JLabel lblCle_2;
	private JLabel lblCle_3;
	private JLabel lblCle;
	private JLabel lblCle_1;
	RSA_GRAPHE()
	{
		//setIconImage(Toolkit.getDefaultToolkit().getImage(RSA_GRAPHE.class.getResource("/pics/home.png")));
		this.setTitle("CRYPTO");
		int tailleFenetreX=Toolkit.getDefaultToolkit().getScreenSize().width*1/2;
		int tailleFenetreY=Toolkit.getDefaultToolkit().getScreenSize().height*2/3;
		this.setSize(tailleFenetreX, tailleFenetreY);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setUndecorated (true);
		fen.getRootPane().setBorder(new LineBorder(new Color(255,0,0), 2));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, tailleFenetreX, tailleFenetreY);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				label.setIcon(new ImageIcon(RSA_GRAPHE.class.getResource("/img/exit_1.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				label.setIcon(new ImageIcon(RSA_GRAPHE.class.getResource("/img/exit.png")));
			}
		});
		label.setIcon(new ImageIcon(RSA_GRAPHE.class.getResource("/img/exit.png")));
		label.setBounds(tailleFenetreX-36, 0, 32, 32);
		panel.add(label);
		
		JButton btnCryptage = new JButton("Cryptage");
		btnCryptage.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCryptage.setForeground(new Color(255, 255, 255));
		btnCryptage.setBackground(SystemColor.desktop);
		btnCryptage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_2.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		btnCryptage.setBounds(0,32, tailleFenetreX/2, tailleFenetreY/10);
		panel.add(btnCryptage);
		
		JButton btnDecryptage = new JButton("Decryptage");
		btnDecryptage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
				panel_1.setVisible(false);
			}
		});
		btnDecryptage.setForeground(new Color(255, 255, 255));
		btnDecryptage.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDecryptage.setBackground(new Color(46, 139, 87));
		btnDecryptage.setBounds(tailleFenetreX/2, 32, tailleFenetreX/2, tailleFenetreY/10);
		panel.add(btnDecryptage);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, tailleFenetreY/10+32, tailleFenetreX, tailleFenetreY-tailleFenetreY/10-32);
		panel_1.setBackground(SystemColor.desktop);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textacode = new JTextPane();
		//textacode.setHorizontalAlignment(JTextPane.);
		textacode.setBounds(tailleFenetreX/20, tailleFenetreY/20+20, tailleFenetreX - 2*tailleFenetreX/20, tailleFenetreY/4+tailleFenetreY/20);
		panel_1.add(textacode);
		//textacode.setColumns(10);
		
		JLabel lblVeuillezSaisirVotre = new JLabel("Veuillez Saisir Votre Text");
		lblVeuillezSaisirVotre.setForeground(new Color(255, 255, 255));
		lblVeuillezSaisirVotre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVeuillezSaisirVotre.setBounds(tailleFenetreX/20, tailleFenetreY/20, 181, 14);
		panel_1.add(lblVeuillezSaisirVotre);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textacode.getText().toString();
				
				   String n=Integer.toString(a[2]); 
				  int[] b = {a[0],a[2]};
				   int[] c = {a[1],a[2]};
				   int taille;
				   
				   if(n.length()>3)
				    taille =n.length();
				   else	
				     taille =3; // si n a la taille inf a 3 on doit respecter dans le calcul la taille min du code ascii qui est 3 
				   String x = RSA.cryptage (text ,b ,taille);
				    cpr1.setText(""+a[1]);
				    cpr2.setText(""+a[2]);
				   result_crypto.setText(x);
			}
		});
		btnValider.setBounds(tailleFenetreX - tailleFenetreX/8 - tailleFenetreX/20, tailleFenetreY/4+tailleFenetreY/7, tailleFenetreX/8, tailleFenetreY/20);
		panel_1.add(btnValider);
		
		result_crypto = new JTextPane();
		result_crypto.setBounds(tailleFenetreX/20, 5*tailleFenetreY/9-tailleFenetreY/20, tailleFenetreX - 2*tailleFenetreX/20, tailleFenetreY/4+tailleFenetreY/20);
		panel_1.add(result_crypto);
		//result_crypto.setColumns(10);
		
		JLabel lblRsultat = new JLabel("R\u00E9sultat");
		lblRsultat.setForeground(Color.WHITE);
		lblRsultat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRsultat.setBounds(tailleFenetreX/20, 5*tailleFenetreY/9-tailleFenetreY/20-20, 181, 14);
		panel_1.add(lblRsultat);
		
		cpr1 = new JTextField();
		cpr1.setBounds(182, 209, 86, 20);
		panel_1.add(cpr1);
		cpr1.setColumns(10);
		
		cpr2 = new JTextField();
		cpr2.setBounds(324, 209, 86, 20);
		panel_1.add(cpr2);
		cpr2.setColumns(10);
		
		lblCle_2 = new JLabel("Cl\u00E9e 1");
		lblCle_2.setBounds(139, 212, 46, 14);
		panel_1.add(lblCle_2);
		
		lblCle_3 = new JLabel("Cl\u00E9e 2");
		lblCle_3.setBounds(281, 212, 46, 14);
		panel_1.add(lblCle_3);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, tailleFenetreY/10+32, tailleFenetreX, tailleFenetreY-tailleFenetreY/10-32);
		panel_2.setBackground(new Color(46,139,87));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		textadecode = new JTextPane();
		//textadecode.setColumns(10);
		textadecode.setBounds(tailleFenetreX/20, tailleFenetreY/20+20, tailleFenetreX - 2*tailleFenetreX/20, tailleFenetreY/4+tailleFenetreY/20);
		panel_2.add(textadecode);
		
		JLabel label_1 = new JLabel("Veuillez Saisir Votre Text");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(tailleFenetreX/20, tailleFenetreY/20, 181, 14);
		panel_2.add(label_1);
		
		JButton btn_valider_decry = new JButton("Valider");
		btn_valider_decry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cprive1.getText().toString().equals("") || cprive2.getText().toString().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez remplir tout les clées ");
				}
				else {
					String text = textadecode.getText().toString();
					int[] a = {Integer.parseInt(cprive1.getText().toString()),Integer.parseInt(cprive2.getText().toString())};
					   String n=Integer.toString(a[1]);
					   int[] c = {a[0],a[1]};
					   int taille;
					   
					   if(n.length()>3)
					    taille =n.length();
					   else	
					     taille =3; // si n a la taille inf a 3 on doit respecter dans le calcul la taille min du code ascii qui est 3 
					   String x = RSA.decryptage(text, c ,taille);
					   result_decrypto.setText(x);
				}
				
			}
		});
		btn_valider_decry.setBounds(tailleFenetreX - tailleFenetreX/8 - tailleFenetreX/20, tailleFenetreY/4+tailleFenetreY/7, tailleFenetreX/8, tailleFenetreY/20);
		panel_2.add(btn_valider_decry);
		
		result_decrypto = new JTextPane();
		//result_decrypto.setColumns(10);
		result_decrypto.setBounds(tailleFenetreX/20, 5*tailleFenetreY/9-tailleFenetreY/20, tailleFenetreX - 2*tailleFenetreX/20, tailleFenetreY/4+tailleFenetreY/20);
		panel_2.add(result_decrypto);
		
		JLabel label_2 = new JLabel("R\u00E9sultat");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(tailleFenetreX/20, 5*tailleFenetreY/9-tailleFenetreY/20-20, 181, 14);
		panel_2.add(label_2);
		
		cprive1 = new JTextField();
		cprive1.setColumns(10);
		cprive1.setBounds(182, 209, 86, 20);
		panel_2.add(cprive1);
		
		cprive2 = new JTextField();
		cprive2.setColumns(10);
		cprive2.setBounds(324, 209, 86, 20);
		panel_2.add(cprive2);
		
		lblCle = new JLabel("Cl\u00E9e 1");
		lblCle.setBounds(139, 212, 46, 14);
		panel_2.add(lblCle);
		
		lblCle_1 = new JLabel("Cl\u00E9e 2");
		lblCle_1.setBounds(281, 212, 46, 14);
		panel_2.add(lblCle_1);
		
	}
	
	public static int Puissance_modulaire(int a,int n,int m)
	{
      int  p = 1;
      while (n>0) 
      {
	    	while (n%2 == 0)
	       	{  
	    		a = (a*a)%m; if (a<0) a*=(-1); // pour certain nombres %m nous donne le resultat juste mais negatif
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
	{
		RSA_GRAPHE app = new RSA_GRAPHE();
		app.setVisible(true);
	}
}
