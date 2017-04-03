package es.upm.dit.adsw.ej5;
/**
 * Recibe los datos del router
 * @author juanalvarez
 *@author jpuente
 *@version 13.4.2016
 */
public class Receiver implements Runnable {

	private TsRouter router;
	private int id;

	public Receiver(int id, TsRouter router2) {
		this.id = id;
		this.router = router2;
	}

	
	@Override
	public void run() {
		Packet pack;
		while (true) {
			pack = router.get();
			if (pack != null)
				Log.receiving(id);
			Nap.random(0, 50);
			// Nap.sleep(10);
		}
	}
}
