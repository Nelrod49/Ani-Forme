package fr.eni.clinique.ihm.gestionAnimal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;

public class EcranAnimal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("142px"),
				ColumnSpec.decode("65px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("69px"),},
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton btnValider = new JButton("Valider");
		panel.add(btnValider, "2, 1, left, top");
		
		JButton btnAnnuler = new JButton("Annuler");
		panel.add(btnAnnuler, "4, 1, left, top");
		
		JLabel lblNewLabel = new JLabel("Client : ");
		panel.add(lblNewLabel, "1, 3, right, default");
		
		textField = new JTextField();
		panel.add(textField, "2, 3, fill, default");
		textField.setColumns(10);
		
		JLabel lblCode = new JLabel("Code : ");
		panel.add(lblCode, "1, 5, right, default");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "2, 5, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		panel.add(lblNom, "1, 7, right, default");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "2, 7, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblSexe = new JLabel("Sexe");
		panel.add(lblSexe, "1, 9");
		
		JLabel lblCouleur = new JLabel("Couleur");
		panel.add(lblCouleur, "1, 11");
		
		JLabel lblEspece = new JLabel("Espece");
		panel.add(lblEspece, "1, 13");
		
		JLabel lblRace = new JLabel("Race");
		panel.add(lblRace, "1, 15");
		
		JLabel lblTatouage = new JLabel("Tatouage");
		panel.add(lblTatouage, "1, 17");
	}

}
