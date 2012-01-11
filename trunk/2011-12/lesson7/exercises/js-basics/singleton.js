var Singleton =(function(){
    var instantiated;

    function init (){
            /*singleton code here*/
            return {
                publicMethod: function(){
                    console.log('hello world');
                },
                publicProperty: 'test'
            };
    }

    return {
        getInstance: function(){
            if (!instantiated){
                instantiated = init();
            }
            return instantiated; 
        }
    };
})();

Singleton.getInstance().publicMethod();


