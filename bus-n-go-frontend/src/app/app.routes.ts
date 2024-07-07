import {Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {TicketsPageComponent} from "./components/tickets-page/tickets-page.component";
import {TicketsBuyComponent} from "./components/tickets-buy/tickets-buy.component";
import {StartRouteInstanceComponent} from "./route-instances/start-route-instnace/start-route-instance.component";
import {RouteInstanceComponent} from "./route-instances/route-instance/route-instance.component";

export const routes: Routes = [{
  path: 'login',
  component: LoginComponent
},
  {
    path: 'tickets',
    component: TicketsPageComponent
  },
  {
    path: 'tickets/buy',
    component: TicketsBuyComponent,
  },
  {
    path: 'route-instances/start',
    component: StartRouteInstanceComponent
  },
  {
    path: 'route-instances/:id',
    component: RouteInstanceComponent
  },

  ];
