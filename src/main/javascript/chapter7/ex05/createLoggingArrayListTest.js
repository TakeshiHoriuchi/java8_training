load("createLoggingArrayList.js");

var byteArrayOutputStream = new java.io.ByteArrayOutputStream;
var printStream = new java.io.PrintStream(byteArrayOutputStream);

var arr = createLoggingArrayList(printStream);
arr.add(1);
var actual = byteArrayOutputStream.toString();

if (actual === 'Adding 1')
  print('test success!');
else
  print('test failed...');
