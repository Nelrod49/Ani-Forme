package fr.eni.clinique.dal.jdbc;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
	private static Properties prop = new Properties();
	private static InputStream input = null;

	static {
		try {
			prop.load(Settings.class.getResourceAsStream("config.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getProperty(String key) {
		String parametre = prop.getProperty(key);
		return parametre;
	}
}