const length = 500;
var count, title, svg;

var init = function() {
    svg = d3.select(".Aligner-item")
        .append("svg")
        .attr("width", length+5)
        .attr("height", length+5)
        .attr("align","center");

    $.ajax({
        url: '/init',
        dataType: 'json',
        success: function(response) {
            count = response.dimn;
            title = response.title;
            document.getElementById('subtitle').innerHTML = title;
            drawBoard(count, length);
            requestData();
        },
        error: function(xhr) {
            console.log("error requesting dataset list");
        }
    });
};
var requestData = function() {
    $.ajax({
        url: '/data',
        dataType: 'json',
        success: function(response) {
            drawAgents(response, count, length);
            setTimeout(requestData, 250);
        },
        error: function(xhr) {
            console.log("error requesting dataset list");
        }
    });
};
var drawBoard = function(count, size) {
    var margin = size / count;
    var lines_data = Array.prototype.concat(
                d3.range(0, count+1) //rows
                    .map(i => ([{ "x":i*margin, "y":0}, {"x":i*margin, "y":size}]))
            .map((d,j) => [d, (j == 0 || j == count) ? 'outer' : 'inner']),
                d3.range(0, count+1) //cols
                    .map(j => ([{ "x":0, "y":j*margin}, {"x":size, "y":j*margin}]))
                    .map((d,i) => [d, (i == 0 || i == count) ? 'outer' : 'inner']));

    var line = d3.line()
                 .x((d) => d.x)
                 .y((d) => d.y);

    svg.selectAll("path").data(lines_data)
        .enter().append("path")
        .attr("class", (d) => d[1])
        .attr("d", (d) => line(d[0]));

};
var drawAgents = function(data, count, size) {
    var t = d3.transition()
              .duration(225);

    var margin = size / count;

    var expand = (x) => x * margin;

    var circles = svg.selectAll("circle").data(data);

    circles
        .exit()
        .attr("class", (d) => d._class)
        .transition(t)
            .attr("y", 60)
            .style("fill-opacity", 1e-6)
            .remove();

    circles
        .attr("class", (d) => d._class)
        .attr("r", 3)
        .style("fill-opacity", 1)
        .transition(t)
            .attr("cx", (d) => expand(d.location.x))
            .attr("cy", (d) => expand(d.location.y));

    circles
        .enter().append("circle")
        .attr("class", (d) => d._class)
        .attr("r", 3)
        .attr("cx", (d) => expand(d.location.x))
        .attr("cy", (d) => expand(d.location.y))
        .style("fill-opacity", 1);
};

init();