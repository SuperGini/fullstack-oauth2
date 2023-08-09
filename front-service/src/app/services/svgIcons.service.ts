import {inject, Injectable} from "@angular/core";
import {DomSanitizer} from "@angular/platform-browser";
import {MatIconRegistry} from "@angular/material/icon";
import {SvgIcons} from "../utility/svg.icons";


/**
 * https://medium.com/ngconf/how-to-using-mat-icon-part-two-2dfb748c7bfc
 * How to add SVG
 * */

@Injectable({providedIn: 'root'})
export class SvgIconsService {

    private matIconRegistry: MatIconRegistry = inject(MatIconRegistry);
    private domSanitizer: DomSanitizer = inject(DomSanitizer);

    addIconsToRegistry(): void {
        Object.values(SvgIcons)
              .forEach(icon =>
                  this.matIconRegistry.addSvgIcon(icon,
                      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/svg/${icon}.svg`))
              )
    }

}
