package es.upm.dit.adsw.ej5;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

/**
 * Registro de actividad.
 * Arranca una thread que genera una traza cada 3s.
 */
class Log {
    private static final int DELAY = 3000;

    private static Map<Integer, Integer> senders = new TreeMap<>();
    private static Map<Integer, Integer> receivers = new TreeMap<>();

    static {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                synchronized (senders) {
                    System.out.println("senders:   " + senders);
                }
                synchronized (receivers) {
                    System.out.println("receivers: " + receivers);
                }
            }
        };
        timer.schedule(task, DELAY, DELAY);
    }

    /**
     * Le llama un Sender cada vez que acaba de enviar un paquete.
     *
     * @param id identificacion del emisor.
     */
    static void sending(int id) {
        synchronized (senders) {
            Integer cnt = senders.get(id);
            if (cnt == null)
                cnt = 1;
            else
                cnt = cnt + 1;
            senders.put(id, cnt);
        }
    }

    /**
     * Le llama un Receiver cada vez que acaba de recibir un paquete.
     *
     * @param id identificacion del receptor.
     */
    static void receiving(int id) {
        synchronized (receivers) {
            Integer cnt = receivers.get(id);
            if (cnt == null)
                cnt = 1;
            else
                cnt = cnt + 1;
            receivers.put(id, cnt);
        }
    }
}