package fr.eni.clinique.ihm.gestionAnimal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Races;
import fr.eni.clinique.dal.RacesDAO;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class EcranAnimal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCode;
	private JTextField textFieldNom;
	private JTextField textFieldTatouage;
	private JTextField textFieldCouleur;
	private JTextField textField;
	/*atribut pour la combobox de race pour permettre par la suite
	que sa n'affiche que les espèces lié a une race
	*/
	private JComboBox<String> comboBoxRace;
	private ArrayList<Races>lesRaces = new ArrayList<Races>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranAnimal frame = new EcranAnimal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EcranAnimal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("127px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(87dlu;default):grow"),
				ColumnSpec.decode("109px:grow"),
				ColumnSpec.decode("69px"),
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("23px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton btnValider = new JButton("Valider");
		panel.add(btnValider, "3, 1, center, top");
		
		JButton btnAnnuler = new JButton("Annuler");
		panel.add(btnAnnuler, "4, 1, center, top");
		
		JLabel lblClient = new JLabel("Client");
		panel.add(lblClient, "1, 3, center, default");
		
		textField = new JTextField();
		panel.add(textField, "3, 3, 2, 1, fill, default");
		textField.setColumns(10);
		
		JLabel lblCode = new JLabel("Code");
		panel.add(lblCode, "1, 7, center, default");
		
		textFieldCode = new JTextField();
		panel.add(textFieldCode, "3, 7, fill, default");
		textFieldCode.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		panel.add(lblNom, "1, 9, center, default");
		
		textFieldNom = new JTextField();
		panel.add(textFieldNom, "3, 9, fill, default");
		textFieldNom.setColumns(10);
		
		JLabel lblSexe = new JLabel("Sexe");
		panel.add(lblSexe, "4, 9, center, default");
		
		//Creation Combobox avec insertion de données
		String[] sexeStrings = { "Male, Femelle, Hermaphrodite" };
		JComboBox comboBoxSexe = new JComboBox(sexeStrings);
		panel.add(comboBoxSexe, "5, 9, fill, default");
		comboBoxSexe.setSelectedIndex(2);
		comboBoxSexe.addActionListener(comboBoxSexe);
		
		JLabel lblCouleur = new JLabel("Couleur");
		panel.add(lblCouleur, "1, 11, center, default");
		
		textFieldCouleur = new JTextField();
		panel.add(textFieldCouleur, "3, 11, fill, default");
		textFieldCouleur.setColumns(10);
		
		JLabel lblEspece = new JLabel("Espece");
		panel.add(lblEspece, "1, 13, center, default");
		
		JComboBox comboBoxEspece = new JComboBox();
		panel.add(comboBoxEspece, "3, 13, fill, default");
		
		JLabel lblRace = new JLabel("Race");
		panel.add(lblRace, "4, 13, center, default");
		
		JComboBox comboBoxRace = new JComboBox();
		panel.add(comboBoxRace, "5, 13, fill, default");
		
		JLabel lblTatouage = new JLabel("Tatouage");
		panel.add(lblTatouage, "1, 15, center, default");
		
		textFieldTatouage = new JTextField();
		panel.add(textFieldTatouage, "3, 15, 2, 1, fill, default");
		textFieldTatouage.setColumns(10);
	}
	
	
	//méthode de la comboboxRace pour aller chercher toutes les races
	private JComboBox<String> getComboBxRaces(){
	
		if(comboBoxRace == null){
			comboBoxRace = new JComboBox<String>();
			RacesDAO racesDAO = DAOFactory.getRaceDAO();
			
			try {
				lesRaces = racesDAO.allRaces() ;
			} catch (DALException e) {
				e.printStackTrace();
			}
			if(!lesRaces.isEmpty()){
				int i = 0;
				comboBoxRace.addItem("Sélectionnez une race");
				while (i < lesRaces.size()){
					comboBoxRace.addItem(
							lesRaces.get(i).getRace());
		            i=i+1;		 
		        }
			}else{
				comboBoxRace.addItem("Aucune race sauf celle qu'on se met");
			}
		}
		return comboBoxRace;
	}
	

}
