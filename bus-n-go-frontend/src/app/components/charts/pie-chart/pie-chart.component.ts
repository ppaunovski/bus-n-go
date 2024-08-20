import {Component, Input, OnInit} from '@angular/core';
import {NGX_ECHARTS_CONFIG, NgxEchartsModule} from "ngx-echarts";
import {Observable} from "rxjs";
import {PieChartValues} from "../../../model/PieChartValues";



@Component({
  selector: 'app-pie-chart',
  standalone: true,
  imports: [NgxEchartsModule],
  providers: [
    {
      provide: NGX_ECHARTS_CONFIG,
      useFactory: () => ({ echarts: () => import('echarts') }),
    },
  ],
  templateUrl: './pie-chart.component.html',
  styleUrl: './pie-chart.component.css'
})
export class PieChartComponent implements OnInit {
  @Input() dataFn!: Observable<PieChartValues[]>
  chartOptions: any;

  ngOnInit(): void {
    this.dataFn.subscribe({
      next: value => {
        this.chartOptions = this._getOptions(value)
      }
    })

  }

  private _getOptions(value: PieChartValues[]) {
    return {
      title: {
        text: 'Fines per Line',
          subtext: 'Current Year',
          left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
          left: 'left'
      },
      series: [
        {
          name: 'Fines',
          type: 'pie',
          radius: '50%',
          data: [
            ...value
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
  }
}
