package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * A rather trivial Actor that just plays FizzBuzz
 */
public class FizzBuzzActor extends UntypedActor {

    int nextNum = 0;

    /**
     * The marshall. We'll also send them the messages
     */
    ActorRef marshallActor;

    /**
     *
     */
    public static Props props = Props.create(FizzBuzzActor.class);


    @Override
    public void onReceive(Object message) {
        if (message.equals("Marshall")) {
            /*
             * We're given the marshall's ActorRef by a message saying "Marshall". The Marshall will appear
             * to be the sender (even though it's actually been sent by Setup)
             */
            marshallActor = getSender();
        } else if (message instanceof ActorRef) {
            // If we're sent an ActorRef, that's the marshall telling us who our opponent is and to get started
            ((ActorRef) message).tell(1, getSelf());
            marshallActor.tell(1, getSelf());
        } else if (message instanceof Integer) {
            nextNum = (Integer) message + 1;
            reply();
        } else  {
            nextNum = nextNum + 2;

            // Stop at 1000 so we don't blow the heap by writing forever into a StringBuilder
            if (nextNum < 1000) {
                reply();
            }
        }
    }

    void reply() {
        if (nextNum % 15 == 0) {
            getSender().tell("fizzbuzz", getSelf());
            marshallActor.tell("fizzbuzz", getSelf());
        } else if (nextNum % 5 == 0) {
            getSender().tell("buzz", getSelf());
            marshallActor.tell("buzz", getSelf());
        } else if (nextNum % 3 == 0) {
            getSender().tell("fizz", getSelf());
            marshallActor.tell("fizz", getSelf());
        } else {
            getSender().tell(nextNum, getSelf());
            marshallActor.tell(nextNum, getSelf());
        }

        // Put this Actor to sleep for 250ms before it checks its next message
        try {
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            // do nothing
        }
    }

}
