package es.upm.dit.adsw.ej5;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

/**
 * Algunas pruebas para saber si el algoritmo de ordenación y tratamiento de
 * cola es correcto. Las pruebas son poco exigentes. Se avisa de que la mayor
 * parte de las pruebas se han hecho introduciendo trazas por las clases y
 * viendo que los paquetes son tratados correctamente.
 * 
 * @author jpuente
 * @author juanalvarez
 * @version 12.4.2016
 *
 */
public class TsRouterTest {
	private final Packet packetA1 = new Packet(Priority.ALTA, 1);
	private final Packet packetA2 = new Packet(Priority.ALTA, 2);
	private final Packet packetA3 = new Packet(Priority.ALTA, 3);
	private final Packet packetA4 = new Packet(Priority.ALTA, 4);
	private final Packet packetA5 = new Packet(Priority.ALTA, 5);
	private final Packet packetM1 = new Packet(Priority.MEDIA, 1);
	private final Packet packetM2 = new Packet(Priority.MEDIA, 2);
	private final Packet packetM3 = new Packet(Priority.MEDIA, 3);
	private final Packet packetB1 = new Packet(Priority.BAJA, 1);
	private final Packet packetB2 = new Packet(Priority.BAJA, 2);
	private final Packet packetB3 = new Packet(Priority.BAJA, 3);
	/**
	 * Comprobamos que lo que enviamos desordenado, se ordena en función de la
	 * prioridad y de la antiguedad.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void ejemplo() throws InterruptedException {
		Packet[] seq_send = new Packet[] { packetM1, packetA2, packetA1, packetM3, packetM2, packetA3 };
		Packet[] seq_rec = new Packet[] { packetA2, packetA1, packetA3, packetM3, packetM2, };
		TsRouter router = new TsRouter(5);
		for (Packet packet : seq_send)
			router.send(packet);
		for (Packet packet : seq_rec)
			assertSame(packet, router.get());
		assertNull(router.get());
	}

	/**
	 * Comprobamos que si esta llena, se borra el paquete adecuado.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void estaLlena() throws InterruptedException {
		Packet[] seq_send = new Packet[] { packetM1, packetB1, packetA1, packetM2, packetA2, packetB3, packetM3,
				packetA3, packetB2 };
		Packet[] seq_rec = new Packet[] { packetA1, packetA2, packetA3, packetM1, packetM2, packetM3, packetB3,
				packetB2 };
		TsRouter router = new TsRouter(8);
		for (Packet packet : seq_send)
			router.send(packet);
		for (Packet packet : seq_rec)
			assertSame(packet, router.get());
		assertNull(router.get());
	}

	/**
	 * Comprobamos que si sacamos todos los paquetes que metemos, la longitud de
	 * la cola es cero.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testNuevo() throws InterruptedException {
		TsRouter router = new TsRouter(4);
		Random ran = new Random();
		int n = 100;
		for (int i = 0; i < n; i++) {
			Packet pack = new Packet(Priority.random(), ran.nextInt(10));
			router.send(pack);
		}
		for (int i = 0; i < n; i++) {
			router.get();
		}
		assertEquals(0, router.getSize());

	}

	/**
	 * Comprobamos que si la cola es de dos paquetes y metemos tres iguales y
	 * sacamos solo 1, sólo quede uno y no dos.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testRepetidos() throws InterruptedException {
		TsRouter router = new TsRouter(2);
		int n = 3;
		for (int i = 0; i < n; i++) {
			Packet pack = new Packet(Priority.BAJA, 3);
			router.send(pack);// Mandamos 3 paquetes iguales
		}
		router.get();
		
		assertEquals(1, router.getSize());
	}
	/**
	 * Comprobamos que se ordena bien y saca null cuando hace falta.
	 * @throws InterruptedException
	 */
	@Test
	public void test4() {
		TsRouter router =new TsRouter(5);
		Packet[] seq_send = new Packet[] { packetA1, packetA2, packetA3, packetA4, packetA5, packetB1, packetM1};
		Packet[] seq_rec = new Packet[] { packetA1, packetA2, packetA3, packetA4, packetA5, };
		for (Packet packet : seq_send)
			router.send(packet);
		assertEquals(5,router.getSize());
		for (Packet packet : seq_rec)
			assertSame(packet, router.get());
		assertNull(router.get());
	}
	
	/**
	 * Comprobamos que cuando el router esta vacio, se devuelve nulo
	 */
	@Test
	public void test6(){
		TsRouter router=new TsRouter(10);
		assertNull(router.get());
	}
	

		
	
		
	}


