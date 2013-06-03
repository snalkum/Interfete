package proiectinterfete;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class ProiectInterfete extends JFrame{
    private Connection conexiune;
    private ComenziBD comanda;
    private JTree tree;
    private JButton trimite;
    private JLabel interogareExecutata;
    private JTextField interogareIntrodusa;
    private JLabel rezultatInterogare;
    private DefaultMutableTreeNode top;
    private String[] numeColoane;
    private void InitializareObiect(){
            interogareExecutata = new JLabel("Testingasfasfasfffffffffffffffffffffffffffffffffffffffffff");
            interogareExecutata.setBounds(20, 150, 20, 20);
            
            rezultatInterogare = new JLabel("aici");
            rezultatInterogare.setBounds(240, 150, 200, 250);
            
            
           
           top = new DefaultMutableTreeNode("Student");
           numeColoane= comanda.retrageNumeTabel("Select * from student");
           
            tree = new JTree(top);
            for(int i=1; i<numeColoane.length; i++){
                top.add(new DefaultMutableTreeNode(numeColoane[i]) {});
                
            }
            tree.addTreeSelectionListener(new TreeSelectionListener(){

            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                String node = tse.getNewLeadSelectionPath().getLastPathComponent().toString();
                rezultatInterogare.setText(comanda.retragereDateTabel("Select " +node+ " from student;"));
                interogareExecutata.setText("Select " +node+ " from student;");
            }
                
            });
            
            tree.setBounds(20, 20, 200, 400);
       
            trimite = new JButton("Trimite");
            trimite.setBounds(240, 110, 200, 20);
     
            interogareIntrodusa = new JTextField(""); 
            interogareIntrodusa.setBounds(240, 80, 540, 20);
            
            this.add(rezultatInterogare);
            this.add(tree);
            this.add(trimite);
            this.add(interogareIntrodusa);
            this.add(interogareExecutata);
             trimite.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent ae) {
                    comanda.retrageNumeTabel(interogareIntrodusa.getText());
                    interogareExecutata.setText(interogareIntrodusa.getText());
                    rezultatInterogare.setText(comanda.retragereDateTabel(interogareIntrodusa.getText()));
                    
                }
            });
            this.setPreferredSize(new Dimension(800, 500));
            this.setSize(500, 500);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();  
    }
    ProiectInterfete(){
        try{
            conexiune = Conexiune.getConnection();
            comanda = new ComenziBD(this.conexiune);
            InitializareObiect();

        } catch(Exception e){
            System.out.println("Nu s-a putut realiza conexiunea");
        }
    }
   
    public static void main(String[] args) {
        ProiectInterfete interfata = new ProiectInterfete();
//        System.out.println(interfata.conexiune);
//         System.out.println(interfata.conexiune);
      
    }
}
