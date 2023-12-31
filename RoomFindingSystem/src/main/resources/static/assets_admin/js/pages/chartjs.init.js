!function(s) {
    "use strict";
    var r = function() {};

    r.prototype.respChart = function(r, o, e, a) {
        var t = r.get(0).getContext("2d");
        var n = s(r).parent();

        function i() {
            r.attr("width", s(n).width());
            switch (o) {
                case "Line":
                case "Doughnut":
                case "Pie":
                case "Bar":
                case "Radar":
                case "PolarArea":
                    new Chart(t, { type: o.toLowerCase(), data: e, options: a });
                    break;
            }
        }

        s(window).resize(i);
        i();
    };

    r.prototype.init = function() {
        this.respChart($("#lineChart"), "Line", {
            labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September"],
            datasets: [{
                label: "Sales Analytics",
                fill: !1,
                lineTension: .05,
                backgroundColor: "#fff",
                borderColor: "#3ec396",
                borderCapStyle: "butt",
                borderDash: [],
                borderDashOffset: 0,
                borderJoinStyle: "miter",
                pointBorderColor: "#3ec396",
                pointBackgroundColor: "#fff",
                pointBorderWidth: 8,
                pointHoverRadius: 6,
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "#3ec396",
                pointHoverBorderWidth: 3,
                pointRadius: 1,
                pointHitRadius: 10,
                data: [65, 59, 80, 81, 56, 55, 40, 35, 30]
            }]
        }, {
            scales: {
                yAxes: [{
                    ticks: { max: 100, min: 20, stepSize: 10 }
                }]
            }
        });

        this.respChart($("#doughnut"), "Doughnut", {
            labels: ["Desktops", "Tablets", "Mobiles", "Mobiles", "Tablets"],
            datasets: [{
                data: [80, 50, 100, 121, 77],
                backgroundColor: ["#5553ce", "#297ef6", "#e52b4c", "#ffa91c", "#32c861"],
                hoverBackgroundColor: ["#5553ce", "#297ef6", "#e52b4c", "#ffa91c", "#32c861"],
                hoverBorderColor: "#fff"
            }]
        });

        this.respChart($("#pie"), "Pie", {
            labels: ["Desktops", "Tablets", "Mobiles", "Mobiles", "Tablets"],
            datasets: [{
                data: [80, 50, 100, 121, 77],
                backgroundColor: ["#5d6dc3", "#3ec396", "#f9bc0b", "#4fbde9", "#313a46"],
                hoverBackgroundColor: ["#5d6dc3", "#3ec396", "#f9bc0b", "#4fbde9", "#313a46"],
                hoverBorderColor: "#fff"
            }]
        });

        this.respChart($("#bar"), "Bar", {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "Sales Analytics",
                backgroundColor: "rgba(60, 134, 216, 0.3",
                borderColor: "#3c86d8",
                borderWidth: 2,
                hoverBackgroundColor: "rgba(60, 134, 216, 0.7)",
                hoverBorderColor: "#3c86d8",
                data: [65, 59, 80, 81, 56, 55, 40, 20]
            }]
        });

        this.respChart($("#radar"), "Radar", {
            labels: ["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"],
            datasets: [{
                label: "Desktops",
                backgroundColor: "rgba(179,181,198,0.2)",
                borderColor: "rgba(179,181,198,1)",
                pointBackgroundColor: "rgba(179,181,198,1)",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "rgba(179,181,198,1)",
                data: [65, 59, 90, 81, 56, 55, 40]
            }, {
                label: "Tablets",
                backgroundColor: "rgba(255,99,132,0.2)",
                borderColor: "rgba(255,99,132,1)",
                pointBackgroundColor: "rgba(255,99,132,1)",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "rgba(255,99,132,1)",
                data: [28, 48, 40, 19, 96, 27, 100]
            }]
        });

        this.respChart($("#polarArea"), "PolarArea", {
            datasets: [{
                data: [11, 16, 7, 18],
                backgroundColor: ["#297ef6", "#45bbe0", "#ebeff2", "#1ea69a"],
                label: "My dataset",
                hoverBorderColor: "#fff"
            }],
            labels: ["Series 1", "Series 2", "Series 3", "Series 4"]
        });
    };

    s.ChartJs = new r;
    s.ChartJs.Constructor = r;
}(window.jQuery);

(function(r) {
    "use strict";
    window.jQuery.ChartJs.init();
})();
