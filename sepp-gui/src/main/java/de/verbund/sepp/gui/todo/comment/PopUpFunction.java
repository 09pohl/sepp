package de.verbund.sepp.gui.todo.comment;

import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class PopUpFunction {

	private DatenSchnittstelleImpl dataSchnittstelle;
	private StringBuffer contentString;

	
	
	
	
	void add(int table, int index, String comment) {
		
		if (table == 0) {
			dataSchnittstelle = new DatenSchnittstelleImpl();
			DateiInformationen dataComments;
			try {
				dataComments = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektPfad() + "\\"
								+ DatenSchnittstelle.PRIMAER_DATEINAME);
				String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
				String[][] newContent = new String[userAndComments.length + 1][userAndComments[0].length];
				contentString = new StringBuffer();
				for (int i = 0; i < newContent.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != newContent.length-1) {
							newContent[i][j] = userAndComments[i][j];
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");
							}
							else {
								contentString.append(userAndComments[i][j] + "\n");
							}
							
						}
					}
				}
				contentString.append("user:" + comment + "\n");
				
				dataComments.setKommentare(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataComments);
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			dataSchnittstelle = new DatenSchnittstelleImpl();
			DateiInformationen dataToDos;

			try {
				dataToDos = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektPfad() + "\\"
								+ DatenSchnittstelle.PRIMAER_DATEINAME);
				String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
				String[][] newContent = new String[userAndToDos.length + 1][userAndToDos[0].length];
				contentString = new StringBuffer();
				for (int i = 0; i < newContent.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != newContent.length-1) {
							newContent[i][j] = userAndToDos[i][j];
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");
							}
							else {
								contentString.append(userAndToDos[i][j] + "\n");
							}
							
						}
					}
				}
				contentString.append("user:" + comment + "\n");
				
				dataToDos.setToDos(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataToDos);
				

			} catch (Exception e) {
				System.out.println("nicht funktioniert123");
			}

		}

	}

	void edit(int table, int index, String comment) {
		if (table == 0) {

			dataSchnittstelle = new DatenSchnittstelleImpl();
			DateiInformationen dataComments;

			try {
				dataComments = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektPfad() + "\\"
								+ DatenSchnittstelle.PRIMAER_DATEINAME);
				String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
				String[][] newContent = new String[userAndComments.length][userAndComments[0].length];
				contentString = new StringBuffer();
				for (int i = 0; i < newContent.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							newContent[i][j] = userAndComments[i][j];
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");
							}
							else {
								contentString.append(userAndComments[i][j] + "\n");
							}
							
						}
						else {
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");  
								//User muss noch geholt werden!
							}
							else {
								contentString.append(comment + "\n");
								newContent[i][j] = comment;
							}
						}
					}
				}
				dataComments.setKommentare(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataComments);
				

			} catch (Exception e) {
				System.out.println("nicht funktioniert");
			}
			
		} else {
			dataSchnittstelle = new DatenSchnittstelleImpl();
			DateiInformationen dataToDos;

			try {
				dataToDos = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektPfad() + "\\"
								+ DatenSchnittstelle.PRIMAER_DATEINAME);
				String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
				String[][] newContent = new String[userAndToDos.length][userAndToDos[0].length];
				contentString = new StringBuffer();
				for (int i = 0; i < newContent.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							newContent[i][j] = userAndToDos[i][j];
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");
							}
							else {
								contentString.append(userAndToDos[i][j] + "\n");
							}
							
						}
						else {
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");  
								//User muss noch geholt werden!
							}
							else {
								contentString.append(comment + "\n");
								newContent[i][j] = comment;
							}
						}
					}
				}
				dataToDos.setToDos(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataToDos);
				

			} catch (Exception e) {
				System.out.println("nicht funktioniert");
			}

		}

	}

	void delete(int table, int index) {
		if (table == 0) {

			dataSchnittstelle = new DatenSchnittstelleImpl();
			DateiInformationen dataComments;

			try {
				dataComments = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektPfad() + "\\"
								+ DatenSchnittstelle.PRIMAER_DATEINAME);
				String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
				String[][] newContent = new String[userAndComments.length - 1][userAndComments[0].length];
				contentString = new StringBuffer();
				for (int i = 0; i < newContent.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							newContent[i][j] = userAndComments[i][j];
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");
							}
							else {
								contentString.append(userAndComments[i][j] + "\n");
							}
							
						}
					}
				}
				dataComments.setKommentare(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataComments);
				

			} catch (Exception e) {
				System.out.println("nicht funktioniert");
			}
			
		} else {
			dataSchnittstelle = new DatenSchnittstelleImpl();
			DateiInformationen dataToDos;

			try {
				dataToDos = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektPfad() + "\\"
								+ DatenSchnittstelle.PRIMAER_DATEINAME);
				String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
				String[][] newContent = new String[userAndToDos.length - 1][userAndToDos[0].length];
				contentString = new StringBuffer();
				for (int i = 0; i < newContent.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							newContent[i][j] = userAndToDos[i][j];
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");
							}
							else {
								contentString.append(userAndToDos[i][j] + "\n");
							}
							
						}
					}
				}
				dataToDos.setToDos(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataToDos);
				

			} catch (Exception e) {
				System.out.println("nicht funktioniert");
			}

		}

	}
}
