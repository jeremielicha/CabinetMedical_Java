package Views;

import Entity.RendezVous;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public class FrmPrincipal extends JFrame {

    private JPanel pnlRoot;
    private JComboBox cbxPatologie;
    private JPanel pnlDate;
    private JSpinner spHeures;
    private JLabel lblTitre;
    private JLabel lblHeure;
    private JLabel lblMinute;
    private JSpinner spMinutes;
    private JButton btnEnvoyer;
    private JLabel lblPatologie;
    private JTextField txtNomPatient;
    private JLabel lblNomPatologie;
    private JLabel lblDateRdv;
    private JTree trRdv;
    private JDateChooser cldDate;
    private TreeMap<String,TreeMap<String,RendezVous>>monPlanning;
    DefaultMutableTreeNode root;
    DefaultTreeModel model;
    public FrmPrincipal() {
        this.setTitle("Cabinet Medical");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(500,300);

        cldDate=new JDateChooser();
        cldDate.setDateFormatString("dd/MM/yyyy");
        pnlDate.add(cldDate);
        spHeures.setModel(new SpinnerNumberModel(7,7,18,1));
        spMinutes.setModel(new SpinnerNumberModel(00,00,45,15));

        root = new DefaultMutableTreeNode("Mon planning");
        model = new DefaultTreeModel(root);
        trRdv.setModel(model);


        btnEnvoyer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtNomPatient.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Saisir le nom du patient");
                } else if (cldDate.getDate()==null) {
                    JOptionPane.showMessageDialog(null,"Saisir une date");
                }else {
                    String nomPatient=txtNomPatient.getText();
                    String nomPatologie=cbxPatologie.getSelectedItem().toString();
                    String date=cldDate.getDate().toString();
                    String heure=spHeures.getValue().toString();
                    String minute=spMinutes.getValue().toString();

                    RendezVous rendezVous=new RendezVous(heure,nomPatient,nomPatologie);

                    TreeMap<String, RendezVous> tmHeure= new TreeMap<>();
                    monPlanning=new TreeMap<>();

                    tmHeure.put(heure,rendezVous);
                    monPlanning.put(date,tmHeure);

                    System.out.println("Planning : "+monPlanning);

                }
            }
        });
    }
}
