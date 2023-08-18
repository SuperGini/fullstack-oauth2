import {JwtHelperService} from "@auth0/angular-jwt";
import {ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot} from "@angular/router";
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {map} from "rxjs";


export const canActivateRoute: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {

    const jwtHelper: JwtHelperService = new JwtHelperService();
    const accessToken: string = <string>sessionStorage.getItem('access_token');
    const isTokenExpired: boolean = jwtHelper.isTokenExpired(accessToken);
    const x = jwtHelper.getTokenExpirationDate(accessToken);

    console.log(`is token expired?: ${isTokenExpired}`)

    if (isTokenExpired) {
        return inject(AuthService).regenerateAuthToken2()
            .pipe(
                map(token => {
                    sessionStorage.setItem('id_token', token.id_token);
                    sessionStorage.setItem('refresh_token', token.refresh_token);
                    sessionStorage.setItem('access_token', token.access_token);

                    console.log(`id_token = ${token.id_token}`);
                    console.log(`refresh_token = ${token.refresh_token}`);
                    console.log(`access_token = ${token.access_token}`);

                    return true;
                }),
            )
    }

    return true;
}


