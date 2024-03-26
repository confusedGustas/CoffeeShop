import {Component, OnInit} from '@angular/core';
import {AdminService} from "../_services/admin.service";
import {AppComponent} from "../app.component";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './contact.component.html',
})
export class ContactComponent implements OnInit {
  isLoggedIn = false;
  showModal = false;
  address = '';
  phone = '';
  email = '';
  github = '';

  EditForm = new FormGroup({
    address: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    github: new FormControl('', [Validators.required])
  });
  EmailForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    message: new FormControl('', [Validators.required])
  });

  constructor(private adminService: AdminService,
              private appComponent: AppComponent) {}

  ngOnInit(): void {
    this.adminService.verify().subscribe({
      next: () => this.isLoggedIn = true
    })

    this.adminService.getContact().subscribe({
      next: (data: any) => {
        this.address = data.address;
        this.phone = data.phone;
        this.email = data.email;
        this.github = data.github;
      }
    });
  }

  logout() {
    this.adminService.logout().subscribe({
      next: () => {
        this.isLoggedIn = false;
        this.appComponent.redirect('/');
      },
      error: () => {
        this.isLoggedIn = false;
        this.appComponent.redirect('/');
      }
    });
  }

  editContact() {
    const {address, phone, email, github} = this.EditForm.value;
    if (address && phone && email && github) {
      this.adminService.editContact(address, phone, email, github).subscribe({
        next: () => window.location.reload(),
        error: () => window.location.reload()
      });
    }
  }

  sendEmail() {
    const {firstName, lastName, email, message} = this.EmailForm.value;
    if (firstName && lastName && email && message) {
      this.adminService.sendEmail(firstName, lastName, email, message).subscribe({
        next: () => window.location.reload(),
        error: () => {
          this.adminService.csrf().subscribe({
            next: () => this.adminService.sendEmail(firstName, lastName, email, message).subscribe({
              next: () => window.location.reload(),
              error: () => window.location.reload()
            })
          });
        }
      });
    }
  }

  toggleModal() {
    this.showModal = !this.showModal;
  }
}
