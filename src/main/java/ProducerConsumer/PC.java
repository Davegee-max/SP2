package ProducerConsumer;


public class PC {
    /*EKGSensor ekgSensor;
    // Create a list shared by producer and consumer
    // Size of list is 2.
    LinkedList<EKGDTO> list = new LinkedList<EKGDTO>();
    int capacity = 1000;

    // Function called by producer thread
    public void produce() throws InterruptedException {

        while (true) {
            synchronized (this) {
                // producer thread waits while list
                // is full
                while (list.size() == capacity)
                    wait();
                List<EKGDTO> value = ekgSensor.hentData();
                System.out.println("Producer produced-"
                        + value);

                // to insert the jobs in the list
                list.add(value.get(0));
                list.add(value.get(1));

                // notifies the consumer thread that
                // now it can start consuming
                notify();

                // makes the working of program easier
                // to understand
                Thread.sleep(1000);
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // consumer thread waits while list
                // is empty
                while (list.size() < 50)
                    wait();

                // to retrive the ifrst job in the list
                LinkedList<Integer> removedObjket = new LinkedList<>();
                list.removeAll(removedObjket);

                System.out.println("Consumer consumed-");

                // Wake up producer thread
                notify();

                // and sleep
                Thread.sleep(1000);
            }
        }
    }*/
}


