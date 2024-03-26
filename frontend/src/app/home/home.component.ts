import {Component, OnInit} from '@angular/core';
import {AdminService} from "../_services/admin.service";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  isLoggedIn: boolean = false;
  menuItems: any[] = [];
  Coffee: number = 0;
  Snacks: number = 0;
  Desserts: number = 0;

  constructor(private adminService: AdminService,
              private appComponent: AppComponent) { }

  ngOnInit(): void {
    this.adminService.verify().subscribe({
      next: () => this.isLoggedIn = true,
      error: () => this.isLoggedIn = false
    });

    this.adminService.getMenu().subscribe({
      next: (data: any) => {
        this.menuItems = data
        for (let i = 0; i < this.menuItems.length; i++) {
          if (this.menuItems[i].category === 'Coffee') this.Coffee++;
          else if (this.menuItems[i].category === 'Snacks') this.Snacks++;
          else if (this.menuItems[i].category === 'Desserts') this.Desserts++;
        }
      }
    });
  }

  logout() {
    this.adminService.logout().subscribe({
      next: () => {
        this.isLoggedIn = false;
        location.reload();
      },
      error: () => {
        this.isLoggedIn = false;
        location.reload();
      }
    });
  }
}
