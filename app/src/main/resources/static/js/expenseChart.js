const ctx = document.getElementById('expensePieChart').getContext('2d');

const chartData = {
    labels: labels,
    datasets: [{
        label: '支出',
        data: data,
        backgroundColor: [
           '#9E9E9E',
           '#FF8A65',
           '#4FC3F7',
           '#81C784',
           '#BA68C8',
           '#FFD54F',
           '#90CAF9',
           '#A1887F',
        ],
        borderWidth: 1
    }]
};

const centerTextPlugin = {
    id: 'centerText',
    afterDraw(chart) {
        const {ctx, width, height} = chart;
        ctx.save();

        const total = chart.data.datasets[0].data.reduce((acc, val) => acc + val, 0);

        ctx.font = '30px sans-serif';
        ctx.fillStyle = '#707070';
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText(`${total.toLocaleString()}円`, width / 2, height / 2);

        ctx.restore();
    }
};


const config = {
    type: 'doughnut',
    data: chartData,
    options: {
        responsive: true,
        animation: false,
        cutout: '60%',
        plugins:{
            legend:{ 
				display: false
			 },
            tooltip: {
                callbacks:{
                    label: function(context){
                        const label = context.label || '';
                        const value = context.parsed || 0;
                        return `${label}: ${value.toLocaleString()}円`;
                    }
                }
            }
        }
    },
    plugins: [centerTextPlugin]
};

new Chart(ctx, config);