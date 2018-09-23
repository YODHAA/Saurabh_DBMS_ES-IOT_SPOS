var express = require('express')
var app = express();
const fs = require('fs');
var ejs = require('ejs');

app.set('view engine', 'ejs');
app.use(express.static("public"));

var files1 = []; 
fs.readdir(__dirname + '/files', (err, files) => {
  files.forEach(file => {
	console.log(file);
	files1.push(file);	
  });
});

app.get('/', function(req, res){
	res.render('index', {files:files1});
});

app.get('/:filename', function(req, res){
	res.download(__dirname + '/files/' + req.params.filename);
});

app.listen(3000);
