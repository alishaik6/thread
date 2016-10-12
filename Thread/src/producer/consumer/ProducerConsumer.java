package producer.consumer;

public class ProducerConsumer {


	private static Object lock = new Object();
	private static int[] buffer;
	private static int count;

	static class Producer 
	{
		void produce() throws InterruptedException
		{
			synchronized (lock)
			{
				while(isFull(buffer)) {
					
					lock.wait();
				}
				
				buffer[count++]= 1;
				lock.notify();
			}
			
			
		}
	}


	static class Consumer
	{
		void consume () throws InterruptedException
		{

			synchronized (lock)
			{
				while(isEmpty(buffer)) {
				lock.wait();
				}

				buffer[--count]=0;
				 lock.notify();
			}
		}


	}


	static  boolean isEmpty(int[] buffer) {
		// TODO Auto-generated method stub
		return count ==0;
	}
	static  boolean isFull(int[] buffer) {
		// TODO Auto-generated method stub
		return count == buffer.length;
	}



	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		buffer = new int[10];
		count =0;
		
		 Producer producer = new Producer();
		Consumer consumer = new Consumer();
		
		Runnable producerTask = () -> {
			
			
				for(int i=0; i<=50; i++)
				{
					try {
						producer.produce();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Producer task completed");
			};
			
			
			
		Runnable consumerTask = ()-> 
		{
			for(int i=0; i<=50; i++)
			{
				try {
					consumer.consume();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consumer Task completed");
		};
		
		
		Thread prodcerThread = new Thread (producerTask);
		Thread consumerThread = new Thread(consumerTask);
		
		prodcerThread.start();
		consumerThread.start();
		
		prodcerThread.join();
		consumerThread.join();
		System.out.println("count value is "+ count);
		
	}

}
