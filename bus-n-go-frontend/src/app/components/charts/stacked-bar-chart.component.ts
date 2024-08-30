import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import {NGX_ECHARTS_CONFIG, NgxEchartsModule} from "ngx-echarts";

export interface IncomeData {
  id: number;
  year: string;
  finesIncome: number;
  ticketIncome: number;
  totalIncome: number;
}

@Component({
  selector: 'app-income-chart',
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
export class IncomeChartComponent implements OnInit, OnDestroy {
  @Input() dataFn!: Observable<IncomeData[]>; // Required input

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
            text: 'Yearly Income from Fines and Tickets',
            left: 'center'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: { type: 'shadow' }
          },
          legend: {
            data: ['Fines Income', 'Ticket Income', 'Total Income'],
            left: 'left'
          },
          xAxis: {
            type: 'category',
            data: data.map(d => d.year)
          },
          yAxis: {
            type: 'value',
            name: 'Income'
          },
          series: [
            {
              name: 'Fines Income',
              type: 'bar',
              stack: 'total',
              data: data.map(d => d.finesIncome),
              itemStyle: {
                color: '#ff6f61'
              }
            },
            {
              name: 'Ticket Income',
              type: 'bar',
              stack: 'total',
              data: data.map(d => d.ticketIncome),
              itemStyle: {
                color: '#61a0a8'
              }
            },
            {
              name: 'Total Income',
              type: 'bar',
              data: data.map(d => d.totalIncome),
              itemStyle: {
                color: '#c23531'
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
