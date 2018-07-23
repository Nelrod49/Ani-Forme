package fr.eni.clinique.dal;

import java.util.ArrayList;

import fr.eni.clinique.bo.Agendas;

public interface AgendasDAO {
	public ArrayList<ArrayList> getAllRdvVet(int CodeVet);
}
