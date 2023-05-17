# Report

[Andrew Rutherfoord](https://github.com/AndrewRutherfoord) (s4667646) & [Serban Tonie](https://github.com/Serbbi) (s4721586)

## Introduction

This program is a Real-Time Strategy game. The map consists of a number of locations which are connected by paths. The player has the ability to create, move, and delete all of the locations on the map. The locations can be dragged around on the map. The player can also create paths between the locations.

In the game, the player can add armies to each location. The armies consist of a number of units, which each have their own damage and heath. Each army can belong to a one of five different Factions. These factions belong to either the "good" team ot the "bad" team. These two teams will fight each other in the simulation step. The player can also delete armies from the locations and edges.

On each node and edge, the player can add and remove events. These events have a random chance of occuring when an army arrives at a location. They can have different effects depending on the event.

The game can also be saved to a json file, using a custom built library.

> _Very briefly describe what your program does._

> Expected length: ~100 words

## Program design

> \*Here you go over the structure of the program. Try not to go too in-depth here implementation-wise, but rather discuss the important components and relations between them. If you think it can help, feel free to add a simple diagram here. The design of the program should be clear to the reader.
>
> In particular, describe the model of the program. How is it structured? How did you make sure to separate the different aspects of the program? How do the `model`, `view` and `controller` interact with each other? Additionally, you should include some design decisions in here. There is no need to provide an explanation for every single thing, but there are often multiple ways of implementing a feature and in those cases it makes sense to state why you chose one over the other.\*

This program makes use of the MVC pattern for separating the logic and data in the model from the Java Swing user interface. In our model, the majority of the application is centred around the graph. The graph holds all the data for the map. It consists of Nodes, and Edges. Each node represents a location on the map, and each edge represents a path connecting 2 nodes. These Objects in the graph model are used by the view to draw the nodes and edges onto the graph panel in the view. The graph panel makes use of a mouse observer to detect when the user clicks on the graph panel, and determines whether the user has clicked on a node or edge. When this action is performed, it notifies all of the observers of the graph, causing them to update.

Our user interface is divided up into 3 distinct components:

1. The graph panel - This panel displays the map, based on the graph from the model.
2. The top bar panel - Holds the buttons that are used to interact with the graph.
3. The side bar panel - Used to interact with a selected node or edge in the graph.

The side bar panel is where the information about nodes and edges is shown. For nodes we display the name, the list of armies present there(if any), buttons to add/remove armies. There is also a list of possible events having also the add and remove buttons. For edges we dont't have any buttons to modify armies, but instead we show the nodes it connects to.

The model is centered around the `Graph` which contains the `Nodes` and `Edges`. Nodes and edges can contain armies, so we used an interface `ContainsArmy` to group them with regards to that. Armies belong to a faction so we created an abstract class `Faction` and its children are the actual factions like `Elves`, `Dwarves`... Nodes and edges can also contain events, so we used another interface to group them by that. Each separate event implements the `Event` interface having identical behaviour.

For removing armies/events from nodes and edges we decided to display them as radio buttons, so the user can select which armies/events to delete. For storing the selected army/event we used a separate class in order to maintain the MVC pattern.

> Expected length: as much as you need to explain the above.

## Evaluation of the program

> _Discuss the stability of your implementation. What works well? Are there any bugs? Is everything tested properly? Are there still features that have not been implemented? Also, if you had the time, what improvements would you make to your implementation? Are there things which you would have done completely differently?_

After many thorough investigation, we came to the conclusion that the code is bullet proof. Testing was done every time a new feature was added to make sure that everything was working as intended. Each time we added something we made sure that the implementation is future proof and will allow us, in case we need to change it, to make little modifications to the code. There are no bugs, just features. Nodes can go offscreen, but maybe that teaches the user not to be stupid.

Our project is like a piece of art, you can never finish it, you just abandon it. As long as the minimum requirements have asked, we have done everything and a bit more. We added cool textures, cool interface buttons and annoying pop-up messages. The list of extra features which we did not add included sounds when pressing the buttons, damage based on unit names, army generals and more.

In terms of what we already wrote as code, we think that it is as good as it gets. We focused on keeping the code as clean as possible and as maintainable as possible and our subjective opinion believes that we succeded. Classes are not longer than 300 lines including the java-doc, functions are also no longer than 30 lines. The classes are sorted in packages and it is easy to search for any of them.

As we were developing the program we were discussing how to implement certain features, so we were at all times more or less on the same page regarding the implementation. At some point when adding the factions to the game, we had different ideas on how to implement them, but after a quick discussion we were able to find common ground.

> Expected length: ~300-500 words

## Questions

Please answer the following questions:

1. In this assignment, the program should follow the Model View Controller (MVC) pattern. Please explain the design of the program in terms of the MVC pattern. Specifically try to answer the following questions:
   - MVC consists of three components: Model, view and controller. Can you please explain the role of each component? Please provide examples of these roles from the assignment. How are these three roles (i.e. Model, view and controller) are implemented in the assignment?
   - MVC enforces special constraints on the dependencies between its three components: Model, view and controller. Please explain these constraints, and why are they important?

---

Answer:

The MVC pattern is an efficient way to arrange code in a Java GUI (graphical user interface) project in order to separete the data and logic from the GUI. It consits of 3 parts as follows:

- Model
  - The model package holds all of the application data and logic. It works by running different logic when it recieves commands from the controller of that applicaton. The model also holds all of the methods that allows the view to access the data that is held in the model.
- View

  - The view is the actual graphical user interface of the application. In our case it contains a number of different Java Swing components that build up the GUI. There is no program logic held within the view. The view can access the data held in the model directly, but all mutations occur via the controller. For example, each button is connected to an Action in the controller, so when the button is clicked, the code in the action is executed, and the mutation to the model occurs.

- Controller
  - The controller is where all mutations to the model are performed. The controller contains a number of actions that are executed something is done to the view (eg. a button is clicked). When this occurs the code in the action runs and performs the desired mutations. The controller allows all mutations to the models to be contained in one location, making debugging easier and improving code readability.

In our application, the majority of out data is stored in the graph, which is contained in the model. It consists of 2 lists, of nodes and edges. In the graph model we have a number of methods to access, mutate the items in these lists. The accessors are primarily used by the view to access that data that it needs to display. The view is not able to directly mutate the graph in any way. All mutations of the graph are performed via the controller. For example, on the top bar of the view, we have a number of buttons. Each of these buttons have a dedicated example, such as the `Add Node` button. When it is clicked, the `addNodeAction` is run, which creates a `JOptionPane` to get the name of the new node. The action the creates the new node in the graph.

---

2. The Swing library provides the ability to create nested user interface components. In this assignment, you created multiple JPanel components on the user interface. These contain other user interface components to build-up a tree of user interface components. Which design pattern does Swing implement to create a hierarchy of user interface components? Please explain this pattern and how it is implemented in Swing.

---

Answer:

Swing makes use of the Composite Design Pattern. This pattern is used to a tree like structure made up of types that inherit a base type. It is made up by 3 major parts:

1 - The component which is is the base interface for all the objects in the composition. It manages the child composites.

2 - The leaf which implements the default behavior of the base component(parent).

3 - The composite has component elements, implementing the base component methods(parent).

For example in swing we have a frame(component) which one or more panels(composites). One of the panels could have one or more buttons(leaves), one a table with some data, with each cell in the table having a text field. This is the hierarchical structure of the Composite Design Pattern.

---

3. The Observer pattern is useful to implement the MVC pattern. Can you please explain the relationship between the Observer pattern and the MVC pattern? Please provide an example from the assignment on how the Observer pattern supports implementing the MVC pattern.

---

Answer:

The observer pattern is extremely important for allowing the view to update when the model is mutated. It allows the view to only be updated when necessary, rather than updating it constantly which wastes cpu time. The observer pattern requires each view element that accesses the model to implement the interface `Observable`. This interface specifies the `update()` behaviour, which is run every time the model is mutated and refreshes the view element. The `Observer` is the class which holds a list of `Observable` instances. The model 'has a' instance of `Observer` which it uses to store and update all of the view objects that depend on the model. The update is performed with the `notifyAll()` method.This method iterates through the list of observables and runs their update methods. This causes the update to occur on all view elements. To allow this to work properly, every time the model is mutated, the `notifyAll()` method must be run.

In the case of our application, the `Graph` class has an observer, and all view panels that display data from the graph are added to the graph's observer using the `addObservable()` method. Furthermore, almost all of the mutation methods in the `Graph` class run the `notifyAll()` method at the end, causing the entire view to update. This means that the view is always showing the most up to date data.

---

## Process evaluation

> _Describe shortly the process that led to the final code and the report. What was easy, what was difficult? Did you make interesting mistakes? What have you learned from this assignment?_

Building up this application helped both of us learn the usefulness of the Observer pattern and the MVC pattern in developing a large Java Swing application. These two pattern are crucial to building an application that is easy to develop, and produces a responsive application.

We started the development of this application by building up our `Graph` class in the model, as the entrie application revolves around the data stored in it. We wanted to ensure that all of our data was stored in one place to ensure that the JSON writing at the end was easier. We then moved on to the main graph panel and the actions that mutate the graph on the top bar. Getting the updates and selection to work properly on the graph panel took some time, but worked in the end. We found it interesting to see how much maths is involved in being able to select and drag an element across the screen. We then built out the functionality in the sidebar, and got very frustrate with the layout managers. Finally, we build up the simulation and the JSON writing. Because of the way we had designed our `Graph` model, doing the JSON writing was quite simple.

In the process of making this application, we ran into a number of bugs that took some time to solve. The main issue that we struggled with was getting the view to update properly when a mutation was performed. We would often have the view be one interaction behind, so when we clicked on an element in the view, the data from the previous action would update. To solve this we had to move run the `notifyAll()` method in a few different places. This has the side effect of the view updating twice for each interaction, however we found this to be the only way to consistently update the view.

> Expected length: ~150 words

## Conclusions

> _Add a very short summary/concluding remarks here_

We both found that building this application helped us to understand why the concepts we were being taught in the lectures was useful, and allowed us to extend out knowledge of these concepts on our own through research as we built them.
