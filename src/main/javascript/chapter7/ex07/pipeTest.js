load('pipe.js');

var inputStream = pipe('jjs printFixture.js', 'jjs -scripting readFirstLine.js');
var reader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream));
var actual = reader.readLine();
if (actual.contains("123"))
  print("test success");
else
  print("test failure");
