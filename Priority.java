package es.upm.dit.adsw.ej5;

/**
 * Prioridad relativa de los paquetes de datos.
 * @author juanalvarez
 * @author jose a. manas
 * @version 18.3.2016
 */
public enum Priority {
	BAJA, MEDIA, ALTA;

	/**
	 * Elige aleatoriamente.
	 * 
	 * @return una prioridad.
	 */
	public static Priority random() {
		Priority[] values = values();
		int random = (int) (Math.random() * values.length);
		return values[random];
	}

	/**
	 * Método que asigna un entero a un enum
	 * 
	 * @param El
	 *            enum
	 * @return Su valor
	 */
	public int getNumbers(Priority p) {
		if (p.name().equals(ALTA.name()))
			return 3;
		if (p.name().equals(MEDIA.name()))
			return 2;
		else {
			return 1;
		}
	}

}