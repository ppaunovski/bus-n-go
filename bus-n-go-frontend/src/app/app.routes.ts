import {Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {TicketsPageComponent} from "./components/tickets-page/tickets-page.component";
import {TicketsBuyComponent} from "./components/tickets-buy/tickets-buy.component";
import {StartRouteInstanceComponent} from "./components/route-instances/start-route-instnace/start-route-instance.component";
import {RouteInstanceComponent} from "./components/route-instances/route-instance/route-instance.component";
import {StartCommuteComponent} from "./components/commutes/start-commute/start-commute.component";
import {CommuteComponent} from "./components/commutes/commute/commute.component";
import {StartCcontrolComponent} from "./components/controls/start-ccontrol/start-ccontrol.component";
import {ControlsComponent} from "./components/controls/controls/controls.component";
import {ControlComponent} from "./components/controls/control/control.component";
import {AdminPanelComponent} from "./components/admin/admin-panel/admin-panel.component";
import {RegisterComponent} from "./components/register/register.component";
import {authGuard} from "./guards/auth.guard";
import {authCanmatchGuard} from "./guards/auth-canmatch.guard";

export const routes: Routes = [{
  path: 'login',
  component: LoginComponent
},
  {
    path: 'tickets',
    component: TicketsPageComponent,
    canActivate: [authGuard],
    canMatch: [authCanmatchGuard]
  },
  {
    path: 'tickets/buy',
    component: TicketsBuyComponent,
  },
  {
    path: 'route-instances/start',
    component: StartRouteInstanceComponent,
    canActivate: [authGuard],
    canMatch: [authCanmatchGuard]
  },
  {
    path: 'route-instances/:id',
    component: RouteInstanceComponent
  },
  {
    path: 'commutes/start',
    component: StartCommuteComponent
  },
  {
    path: 'commutes/:id',
    component: CommuteComponent
  },
  {
    path: 'controls/start',
    component: StartCcontrolComponent
  },
  {
    path: 'controls',
    component: ControlsComponent
  },
  {
    path: 'controls/:id',
    component: ControlComponent
  },
  {
    path: 'admin',
    component: AdminPanelComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  }

  ];
