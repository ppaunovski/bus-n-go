import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import {NGX_ECHARTS_CONFIG, NgxEchartsModule} from "ngx-echarts";
import {Line} from "../../model/Line";

export interface CommutesByHour {
   id: number,
   line: Line,
   interval: string,
   numberOfCommutes: number,
}

@Component({
  selector: 'app-line-chart',
  template: `
    <div echarts [options]="chartOptions" class="chart"></div>`,
  standalone: true,
  imports: [NgxEchartsModule],
  providers: [
    {
      provide: NGX_ECHARTS_CONFIG,
      useFactory: () => ({ echarts: () => import('echarts') }),
    },
  ],
  styles: [`
    .chart {
      width: 100%;
      height: 400px;
    }
  `]
})
export class LineChartComponent implements OnInit, OnDestroy {
  @Input() dataFn!: Observable<CommutesByHour[]>; // Required input

  chartOptions: any;

  ngOnInit(): void {
    if (!this.dataFn) {
      console.error('Input dataFn is required');
      return;
    }


      this.dataFn.subscribe(data => {
        console.log(data)
        this.chartOptions = {
          title: {
            text: 'Number of Activities per Hour',
            left: 'center'
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['Number of Activities'],
            left: 'left'
          },
          xAxis: {
            type: 'category',
            data: data.map(d => d.interval),
            axisLabel: {
              rotate: 45
            }
          },
          yAxis: {
            type: 'value',
            name: 'Number of Activities'
          },
          series: [
            {
              name: 'Number of Activities',
              type: 'line',
              data: data.map(d => d.numberOfCommutes),
              smooth: true,
              lineStyle: {
                color: '#ff6f61'
              },
              itemStyle: {
                color: '#ff6f61'
              }
            }
          ]
        };
      }
    );
  }

  ngOnDestroy(): void {
  }
}
