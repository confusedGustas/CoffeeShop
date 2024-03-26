import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";

const BASE_URL = 'http://localhost:8080/'

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private http = inject(HttpClient);

  constructor(private cookieService: CookieService) { }

  login(username: string, password: string) {
    return this.http.post(BASE_URL + 'public/login', {username, password}, {
      headers: {'X-XSRF-TOKEN': this.cookieService.get('XSRF-TOKEN')},
      observe: 'response', withCredentials: true});
  }

  csrf() {
    return this.http.get(BASE_URL + 'public/csrf', {observe: 'response', withCredentials: true});
  }

  verify() {
    return this.http.get(BASE_URL + 'private/verify', {observe: 'response', withCredentials: true});
  }

  logout() {
    return this.http.post(BASE_URL + 'private/logout',{}, {
      headers: {'X-XSRF-TOKEN': this.cookieService.get('XSRF-TOKEN')},
      observe: 'response', withCredentials: true});
  }

  add(category: string, name: string, description: string, price: number) {
    return this.http.post(BASE_URL + 'private/edit/add-item', {category, name, description, price}, {
      headers: {'X-XSRF-TOKEN': this.cookieService.get('XSRF-TOKEN')},
      observe: 'response', withCredentials: true});
  }

  getMenu() {
    return this.http.get(BASE_URL + 'public/get-products');
  }

  delete(id: number) {
    return this.http.delete(`${BASE_URL}private/edit/delete-item?id=${id}`, {
      headers: {'X-XSRF-TOKEN': this.cookieService.get('XSRF-TOKEN')},
      observe: 'response', withCredentials: true});
  }

  edit(id: number, name: string, description: string, price: number) {
    return this.http.put(BASE_URL + 'private/edit/update-item', {id, name, price, description}, {
      headers: {'X-XSRF-TOKEN': this.cookieService.get('XSRF-TOKEN')},
      observe: 'response', withCredentials: true});
  }

  getContact() {
    return this.http.get(BASE_URL + 'public/get-contact');
  }

  editContact(address: string, phone: string, email: string, github: string) {
    return this.http.put(BASE_URL + 'private/edit/contact', {address, phone, email, github}, {
      headers: {'X-XSRF-TOKEN': this.cookieService.get('XSRF-TOKEN')},
      observe: 'response', withCredentials: true});
  }

  sendEmail(firstName: string, lastName: string, email: string, message: string) {
    return this.http.post(BASE_URL + 'public/send-email', {firstName, lastName, email, message}, {
      headers: {'X-XSRF-TOKEN': this.cookieService.get('XSRF-TOKEN')},
      observe: 'response', withCredentials: true});
  }

  getAbout() {
    return this.http.get(BASE_URL + 'public/get-about', {responseType: 'text'});
  }

  editAbout(message: string) {
    return this.http.put(BASE_URL + 'private/edit/about', {message}, {
      headers: {'X-XSRF-TOKEN': this.cookieService.get('XSRF-TOKEN')},
      observe: 'response', withCredentials: true});
  }

  changePassword(oldPassword: string, newPassword: string) {
    return this.http.put(BASE_URL + 'private/edit/password', {oldPassword, newPassword}, {
      headers: {'X-XSRF-TOKEN': this.cookieService.get('XSRF-TOKEN')},
      observe: 'response', withCredentials: true});
  }
}
