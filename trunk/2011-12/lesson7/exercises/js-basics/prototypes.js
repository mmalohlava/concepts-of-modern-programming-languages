// Prototyping and inheritance
console.log('\n=== Prototypes #1 ===');

var Car = function (color) {
    this.color = color || "black";
}
// be aware the following definition override field constructor !!!
/*
Car.prototype = {
    type: null,
    printColor: function () { console.log(this.color); }
    constructor: Car;
};*/
Car.prototype.type = 'abstract-car';
Car.prototype.getColor = function() { return this.color; };

var car = new Car("blue");
car.type = '';
console.log('car =', car);
console.log('cat.getColor() =', car.getColor());
console.log('car.prototype =', car.prototype);
console.log('car.__proto__ =', car.__proto__);
console.log('Car.prototype =', Car.prototype);
console.log('Car.prototype.constructor =', Car.prototype.constructor); // !!!

// Inheritance
console.log('\n=== Prototypes #2 ===');

function BMWCar(color) {
    // call 
    Car.call(this, color);
    // this.type = 'bmw'; // override property lookup
    this.getType = function() { return this.type; };
}
// inheritance by cloning
//BMWCar.prototype = new Car(); 
BMWCar.prototype = clone(Car.prototype); // ASSIGNMENT
console.log('BMWCar.prototype.constructor =', BMWCar.prototype.constructor);
BMWCar.prototype.constructor = BMWCar;
console.log('BMWCar.prototype.constructor =', BMWCar.prototype.constructor);

var bmw = new BMWCar('red');
console.log('bmw =', bmw);
console.log('bmw.getType() =', bmw.getType());
console.log('bmw.getColor() =', bmw.getColor());
Car.prototype.type='nothing';
console.log('bmw.getType() =', bmw.getType());

// Inheritance method and extending the core objects 
console.log('\n=== Prototypes #3 ===');

Object.prototype.properties = function(inheritedProperties) {
    var showInheritedProperties = inheritedProperties || false;
    var result = [];
    for(var property in this) {
        if (showInheritedProperties) {
            result.push(property);
        } else {
            if (this.hasOwnProperty(property)) {
            //if (Object.prototype.hasOwnProperty.call(this, property)) {
                result.push(property);
            }
        }
    }
    return result;
}

console.log('car.properties() =', car.properties());
console.log('bmw.properties() =', bmw.properties());

// Inheritance method and extending the core objects 
console.log('\n=== Prototypes #4 ===');

Object.prototype.inheritFrom = function(baseConstructor) {
    this.prototype = clone(baseConstructor.prototype);
    this.prototype.constructor = this;
};
Object.prototype.method = function(name, func) {
    this.prototype[name] = func;
};

function AudiCar() {
    this.topSpeed = 260;
}
AudiCar.inheritFrom(Car);
AudiCar.method("getTopSpeed", function() {
    return this.topSpeed;
});

var audiCar = new AudiCar();
console.log('audiCar =', audiCar);
console.log('audiCar.properties() =', audiCar.properties(false));

// Every object has a __proto__ object property (except Object); every function has a prototype object property. So objects can be related by 'prototype inheritance' to other objects.  You can test  for inheritance by comparing an object's __proto__ to a function's prototype object.  JavaScript provides a shortcut: the instanceof operator tests an object against a function and returns true if the object inherits from the function prototype.  For example, 
// chris.__proto__ == Engineer.prototype;  
// chris.__proto__.__proto__ == WorkerBee.prototype;  
// chris.__proto__.__proto__.__proto__ == Employee.prototype;  
// chris.__proto__.__proto__.__proto__.__proto__ == Object.prototype;  
// chris.__proto__.__proto__.__proto__.__proto__.__proto__ == null;

// Inheritance ECMAScript 5 style  
console.log('\n=== Prototypes #5 ===');

/* No need for capitalization as it's not a constructor*/
var someCar = {
    drive: function() {},
    name: 'Mazda 3'    
};

/* Use Object.create to generate a new car */
var anotherCar = Object.create(someCar);
anotherCar.name = 'Toyota Camry';

// the same with classical prototype
var vehiclePrototype = {
    init: function(carModel) {
        this.model = carModel;
    },
    getModel: function() {
        console.log('The model of this vehicle is:', this.model);
    }
};


function vehicle(model) {
    function F() {};
    F.prototype = vehiclePrototype;
    var f = new F();
    f.init(model);
    return f;
}

var fordCar = vehicle('Ford Escort');
fordCar.getModel();



// constructor pattern
var Car = (function() {
    var Car = function (model, year, miles){
        this.model = model;
        this.year   = year;
        this.miles = miles;
    };
    return function (model, year, miles) {
        return new Car(model, year, miles);
    };
})();

var civic = new Car("Honda Civic", 2009, 20000);
var mondeo = new Car("Ford Mondeo", 2010, 5000);


function clone(object) {
    var DummyConstructor = function() {};

    DummyConstructor.prototype = object;

    return new DummyConstructor();
}

