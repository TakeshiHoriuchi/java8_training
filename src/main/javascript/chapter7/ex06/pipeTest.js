load('pipe.js');

var actual = pipe('jjs printFixture.js', 'jjs -scripting readFirstLine.js');
if (actual.contains("123"))
  print("test success");
else
  print("test failure");
