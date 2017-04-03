package es.upm.dit.adsw.ej5;
/**
 * Un paquete de datos. Implementa el comparador.
 *
 * @author jose a. manas
 * @author juanalvarez
 * @version 12.4.2016
 */
public class Packet implements Comparable<Packet> {
	private final Priority priority;
	private final int data;
	private static int count=0;

	/**
	 * Constructor.
	 * Asigna un valor único a cada paquete.
	 * @param priority
	 *            prioridad.
	 * @param data
	 *            otros datos. No se usa.
	 *            
	 */
	public Packet(Priority priority, int data) {
		this.priority = priority;
		this.data = data;
		Packet.count++;

	}

	/**
	 * Getter.
	 *
	 * @return prioridad.
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * Getter.
	 *
	 * @return otros datos.
	 */
	public int getData() {
		return data;
	}
	/**
	 * Indicador estático de la antiguedad del paquete. Se usa para ordenar.
	 * @return una referencia única para cada paquete.
	 */
	public static int getRef(){
		return count;
	}
	
	

	/**
	 * Definimos el comparador para que java pueda ordenar la cola de paquetes.
	 * Comparamos en función de la prioridad y de la antiguedad.
	 */
	@Override
	public  int compareTo(Packet o) {
		if (priority.getNumbers(this.priority) > priority.getNumbers(o.getPriority()))
			return -1;
		if (priority.getNumbers(this.priority) < priority.getNumbers(o.getPriority()))
			return 1;
		if (priority.getNumbers(this.priority) == priority.getNumbers(o.getPriority())) {
			if (getRef() < Packet.getRef()){	
				return 1;
			}
			if (getRef() > Packet.getRef())
				return -1;
			else {
				return 0;
			}
		}
		return 0;

	}

	

}