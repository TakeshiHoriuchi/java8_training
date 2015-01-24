var printNextYear = function(x) { print("Next year, you will be ${x}") };

if ($ARG[0]) {
  var age = parseInt($ARG[0], 10) + 1;
  printNextYear(age);
}
else if ($ENV.AGE) {
  var age = parseInt($ENV.AGE, 10) + 1;
  printNextYear(age);
}
else {
  var input = readLine("Input your age: ");
  print(input);
  var age = parseInt(input, 10) + 1;
  printNextYear(age);
}
