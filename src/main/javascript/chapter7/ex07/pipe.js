var pipe = function(args) {
    var prevOutput = null;
    var process = null;
    var builders = [];
    for(var i = 0; i < arguments.length; i++) {
      var commands = java.util.Arrays.asList(arguments[i].split(' '));
      var builder = new java.lang.ProcessBuilder(commands);

      if (i !== 0) builder.redirectInput(prevOutput);
      prevOutput = builder.redirectOutput();

      builders[i] = builder;
    }

    for (var i = 0; i < arguments.length; i++) {
      var process = builders[i].start();
    }
    // for(var i = 0; i < arguments.length; i++) {
        // var commands = java.util.Arrays.asList(arguments[i].split(' '));
        // var builder = new java.lang.ProcessBuilder(commands);
        
        // if (i !== 0) builder.redirectInput(prevOutput);
        // prevOutput = builder.redirectOutput();
        
        // process = builder.start();
    // }
    return process.getInputStream();
};
