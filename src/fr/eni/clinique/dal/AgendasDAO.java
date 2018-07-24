package fr.eni.clinique.dal;

import java.sql.Date;
import java.util.ArrayList;

import fr.eni.clinique.bo.Agendas;

public interface AgendasDAO {
	public ArrayList<ArrayList> getAllRdvVet(int CodeVet) throws DALException;
	public void deleteAgendas(String CodeVet, String CodeAnimal, String DateRdv) throws DALException;
	public boolean insertAgendas(int CodeVet, String dateRdv, int CodeAnimal) throws DALException;
	public ArrayList<ArrayList> getAgendasVeterinaire(int CodeVet, String dateRdv) throws DALException;
}
