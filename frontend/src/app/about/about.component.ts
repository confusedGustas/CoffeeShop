import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AdminService} from "../_services/admin.service";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './about.component.html',
})
export class AboutComponent implements OnInit {
  isLoggedIn = false;
  showModal = false;
  about = '';

  AboutForm = new FormGroup({
    about: new FormControl('', [Validators.required])
  });

  constructor(private adminService: AdminService,
              private appComponent: AppComponent) {}

  ngOnInit(): void {
    this.adminService.verify().subscribe({
      next: () => this.isLoggedIn = true
    })

    this.adminService.getAbout().subscribe({
      next: (data: any) => this.about = data,
      error: () => {
        this.adminService.csrf().subscribe({
          next: () => {
            this.adminService.getAbout().subscribe({
              next: (data: any) => this.about = data
            });
          }
        })
      }
    });

    console.log(this.about);
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

  toggleModal() {
    this.showModal = !this.showModal;
  }

  editAbout() {
    const about = this.AboutForm.value.about;

    if (about) {
      this.adminService.editAbout(about).subscribe({
        next: () => {
          this.toggleModal();
          window.location.reload();
        }, error: () => {
          this.toggleModal();
          window.location.reload();
        }
      });
    }
  }
}
