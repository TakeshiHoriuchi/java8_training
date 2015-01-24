// 難しい。Nashorn自体がJavascriptとJavaに加えてNashornの仕様も覚える必要があるため

var Data = javafx.scene.chart.PieChart.Data;
var FXCollections = javafx.collections.FXCollections;
var Arrays = java.util.Arrays;
var Collectors = java.util.stream.Collectors;

if (!$ARG[0]) {
  print('usage: jjs -fx -scripting showPieChart.js -- [FILENAME]')
  quit();
}

var input = readFully($ARG[0]);
var lines = input.split('\n');
var list = Arrays['stream(java.lang.Object[])'](lines)
  .map(function(line) { return line.split(','); })
  .filter(function(array) {return array[0] !== '' })
  .map(function(array) { return new Data(array[0], parseFloat(array[1])); })
  .collect(Collectors.toList());

var obList = FXCollections.observableList(list);

var chart = new javafx.scene.chart.PieChart(obList);
$STAGE.scene = new javafx.scene.Scene(chart);
$STAGE.title = 'Chart';
