<h1>PeopleCounter</h1>

<h3>Architecture</h3>
To complete this project, I decided to implement an MVC architecture pattern. I chose this pattern because it made it easier to separate the changing behavior of the user interface logic from the business logic and prevent overexposed or repetitive code. My architecture is implemented as the following: 
<br>
<br>1. Model - Counter.java: responsible for managing the total count of people overall and the current count of people at that moment
<br>2. View - MainActivity.java: visualization of the raw data in the model
<br>3. Controller - CounterController.java: gets notified of the user’s behavior and updates the counter model during events
<br>
<br>In my instance, MVC is implemented passively because the controller is the only object that accesses the counter model (as opposed to also using an interface to abstract the methods). When the model is updated, the controller will notify the view that it also needs to update. The view will then request the updated data from the controller. 
<br>
<h3>Getting the Data</h3>
I created the Counter class to be a domain object that stores and retrieves the values for both the total count of people and the current count of people. This is the model that the CounterController will use to pass these two integers to the view when needed. Since the total and people fields are private, the controller has limited access to these fields through the getter and setter methods in the model class. 
<br>
<h3>Distributing the Data</h3>
The controller is implemented as a singleton so that throughout the lifetime of the program, the same controller instance is being used. The counter variable is only exposed when the controller is initialized. The counter is used within the controller’s methods to retrieve and update the total count and people count according to the user’s behavior that is passed downstream from the view. There is also a private boolean that is used to keep track of whether the counter is at capacity along with a method that changes this value depending on whether or not this threshold has been met. 
<br>
<h3>Rendering the Data</h3>
Besides attaching the views to their XML renditions and setting their initial state, there is a lot of interface logic within the main activity that relies on the raw data that is passed between itself and the controller. The view has the instance of the controller which is initialized once in onCreate() and used to allocate the data according to the view’s layout. The methods to add or remove people and reset the counter are exposed by the controller in order to update the counter object. These methods are implemented within user event listeners that are attached to the add, remove, and reset buttons. Other private methods are also called to change the remove button’s visibility when the people count is at zero or to change the people count’s textview color when the counter is at max capacity by using the boolean tracker that is exposed by the controller. In the event that the activity is interrupted or destroyed and has to be recreated, the state of the controller's data is cached and retrieved by storing them within the savedInstanceState bundle and managing the bundle with its associated methods.
<br>
<br>I hope this gave a transparent understanding of my thought process behind the code. If there are any grey areas, I’d be happy to explain them at the next interview :) Take care! 
