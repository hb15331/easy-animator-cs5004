Added a new interface and concrete class for interactive views.  The new interface extends the existing view interface.

Added an interface and a concrete class for the controller.

Modified the main() method to allow for a new view option and launch the program through the controller.

Modified the model to give the it the ability to reset to the initial configuration.  This was acheived by modifying the Shapes to store their starting values and adding a reset() method.  The model implementation was also modified by adding a reset() method that calls each Shape and resets it, then sets the currentFrame back to 0.  