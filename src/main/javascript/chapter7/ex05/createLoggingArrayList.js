var createConsoleLoggingArrayList = function() {
  return createLoggingArrayList(java.lang.System.out);
};

var createLoggingArrayList = function(out) {
  var arr = new (Java.extend(java.util.ArrayList)) {
    add: function(x) {
      out.print('Adding ' + x);
      return Java.super(arr).add;
    }
  };

  return arr;
};
