package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Holds all the "famous" Actors in our system. This hopefully makes things convenient, because if you @Inject Setup,
 * you'll then be able to look up ActorRefs to all the actors just by Setup.marshallActor, etc.
 * <p>
 * It's a Singleton, so Google Guice will only create one of these for us.
 */
@Singleton
public class Setup {

    /**
     * This is a reference to an Actor
     */
    public final ActorRef marshallActor;

    @Inject
    public Setup(ActorSystem system) {
        marshallActor = system.actorOf(MarshallActor.props);

        //  ActorRef dataRetrieval = system.actorOf(DataRetrievalActor.props,"Data Retrieval");


       /* dataRetrieval.tell("Marshall",marshallActor);
        dataRetrieval.tell(dataRetrieval,dataRetrieval);*/


        ActorRef fb1 = system.actorOf(FizzBuzzActor.props, "Player1");
        ActorRef fb2 = system.actorOf(FizzBuzzActor.props, "Player2");
        fb1.tell("Marshall", marshallActor);
        fb2.tell("Marshall", marshallActor);

        fb1.tell(fb2, fb2);

    }

}