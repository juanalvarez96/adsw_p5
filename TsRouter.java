package es.upm.dit.adsw.ej5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Router que envía paquetes y los lee.
 * 
 * @author juanalvarez
 * @author jose.a.mañas
 * @version 4.4.2016
 */
public class TsRouter {
	private int max;
	private ArrayList<Packet> queue;
	

	public TsRouter(int max) {
		this.max = max;
		this.queue = new ArrayList<Packet>();

	}

	/**
	 * De los pendientes de entrega, selecciona el de mas prioridad y, dentro de
	 * la misma prioridad, el mas antiguo. Lo elimina de la cola y lo devuelve.
	 * 
	 * @return El paquete.
	 * @throws InterruptedException
	 */
	public synchronized Packet get() {
		Packet pack;

		if (queue.size() == 0) {
			// System.out.println("sale de get
			// "+Thread.currentThread().getName());
			return null;// Mientras esté lleno, devolvemos null
		}
		pack = queue.get(0);
		// System.out.println("Vamos a sacar " + pack);
		// System.out.println("sale de get "+Thread.currentThread().getName());

		queue.remove(0);
		// System.out.println("La cola que nos queda es " + cola);
		notifyAll(); // Avisamos de que hay sitio
		return pack;

	}

	/**
	 * Indica si la cola permite que un paquete sea añadido.
	 * 
	 * @param E
	 *            El paquete que queremos añadir
	 * @return booleano. True si NO se puede añadir.
	 */
	private boolean getCanAdd(Packet E) {
		Priority last = queue.get(queue.size() - 1).getPriority();
		if (E.getPriority().compareTo(last) < 0) {
			return true;
		} else
			return false;
	}

	/**
	 * Agrega un paquete a la cola del router. Si la cola esta llena, se hace
	 * sitio eliminando el paquete mas antiguo de menor prioridad.
	 * 
	 * @param packet
	 */
	public synchronized void send(Packet packet2) throws IllegalArgumentException {
		if (packet2 == null)
			return;
		if (queue.size() == 0) {
			// System.out.println("Metemos la hebra "+
			// Thread.currentThread().getName());
			// System.out.println("metemos "+ packet2);
			queue.add(packet2);
			notifyAll();// Ya hay un paquete
			return;
		}
		if (queue.size() < max) {
			// System.out.println("Metemos la hebra "+
			// Thread.currentThread().getName());
			// System.out.println("metemos "+packet2);
			queue.add(packet2);
			Collections.sort(queue);
			notifyAll();
			return;
		}

		if (queue.size() == max) {
			if (getCanAdd(packet2)) {
				// System.out.println("Error:Prioridad demasiado baja. Cola
				// llena");
				return;
			}
			// System.out.println(cola);
			// System.out.println("La cola está llena, tenemos que meter " +
			// packet2);
			int i = searchWho(packet2);
			queue.remove(i);
			// System.out.println("Metemos la hebra "
			// +Thread.currentThread().getName());
			// System.out.println("metemos "+packet2);
			queue.add(packet2);
			Collections.sort(queue);
			// System.out.println("Ahora tenemos " + cola);
			return;
		}
	}

	/**
	 * Buscamos el hueco donde poder añadir el paquete del parametro,
	 * 
	 * @return la posición de la lista.
	 */
	private int searchWho(Packet packet2) {
		for (int i = queue.size() - 1; i > 0; i--) {
			if (queue.get(i - 1).getPriority().compareTo(queue.get(i).getPriority()) != 0)
				return i;
		}
		return 0;

	}

	/**
	 * Devuelve el número de elementos no nulos en una lista. Se usa en Junit.
	 * 
	 * @return
	 */
	public int getSize() {
		return queue.size();
	}

}