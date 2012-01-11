/*
 * Example of module pattern. 
 *
 * The pattern is common for Javascript code and can be seen as a singleton object.
 *
 * - method scopes:
 *   - private
 *   - public 
 */


var SearchEngine = (function() {
    // all defined variables and functions are only visible in this function
    var db = [{name: "Pepa", role: "admin"}, {name: "Joe", role: "user"} ];

    function query(name, role) {
        var result = [];
        for (var i = 0; i < db.length; i++) {
            var match = false;
            if (name && db[i].name === name) match = true;
            if (role && db[i].role === role) match = true;

            if (match) result.push(db[i]);
        }
        return result;
    }

    // only privileged methods are returned
    return {
        findByName: function(name) {
            return query(name, undefined);
        },

        findByRole: function(role) {
            return query(undefined, role);
        }
    };

})(); // <- anonymous function defined and called

// it is possible to define public methods
SearchEngine.findAdmins = function() {
        return SearchEngine.findByRole('admin');
};


console.log('Admins:', SearchEngine.findAdmins());

// try to call private methods
try { 
    console.log("Private method call:", SearchEngine.query(undefined, 'user'));
} catch (error) {
    console.log("Private call Search.query(...) raises the exception", error);
}

/* 
 * It is possible to define submodule.
 */
SearchEngine.UserSearch = (function() {
    var roleUser = 'user';

    return {
        findUserByName: function(name) {
            var result = [];
            var users = SearchEngine.findByRole(roleUser);
            for (var i = 0; i < users.length; i++) 
                if (users[i].name === name)
                    result.push(users[i]);
            return result;
        }
    };
})();

console.log('Users with name "Joe":', SearchEngine.UserSearch.findUserByName('Joe'));

/*
 * It is possible to extend the module. ASSIGNMENT
 */
//var NewSearchEngine = ASSIGNMENT
