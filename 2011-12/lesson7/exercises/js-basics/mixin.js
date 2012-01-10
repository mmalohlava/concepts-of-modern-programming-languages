console.log('\n=== Mixins #1 ===');
// There is no support for multiple prototypes, but it is possible to mix
// methods

/* Car Class */
var Car = function(settings){
    this.model = settings.model || 'no model provided';
    this.color = settings.color || 'no color provided';
};

Car.prototype.printModel = function() { console.log(this.model); };

/* Mixin Class */
var Mixin = function() {};
Mixin.prototype = {
    driveForward: function(){
        console.log('drive forward');
    },
    driveBackward: function(){
        console.log('drive backward');
    }
};


/* Augment existing class with a method from another class */
function augment(receivingClass, givingClass) {
    for (var methodName in givingClass.prototype) {
        /* check to make sure the receiving class doesn't 
        have a method of the same name as the one currently 
        being processed */
        if (!receivingClass.prototype[methodName]) {
            receivingClass.prototype[methodName] = givingClass.prototype[methodName];
        }
    }
}


/* Augment the Car class to have the methods 'driveForward' and 'driveBackward'*/
augment(Car, Mixin);

/* Create a new Car */
var vehicle = new Car({model:'Ford Escort', colour:'blue'});

/* Test to make sure we now have access to the methods*/
vehicle.printModel();
vehicle.driveForward();
vehicle.driveBackward();

