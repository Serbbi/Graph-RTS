package nl.rug.oop.rts.controller.observers;

import java.util.HashSet;
import java.util.Set;

/**
 * A parent class for the observables.
 */
public class Observer {

    private final Set<Observable> observables;

    /**
     * Constructor for the observer.
     */
    public Observer() {
        observables = new HashSet<>();
    }

    /**
     * Adding observables to the list.
     * @param observable to be added
     */
    public void addObservable(Observable observable){
        observables.add(observable);
    }

    /**
     * Notifies the view for a change in the model.
     */
    public void notifyObservers() {
        observables.forEach(Observable::update);
        observables.forEach(Observable::update);
    }
}
