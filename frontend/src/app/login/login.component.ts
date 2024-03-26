import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AdminService} from "../_services/admin.service";
import {CookieService} from "ngx-cookie-service";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit{
  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private adminService: AdminService,
              private appComponent: AppComponent) { }

  ngOnInit(): void {
    this.adminService.csrf().subscribe();
  }

  login() {
    const { username, password } = this.loginForm.value;

    if (username && password) {
      this.adminService.login(username, password).subscribe({
        next: () => {
          this.appComponent.redirect('/admin');
        }
      });
    }
  }
}
