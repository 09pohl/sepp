package de.verbund.sepp.gui.todo.comment;

import java.io.IOException;

import de.verbund.sepp.gui.SEPPMainDlg;
import de.verbund.sepp.gui.controller.ActiveFileController;
import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class PopUpFunction {

	private DatenSchnittstelleImpl dataSchnittstelle;
	private StringBuffer contentString;
	private static final int ADD = 0;
	private static final int EDIT = 1;
	private static final int DELETE = 2;

	void add(int table, int index, String comment, SEPPMainDlg seppMainDlg) throws IOException {
		dataSchnittstelle = getDatenSchnittstelle();
		String user = dataSchnittstelle.getEinstellungen().getUsername();

		if (table == 0) {
			commentSetter(user, comment, index, ADD);
		} else {
			toDoSetter(user, comment, index, ADD);
		}

		try {
			seppMainDlg.refreshMainTables();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void edit(int table, int index, String comment, SEPPMainDlg seppMainDlg) throws IOException {
		dataSchnittstelle = getDatenSchnittstelle();
		String user = dataSchnittstelle.getEinstellungen().getUsername();

		if (table == 0) {
			commentSetter(user, comment, index, EDIT);
		} else {
			toDoSetter(user, comment, index, EDIT);
		}

		try {
			seppMainDlg.refreshMainTables();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void delete(int table, int index, SEPPMainDlg seppMainDlg) {
		dataSchnittstelle = getDatenSchnittstelle();

		if (table == 0) {
			commentSetter(index, DELETE);
		} else {
			toDoSetter(index, DELETE);
		}
		try {
			seppMainDlg.refreshMainTables();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void commentSetter(int index, int call) {
		commentSetter(null, null, index, call);
	}

	private void commentSetter(String user, String comment, int index, int call) {

		DateiInformationen dataInformation = null;

		try {
			dataInformation = dataSchnittstelle
					.getDateiInformationen(ActiveFileController.getInstance().getAktiveDateiPfad());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataInformation.getKommentare());
		contentString = new StringBuffer();

		if (call == ADD) {
			try {
				if (!userAndComments[0][0].isEmpty()) {
					for (int i = 0; i <= userAndComments.length - 1; i++) {
						for (int j = 0; j <= 1; j++) {
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");
							} else {
								contentString.append(userAndComments[i][j] + "\n");
							}
						}
					}
					contentString.append(formatString(user, comment));
				} else {
					contentString.append(formatString(user, comment));
				}
				dataInformation.setKommentare(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataInformation);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (call == EDIT) {
			try {
				contentString = new StringBuffer();
				for (int i = 0; i < userAndComments.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							if (j == 0) {
								contentString.append(userAndComments[i][j] + ":");
							} else {
								contentString.append(userAndComments[i][j] + "\n");
							}

						} else {
							if (j == 0) {
								contentString.append(user + ":");
							} else {
								contentString.append(comment + "\n");
							}
						}
					}
				}
				dataInformation.setKommentare(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataInformation);

			} catch (Exception e) {
				//TODO Catch - Block
			}
		} else {
			try {
				dataInformation = dataSchnittstelle
						.getDateiInformationen(ActiveFileController.getInstance().getAktiveDateiPfad());
				contentString = new StringBuffer();
				for (int i = 0; i <= userAndComments.length - 1; i++) {
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
				dataInformation.setKommentare(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataInformation);

			} catch (Exception e) {
				//TODO Catch Block
			}
		}

	}

	private void toDoSetter(int index, int call) {
		toDoSetter(null, null, index, call);

	}

	private void toDoSetter(String user, String comment, int index, int call) {

		DateiInformationen dataInformation = null;

		try {
			dataInformation = dataSchnittstelle
					.getDateiInformationen(ActiveFileController.getInstance().getAktiveDateiPfad());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataInformation.getToDos());
		contentString = new StringBuffer();

		if (call == ADD) {
			try {
				if (!userAndToDos[0][0].isEmpty()) {
					for (int i = 0; i <= userAndToDos.length - 1; i++) {
						for (int j = 0; j <= 1; j++) {
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");
							} else {
								contentString.append(userAndToDos[i][j] + "\n");
							}
						}
					}
					contentString.append(formatString(user, comment));
				} else {
					contentString.append(formatString(user, comment));
				}
				dataInformation.setToDos(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataInformation);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (call == EDIT) {
			try {
				contentString = new StringBuffer();
				for (int i = 0; i < userAndToDos.length; i++) {
					for (int j = 0; j <= 1; j++) {
						if (i != index) {
							if (j == 0) {
								contentString.append(userAndToDos[i][j] + ":");
							} else {
								contentString.append(userAndToDos[i][j] + "\n");
							}

						} else {
							if (j == 0) {
								contentString.append(user + ":");
								// User muss noch geholt werden!
							} else {
								contentString.append(comment + "\n");
							}
						}
					}
				}
				dataInformation.setToDos(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataInformation);

			} catch (Exception e) {
				//TODO Catch Block
			}
		} else {
			try {
				for (int i = 0; i <= userAndToDos.length - 1; i++) {
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
				dataInformation.setToDos(contentString.toString());
				dataSchnittstelle.speichereDateiInformationen(dataInformation);

			} catch (Exception e) {
				//TODO Catch Block
			}
		}

	}

	private String formatString(String user, String comment) {
		String formatString = user + ":" + comment + "\n";
		return formatString;
	}

	private DatenSchnittstelleImpl getDatenSchnittstelle() {
		return DatenSchnittstelleImpl.getInstance();

	}

}
