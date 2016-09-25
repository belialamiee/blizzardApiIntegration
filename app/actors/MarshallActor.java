package actors;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

import javax.inject.Inject;

/**
 * You might or might not want this actor in the end. In this example, its role is to be the central point of
 * contact between your web app and the actor system that might be receiving outside events.
 */
public class MarshallActor extends UntypedActor {

    StringBuilder messages = new StringBuilder();

    public static Props props = Props.create(MarshallActor.class);

    //Constructor
    public MarshallActor() {
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message.equals("getData")) {
            ///at this point we go and get the url
            //then send another actor to get the data from this url
            messages.append("OH HI!!!!");
            getSender().tell(messages, getSelf());
        }
    }
}