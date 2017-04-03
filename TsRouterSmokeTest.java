package es.upm.dit.adsw.ej5;

public class TsRouterSmokeTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		TsRouter router = new TsRouter(5);
		Sender[] senders=new Sender[5];
		Receiver[] receivers=new Receiver[5];
		Thread [] threads=new Thread[10];
		for(int i=0;i<senders.length;i++){
			senders[i]=new Sender(i,router);
			receivers[i]=new Receiver(i,router);
			threads[i]=new Thread(senders[i]);
			threads[i+5]=new Thread(receivers[i]);
		}	
		for(Thread s:threads)
			s.start();		
	}
}
