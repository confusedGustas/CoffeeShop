import {Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {AdminComponent} from "./admin/admin.component";
import {ContactComponent} from "./contact/contact.component";
import {AboutComponent} from "./about/about.component";

export const routes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full', title: 'Home'},
  {path: 'login', component: LoginComponent, title: 'Admin Login'},
  {path: 'admin', component: AdminComponent, title: 'Admin'},
  {path: 'contact', component: ContactComponent, title: 'Contact Us'},
  {path: 'about', component: AboutComponent, title: 'About Us'}
];
