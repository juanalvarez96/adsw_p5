package es.upm.dit.adsw.ej5;

import java.util.Random;

/**
 * Rutinas auxiliares para deterner threads.
 *
 * @author jose a. manas
 * @version 16.3.2016
 */
public class Nap {
	private static final Random RANDOM = new Random();

	/**
	 * Duerme el tiempo indicado.
	 *
	 * @param ms
	 *            milisegundos.
	 */
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ignored) {
		}
	}

	/**
	 * Duerme un periodo aleatorio entre los limites indicados.
	 *
	 * @param min
	 *            milisegundos minimos.
	 * @param max
	 *            milisegundos maximos.
	 */
	public static void random(int min, int max) {
		try {
			Thread.sleep(min + RANDOM.nextInt(max - min));
		} catch (InterruptedException ignored) {
		}
	}
}