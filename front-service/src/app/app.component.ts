import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import {SvgIconsService} from "./services/svgIcons.service";
import {LogoutService} from "./services/logout.service";
import {filter} from "rxjs";
import {State} from "./state/state";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{

  private svgIconService = inject(SvgIconsService);
  private logout = inject(LogoutService);
  private router = inject(Router);
  private state = inject(State);


  ngOnInit(): void {
    this.svgIconService.addIconsToRegistry();
    this.detectRouterOutletUrlChange();
  }

  private detectRouterOutletUrlChange() {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(event => {
        // A route navigation has occurred
        console.log('Route change detected:');
        // Perform your logic here
        this.state.timeoutTime.next(Date.now());
      });
  }

  ngOnDestroy(): void {
    this.logout.closeLogoutTimer();
  }
}
