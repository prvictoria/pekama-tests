var moment;
moment = require('./node_modules/moment/moment.js');
var date;
var dates = [];
var dateIncrements = [7, 8, 30, -7, -8, -30, 0];
var count = dateIncrements.length;
var testDates = dates;

for (var i = 0; i < count; i++) {
    date = moment();
    date.add(dateIncrements[i], 'days');
    dates.push(date.format('DD-MMM-YYYY'));
}

for (var i = 0; i < count; i++) {
    console.log(dates[i]);
}

//var testDates = ['1'];
// use this or uoutput test date
// console.log(testDates[3]);
//console.log(dates[1]);