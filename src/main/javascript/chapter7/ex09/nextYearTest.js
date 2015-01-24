var assertContainsNextYear = function(str, year) {
  if (str.contains(year.toString()))
    print('test success!');
  else
    print('test failure...')
};

var actual = $EXEC('jjs -scripting nextYear.js', '20');
assertContainsNextYear(actual, 21);

var actual = `jjs -scripting nextYear.js -- 20`;
assertContainsNextYear(actual, 21);

$ENV.AGE = '20';
var actual = `jjs -scripting nextYear.js`;
assertContainsNextYear(actual, 21);
