var pipe = function(args) {
    var output = '';
    for(var i = 0; i < arguments.length; i++) {
        if (i === 0) {
            output = $EXEC(arguments[i]);
        }
        else {
            output = $EXEC(arguments[i], output);
        }
    }
    return output;
};
