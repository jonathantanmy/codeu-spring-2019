 google.charts.load('current', {packages: ['corechart']});
 google.charts.setOnLoadCallback(fetchMessageData);

 // draw the chart from dataTable
 function drawChart(dataTable){

    var chart = new google.visualization.BarChart(document.getElementById('chart'));
    var chart_options = {
    width: 800,
    height: 400
    };

    chart.draw(dataTable, chart_options);

  }

 // fetches data to be used in the chart.
 function fetchMessageData() {
          fetch("/messagechart")
               .then((response) => {
                  return response.json();
               })
               .then((msgJson) => {
                 var msgData = new google.visualization.DataTable();
                 //define columns for the DataTable instance
                 msgData.addColumn('date', 'Date');
                 msgData.addColumn('number', 'Message Count');


                 for (i = 0; i < msgJson.length; i++) {
                     msgRow = [];
                     var timestampAsDate = new Date (msgJson[i].timestamp);
                     var totalMessages = i + 1;

                     msgRow.push(timestampAsDate);
                     msgRow.push(totalMessages);

                     //console.log(msgRow);
                     msgData.addRow(msgRow);

                 }
                 //console.log(msgData);
                 drawChart(msgData);
               });
         }

