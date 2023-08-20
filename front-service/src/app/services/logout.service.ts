import {inject, Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {State} from "../state/state";
import {CookieService} from "ngx-cookie-service";

@Injectable({providedIn:'root'})
export class LogoutService {

  timer: number | undefined;

  private router = inject(Router);
  private state = inject(State);
  private cookieService: CookieService = inject(CookieService);


  logoutTimer (){
    this.timer = setInterval(() => {
      const lastUserAction = this.state.timeoutTime.value;
      const timeNow = Date.now();

      const logoutTime = (timeNow - lastUserAction) / 60000;
      console.log(15 - logoutTime + ` time until logout`)

      if(logoutTime > 15){
      this.logout();
      }
    }, 5000);

  }

  //https://stackoverflow.com/questions/59176295/angular-8-clear-cookies-using-cookieservice
  logout(){
    clearInterval(this.timer);
    sessionStorage.clear();
    this.cookieService.delete("superGiniSession", "/", "localhost", false, "Lax");
    this.router.navigate(['/login']);
  }

  closeLogoutTimer(){
    clearInterval(this.timer);
  }
}
