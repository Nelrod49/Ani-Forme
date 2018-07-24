package fr.eni.clinique.ihm.gestionClient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;

public class EcranRechercheClients extends JFrame {

	private JPanel panelRechercheClients;
	private JTextField txtFieldRecherche;
	private JButton buttonRecherche;
	private JTable tableResultat;
	private ArrayList<Clients> lesClients;
	
	public EcranRechercheClients() {
		this.setTitle("Recherche des clients");
		this.setSize(new Dimension(800, 400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initIHM();
	}
	
	
	private void initIHM() {
		panelRechercheClients = new JPanel();
		panelRechercheClients.setLayout(new GridBagLayout());
		panelRechercheClients.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
		//Recherche
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelRechercheClients.add(getTxtFieldRecherche(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelRechercheClients.add(getButtonRecherche(), gbc);
		
		//Multiple résultat
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		panelRechercheClients.add(getTableResultat(), gbc);
		
		this.setContentPane(panelRechercheClients);
	}
	
	private JTextField getTxtFieldRecherche(){
		if(txtFieldRecherche == null){
			txtFieldRecherche = new JTextField(20);
		}
		return txtFieldRecherche;
	}
	
	private JButton getButtonRecherche(){
		if(buttonRecherche == null){
			buttonRecherche = new JButton("Recherche");
			buttonRecherche.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(txtFieldRecherche.getText().isEmpty()){
						JOptionPane d = new JOptionPane();
						d.showMessageDialog(panelRechercheClients,
							    "Veuillez entrez quelque chose",
							    "Refuser",
							    JOptionPane.WARNING_MESSAGE);
					}else{
						ClientsDAO clientsDAO = DAOFactory.getClientsDAO();
						try {
							lesClients = clientsDAO.allClientsNomPrenom(txtFieldRecherche.getText());
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(lesClients.size() == 1){
							EcranRechercheClients.this.dispose();
							EcranGestionClients ecranGestionClients = new EcranGestionClients(lesClients.get(0));
							ecranGestionClients.setVisible(true);
						}else if(lesClients.size() > 0){
							Object[][] resultat = new Object[lesClients.size()][4];
							int i = 0;
							while (i < lesClients.size()) {
								resultat[i][0] = lesClients.get(i).getNomClient();
								resultat[i][1] = lesClients.get(i).getPrenomClient();
								resultat[i][2] = lesClients.get(i).getCodePostal();
								resultat[i][3] = lesClients.get(i).getVille();
								i++;
							}
							String[] entetes = { "Nom", "Prénom", "Code Postal", "Ville" };
							tableResultat.setModel(new DefaultTableModel(resultat, entetes));
						}
						else{
							JOptionPane d = new JOptionPane();
							d.showMessageDialog(panelRechercheClients,
								    "Aucun client trouvé",
								    "Information",
								    JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
				}
			});
		}
		return buttonRecherche;
	}
	
	private JTable getTableResultat(){
		if(null == tableResultat){
			String[] entetes = { "Nom", "Prénom", "Code Postal", "Ville" };
			Object[][] resultat = new Object[1][4];
			resultat[0][0] = "Acune données";
			resultat[0][1] = "Acune données";
			resultat[0][2] = "Acune données";
			resultat[0][3] = "Acune données";
			tableResultat = new JTable(resultat, entetes){
				public boolean isCellEditable(int row, int column){
				     return false;
				}
			};
			TableColumnModel columnModel = tableResultat.getColumnModel();
			tableResultat.setPreferredSize(new Dimension(600, 200));
			columnModel.getColumn(0).setPreferredWidth(200);
			columnModel.getColumn(1).setPreferredWidth(150);
			columnModel.getColumn(2).setPreferredWidth(150);
			columnModel.getColumn(3).setPreferredWidth(150);
			tableResultat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableResultat.setFillsViewportHeight(true);
			JScrollPane js = new JScrollPane(tableResultat);
			js.setVisible(true);
			add(js);
			tableResultat.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {}
				
				@Override
				public void mouseEntered(MouseEvent e) {}
				
				@Override
				public void mouseClicked(MouseEvent mouseEvent) {
					if(mouseEvent.getClickCount() == 2 &&  -1 != tableResultat.getSelectedRow() ){
						if(!lesClients.isEmpty()){
							EcranRechercheClients.this.dispose();
							EcranGestionClients ecranGestionClients = new EcranGestionClients(
									lesClients.get(tableResultat.getSelectedRow())
									);
							ecranGestionClients.setVisible(true);
						}	
					}
				}
			});
		}
		return tableResultat;
	}
}
