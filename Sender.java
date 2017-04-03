package es.upm.dit.adsw.ej5;

/**
 * Mete datos al router.
 * 
 * @author juanalvarez
 * @author jose.a.mañas
 * @version 4.4.2016
 *
 */
public class Sender implements Runnable {

	private int id;
	private TsRouter router;

	public Sender(int id, TsRouter router2) {
		this.id = id;
		this.router = router2;

	}

	
	@Override
	public void run() {

		while (true) {
			Packet pack = new Packet(Priority.random(), id);
			router.send(pack);
			Log.sending(id);
			Nap.random(0, 50);
		//	Nap.sleep(10);
		}

	}

}
