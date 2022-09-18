package Views;

import Entity.RendezVous;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private TreeMap<String,TreeMap<String,RendezVous>>tmMonPlanning;
    DefaultMutableTreeNode root;
    DefaultTreeModel model;
    RendezVous rendezVous;
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
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        spHeures.setModel(new SpinnerNumberModel(7,7,18,1));
        spMinutes.setModel(new SpinnerNumberModel(0, 0,45,15));

        root = new DefaultMutableTreeNode("Mon planning");
        model = new DefaultTreeModel(root);
        trRdv.setModel(model);


        btnEnvoyer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tmMonPlanning=new TreeMap<>();
                TreeMap<String, RendezVous> tmHeure= new TreeMap<>();

                String nomPatient=txtNomPatient.getText();
                String nomPatologie=cbxPatologie.getSelectedItem().toString();
                String date= sdf.format(cldDate.getDate());
                String heure=spHeures.getValue().toString()+" : "+spMinutes.getValue().toString();

                if (txtNomPatient.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Saisir le nom du patient");
                } else if (cldDate.getDate()==null) {
                    JOptionPane.showMessageDialog(null,"Saisir une date");
                }else {
                    if (!tmMonPlanning.containsKey(date)){
                        rendezVous=new RendezVous(heure,nomPatient,nomPatologie);
                        tmHeure.put(heure,rendezVous);
                        tmMonPlanning.put(date,tmHeure);
                    }else {
                        if (!tmMonPlanning.get(date).containsKey(heure)){
                            RendezVous rendezVous=new RendezVous(heure,nomPatient,nomPatologie);
                            tmMonPlanning.get(date).put(heure,rendezVous);
                        }else {
                            JOptionPane.showMessageDialog(null,"Rdv deja pris");
                        }
                    }
                }
                for(String keyDate: tmMonPlanning.keySet()){
                    System.out.println(keyDate);
                    for (String keyHeure: tmMonPlanning.get(keyDate).keySet()){
                        System.out.println(keyHeure);
                        System.out.println(tmMonPlanning.get(keyDate).get(keyHeure).getNomPatient());
                    }
                }
            }
        });
    }
}