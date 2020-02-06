package de.verbund.sepp.gui.todo.comment;

import java.io.IOException;

import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class PopUpFunction {

	private DatenSchnittstelleImpl dataSchnittstelle;
	private StringBuffer contentString;
	private String[][] newContent;

	void add(int table, int index, String comment) { // funktioniert, aber User muss noch geholt werden 

		if (table == 0) {
			dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
			DateiInformationen dataComments;
			contentString = new StringBuffer();

			try {
				dataComments = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektDateiPfad());
				
				String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
				if (!userAndComments[0][0].isEmpty()) {
					for (int i = 0; i <= userAndComments.length -1; i++) {
						for (int j = 0; j <= 1; j++) {
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");
							} else {
								contentString.append(userAndComments[i][j] + "\n");
							}
						}
					}
					contentString.append("user:" + comment + "\n");
				
				} else {
					contentString.append("user:" + comment + "\n");
				}
				dataComments.setKommentare(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataComments);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
			DateiInformationen dataToDos;
			contentString = new StringBuffer();

			try {
				dataToDos = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektDateiPfad());
				
				String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
				if (!userAndToDos[0][0].isEmpty()) {
					for (int i = 0; i <= userAndToDos.length -1; i++) {
						for (int j = 0; j <= 1; j++) {
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");
							} else {
								contentString.append(userAndToDos[i][j] + "\n");
							}
						}
					}
					contentString.append("user:" + comment + "\n");
				
				} else {
					contentString.append("user:" + comment + "\n");
				}
				dataToDos.setToDos(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataToDos);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	void edit(int table, int index, String comment) throws IOException { // funktioniert, aber User muss geholt werden
		dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
		String user = dataSchnittstelle.getEinstellungen().getUsername();
		if (table == 0) {

			DateiInformationen dataComments;

			try {
				dataComments = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektDateiPfad());
				String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
				String[][] newContent = new String[userAndComments.length][userAndComments[0].length];
				contentString = new StringBuffer();
				for (int i = 0; i < newContent.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							newContent[i][j] = userAndComments[i][j];
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");
							} else {
								contentString.append(userAndComments[i][j] + "\n");
							}

						} else {
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");
								// User muss noch geholt werden!
							} else {
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
			dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
			DateiInformationen dataToDos;

			try {
				dataToDos = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektDateiPfad());
				String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
				String[][] newContent = new String[userAndToDos.length][userAndToDos[0].length];
				contentString = new StringBuffer();
				for (int i = 0; i < newContent.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							newContent[i][j] = userAndToDos[i][j];
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");
							} else {
								contentString.append(userAndToDos[i][j] + "\n");
							}

						} else {
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");
								// User muss noch geholt werden!
							} else {
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

	void delete(int table, int index) { // funktioniert
		if (table == 0) {

			dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
			DateiInformationen dataComments;

			try {
				dataComments = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektDateiPfad());
				String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
				contentString = new StringBuffer();
				for (int i = 0; i <= userAndComments.length-1; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");
							} else {
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
			dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
			DateiInformationen dataToDos;

			try {
				dataToDos = dataSchnittstelle
						.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektDateiPfad());
				String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
				contentString = new StringBuffer();
				for (int i = 0; i <= userAndToDos.length-1; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");
							} else {
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
